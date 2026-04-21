package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        // ログインユーザー取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            teacher = new Teacher();
            School school = new School();
            school.setCd("tes"); // 実在コード
            teacher.setSchool(school);
            session.setAttribute("user", teacher);
        }

        // =========================
        // パラメータ取得
        // =========================
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (isAttendStr != null) {
            isAttend = true;
        }

        // =========================
        // 年度リスト（過去10年〜今年）
        // =========================
        int year = LocalDate.now().getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // =========================
        // DAO
        // =========================
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        List<String> classNumSet = cNumDao.filter(teacher.getSchool());

        // =========================
        // 検索処理
        // =========================
        List<Student> students = null;
        Map<String, String> errors = new HashMap<>();

        if (entYear != 0 && classNum != null && !classNum.equals("0")) {

            // 入学年度 + クラス
            students = sDao.filter(
                    teacher.getSchool(),
                    entYear,
                    classNum,
                    isAttend
            );

        } else if (entYear != 0) {

            // 入学年度のみ
            students = sDao.filter(
                    teacher.getSchool(),
                    entYear,
                    isAttend
            );

        } else if (classNum == null || classNum.equals("0")) {

            // 条件なし（全件 or 在学のみ）
            students = sDao.filter(
                    teacher.getSchool(),
                    isAttend
            );

        } else {

            // クラスだけ指定 → エラー
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            request.setAttribute("errors", errors);

            students = sDao.filter(
                    teacher.getSchool(),
                    isAttend
            );
        }

        // =========================
        // JSPへ渡す
        // =========================
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttend);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);

        // フォワード
        request.getRequestDispatcher("student_list.jsp")
               .forward(request, response);
    }
}