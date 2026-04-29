/** 河合太一 */
package scoremanager.main;

import java.util.List;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {

    /**
     * 学生情報の更新処理を行う
     * 初期表示時は対象学生データとクラス一覧を取得して画面表示、
     * 更新時は入力された内容でデータベースを更新する
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // DAO初期化
        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();

        // パラメータ取得
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String isAttendStr = request.getParameter("is_attend");

        // 送信判定（更新ボタン押下時のみ値が入る想定）
        String submit = request.getParameter("submit");

        if (submit == null) {

            // --- 初期表示処理 ---
            Student student = sDao.get(no);

            // クラス一覧取得（プルダウン用）
            List<String> classNumSet = cDao.filter(student.getSchool());

            // リクエストにセット
            request.setAttribute("student", student);
            request.setAttribute("class_list", classNumSet);

            // 編集画面へ遷移
            request.getRequestDispatcher("update.jsp").forward(request, response);

        } else {

            // --- 更新処理 ---
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(Integer.parseInt(entYearStr));
            student.setClassNum(classNum);
            student.setAttend(isAttendStr != null);

            // DB更新
            sDao.save(student);

            // 更新後画面へ遷移
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
    }
}