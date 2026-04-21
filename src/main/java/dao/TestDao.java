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
 
    public List<Test> filter(School school, int entYear, String classNum, String subjectCd, int no) throws Exception {
 
        List<Test> list = new ArrayList<>();
 
        String sql = """

            SELECT 

                s.no AS student_no,

                s.name AS student_name,

                s.ent_year,

                s.class_num,

                t.subject_cd,

                t.no AS test_no,

                t.point

            FROM student s

            LEFT JOIN test t

              ON s.no = t.student_no

             AND t.subject_cd = ?

             AND t.no = ?

             AND t.school_cd = ?

            WHERE s.school_cd = ?

              AND s.ent_year = ?

              AND s.class_num = ?

            ORDER BY s.no

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
 
                    Subject subject = new Subject();

                    subject.setCd(subjectCd);

                    test.setSubject(subject);
 
                    test.setNo(no);
 
                    int point = rs.getInt("point");

                    if (rs.wasNull()) point = 0;
 
                    test.setPoint(point);
 
                    list.add(test);

                }

            }

        }
 
        return list;

    }
 
    public void save(Test test, School school) throws Exception {
 
        String sql = """

            MERGE INTO test (student_no, subject_cd, no, school_cd, point)

            KEY(student_no, subject_cd, no, school_cd)

            VALUES (?, ?, ?, ?, ?)

        """;
 
        try (Connection con = getConnection();

             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, test.getStudent().getNo());

            ps.setString(2, test.getSubject().getCd());

            ps.setInt(3, test.getNo());

            ps.setString(4, school.getCd());

            ps.setInt(5, test.getPoint());
 
            ps.executeUpdate();

        }

    }

}
 