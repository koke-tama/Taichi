package scoremanager.main;
 
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
public class StudentDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. パラメータの取得
        String no = request.getParameter("no");
        String isExecute = request.getParameter("execute");
 
        StudentDao sDao = new StudentDao();
        
        // 2. 実行フラグの確認
        if (isExecute != null && isExecute.equals("true")) {
            // --- 【削除実行モード】 ---
            // すでにStudentDaoのdeleteは「UPDATE STUDENT SET IS_DELETED=TRUE...」に書き換え済み
            Student student = sDao.get(no);
            if (student != null) {
                sDao.delete(student);
            }
            // 削除（フラグ更新）完了後は、通常の一覧へ戻す
            response.sendRedirect("StudentList.action");
 
        } else {
            // --- 【確認画面表示モード】 ---
            Student student = sDao.get(no);
            
            if (student != null) {
                // 学生情報をリクエストにセットして確認JSPへ
                request.setAttribute("student", student);
                request.getRequestDispatcher("student_delete.jsp").forward(request, response);
            } else {
                // 万が一対象の学生が見つからない場合は一覧へ戻る
                response.sendRedirect("StudentList.action");
            }
        }
    }
}
 