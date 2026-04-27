package scoremanager.main;

import java.util.Enumeration;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // パラメータ取得
        String subjectCd = request.getParameter("subjectCd");
        String countStr = request.getParameter("count");
        String classNum = request.getParameter("classNum");

        // 入力チェック
        if (subjectCd == null || subjectCd.isEmpty()
                || countStr == null || countStr.isEmpty()
                || classNum == null || classNum.isEmpty()) {

            request.setAttribute("errors", "必要な項目が未入力です");
            request.getRequestDispatcher("TestRegist.action").forward(request, response);
            return;
        }

        int count;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errors", "回数が不正です");
            request.getRequestDispatcher("TestRegist.action").forward(request, response);
            return;
        }

        TestDao dao = new TestDao();
        Enumeration<String> params = request.getParameterNames();

        // 保存処理
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();

            if (paramName.startsWith("point_")) {
                String studentNo = paramName.substring(6);
                String pointStr = request.getParameter(paramName);

                if (pointStr == null || pointStr.isEmpty()) continue;

                int point;
                try {
                    point = Integer.parseInt(pointStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("errors", "点数は数値で入力してください");
                    request.getRequestDispatcher("TestRegist.action").forward(request, response);
                    return;
                }

                if (point < 0 || point > 100) {
                    request.setAttribute("errors", "点数は0〜100で入力してください");
                    request.getRequestDispatcher("TestRegist.action").forward(request, response);
                    return;
                }

                Test test = new Test();

                // 学生
                Student student = new Student();
                student.setNo(studentNo);
                test.setStudent(student);

                // 科目
                Subject subject = new Subject();
                subject.setCd(subjectCd.trim());
                test.setSubject(subject);

                // 回数・点数・クラス
                test.setNo(count);
                test.setPoint(point);
                test.setClassNum(classNum);

                // 保存
                dao.save(test, teacher.getSchool());
            }
        }

        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}