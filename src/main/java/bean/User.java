/**小野田匠希*/
package bean;

import java.io.Serializable;

/** ユーザー情報を表すBean */
public class User implements Serializable {

    /** 所属学校 */
    private School school;

    /** 認証状態（true: 認証済み） */
    private boolean isAuthenticated;

    /** 学校情報を取得 */ 
    public School getSchool() { return school; }

    /** 学校情報を設定 */ 
    public void setSchool(School school) { this.school = school; }

    /** 認証状態を取得 */ 
    public boolean isAuthenticated() { return isAuthenticated; }

    /** 認証状態を設定 */ 
    public void setAuthenticated(boolean isAuthenticated) { this.isAuthenticated = isAuthenticated; }
}