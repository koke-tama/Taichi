/*高野翔*/
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
     * 学生別の成績一覧を取得する
     * @param student 学生
     * @return 成績リスト
     * @throws Exception
     */
    public List<Test> filter(Student student) throws Exception {
        List<Test> list = new ArrayList<>();

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

            ps.setString(1, student.getNo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();

                    test.setStudent(student);

                    Subject subject = new Subject();
                    subject.setCd(rs.getString("subject_cd"));
                    subject.setName(rs.getString("subject_name"));
                    test.setSubject(subject);

                    test.setNo(rs.getInt("test_no"));
                    test.setPoint(rs.getInt("point"));

                    list.add(test);
                }
            }
        }
        return list;
    }

    /**
     * 科目・クラス別の成績一覧を取得する
     * @param entYear 入学年度
     * @param classNum クラス番号
     * @param subject 科目
     * @param school 学校
     * @return 成績リスト
     * @throws Exception
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
        }
        return list;
    }

    /**
     * 成績登録画面用のデータを取得する（未登録も含む）
     * @param school 学校
     * @param entYear 入学年度
     * @param classNum クラス
     * @param subjectCd 科目コード
     * @param no 回数
     * @return 成績リスト
     * @throws Exception
     */
    public List<Test> filter(School school, int entYear, String classNum, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();

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

            ps.setString(1, subjectCd);
            ps.setInt(2, no);
            ps.setString(3, school.getCd());
            ps.setString(4, school.getCd());
            ps.setInt(5, entYear);
            ps.setString(6, classNum);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();

                    Student student = new Student();
                    student.setNo(rs.getString("student_no"));
                    student.setName(rs.getString("student_name"));
                    student.setEntYear(rs.getInt("ent_year"));
                    student.setClassNum(rs.getString("class_num"));

                    test.setStudent(student);
                    test.setNo(no);
                    test.setPoint(rs.getInt("point"));

                    list.add(test);
                }
            }
        }
        return list;
    }

    /**
     * 成績データを登録または更新する
     * @param test 成績
     * @param school 学校
     * @throws Exception
     */
    public void save(Test test, School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement checkSt = con.prepareStatement(
            "SELECT COUNT(*) FROM TEST WHERE student_no=? AND subject_cd=? AND no=? AND school_cd=?"
        );

        checkSt.setString(1, test.getStudent().getNo());
        checkSt.setString(2, test.getSubject().getCd());
        checkSt.setInt(3, test.getNo());
        checkSt.setString(4, school.getCd());

        ResultSet rs = checkSt.executeQuery();
        rs.next();

        if (rs.getInt(1) > 0) {
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