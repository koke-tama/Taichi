package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            // パスの取得とクラス名への変換
            String path = request.getServletPath().substring(1);
            String name = path.replace(".a", "A")
                            .replace('/', '.');

            System.out.println("★ servlet path -> " + request.getServletPath());
            System.out.println("★ class name -> " + name);

            // Actionクラスのインスタンス生成
            Action action = (Action) Class.forName(name)
                    .getDeclaredConstructor()
                    .newInstance();

            // execute実行
            action.execute(request, response);

        } catch (Exception e) {
            // ログにスタックトレースを出力（サーバー側で確認用）
            e.printStackTrace();

            // JSPでエラー内容を表示したい場合はリクエスト属性にセット
            request.setAttribute("exception", e);

            // ★ エラーページへ遷移
            // パスはプロジェクトの構造に合わせて調整してください（例: "/scoremanager/main/error.jsp"）
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        doGet(request, response);
    }
}