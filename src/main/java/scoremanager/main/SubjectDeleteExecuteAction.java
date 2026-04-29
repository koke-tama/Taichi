/** 小野田匠希 */
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
 
        SubjectDao dao = new SubjectDao();
 
        // 科目取得して削除
        bean.Subject subject = dao.get(cd, user.getSchool());
        if (subject != null) {
            dao.delete(subject);
        }
 
        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}