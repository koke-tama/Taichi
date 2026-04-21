package bean;
 
import java.io.Serializable;
 
public class Test implements Serializable {
 
    private Student student;
    private Subject subject;
    private int no;
    private int point;
 
    public Student getStudent() {
        return student;
    }
 
    public void setStudent(Student student) {
        this.student = student;
    }
 
    public Subject getSubject() {
        return subject;
    }
 
    public void setSubject(Subject subject) {
        this.subject = subject;
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
 