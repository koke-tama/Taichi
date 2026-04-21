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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            SubjectDao sDao = new SubjectDao();
            // ユーザーの所属学校を引数に渡す
            List<Subject> list = sDao.filter(user.getSchool());
            
            // JSPに渡す
            request.setAttribute("subjects", list);
        }

        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}