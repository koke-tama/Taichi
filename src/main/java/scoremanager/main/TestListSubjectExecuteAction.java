/**河合太一 */
package scoremanager.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Test;
import bean.User;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ■ セッションからユーザー取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ■ パラメータ取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // ■ 入力チェック
        if (entYearStr == null || entYearStr.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty()) {

            request.setAttribute("errors", "入学年度とクラスと科目を選択してください");

            // 再表示用データ
            ClassNumDao cNumDao = new ClassNumDao();
            SubjectDao sDao = new SubjectDao();

            request.setAttribute("class_num_set", cNumDao.filter(user.getSchool()));
            request.setAttribute("subjects", sDao.filter(user.getSchool()));

            // 入学年度リスト生成
            List<Integer> ent_year_set = new ArrayList<>();
            int year = java.time.LocalDate.now().getYear();
            for (int i = year - 10; i <= year + 1; i++) {
                ent_year_set.add(i);
            }
            request.setAttribute("ent_year_set", ent_year_set);

            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // ■ 型変換
        int entYear = Integer.parseInt(entYearStr);

        // ■ DAO初期化
        TestDao tDao = new TestDao();
        SubjectDao sDao = new SubjectDao();

        // ■ 科目取得
        Subject subject = sDao.get(subjectCd, user.getSchool());

        // ■ 成績データ取得
        List<Test> tests = tDao.filter(entYear, classNum, subject, user.getSchool());

        // ■ 学生ごとに成績をまとめる
        Map<String, Object[]> scoreMap = new LinkedHashMap<>();

        for (Test t : tests) {

            // ▼ studentがnullのデータは除外
            if (t.getStudent() == null) continue;

            String studentNo = t.getStudent().getNo();

            // ▼ 既存データ取得 or 新規作成
            Object[] data = scoreMap.get(studentNo);
            if (data == null) {
                data = new Object[3];
                data[0] = t.getStudent(); // 学生情報
                data[1] = 0;              // 1回目
                data[2] = 0;              // 2回目
                scoreMap.put(studentNo, data);
            }

            // ▼ 回数ごとに点数セット
            if (t.getNo() == 1) {
                data[1] = t.getPoint();
            } else if (t.getNo() == 2) {
                data[2] = t.getPoint();
            }
        }

        // ■ リクエスト属性にセット（検索条件）
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("subject", subject);

        // ■ 成績データ
        request.setAttribute("scoreMap", scoreMap);

        // ■ 再表示用データ
        ClassNumDao cNumDao = new ClassNumDao();
        request.setAttribute("class_num_set", cNumDao.filter(user.getSchool()));
        request.setAttribute("subjects", sDao.filter(user.getSchool()));

        // 入学年度リスト
        List<Integer> ent_year_set = new ArrayList<>();
        int year = java.time.LocalDate.now().getYear();
        for (int i = year - 10; i <= year + 1; i++) {
            ent_year_set.add(i);
        }
        request.setAttribute("ent_year_set", ent_year_set);

        // ■ 画面遷移
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}