package scoremanager.main;
 
import bean.Subject;
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class SubjectDeleteAction extends Action {
 
    @Override

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String cd = request.getParameter("cd");
 
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
 
        SubjectDao dao = new SubjectDao();
 
        // 削除対象取得

        Subject subject = dao.get(cd, user.getSchool());
 
        request.setAttribute("subject", subject);
 
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);

    }

}
 