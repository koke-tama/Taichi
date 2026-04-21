package scoremanager.main;
 
import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
/**
* 削除済み学生一覧（ゴミ箱）を表示するためのアクション
*/
public class StudentTrashAction extends Action {
 
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションからログインユーザー（教員）情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
 
        // 2. DAOを初期化
        StudentDao sDao = new StudentDao();
 
        // 3. 教員の所属校に紐づく「削除済み（is_deleted=true）」の学生リストを取得
        // StudentDao.java で新しく作成した filterDeleted メソッドを使用します
        List<Student> deletedStudents = sDao.filterDeleted(teacher.getSchool());
 
        // 4. 取得したリストをリクエスト属性にセット
        // JSP（student_trash.jsp）側では ${deletedStudents} でこのリストをループさせます
        request.setAttribute("deletedStudents", deletedStudents);
 
        // 5. ゴミ箱画面のJSPへフォワード
        request.getRequestDispatcher("student_trash.jsp").forward(request, response);
    }
}
 