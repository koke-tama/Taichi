package scoremanager.main;

import java.util.List;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // DAOの初期化
        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();

        // リクエストパラメータの取得
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no"); // 学生番号
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String isAttendStr = request.getParameter("is_attend");

        // 判別フラグ（jsllllllllllllllllklp側で「更新ボタン」を押したときにパラメータを送るようにします）
        String submit = request.getParameter("submit");

        if (submit == null) {
            // --- 初期表示処理 (GET相当) ---
            // 学生番号から現在のデータを取得
            Student student = sDao.get(no);
            // クラス一覧を取得（ドロップダウン用）
            List<String> classNumSet = cDao.filter(student.getSchool());


            // リクエスト属性にセット
            request.setAttribute("student", student);
            request.setAttribute("class_list", classNumSet);
            
            // 編集画面へフォワード
            request.getRequestDispatcher("update.jsp").forward(request, response);

        } else {
            // --- 更新実行処理 (POST相当) ---
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(Integer.parseInt(entYearStr));
            student.setClassNum(classNum);
            // 在学フラグの処理
            student.setAttend(isAttendStr != null);

            // データベース更新
            sDao.save(student);

            // 更新完了後は一覧画面へリダイレクト（またはフォワード）
            request.getRequestDispatcher("update.jsp").forward(request, response);
            
        }
    }
}