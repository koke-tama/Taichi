package scoremanager.main;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("/scoremanager/main/StudentAdd")
public class StudentAddServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        request.setAttribute("student", new Student());
 
        try {
            StudentDao dao = new StudentDao();
 
            School school = (School) request.getSession().getAttribute("school");
            String schoolCd = (school != null) ? school.getCd() : "tes";
 
            List<Integer> entYearList = dao.getEntYearList();
            List<String> classNumList = dao.getClassNumList(schoolCd);
 
            request.setAttribute("ent_year_set", entYearList);
            request.setAttribute("class_num_set", classNumList);
 
        } catch (Exception e) {
            throw new ServletException(e);
        }
 
        request.getRequestDispatcher("/scoremanager/main/studentadd.jsp")
               .forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        request.setCharacterEncoding("UTF-8");
 
        String entYear = request.getParameter("entYear");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");
        String isAttend = request.getParameter("isAttend");
 
        Map<String, String> errors = new HashMap<>();
 
        // =========================
        // 入力チェック
        // =========================
        if (entYear == null || entYear.isBlank()) {
            errors.put("entYear", "入学年度を入力してください");
        }
 
        if (no == null || no.isBlank()) {
            errors.put("no", "このフィールドを入力してください");
        }
 
        if (name == null || name.isBlank()) {
            errors.put("name", "このフィールドを入力してください");
        }
 
        try {
            StudentDao dao = new StudentDao();
 
            School school = (School) request.getSession().getAttribute("school");
            String schoolCd = (school != null) ? school.getCd() : "tes";
 
            // =========================
            // 重複チェック
            // =========================
            if (no != null && !no.isBlank()) {
                if (dao.exists(no, schoolCd)) {
                    errors.put("no", "この学生番号が重複しています");
                }
            }
 
            // =========================
            // エラー処理
            // =========================
            if (!errors.isEmpty()) {
 
                Student student = new Student();
 
                if (entYear != null && !entYear.isBlank()) {
                    student.setEntYear(Integer.parseInt(entYear));
                }
 
                student.setNo(no);
                student.setName(name);
                student.setClassNum(classNum);
                student.setAttend(isAttend != null);
 
                request.setAttribute("student", student);
                request.setAttribute("errors", errors);
 
                request.setAttribute("ent_year_set", dao.getEntYearList());
                request.setAttribute("class_num_set", dao.getClassNumList(schoolCd));
 
                request.getRequestDispatcher("/scoremanager/main/studentadd.jsp")
                       .forward(request, response);
                return;
            }
 
            // =========================
            // 登録処理
            // =========================
            Student student = new Student();
 
            student.setEntYear(Integer.parseInt(entYear));
            student.setNo(no);
            student.setName(name);
            student.setClassNum(classNum);
            student.setAttend(isAttend != null);
            student.setSchool(school);
 
            dao.save(student);
 
            request.getRequestDispatcher("/scoremanager/main/studentadddone.jsp")
                   .forward(request, response);
 
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
 