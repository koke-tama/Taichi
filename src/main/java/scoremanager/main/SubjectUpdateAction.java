/** 河合太一 */
package scoremanager.main;

import bean.Subject;
import bean.User;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 変更対象の科目データを取得し変更画面へ遷移するアクション
 */
public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String cd = request.getParameter("cd");

        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, user.getSchool());

        request.setAttribute("subject", subject);

        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}