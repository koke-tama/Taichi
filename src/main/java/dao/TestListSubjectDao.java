package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    /**
     * 指定された条件に合致する成績一覧を取得する
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        
        // 学生(student)と成績(test)を結合して取得
        // 成績がない場合も考慮して、要件に応じて INNER JOIN か LEFT JOIN を使い分けます
        String sql = """
            SELECT 
                s.ent_year, s.no AS student_no, s.name AS student_name, 
                s.class_num, t.no AS test_num, t.point
            FROM student s
            INNER JOIN test t ON s.no = t.student_no
            WHERE s.school_cd = ? 
              AND s.ent_year = ? 
              AND s.class_num = ? 
              AND t.subject_cd = ?
            ORDER BY s.no ASC, t.no ASC
        """;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, classNum);
            ps.setString(4, subject.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TestListSubject res = new TestListSubject();
                    res.setEntYear(rs.getInt("ent_year"));
                    res.setStudentNo(rs.getString("student_no"));
                    res.setStudentName(rs.getString("student_name"));
                    res.setClassNum(rs.getString("class_num"));
                    res.setNum(rs.getInt("test_num"));
                    res.setPoint(rs.getInt("point"));
                    
                    list.add(res);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return list;
    }
}