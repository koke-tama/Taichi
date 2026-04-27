package bean;

import java.io.Serializable;
import java.util.List;

import dao.TestDao;

/**
 * 成績情報を保持するBean
 */
public class Test implements Serializable {

    private Student student; // 学生情報
    private Subject subject; // 科目情報
    private int no;          // テストの回数
    private int point;       // 得点
    private String classNum; // ← 追加

    /**
     * デフォルトコンストラクタ
     */
    public Test() {
    }

    // --- ゲッター・セッター ---
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public String getClassNum() { 
        return classNum; 
    }

    public void setClassNum(String classNum) { 
        this.classNum = classNum; 
    }

    // --- ビジネスロジック / DAO連携 ---

    /**
     * 【学生別成績参照用】
     * 指定された学生（学生番号）にマッチする成績データの一覧を取得します。
     * TestListStudentExecuteAction からの呼び出しを想定しています。
     * * @param student 学生オブジェクト
     * @return 特定の学生に紐づく Test オブジェクトのリスト
     * @throws Exception
     */
    public List<Test> filter(Student student) throws Exception {
        TestDao dao = new TestDao();
        // TestDaoの学生用filterメソッドを呼び出し、DBから一致するデータを取得して返します
        return dao.filter(student);
    }

    /**
     * 【科目別成績参照用】
     * 指定された条件（入学年度、クラス、科目）に合致する成績データの一覧を取得します。
     * TestListSubjectExecuteAction からの呼び出しを想定しています。
     * * @param entYear 入学年度
     * @param classNum クラス番号
     * @param subject 科目オブジェクト
     * @param school 学校オブジェクト
     * @return 条件に合致する Test オブジェクトのリスト
     * @throws Exception
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        TestDao dao = new TestDao();
        // TestDaoの科目・クラス用filterメソッドを呼び出し、DBからデータをリストで取得して返します
        return dao.filter(entYear, classNum, subject, school);
    }
}