/*長家優紀*/
package scoremanager.main;
 
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class LoginExecuteAction extends Action {
 
    /**
     * ログイン認証を行う
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        HttpSession session = request.getSession();
 
        String id = request.getParameter("id");
        String password = request.getParameter("password");
 
        // 未入力チェック
        if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "IDとパスワードを入力してください");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
 
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.get(id);
 
        // 認証チェック
        if (teacher == null || !teacher.getPassword().equals(password)) {
            request.setAttribute("error", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
            request.setAttribute("id", id);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
 
        // ログイン成功
        teacher.setAuthenticated(true);
        session.setAttribute("user", teacher);
 
        response.sendRedirect("menu.jsp");
    }
}