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

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String oldCd = request.getParameter("oldCd");
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(user.getSchool());

        SubjectDao sDao = new SubjectDao();

        boolean isSuccess = sDao.update(subject, oldCd);

        if (isSuccess) {
            request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        } else {
            List<String> errors = new ArrayList<>();
            errors.add("科目の更新に失敗しました。");
            request.setAttribute("errors", errors);
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
        }
    }
}