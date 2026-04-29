/**長家優紀*/
package scoremanager.main;
 
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
 
        String subjectCd = request.getParameter("subjectCd");
        String countStr = request.getParameter("count");
        String classNum = request.getParameter("classNum");
 
        Map<String, String> errorMap = new HashMap<>();
 
        int count = 0;
        try {
            count = Integer.parseInt(countStr);
        } catch (Exception e) {
            request.setAttribute("errors", "回数が不正です");
            request.getRequestDispatcher("TestRegist.action").forward(request, response);
            return;
        }
 
        TestDao dao = new TestDao();
        Enumeration<String> params = request.getParameterNames();
 
        boolean hasError = false;
 
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
                    errorMap.put(studentNo, "数値で入力してください");
                    hasError = true;
                    continue;
                }
 
                if (point < 0 || point > 100) {
                    errorMap.put(studentNo, "0～100の範囲で入力してください");
                    hasError = true;
                    continue;
                }
 
                Test test = new Test();
 
                Student student = new Student();
                student.setNo(studentNo);
                test.setStudent(student);
 
                Subject subject = new Subject();
                subject.setCd(subjectCd.trim());
                test.setSubject(subject);
 
                test.setNo(count);
                test.setPoint(point);
                test.setClassNum(classNum);
 
                dao.save(test, teacher.getSchool());
            }
        }
 
        if (hasError) {
            request.setAttribute("errorMap", errorMap);
            request.getRequestDispatcher("TestRegist.action").forward(request, response);
            return;
        }
 
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}