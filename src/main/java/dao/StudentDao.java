/*高野翔*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

/** 学生データアクセスクラス */
public class StudentDao extends Dao {

    /** 基本検索SQL（未削除のみ） */
    private String baseSql = "SELECT * FROM student WHERE school_cd=? AND is_deleted=false";

    /** 学生を1件取得（削除含む） */
    public Student get(String no) throws Exception {
        Student student = null;
        String sql = "SELECT * FROM student WHERE no=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, no);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    student = createStudent(rs);
                }
            }
        }
        return student;
    }

    /** ResultSet → Student変換 */
    private Student createStudent(ResultSet rs) throws Exception {
        Student student = new Student();
        student.setNo(rs.getString("no"));
        student.setName(rs.getString("name"));
        student.setEntYear(rs.getInt("ent_year"));
        student.setClassNum(rs.getString("class_num"));
        student.setAttend(rs.getBoolean("is_attend"));

        School school = new School();
        school.setCd(rs.getString("school_cd"));
        student.setSchool(school);

        return student;
    }

    /** ResultSet → List変換 */
    private List<Student> toList(ResultSet rs) throws Exception {
        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            list.add(createStudent(rs));
        }
        return list;
    }

    /** 保存（新規 or 更新） */
    public boolean save(Student student) throws Exception {
        Student existing = get(student.getNo());

        String sql;
        if (existing == null) {
            sql = "INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd, is_deleted) VALUES (?, ?, ?, ?, ?, ?, false)";
        } else {
            sql = "UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=? WHERE no=?";
        }

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            if (existing == null) {
                st.setString(1, student.getNo());
                st.setString(2, student.getName());
                st.setInt(3, student.getEntYear());
                st.setString(4, student.getClassNum());
                st.setBoolean(5, student.isAttend());
                st.setString(6, student.getSchool().getCd());
            } else {
                st.setString(1, student.getName());
                st.setInt(2, student.getEntYear());
                st.setString(3, student.getClassNum());
                st.setBoolean(4, student.isAttend());
                st.setString(5, student.getNo());
            }

            return st.executeUpdate() > 0;
        }
    }

    /** 論理削除 */
    public boolean delete(Student student) throws Exception {
        String sql = "UPDATE student SET is_deleted=true WHERE no=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, student.getNo());
            return st.executeUpdate() > 0;
        }
    }

    /** 復元 */
    public boolean restore(String no) throws Exception {
        String sql = "UPDATE student SET is_deleted=false WHERE no=?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, no);
            return st.executeUpdate() > 0;
        }
    }

    /** 条件検索（年度＋クラス） */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        String sql = baseSql + " AND ent_year=? AND class_num=?"
                + (isAttend ? " AND is_attend=true" : "") + " ORDER BY no";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);

            return toList(st.executeQuery());
        }
    }

    /** 条件検索（年度） */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        String sql = baseSql + " AND ent_year=?"
                + (isAttend ? " AND is_attend=true" : "") + " ORDER BY no";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            st.setInt(2, entYear);

            return toList(st.executeQuery());
        }
    }

    /** 条件検索（全体） */
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        String sql = baseSql
                + (isAttend ? " AND is_attend=true" : "") + " ORDER BY no";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            return toList(st.executeQuery());
        }
    }

    /** 削除済み一覧 */
    public List<Student> filterDeleted(School school) throws Exception {
        String sql = "SELECT * FROM student WHERE school_cd=? AND is_deleted=true ORDER BY no";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            return toList(st.executeQuery());
        }
    }

    /** クラス一覧取得 */
    public List<String> getClassNumList(String schoolCd) throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT class_num FROM student WHERE school_cd=? ORDER BY class_num";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, schoolCd);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("class_num"));
                }
            }
        }
        return list;
    }

    /** 入学年度リスト */
    public List<Integer> getEntYearList() {
        List<Integer> list = new ArrayList<>();
        int year = java.time.Year.now().getValue();

        for (int i = year - 10; i <= year; i++) {
            list.add(i);
        }
        return list;
    }

    /** 存在チェック */
    public boolean exists(String no, String schoolCd) throws Exception {
        String sql = "SELECT COUNT(*) FROM student WHERE no=? AND school_cd=? AND is_deleted=false";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, no);
            st.setString(2, schoolCd);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}