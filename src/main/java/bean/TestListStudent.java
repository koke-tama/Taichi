package bean;

import java.io.Serializable;

/**
 * 学生別成績参照用の表示用Bean
 */
public class TestListStudent implements Serializable {

    private String subjectName; // 科目名
    private int no;              // 回数
    private int point;           // 点数

    /**
     * デフォルトコンストラクタ
     */
    public TestListStudent() {
    }

    /**
     * ゲッター・セッター
     */
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}