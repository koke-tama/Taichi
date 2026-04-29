/**小野田匠希*/
package bean;

import java.io.Serializable;

/**教員を表すBean*/
public class Teacher extends User implements Serializable {

    /** 教員ID */
    private String id;

    /** パスワード */
    private String password;

    /** 教員名 */
    private String name;

    /** 所属学校 */
    private School school;

    /** 教員IDを取得 */
    public String getId() { return id; }

    /** 教員IDを設定 */
    public void setId(String id) { this.id = id; }

    /** パスワードを取得 */
    public String getPassword() { return password; }

    /** パスワードを設定 */
    public void setPassword(String password) { this.password = password; }

    /** 教員名を取得 */
    public String getName() { return name; }

    /** 教員名を設定 */
    public void setName(String name) { this.name = name; }

    /** 学校情報を取得 */
    public School getSchool() { return school; }

    /** 学校情報を設定 */
    public void setSchool(School school) { this.school = school; }
}