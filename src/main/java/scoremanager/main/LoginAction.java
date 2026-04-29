/*長家優紀*/
package scoremanager.main;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
public class LoginAction extends Action {
 
    /**ログイン画面を表示する*/
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}