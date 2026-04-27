package scoremanager.main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Test;
import bean.User;
import dao.StudentDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String studentNo = request.getParameter("f4");

        StudentDao sDao = new StudentDao();
        TestDao tDao = new TestDao();

        Student student = sDao.get(studentNo);

        if (student != null) {

            List<Test> tests = tDao.filter(student);

            // ★ subjectと同じ構造にする
            Map<String, Object[]> scoreMap = new LinkedHashMap<>();

            Object[] data = new Object[3];
            data[0] = student; // 学生情報
            data[1] = 0;       // 1回
            data[2] = 0;       // 2回

            for (Test t : tests) {
                if (t.getNo() == 1) {
                    data[1] = t.getPoint();
                } else if (t.getNo() == 2) {
                    data[2] = t.getPoint();
                }
            }

            scoreMap.put(studentNo, data);

            request.setAttribute("student", student);
            request.setAttribute("scoreMap", scoreMap);

        } else {
            request.setAttribute("errors", "学生情報が見つかりませんでした。");
        }

        request.setAttribute("f4", studentNo);

        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}