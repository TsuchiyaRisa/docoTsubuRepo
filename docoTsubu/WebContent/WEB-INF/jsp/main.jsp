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
<h1>どこつぶメイン</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中 &nbsp;&nbsp;
<a href="/docoTsubu/Logout">ログアウト</a>
</p>
<p>
<a href="/docoTsubu/Change">つぶやき編集</a> &nbsp;&nbsp;
<a href="/docoTsubu/Delete">つぶやき削除</a> &nbsp;&nbsp;
<a href="/docoTsubu/Main">更新</a>
</p>

<form action="/docoTsubu/Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<% if(errorMsg != null) {%>
<p><%= errorMsg %></p>
<%} %>
<% for(Mutter mutter : mutterList) { %>
<p>No<%= mutter.getNumber() %>&nbsp;<%= mutter.getUserName() %>&nbsp;:&nbsp;<%= mutter.getText() %></p>
<% } %>
</body>
</html>