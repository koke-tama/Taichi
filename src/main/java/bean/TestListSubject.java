package bean;

import java.io.Serializable;

/**
 * 科目ごとの成績参照結果を保持するBean
 */
public class TestListSubject implements Serializable {

    private int entYear;      // 入学年度
    private String studentNo; // 学籍番号
    private String studentName;// 氏名
    private String classNum;  // クラス
    private int num;          // 回数
    private int point;        // 点数

    /**
     * デフォルトコンストラクタ
     */
    public TestListSubject() {
    }

    // --- ゲッター・セッター ---
    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }
}