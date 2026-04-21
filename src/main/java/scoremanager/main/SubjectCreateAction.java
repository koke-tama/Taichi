package scoremanager.main;

import bean.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 科目登録画面を表示するためのアクション
 */
public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ログインチェック（未ログインならログイン画面へ）
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. 登録画面（subject_create.jsp）へ遷移する
        // リクエスト属性に何か初期値が必要な場合はここでセットします
        request.getRequestDispatcher("subject_create.jsp").forward(request, response);
    }
}