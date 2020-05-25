package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

/**
 * ログイン処理用サーブレットクラス
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");//ユーザ名
        String pass = request.getParameter("pass");//パスワード

        //Userインスタンス（ユーザー情報）の生成
        User user = new User(name, pass);

        //ログイン処理
        LoginLogic loginLogic = new LoginLogic();
        boolean isLogin = loginLogic.execute(user);

        HttpSession session = request.getSession();

        //ログイン成功時の処理
        if (isLogin) {
            //ユーザー情報をセッションスコープに保存
            session.setAttribute("loginUser", user);
            //ログイン失敗時の処理
        } else {
            //ユーザー情報をセッションスコープから削除
            session.removeAttribute("loginUser");
        }

        //ログイン結果画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request, response);
    }
}
