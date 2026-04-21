package bean;
 
import java.io.Serializable;
 
public class Teacher extends User implements Serializable {

    private String id;

    private String password;

    private String name;

    private School school;
 
    // 教員ID

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
 
    // パスワード

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
 
    // 教員名

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
 
    // 所属校

    public School getSchool() { return school; }

    public void setSchool(School school) { this.school = school; }

}
 