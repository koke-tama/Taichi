package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * 指定された学校の科目一覧を取得する (既存のメソッド)
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, school.getCd());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                    subject.setSchool(school);
                    list.add(subject);
                }
            }
        }
        return list;
    }

    /**
     * 【追加】科目コードと学校から特定の科目を1件取得する
     */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        String sql = "SELECT * FROM subject WHERE cd = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cd);
            ps.setString(2, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                    subject.setSchool(school);
                }
            }
        }
        return subject;
    }

    /**
     * 【追加】科目をDBに保存（新規登録）する
     */
    public boolean save(Subject subject) throws Exception {
        String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
        int count = 0;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getName());
            ps.setString(3, subject.getSchool().getCd());

            count = ps.executeUpdate();
        }
        // 1件以上更新（登録）されたらtrueを返す
        return count > 0;
    }
}