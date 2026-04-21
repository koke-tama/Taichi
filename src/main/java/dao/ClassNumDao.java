package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    /**

     * 指定したクラス番号と学校に紐づく情報を取得します

     */

    public ClassNum get(String class_num, School school) throws Exception {

        ClassNum classNum = null;

        String sql = "select * from class_num where class_num = ? and school_cd = ?";

        // try-with-resources文を使用（自動でcloseされるためfinallyが不要になります）

        try (Connection connection = getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, class_num);

            statement.setString(2, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {

                SchoolDao sDao = new SchoolDao();

                if (rSet.next()) {

                    classNum = new ClassNum();

                    classNum.setClass_num(rSet.getString("class_num"));

                    classNum.setSchool(sDao.get(rSet.getString("school_cd")));

                }

            }

        } catch (Exception e) {

            throw e;

        }

        return classNum;

    }

    /**

     * 学校に紐づくすべてのクラス番号を取得します

     */

    public List<String> filter(School school) throws Exception {

        List<String> list = new ArrayList<>();

        String sql = "select class_num from class_num where school_cd=? order by class_num";

        try (Connection connection = getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {

                while (rSet.next()) {

                    list.add(rSet.getString("class_num"));

                }

            }

        } catch (Exception e) {

            throw e;

        }

        return list;

    }

    /**

     * クラス番号を新規登録（または上書き）します

     */

    public boolean save(ClassNum classNum) throws Exception {

        int count = 0;

        // 既存チェック

        ClassNum old = get(classNum.getClass_num(), classNum.getSchool());

        try (Connection connection = getConnection()) {

            PreparedStatement statement = null;

            if (old == null) {

                // 新規登録

                statement = connection.prepareStatement(

                    "insert into class_num (class_num, school_cd) values (?, ?)");

                statement.setString(1, classNum.getClass_num());

                statement.setString(2, classNum.getSchool().getCd());

            } else {

                // 既存更新（実質同じ値での上書き）

                statement = connection.prepareStatement(

                    "update class_num set class_num=? where class_num=? and school_cd=?");

                statement.setString(1, classNum.getClass_num());

                statement.setString(2, classNum.getClass_num());

                statement.setString(3, classNum.getSchool().getCd());

            }

            count = statement.executeUpdate();

            statement.close();

        } catch (Exception e) {

            throw e;

        }

        return count > 0;

    }

    /**

     * クラス番号を変更します

     */

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

        int count = 0;

        String sql = "update class_num set class_num=? where class_num=? and school_cd=?";

        try (Connection connection = getConnection();

             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newClassNum);        // 新しい番号

            statement.setString(2, classNum.getClass_num()); // 以前の番号

            statement.setString(3, classNum.getSchool().getCd());

            count = statement.executeUpdate();

        } catch (Exception e) {

            throw e;

        }

        return count > 0;

    }

}

 