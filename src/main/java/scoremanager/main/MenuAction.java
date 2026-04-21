package scoremanager.main;
 
import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
/**

* メインメニューを表示するためのアクションクラス

*/

public class MenuAction extends Action {
 
    @Override

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. セッションの取得

        HttpSession session = request.getSession();
 
        // 2. セッションからログインユーザー（Teacher）を取得

        Teacher teacher = (Teacher) session.getAttribute("user");
 
        // 3. ログインチェック（シーケンス図にはありませんが、セキュリティ上必須です）

        if (teacher == null) {

            // 未ログインの場合はログイン画面へ戻す

            response.sendRedirect("Login.action");

            return;

        }
 
        // 4. メインメニュー画面（menu.jsp）へフォワード

        // 図にある「表示する」の工程に対応します

        request.getRequestDispatcher("menu.jsp").forward(request, response);

    }

}
 