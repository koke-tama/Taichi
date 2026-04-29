/** 河合太一*/
package bean;

import java.io.Serializable;

import dao.SubjectDao;

/**科目を表すBean*/
public class Subject implements Serializable {

    /** 科目コード */
    private String cd;

    /** 科目名 */
    private String name;

    /** 所属学校 */
    private School school;

    /** コンストラクタ */
    public Subject() {
    }

    // --- getter / setter ---

    /** 科目コードを取得 */
    public String getCd() { return cd; }

    /** 科目コードを設定 */
    public void setCd(String cd) { this.cd = cd; }

    /** 科目名を取得 */
    public String getName() { return name; }

    /** 科目名を設定 */
    public void setName(String name) { this.name = name; }

    /** 学校情報を取得 */
    public School getSchool() { return school; }

    /** 学校情報を設定 */
    public void setSchool(School school) { this.school = school; }


    // --- DAO操作 ---

    /** 指定した科目コードと学校に一致する科目を取得 */
    public Subject get(String cd, School school) throws Exception {
        SubjectDao dao = new SubjectDao();
        return dao.get(cd, school);
    }

    /** 科目を新規登録（既に存在する場合は登録しない） */
    public boolean save() throws Exception {
        SubjectDao dao = new SubjectDao();

        Subject existing = dao.get(this.cd, this.school);

        if (existing == null) {
            return dao.save(this);
        } else {
            return false;
        }
    }
}