/*河合太一*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Test;

public class TestListStudentDao extends Dao {

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
}