/*河合太一*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

/** 科目データアクセスクラス */
public class SubjectDao extends Dao {

    /** 科目一覧取得 */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE school_cd=? ORDER BY cd";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(createSubject(rs, school));
                }
            }
        }
        return list;
    }

    /** 科目1件取得 */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        String sql = "SELECT * FROM subject WHERE cd=? AND school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cd);
            ps.setString(2, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subject = createSubject(rs, school);
                }
            }
        }
        return subject;
    }

    /** Subject生成共通処理 */
    private Subject createSubject(ResultSet rs, School school) throws Exception {
        Subject subject = new Subject();
        subject.setCd(rs.getString("cd"));
        subject.setName(rs.getString("name"));
        subject.setSchool(school);
        return subject;
    }

    /** 新規保存 */
    public boolean save(Subject subject) throws Exception {
        String sql = "INSERT INTO subject(cd, name, school_cd) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getName());
            ps.setString(3, subject.getSchool().getCd());

            return ps.executeUpdate() > 0;
        }
    }

    /** 更新 */
    public boolean update(Subject subject, String oldCd) throws Exception {
        String sql = "UPDATE subject SET cd=?, name=? WHERE cd=? AND school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getName());
            ps.setString(3, oldCd);
            ps.setString(4, subject.getSchool().getCd());

            return ps.executeUpdate() > 0;
        }
    }

    /** 削除 */
    public boolean delete(Subject subject) throws Exception {
        String sql = "DELETE FROM subject WHERE cd=? AND school_cd=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getSchool().getCd());

            return ps.executeUpdate() > 0;
        }
    }
}