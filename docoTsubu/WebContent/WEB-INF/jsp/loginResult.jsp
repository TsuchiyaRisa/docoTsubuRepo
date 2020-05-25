<%--ログイン結果画面表示用JSP--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
  //セッションスコープからユーザー情報を取得
  User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>どこつぶ</title>
</head>
<body>
  <h1>どこつぶログイン</h1>
  <%
    //ログインに成功した場合
    if (loginUser != null) {
  %>
  <p>ログインに成功しました</p>
  <p>
    ようこそ<%=loginUser.getName()%>さん
  </p>
  <form action="/docoTsubu/Main">
    <%--押下するリンクによってメイン画面の表示内容を変更--%>
    <p>
      <a href="/docoTsubu/Main?method=insert"><span class="blank10">つぶやき投稿</span></a><a
        href="/docoTsubu/Main?method=update"><span class="blank10">つぶやき編集</span></a><a
        href="/docoTsubu/Main?method=delete">つぶやき削除</a>
    </p>
  </form>
  <%
    //ログインに失敗した場合
    } else {
  %>
  <p>ログインに失敗しました</p>
  <a href="/docoTsubu/">TOPへ</a>
  <%
    }
  %>
</body>
</html>