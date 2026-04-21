package scoremanager.main;
 
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
/**
* 削除された学生を元に戻す（復活させる）アクション
*/
public class StudentRestoreAction extends Action {
 
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. JSPのリンク（StudentRestore.action?no=...）から学生番号を取得
        String no = request.getParameter("no");
 
        // 2. DAOを初期化
        StudentDao sDao = new StudentDao();
 
        // 3. 復活処理を実行
        // 先ほど StudentDao.java に追加した restore メソッドを呼び出します
        if (no != null) {
            sDao.restore(no);
        }
 
        // 4. 復活完了後、通常の一覧画面（StudentList.action）へリダイレクト
        // forwardではなくredirectを使うことで、ブラウザの更新ボタンによる再処理を防ぎます
        response.sendRedirect("StudentList.action");
    }
}
 