/**河合太一*/
package bean;

import java.io.Serializable;

/**学生別成績表示用Bean*/
public class TestListStudent implements Serializable {

    /** 科目名 */
    private String subjectName;

    /** 回数 */
    private int no;

    /** 点数 */
    private int point;

    /** コンストラクタ */
    public TestListStudent() {
    }

    /** 科目名を取得 */
    public String getSubjectName() {
        return subjectName;
    }

    /** 科目名を設定 */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /** 回数を取得 */
    public int getNo() {
        return no;
    }

    /** 回数を設定 */
    public void setNo(int no) {
        this.no = no;
    }

    /** 点数を取得 */
    public int getPoint() {
        return point;
    }

    /** 点数を設定 */
    public void setPoint(int point) {
        this.point = point;
    }
}