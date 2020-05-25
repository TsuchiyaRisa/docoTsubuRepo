<%--メイン画面表示用JSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page
  import="model.User,model.Mutter,model.PostMutterLogic,java.util.List"%>
<%
  //セッションスコープに保存されたユーザ情報を取得
  User loginUser = (User) session.getAttribute("loginUser");
  //DBに登録されているつぶやきリストを取得
  PostMutterLogic postMutterLogic = new PostMutterLogic();
  List<Mutter> mutterList = postMutterLogic.selectTsubuyaki(request);
  //リクエストスコープに保存されたエラーメッセージを取得
  String errorMsg = (String) request.getAttribute("errorMsg");
  //リクエストスコープに保存されたタイトルを取得
  String title = (String) request.getAttribute("title");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>どこつぶ</title>
</head>
<body>
  <h1><%=title%></h1>
  <p>
    <span class="blank10"><%=loginUser.getName()%>さん、ログイン中 </span> <a
      href="/docoTsubu/Logout">ログアウト</a>
  </p>
  <form action="/docoTsubu/Main" method="post">
    <%
      //ログイン結果画面でつぶやき投稿リンク以外を押下した場合
      if (!(title.equals("つぶやき投稿"))) {
    %>
    <%--つぶやきNo入力用テキストボックスを表示--%>
    <p>
      つぶやきNo<input type="text" name="number">
    </p>
    <%
      }
    %>
    <%
      //ログイン結果画面でつぶやき削除リンク以外を押下した場合
      if (!(title.equals("つぶやき削除"))) {
    %>
    <%--つぶやき入力用テキストボックスを表示--%>
    <p>
      つぶやき<input type="text" name="text">
    </p>
    <%
      }
    %>
    <p>
      <%
        //ログイン結果画面でつぶやき投稿リンクを押下した場合
        if (title.equals("つぶやき投稿")) {
      %>
      <%--つぶやき投稿ボタンを表示--%>
      <input type="submit" name="insert" value="投稿"> <input
        type="hidden" name="insert" value="投稿">
      <%
        //ログイン結果画面でつぶやき編集リンクを押下した場合
        } else if (title.equals("つぶやき編集")) {
      %>
      <%--つぶやき編集ボタンを表示--%>
      <input type="submit" name="update" value="編集"> <input
        type="hidden" name="update" value="編集">
      <%
        //ログイン結果画面でつぶやき削除リンクを押下した場合
        } else if (title.equals("つぶやき削除")) {
      %>
      <%--つぶやき削除ボタンを表示--%>
      <input type="submit" name="delete" value="削除"> <input
        type="hidden" name="delete" value="削除">
      <%
        }
      %>
    </p>
  </form>
  <%
    if (errorMsg != null) {
  %>
  <p><%=errorMsg%></p>
  <%
    }
  %>
  <%
    for (Mutter mutter : mutterList) {
  %>
  <p>
    <span class="blank5">No<%=mutter.getNumber()%></span><%=mutter.getUserName()%>:<%=mutter.getText()%></p>
  <%
    }
  %>
</body>
</html>