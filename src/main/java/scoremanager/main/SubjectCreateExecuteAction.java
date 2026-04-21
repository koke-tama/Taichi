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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 2. リクエストパラメータ（入力値）の取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 3. バリデーションの準備
        List<String> errors = new ArrayList<>();
        SubjectDao sDao = new SubjectDao();

        // 【要件】科目コードは3文字で入力してください
        if (cd == null || cd.length() != 3) {
            errors.add("科目コードは3文字で入力してください");
        } else {
            // 【要件】詳細データを取得（重複チェック）
            // 既に同じコードの科目が存在するか確認
            Subject existingSubject = sDao.get(cd, user.getSchool());
            if (existingSubject != null) {
                // 【要件】科目コードが重複しています
                errors.add("科目コードが重複しています");
            }
        }

        // 4. エラーがある場合の処理
        if (!errors.isEmpty()) {
            // 入力値を保持して登録画面に戻る
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        // 5. DBに科目を保存（エラーがない場合）
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(user.getSchool());

        // 保存実行
        sDao.save(subject);

        // 6. 登録完了画面を表示
        request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
    }
}