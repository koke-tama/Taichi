package scoremanager.main;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class LogoutAction extends Action {
 
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        HttpSession session = request.getSession();
 
        session.invalidate();
 
        // logout.jspへ
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
}
 