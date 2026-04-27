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

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 1. パラメータの取得
        String oldCd = request.getParameter("oldCd"); // ★隠しパラメータ（元のコード）
        String cd = request.getParameter("cd");       // 新しいコード
        String name = request.getParameter("name");   // 新しい名前

        // 2. 更新用データのセット
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(user.getSchool());

        SubjectDao sDao = new SubjectDao();

        // 3. 更新処理の実行
        // ★ save ではなく、oldCdを引数に持つ update メソッドを呼び出す
        boolean isSuccess = sDao.update(subject, oldCd);

        if (isSuccess) {
            // 更新成功
            request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        } else {
            // 更新失敗
            List<String> errors = new ArrayList<>();
            errors.add("科目の更新に失敗しました。");
            request.setAttribute("errors", errors);
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
        }
    }
}