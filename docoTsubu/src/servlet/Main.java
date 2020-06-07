package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PostMutterLogic;
import model.User;

/**
 * メイン画面表示用サーブレットクラス
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ログインしているか確認するため
        //セッションスコープに保存されたユーザ情報を取得
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            //ログインしていない場合
            //リダイレクト
            response.sendRedirect("/docoTsubu/");
        } else {//ログイン済みの場合
            //リクエストパラメータの取得
            request.setCharacterEncoding("UTF-8");
            String method = request.getParameter("method");

            //押下されたボタンによってメイン画面のタイトルとボタン名を設定
            if (method == null || method.length() == 0) {
                //リダイレクト
                response.sendRedirect("/docoTsubu/");
            } else if (method.equals("insert")) {
                //メイン画面のタイトルをリクエストスコープに保存
                request.setAttribute("title", "つぶやき投稿");
            } else if (method.equals("update")) {
                //メイン画面のタイトルをリクエストスコープに保存
                request.setAttribute("title", "つぶやき編集");
            } else if (method.equals("delete")) {
                //メイン画面のタイトルをリクエストスコープに保存
                request.setAttribute("title", "つぶやき削除");
            }
            //フォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String insert = request.getParameter("insert");//つぶやき投稿ボタン押下情報
        String update = request.getParameter("update");//つぶやき編集ボタン押下情報
        String delete = request.getParameter("delete");//つぶやき削除ボタン押下情報
        String number = request.getParameter("number");//つぶやきNo
        String text = request.getParameter("text");//つぶやき

        //つぶやき投稿ボタンを押下した場合
        if (insert != null && insert.length() != 0) {
            //メイン画面のタイトルをリクエストスコープに保存
            request.setAttribute("title", "つぶやき投稿");

            //入力値チェック
            if (text != null && text.length() != 0) {
                //セッションスコープに保存されたユーザ情報を取得
                HttpSession session = request.getSession();
                User loginUser = (User) session.getAttribute("loginUser");
                String userName = loginUser.getName();

                //つぶやきをDBに追加
                PostMutterLogic postMutterLogic = new PostMutterLogic();
                postMutterLogic.insertTsubuyaki(userName, text, request);

            } else {
                //エラーメッセージをリクエストスコープに保存
                request.setAttribute("errorMsg", "つぶやきが入力されていません");
            }
            //つぶやき編集ボタンを押下した場合
        } else if (update != null && update.length() != 0) {
            //メイン画面のタイトルをリクエストスコープに保存
            request.setAttribute("title", "つぶやき編集");

            //入力値チェック
            if (number == null || number.length() == 0) {
                //エラーメッセージをリクエストスコープに保存
                request.setAttribute("errorMsg", "つぶやきNoが入力されていません");
            } else if (text == null || text.length() == 0) {
                //エラーメッセージをリクエストスコープに保存
                request.setAttribute("errorMsg", "つぶやきが入力されていません");
            } else {
                //つぶやきをDBに追加
                PostMutterLogic postMutterLogic = new PostMutterLogic();
                postMutterLogic.changeTsubuyaki(text, number, request);
            }
            //つぶやき削除ボタンを押下した場合
        } else if (delete != null && delete.length() != 0) {
            //メイン画面のタイトルをリクエストスコープに保存
            request.setAttribute("title", "つぶやき削除");

            //入力値チェック
            if (number != null && number.length() != 0) {
                //つぶやきをDBから削除
                PostMutterLogic postMutterLogic = new PostMutterLogic();
                postMutterLogic.deleteTsubuyaki(number, request);

            } else {
                //エラーメッセージをリクエストスコープに保存
                request.setAttribute("errorMsg", "つぶやきNoが入力されていません");
            }
        } else {
            //リダイレクト
            response.sendRedirect("/docoTsubu/");
        }
        //メイン画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}
