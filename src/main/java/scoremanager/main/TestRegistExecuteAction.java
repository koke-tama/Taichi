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
 
        // 検索条件の取得
        String subjectCd = request.getParameter("subjectCd");
        String countStr = request.getParameter("count");
        int count = Integer.parseInt(countStr);
 
        TestDao dao = new TestDao();
        Enumeration<String> params = request.getParameterNames();
 
        // バリデーションチェック（0〜100点か確認）
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            if (paramName.startsWith("point_")) {
                String pointStr = request.getParameter(paramName);
                if (pointStr != null && !pointStr.isEmpty()) {
                    int point = Integer.parseInt(pointStr);
                    if (point < 0 || point > 100) {
                        // 範囲外ならエラーメッセージをセットして入力画面に戻る
                        request.setAttribute("errors", "0〜100の範囲で入力してください");
                        request.getRequestDispatcher("TestRegist.action").forward(request, response);
                        return;
                    }
                }
            }
        }
 
        // 保存処理
        params = request.getParameterNames(); // ポインタをリセット
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
 
            if (paramName.startsWith("point_")) {
                String studentNo = paramName.substring(6);
                String pointStr = request.getParameter(paramName);
 
                if (pointStr == null || pointStr.isEmpty()) continue;
 
                Test test = new Test();
                Student student = new Student();
                student.setNo(studentNo);
                test.setStudent(student);
 
                Subject subject = new Subject();
                subject.setCd(subjectCd != null ? subjectCd.trim() : "");
                test.setSubject(subject);
 
                test.setNo(count);
                test.setPoint(Integer.parseInt(pointStr));
 
                dao.save(test, teacher.getSchool());
            }
        }
 
        // 直接完了画面のJSPを表示（画像通りのレイアウトにするため）
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}
 