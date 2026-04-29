/*河合太一*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

/** クラス番号データアクセスクラス */
public class ClassNumDao extends Dao {

    /** 学校に紐づくクラス一覧を取得（表示用） */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num ASC";

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    list.add(rSet.getString("class_num"));
                }
            }
        }
        return list;
    }

    /** クラス番号と学校で1件取得 */
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
        }
        return classNum;
    }
}