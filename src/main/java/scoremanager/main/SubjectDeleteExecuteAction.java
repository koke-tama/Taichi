package scoremanager.main;
 
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class SubjectDeleteExecuteAction extends Action {
 
    @Override

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String cd = request.getParameter("cd");
 
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
 
     // SubjectDeleteExecuteAction.java の該当箇所

        SubjectDao dao = new SubjectDao();

        // 1. 科目コードと学校情報を使って、一旦 Subject オブジェクトを取得
        bean.Subject subject = dao.get(cd, user.getSchool());

        // 2. 取得したオブジェクトを delete メソッドに渡す
        if (subject != null) {
            dao.delete(subject);
        } 
        // 削除実行（学校単位）
 
        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);

    }

}
 