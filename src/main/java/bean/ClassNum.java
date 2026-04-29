/**河合太一*/
package bean;

import java.io.Serializable;

/**
 * クラス番号を表すBean
 */
public class ClassNum implements Serializable {

    /** クラス番号 */
    private String class_num;

    /** 所属学校 */
    private School school;

    /** コンストラクタ */
    public ClassNum() {
    }

    /** クラス番号を取得 */
    public String getClass_num() {
        return class_num;
    }

    /** クラス番号を設定 */
    public void setClassNum(String class_num) {
        this.class_num = class_num;
    }

    /** 学校情報を取得 */
    public School getSchool() {
        return school;
    }

    /** 学校情報を設定 */
    public void setSchool(School school) {
        this.school = school;
    }
}