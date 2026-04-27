package bean;

import java.io.Serializable;

import dao.SubjectDao;

/**
 * 科目情報を保持するBean
 * 自身の永続化や、Daoを介したデータ取得の窓口としても機能します。
 */
public class Subject implements Serializable {

    private String cd;     // 科目コード
    private String name;   // 科目名
    private School school; // 所属学校オブジェクト

    /**
     * デフォルトコンストラクタ
     */
    public Subject() {
    }

    // --- ゲッター・セッター ---
    public String getCd() { return cd; }
    public void setCd(String cd) { this.cd = cd; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }


    // --- DAO連携メソッド ---

    /**
     * 科目コードと学校に合致するデータをDBから取得して返します。
     * このメソッドを呼び出すことで、SubjectDaoを介して特定の科目情報を取得できます。
     * 
     * @param cd 科目コード
     * @param school 学校オブジェクト
     * @return 取得したSubjectオブジェクト（存在しない場合はnull）
     * @throws Exception
     */
    public Subject get(String cd, School school) throws Exception {
        // Daoをインスタンス化
        SubjectDao dao = new SubjectDao();
        // SubjectDaoのgetメソッドを呼び出し、DBから一致するデータを取得して返す
        return dao.get(cd, school);
    }

    /**
     * 自身のインスタンスにセットされている科目コードと学校情報を使い、
     * DBに保存（新規登録）します。
     * 
     * @return 保存成功ならtrue、既に存在する場合はfalse
     * @throws Exception
     */
    public boolean save() throws Exception {
        SubjectDao dao = new SubjectDao();
        
        // 1. 自身のcdとschoolで重複チェック（既存データの有無を確認）
        Subject existing = dao.get(this.cd, this.school);
        
        if (existing == null) {
            // 2. 存在しない場合のみ、Daoのsaveメソッドを呼び出して新規保存
            return dao.save(this);
        } else {
            // すでに存在する場合は保存不可としてfalseを返す
            return false;
        }
    }
}