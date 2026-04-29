/**長家優紀*/
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            request.getRequestDispatcher("/scoremanager/main/login.jsp").forward(request, response);
            return;
        }

        School school = teacher.getSchool();

        // クラス一覧
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classList = classNumDao.filter(school);

        // 科目一覧
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(school);

        // 入学年度
        int now = LocalDate.now().getYear();
        List<Integer> yearList = new ArrayList<>();
        for (int i = now - 10; i <= now; i++) {
            yearList.add(i);
        }

        // テスト回数
        List<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            countList.add(i);
        }

        // パラメータ取得
        String yearStr = request.getParameter("year");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String countStr = request.getParameter("count");

        // 条件が揃っている場合のみ検索
        if (yearStr != null && classNum != null && subjectCd != null && countStr != null
                && !yearStr.equals("0") && !classNum.equals("0")
                && !subjectCd.equals("0") && !countStr.equals("0")) {

            int year = Integer.parseInt(yearStr);
            int count = Integer.parseInt(countStr);

            TestDao testDao = new TestDao();
            List<Test> testList = testDao.filter(school, year, classNum, subjectCd, count);

            request.setAttribute("testList", testList);
            request.setAttribute("selectedYear", year);
            request.setAttribute("selectedClassNum", classNum);
            request.setAttribute("selectedSubjectId", subjectCd);
            request.setAttribute("selectedCount", count);

            // 科目名取得
            for (Subject s : subjectList) {
                if (s.getCd().equals(subjectCd)) {
                    request.setAttribute("selectedSubjectName", s.getName());
                }
            }
        }

        // 共通データ
        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("yearList", yearList);
        request.setAttribute("countList", countList);

        // デバッグ
        System.out.println("school_cd = " + school.getCd());
        System.out.println("subjectList size = " + subjectList.size());

        request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
    }
}