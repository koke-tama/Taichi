package scoremanager.main;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // DAO
        StudentDao sDao = new StudentDao();

        // パラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num");
        String isAttendStr = request.getParameter("is_attend");

        // Student生成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setClassNum(classNum);
        student.setAttend(isAttendStr != null);

        // 🔥 ここ重要（schoolをセットしないとまたエラー）
        School school = new School();
        school.setCd(request.getParameter("school_cd")); // hiddenで渡す
        student.setSchool(school);

        // DB更新
        sDao.save(student);
     // DB更新
        boolean result = sDao.save(student);
        System.out.println("更新結果: " + result);

        // 一覧へリダイレクト
        response.sendRedirect("StudentList.action");
    }
}