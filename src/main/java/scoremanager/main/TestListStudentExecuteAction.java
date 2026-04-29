/**河合太一 */
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Subject;
import bean.Test;
import bean.User;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ■ セッションからログインユーザー取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ■ リクエストパラメータ取得（学生番号）
        String studentNo = request.getParameter("f4");

        // ■ DAO初期化
        StudentDao sDao = new StudentDao();
        SubjectDao sbDao = new SubjectDao();
        TestDao tDao = new TestDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // ■ 学生情報取得
        Student student = sDao.get(studentNo);

        if (student != null) {

            // ■ 成績データ取得
            List<Test> tests = tDao.filter(student);

            // ■ 成績表示用Map作成（学生ごとにまとめる）
            Map<String, Object[]> scoreMap = new LinkedHashMap<>();

            Object[] data = new Object[3];
            data[0] = student; // 学生情報
            data[1] = 0;       // 1回目
            data[2] = 0;       // 2回目

            // ■ 回数ごとに点数をセット
            for (Test t : tests) {
                if (t.getNo() == 1) {
                    data[1] = t.getPoint();
                } else if (t.getNo() == 2) {
                    data[2] = t.getPoint();
                }
            }

            scoreMap.put(studentNo, data);

            // ■ 画面表示用データ取得
            List<String> class_num_set = cNumDao.filter(user.getSchool());
            List<Subject> subjects = sbDao.filter(user.getSchool());

            // ■ 入学年度リスト作成
            List<Integer> ent_year_set = new ArrayList<>();
            int year = LocalDate.now().getYear();
            for (int i = year - 10; i < year + 1; i++) {
                ent_year_set.add(i);
            }

            // ■ JSPへデータセット
            request.setAttribute("class_num_set", class_num_set);
            request.setAttribute("subjects", subjects);
            request.setAttribute("ent_year_set", ent_year_set);
            request.setAttribute("student", student);
            request.setAttribute("scoreMap", scoreMap);

        } else {
            // ■ 学生が存在しない場合
            request.setAttribute("errors", "学生情報が見つかりませんでした。");
        }

        // ■ 入力値保持
        request.setAttribute("f4", studentNo);

        // ■ 画面遷移
        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }
}