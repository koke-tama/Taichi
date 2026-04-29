/*小野田匠希*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

/** 学校データアクセスクラス */
public class SchoolDao extends Dao {

    /** 学校コードで1件取得 */
    public School get(String cd) throws Exception {
        School school = null;
        String sql = "SELECT * FROM school WHERE cd = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cd);

            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    school = new School();
                    school.setCd(rSet.getString("cd"));
                    school.setName(rSet.getString("name"));
                }
            }
        }
        return school;
    }
}