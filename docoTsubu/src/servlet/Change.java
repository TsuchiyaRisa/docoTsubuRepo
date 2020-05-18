package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしているか確認するため
		//セッションスコープに保存されたユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");

		if(loginUser == null) {
			//ログインしていない場合
			//リダイレクト
			response.sendRedirect("/docoTsubu/");
		} else {//ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/change.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String number = request.getParameter("number");
		String text = request.getParameter("text");

		//入力値チェック
		if(number == null || number.length() == 0) {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきNoが入力されていません");
		} else if(text == null || text.length() == 0) {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		} else {
			//セッションスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");

			//つぶやきをDBに追加
			Mutter mutter = new Mutter(number,loginUser.getName(),text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.changeTsubuyaki(mutter, request);
		}

		//つぶやき編集画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/change.jsp");
		dispatcher.forward(request, response);
	}

}
