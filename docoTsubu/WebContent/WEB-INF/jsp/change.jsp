<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,model.PostMutterLogic,java.util.List" %>
<%
//セッションスコープに保存されたユーザ情報を取得
User loginUser = (User)session.getAttribute("loginUser");
//DBに登録されているつぶやきリストを取得
PostMutterLogic postMutterLogic = new PostMutterLogic();
List<Mutter> mutterList = postMutterLogic.selectTsubuyaki(request);
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶ編集</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="/docoTsubu/Logout">ログアウト</a>
</p>
<p>
<a href="/docoTsubu/Main">メイン画面へ戻る</a> &nbsp;&nbsp;
<a href="/docoTsubu/Change">更新</a>
</p>
<form action="/docoTsubu/Change" method="post">
<p>つぶやきNo<input type="text" name="number"></p>
<p>つぶやき<input type="text" name="text">
<input type="submit" value="変更"></p>
</form>
<% if(errorMsg != null) {%>
<p><%= errorMsg %></p>
<%} %>
<% for(Mutter mutter : mutterList) { %>
<p>No<%= mutter.getNumber() %>&nbsp;<%= mutter.getUserName() %>&nbsp;:&nbsp;<%= mutter.getText() %></p>
<% } %>
</body>
</html>