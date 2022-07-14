<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    String notice_seq = (String) request.getAttribute("Notice");
%>
<html>
<head>
    <meta charset="utf-8">
    <title>웹 페이지 제목</title>
</head>
<body>
    <h2>글 수정 페이지</h2>
    <form action="DoNoticeUpdate">
    <div>제목</div>
    <div><input name="title"></div>
    <div>내용</div>
    <div><textarea name="contents"></textarea> </div>
    <div><input name="notice_seq" hidden="hidden" value="<%=notice_seq%>"></div>
    <div><input type="submit" value="업데이트"></div></form>
</body>
</html>