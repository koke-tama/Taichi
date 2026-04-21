package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
 
public class StudentDao extends Dao {
 
    // 通常の検索は「削除されていない(false)」データのみを対象にする

    private String baseSql = "SELECT * FROM student WHERE school_cd=? AND is_deleted=false ";
 
    /**

     * 学生を1件取得（削除フラグに関わらず取得）

     */

    public Student get(String no) throws Exception {

        Student student = null;

        Connection connection = getConnection();

        PreparedStatement statement = null;

        ResultSet rSet = null;
 
        try {

            statement = connection.prepareStatement("SELECT * FROM student WHERE no=?");

            statement.setString(1, no);

            rSet = statement.executeQuery();
 
            if (rSet.next()) {

                student = new Student();

                student.setNo(rSet.getString("no"));

                student.setName(rSet.getString("name"));

                student.setEntYear(rSet.getInt("ent_year"));

                student.setClassNum(rSet.getString("class_num"));

                student.setAttend(rSet.getBoolean("is_attend"));

                School school = new School();

                school.setCd(rSet.getString("school_cd"));

                student.setSchool(school);

            }

        } finally {

            if (statement != null) statement.close();

            if (connection != null) connection.close();

        }

        return student;

    }
 
    /**

     * リザルトセットをリストに変換する共通メソッド

     */

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        try {

            while (rSet.next()) {

                Student student = new Student();

                student.setNo(rSet.getString("no"));

                student.setName(rSet.getString("name"));

                student.setEntYear(rSet.getInt("ent_year"));

                student.setClassNum(rSet.getString("class_num"));

                student.setAttend(rSet.getBoolean("is_attend"));

                student.setSchool(school);

                list.add(student);

            }

        } catch (SQLException | NullPointerException e) {

            e.printStackTrace();

        }

        return list;

    }
 
    // ===== 保存・削除・復活 =====
 
    /**

     * 新規保存または更新

     */

    public boolean save(Student student) throws Exception {

        Connection connection = getConnection();

        PreparedStatement statement = null;

        try {

            // すでに存在するか確認

            Student existing = get(student.getNo());

            String sql;

            if (existing == null) {

                sql = "INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd, is_deleted) VALUES (?, ?, ?, ?, ?, ?, false)";

                statement = connection.prepareStatement(sql);

                statement.setString(1, student.getNo());

                statement.setString(2, student.getName());

                statement.setInt(3, student.getEntYear());

                statement.setString(4, student.getClassNum());

                statement.setBoolean(5, student.isAttend());

                statement.setString(6, student.getSchool().getCd());

            } else {

                sql = "UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=? WHERE no=?";

                statement = connection.prepareStatement(sql);

                statement.setString(1, student.getName());

                statement.setInt(2, student.getEntYear());

                statement.setString(3, student.getClassNum());

                statement.setBoolean(4, student.isAttend());

                statement.setString(5, student.getNo());

            }

            return statement.executeUpdate() > 0;

        } finally {

            if (statement != null) statement.close();

            if (connection != null) connection.close();

        }

    }
 
    /**

     * 論理削除

     */

    public boolean delete(Student student) throws Exception {

        Connection connection = getConnection();

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("UPDATE student SET is_deleted=true WHERE no=?");

            statement.setString(1, student.getNo());

            return statement.executeUpdate() > 0;

        } finally {

            if (statement != null) statement.close();

            if (connection != null) connection.close();

        }

    }
 
    /**

     * 復活

     */

    public boolean restore(String no) throws Exception {

        Connection connection = getConnection();

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("UPDATE student SET is_deleted=false WHERE no=?");

            statement.setString(1, no);

            return statement.executeUpdate() > 0;

        } finally {

            if (statement != null) statement.close();

            if (connection != null) connection.close();

        }

    }
 
    // ===== 検索・フィルタリング =====
 
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        String sql = baseSql + " AND ent_year=? AND class_num=? " + (isAttend ? " AND is_attend=true" : "") + " ORDER BY no ASC";

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());

            st.setInt(2, entYear);

            st.setString(3, classNum);

            return postFilter(st.executeQuery(), school);

        }

    }
 
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        String sql = baseSql + " AND ent_year=? " + (isAttend ? " AND is_attend=true" : "") + " ORDER BY no ASC";

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());

            st.setInt(2, entYear);

            return postFilter(st.executeQuery(), school);

        }

    }
 
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        String sql = baseSql + (isAttend ? " AND is_attend=true" : "") + " ORDER BY no ASC";

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());

            return postFilter(st.executeQuery(), school);

        }

    }
 
    /**

     * ゴミ箱一覧取得

     */

    public List<Student> filterDeleted(School school) throws Exception {

        String sql = "SELECT * FROM student WHERE school_cd=? AND is_deleted=true ORDER BY no ASC";

        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());

            return postFilter(st.executeQuery(), school);

        }

    }
 
    // ===== 補助機能 =====
 
    public List<String> getClassNumList(String schoolCd) throws Exception {

        List<String> list = new ArrayList<>();

        String sql = "SELECT DISTINCT class_num FROM student WHERE school_cd=? ORDER BY class_num ASC";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, schoolCd);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) list.add(rs.getString("class_num"));

            }

        }

        return list;

    }
 // ===== 入学年度リスト（追加） =====
    public List<Integer> getEntYearList() {

        List<Integer> list = new ArrayList<>();

        int year = java.time.Year.now().getValue();

        // 過去10年分（現在年含む）
        for (int i = year - 10; i <= year; i++) {
            list.add(i);
        }

        return list;
    }

}
 