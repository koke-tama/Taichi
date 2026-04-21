package bean;

import java.io.Serializable;

public class User implements Serializable {

    // 追加：ユーザーが所属する学校の情報
    private School school;
    
    /**
     * 認証済みフラグ:boolean true:認証済み
     */
    private boolean isAuthenticated;

    // --- 追加：getSchoolメソッド ---
    // これがないと SubjectListAction の user.getSchool() でエラーになります
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    /**
     * ゲッター、セッター
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}