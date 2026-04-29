/*長家優紀*/
package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {

    /** セッションを破棄してログアウト処理を行う */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        // セッション破棄
        session.invalidate();

        // ログアウト画面へ遷移
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
}