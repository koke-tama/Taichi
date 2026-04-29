/** 河合太一 */
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    /**
     * 科目登録処理を実行する
     * 入力値のバリデーションと重複チェックを行い、問題なければDBに保存する
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

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // バリデーション準備
        List<String> errors = new ArrayList<>();
        SubjectDao sDao = new SubjectDao();

        // 科目コードチェック（3文字）
        if (cd == null || cd.length() != 3) {
            errors.add("科目コードは3文字で入力してください");
        } else {
            // 重複チェック
            Subject existingSubject = sDao.get(cd, user.getSchool());
            if (existingSubject != null) {
                errors.add("科目コードが重複しています");
            }
        }

        // エラー時処理
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        // 科目生成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(user.getSchool());

        // DB保存
        sDao.save(subject);

        // 完了画面へ遷移
        request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
    }
}