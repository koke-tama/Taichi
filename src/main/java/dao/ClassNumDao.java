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
     * 【TestListActionから呼び出し】
     * ユーザーが所属している学校(School)のクラスデータを文字列のリストで取得します。
     * ドロップダウンリストなどの表示用に使用します。
     */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        // 学校コード(school_cd)に合致するクラス番号を取得するSQL
        String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num ASC";

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            
            // 引数で渡されたSchoolオブジェクトからコードをセット
            statement.setString(1, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    // クラス番号（文字列）をリストに追加
                    list.add(rSet.getString("class_num"));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * クラス番号と学校から、ClassNum Bean形式でデータを取得します。
     * 特定の一致データが必要な場合に使用します。
     */
    public ClassNum get(String classNumStr, School school) throws Exception {
        ClassNum classNum = null;
        String sql = "SELECT * FROM class_num WHERE class_num = ? AND school_cd = ?";

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            
            statement.setString(1, classNumStr);
            statement.setString(2, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    classNum = new ClassNum();
                    classNum.setClassNum(rSet.getString("class_num"));
                    classNum.setSchool(school);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return classNum;
    }
}