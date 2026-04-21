package tool;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/StudentAction")

public class StudentAction extends HttpServlet {
 
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {
 
        request.setCharacterEncoding("UTF-8");
 
        // 入力値取得

        String entYear = request.getParameter("entYear");

        String no = request.getParameter("no");

        String name = request.getParameter("name");

        String classNum = request.getParameter("classNum");

        String isAttend = request.getParameter("isAttend");
 
        // ★ 学校情報（ログイン時にセッションへ入れている想定）

        School school = (School) request.getSession().getAttribute("school");
 
        // エラーメッセージ格納用

        Map<String, String> errors = new HashMap<>();
 
        // ★ 氏名チェック

        if (name == null || name.isBlank()) {

            errors.put("name", "氏名を正しく入力してください");

        }
 
        // ★ クラスチェック

        if (classNum == null || classNum.isBlank()) {

            errors.put("classNum", "クラスを正しく入力してください");

        }
 
        // エラーがあれば入力画面に戻す

        if (!errors.isEmpty()) {
 
            Student student = new Student();

            student.setEntYear(Integer.parseInt(entYear));

            student.setNo(no);

            student.setName(name);

            student.setClassNum(classNum);

            student.setAttend(isAttend != null);
 
            request.setAttribute("student", student);

            request.setAttribute("errors", errors);
 
            request.getRequestDispatcher("/scoremanager/student/studentAdd.jsp")

                   .forward(request, response);

            return;

        }
 
        // ★ 登録処理

        try {

            StudentDao dao = new StudentDao();
 
            Student student = new Student();

            student.setEntYear(Integer.parseInt(entYear));

            student.setNo(no);

            student.setName(name);

            student.setClassNum(classNum);

            student.setAttend(isAttend != null);

            student.setSchool(school);  // ← school_cd をセット
 
            dao.save(student);
 
            response.sendRedirect("StudentList.action");
 
        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}

 