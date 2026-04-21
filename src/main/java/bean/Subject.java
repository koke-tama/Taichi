package bean;

import java.io.Serializable;

import dao.SubjectDao;

/**
 * 科目情報を保持し、自身の永続化（保存）や取得を行うクラス
 */
public class Subject implements Serializable {

    private String cd;     // 科目コード
    private String name;   // 科目名
    private School school; // 所属学校オブジェクト

    // --- ゲッター・セッター ---
    public String getCd() { return cd; }
    public void setCd(String cd) { this.cd = cd; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }


    // --- ビジネスロジック / DAO連携 ---

    /**
     * 1. 科目コードと学校コードに合致する詳細データを取得して返す
     * SubjectCreateExecuteAction などから呼び出される想定
     */
    public Subject get(String cd, School school) throws Exception {
        SubjectDao dao = new SubjectDao();
        // 指定されたcdとschoolに一致するデータをDBから取得
        // ※SubjectDaoに get(String, School) メソッドがある前提
        return dao.get(cd, school); 
    }

    /**
     * 2. 入力された現在の自身のデータをDBに保存し、結果を返す
     * SubjectCreateExecuteAction で値がセットされた後に呼び出される想定
     */
    public boolean save() throws Exception {
        SubjectDao dao = new SubjectDao();
        
        // 既存チェック（すでに存在するか確認）
        Subject existing = dao.get(this.cd, this.school);
        
        if (existing == null) {
            // データがなければ新規登録(save)
            // ※SubjectDaoに save(Subject) メソッドがある前提
            return dao.save(this);
        } else {
            // すでにデータがあれば更新(update)など、必要に応じて処理
            return false; // または dao.update(this);
        }
    }
}