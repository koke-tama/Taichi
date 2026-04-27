package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

/**
 * 科目テーブル(subject)を操作するDAO
 */
public class SubjectDao extends Dao {

    /**
     * 【TestListActionで使用】
     * ログインユーザーの所属校(School)に基づき、その学校の全科目データを取得します。
     * 
     * @param school 学校Bean
     * @return 科目Beanのリスト
     * @throws Exception
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        // 学校コードで絞り込み、科目コード順に取得
        String sql = "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd ASC";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // ユーザーデータ（学校オブジェクト）から取得した学校コードをセット
            ps.setString(1, school.getCd());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    // ResultSetからデータを取得しSubject Beanへセット
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                    subject.setSchool(school);

                    list.add(subject);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * 【科目コードに合致するデータ取得】
     * 指定された科目コードと学校に合致する科目情報を1件取得し、Subject Beanとして返却します。
     * 
     * @param cd 科目コード
     * @param school 学校Bean
     * @return 科目Bean（該当なしの場合はnull）
     * @throws Exception
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
                    // データベースから取得した値をBeanに格納
                    subject.setCd(rs.getString("cd"));
                    subject.setName(rs.getString("name"));
                    subject.setSchool(school);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return subject;
    }
 // SubjectDao.java 内
    public boolean save(Subject subject) throws Exception {
        // ...保存処理...
        return true; // 成功時にbooleanを返す
    }
    /**
     * 科目データを更新します。
     * 科目コード(cd)が変更される可能性を考慮し、検索条件として変更前のコードを使用します。
     * 
     * @param subject 更新するデータが入ったSubjectオブジェクト
     * @param oldCd 変更前の科目コード
     * @return 更新成功ならtrue
     * @throws Exception
     */
    public boolean update(Subject subject, String oldCd) throws Exception {
        // 学校コードと「変更前の科目コード」を条件に、新しいコードと名前をセットする
        String sql = "UPDATE subject SET cd = ?, name = ? WHERE cd = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getCd());    // 新しい科目コード
            ps.setString(2, subject.getName());  // 新しい科目名
            ps.setString(3, oldCd);              // 検索条件（変更前のコード）
            ps.setString(4, subject.getSchool().getCd());

            int count = ps.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            throw e;
        }
    }
    

    /**
     * 科目データを削除します。
     * 
     * @param subject 削除したい科目の情報（cdとschoolが必要）
     * @return 削除成功ならtrue
     * @throws Exception
     */
    public boolean delete(Subject subject) throws Exception {
        String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getSchool().getCd());

            int count = ps.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            throw e;
            
        }
    }
    
}