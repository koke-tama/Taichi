/** 河合太一 */
package scoremanager.main;

import bean.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

    /**
     * 科目登録画面を表示する
     * 未ログインの場合はログイン画面へリダイレクトする
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションからユーザー取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ログインチェック
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 登録画面へ遷移
        request.getRequestDispatcher("subject_create.jsp").forward(request, response);
    }
}