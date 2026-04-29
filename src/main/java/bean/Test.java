/**長家優紀*/
package bean;

import java.io.Serializable;
import java.util.List;

import dao.TestDao;

/**成績を表すBean*/
public class Test implements Serializable {

    /** 学生情報 */
    private Student student;

    /** 科目情報 */
    private Subject subject;

    /** テスト回数 */
    private int no;

    /** 得点 */
    private int point;

    /** クラス番号 */
    private String classNum;

    /** コンストラクタ */
    public Test() {
    }

    // --- getter / setter ---

    /** 学生情報を取得 */
    public Student getStudent() { return student; }

    /** 学生情報を設定 */
    public void setStudent(Student student) { this.student = student; }

    /** 科目情報を取得 */
    public Subject getSubject() { return subject; }

    /** 科目情報を設定 */
    public void setSubject(Subject subject) { this.subject = subject; }

    /** テスト回数を取得 */
    public int getNo() { return no; }

    /** テスト回数を設定 */
    public void setNo(int no) { this.no = no; }

    /** 得点を取得 */
    public int getPoint() { return point; }

    /** 得点を設定 */
    public void setPoint(int point) { this.point = point; }

    /** クラス番号を取得 */
    public String getClassNum() { return classNum; }

    /** クラス番号を設定 */
    public void setClassNum(String classNum) { this.classNum = classNum; }


    // --- DAO操作 ---

    /** 学生に紐づく成績一覧を取得 */
    public List<Test> filter(Student student) throws Exception {
        TestDao dao = new TestDao();
        return dao.filter(student);
    }

    /** 条件に一致する成績一覧を取得 */
    public List<Test> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        TestDao dao = new TestDao();
        return dao.filter(entYear, classNum, subject, school);
    }
}