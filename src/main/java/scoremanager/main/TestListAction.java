package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.User;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * 成績参照の初期表示アクション
 */
public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 2. Daoの初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();

        // 3. 各種データの取得
        // ユーザーが所属している学校（user.getSchool()）のクラスデータを取得
        List<String> class_num_set = cNumDao.filter(user.getSchool());
        
        // ユーザーが所属している学校（user.getSchool()）の科目データを取得
        List<Subject> subjects = sDao.filter(user.getSchool());

        // 入学年度リストを生成（現在の年から10年前〜1年後まで）
        List<Integer> ent_year_set = new ArrayList<>();
        int year = LocalDate.now().getYear(); 
        for (int i = year - 10; i < year + 1; i++) {
            ent_year_set.add(i);
        }

        // 4. 収穫したデータをリクエスト属性に設定
        request.setAttribute("class_num_set", class_num_set); // クラスリスト
        request.setAttribute("subjects", subjects);           // 科目リスト
        request.setAttribute("ent_year_set", ent_year_set);   // 入学年度リスト

        // 5. test_list.jsp（検索画面）に遷移
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}