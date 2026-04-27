package scoremanager.main;

import bean.Subject;
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 変更対象の科目データを取得し、変更画面へ遷移するアクション
 */
public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 2. 一覧画面から送られてきた科目コード(cd)を取得
        String cd = request.getParameter("cd");

        // 3. DBから該当する科目の詳細データを取得
        SubjectDao sDao = new SubjectDao();
        // 重複チェックでも使った get メソッドを利用してデータを検索
        Subject subject = sDao.get(cd, user.getSchool());

        // 4. 取得したデータをリクエスト属性にセット
        request.setAttribute("subject", subject);

        // 5. 変更画面（student_update.jsp）へフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}