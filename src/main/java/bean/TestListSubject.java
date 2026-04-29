/**河合太一*/
package bean;

import java.io.Serializable;

/** 科目別成績表示用Bean */
public class TestListSubject implements Serializable {

    /** 入学年度 */ private int entYear;
    /** 学生番号 */ private String studentNo;
    /** 氏名 */ private String studentName;
    /** クラス */ private String classNum;
    /** 回数 */ private int num;
    /** 点数 */ private int point;

    /** コンストラクタ */
    public TestListSubject() {}

    /** 入学年度を取得 */ public int getEntYear() { return entYear; }
    /** 入学年度を設定 */ public void setEntYear(int entYear) { this.entYear = entYear; }

    /** 学生番号を取得 */ public String getStudentNo() { return studentNo; }
    /** 学生番号を設定 */ public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    /** 氏名を取得 */ public String getStudentName() { return studentName; }
    /** 氏名を設定 */ public void setStudentName(String studentName) { this.studentName = studentName; }

    /** クラスを取得 */ public String getClassNum() { return classNum; }
    /** クラスを設定 */ public void setClassNum(String classNum) { this.classNum = classNum; }

    /** 回数を取得 */ public int getNum() { return num; }
    /** 回数を設定 */ public void setNum(int num) { this.num = num; }

    /** 点数を取得 */ public int getPoint() { return point; }
    /** 点数を設定 */ public void setPoint(int point) { this.point = point; }
}