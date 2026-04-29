/*小野田匠希*/
package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

    /**
     * リクエストURLからActionクラスを特定して実行する
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            // URLからクラス名を生成
            String path = request.getServletPath().substring(1);
            String name = path.replace(".a", "A")
                              .replace('/', '.');

            System.out.println("★ servlet path -> " + request.getServletPath());
            System.out.println("★ class name -> " + name);

            // Actionクラス生成
            Action action = (Action) Class.forName(name)
                    .getDeclaredConstructor()
                    .newInstance();

            // 処理実行
            action.execute(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("exception", e);

            // エラーページへ遷移
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * POSTリクエストをGETに委譲する
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        doGet(request, response);
    }
}