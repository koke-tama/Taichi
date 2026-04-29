/** 河合太一 */
package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    /**
     * ログインユーザーの所属学校に紐づく科目一覧を取得し、画面に表示する
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションからユーザー取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {

            // DAO初期化
            SubjectDao sDao = new SubjectDao();

            // 科目一覧取得
            List<Subject> list = sDao.filter(user.getSchool());

            // JSPへ渡す
            request.setAttribute("subjects", list);
        }

        // 一覧画面へフォワード
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}