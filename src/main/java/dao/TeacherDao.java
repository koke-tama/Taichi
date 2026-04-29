/*長家優紀*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;
 
public class TeacherDao extends Dao {
 
    /**
     * 教員IDをもとに教員情報を1件取得する
     * @param id 教員ID
     * @return Teacherオブジェクト（存在しない場合はnull）
     * @throws Exception
     */
    public Teacher get(String id) throws Exception {
 
        Teacher teacher = null;
        Connection con = getConnection();
 
        String sql = "SELECT * FROM teacher WHERE id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, id);
 
        ResultSet rs = st.executeQuery();
 
        if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setPassword(rs.getString("password"));
            teacher.setName(rs.getString("name"));
 
            // 学校情報を設定
            School school = new School();
            school.setCd(rs.getString("school_cd"));
            teacher.setSchool(school);
        }
 
        st.close();
        con.close();
 
        return teacher;
    }
}