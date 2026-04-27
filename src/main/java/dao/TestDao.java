package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    /**
     * 【TestListStudentExecuteActionで使用】
     * 特定の学生にマッチする成績データをすべて取得します。
     * 科目名を表示するために subject テーブルと結合します。
     * 
     * @param student 学生Bean
     * @return 成績Beanのリスト
     * @throws Exception
     */
    public List<Test> filter(Student student) throws Exception {
        List<Test> list = new ArrayList<>();

        // 学生番号にマッチする成績と、その科目情報を取得するSQL
        String sql = """
            SELECT 
                t.subject_cd, 
                sub.name AS subject_name, 
                t.no AS test_no, 
                t.point
            FROM test t
            INNER JOIN subject sub 
               ON t.subject_cd = sub.cd 
              AND t.school_cd = sub.school_cd
            WHERE t.student_no = ?
            ORDER BY t.subject_cd ASC, t.no ASC
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 引数の学生オブジェクトから学生番号をセット
            ps.setString(1, student.getNo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    
                    // 学生情報をセット
                    test.setStudent(student);

                    // 科目情報をセット（科目名を取得してセット）
                    Subject subject = new Subject();
                    subject.setCd(rs.getString("subject_cd"));
                    subject.setName(rs.getString("subject_name"));
                    test.setSubject(subject);

                    // テスト情報（回数・点数）をセット
                    test.setNo(rs.getInt("test_no"));
                    test.setPoint(rs.getInt("point"));

                    list.add(test);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return list;
    }

    /**
     * 【成績参照：科目・クラス毎】（既存）
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql = """
            SELECT 
                t.student_no, 
                s.name AS student_name, 
                s.ent_year, 
                s.class_num, 
                t.no AS test_no, 
                t.point
            FROM test t
            INNER JOIN student s ON t.student_no = s.no
            WHERE s.school_cd = ? 
              AND s.ent_year = ? 
              AND s.class_num = ? 
              AND t.subject_cd = ?
            ORDER BY t.student_no ASC, t.no ASC
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setString(4, subject.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    Student student = new Student();
                    student.setNo(rs.getString("student_no"));
                    student.setName(rs.getString("student_name"));
                    student.setEntYear(rs.getInt("ent_year"));
                    student.setClassNum(rs.getString("class_num"));
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setNo(rs.getInt("test_no"));
                    test.setPoint(rs.getInt("point"));
                    list.add(test);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    /**
     * 【成績登録用】
     * 学校、入学年度、クラス、科目コード、回数に合致する成績リストを取得します。
     * 引数の型と順番をアクション側の呼び出し (School, int, String, String, int) に合わせています。
     */
    public List<Test> filter(School school, int entYear, String classNum, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();

        // 学生を主軸に、その学生の特定の科目の成績を外部結合(LEFT JOIN)で取得
        String sql = """
            SELECT 
                s.no AS student_no, 
                s.name AS student_name, 
                s.ent_year, 
                s.class_num,
                t.point
            FROM student s
            LEFT JOIN test t ON s.no = t.student_no 
                AND t.subject_cd = ? 
                AND t.no = ? 
                AND t.school_cd = ?
            WHERE s.school_cd = ? 
              AND s.ent_year = ? 
              AND s.class_num = ?
            ORDER BY s.no ASC
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // SQLの「?」に値をセット（引数の順番に注意）
            ps.setString(1, subjectCd);    // t.subject_cd
            ps.setInt(2, no);              // t.no
            ps.setString(3, school.getCd()); // t.school_cd
            ps.setString(4, school.getCd()); // s.school_cd
            ps.setInt(5, entYear);         // s.ent_year
            ps.setString(6, classNum);      // s.class_num

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    
                    // 学生情報をセット
                    Student student = new Student();
                    student.setNo(rs.getString("student_no"));
                    student.setName(rs.getString("student_name"));
                    student.setEntYear(rs.getInt("ent_year"));
                    student.setClassNum(rs.getString("class_num"));
                    test.setStudent(student);

                    // テスト情報をセット
                    test.setNo(no);
                    // ポイントを取得（レコードがない場合は rs.getInt は 0 を返しますが、
                    // 必要に応じて rs.wasNull() で未入力判定も可能です）
                    test.setPoint(rs.getInt("point"));

                    list.add(test);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return list;
    }
    /**
     * 成績データの保存（MERGE）（既存）
     */
    public void save(Test test, School school) throws Exception {

        Connection con = getConnection();

        // 既存データ確認
        PreparedStatement checkSt = con.prepareStatement(
            "SELECT COUNT(*) FROM TEST WHERE student_no=? AND subject_cd=? AND no=? AND school_cd=?"
        );

        checkSt.setString(1, test.getStudent().getNo());
        checkSt.setString(2, test.getSubject().getCd());
        checkSt.setInt(3, test.getNo());
        checkSt.setString(4, school.getCd());

        ResultSet rs = checkSt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        if (count > 0) {
            // --- UPDATE ---
            PreparedStatement st = con.prepareStatement(
                "UPDATE TEST SET point=?, class_num=? WHERE student_no=? AND subject_cd=? AND no=? AND school_cd=?"
            );

            st.setInt(1, test.getPoint());
            st.setString(2, test.getClassNum());
            st.setString(3, test.getStudent().getNo());
            st.setString(4, test.getSubject().getCd());
            st.setInt(5, test.getNo());
            st.setString(6, school.getCd());

            st.executeUpdate();

            st.close();

        } else {
            // --- INSERT ---
            PreparedStatement st = con.prepareStatement(
                "INSERT INTO TEST (student_no, subject_cd, no, point, class_num, school_cd) VALUES (?, ?, ?, ?, ?, ?)"
            );

            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getSubject().getCd());
            st.setInt(3, test.getNo());
            st.setInt(4, test.getPoint());
            st.setString(5, test.getClassNum());
            st.setString(6, school.getCd());

            st.executeUpdate();

            st.close();
        }

        rs.close();
        checkSt.close();
        con.close();
    }
}