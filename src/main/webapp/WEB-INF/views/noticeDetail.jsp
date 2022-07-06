<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    NoticeDTO rDTO = (NoticeDTO) request.getAttribute("rDTO");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>웹 페이지 제목</title>
</head>
<body>
    <div style="margin: auto;width: 400px;">
        <h4><%=CmmUtil.nvl(rDTO.getTitle())%></h4>
        <hr>
        <div style="text-align: right;"> 작성자 :<%=CmmUtil.nvl(rDTO.getReg_id())%></div>
        <div>
            <p>
                <%=CmmUtil.nvl(rDTO.getContents())%>
            </p>
        </div>
        <hr>
        <div style="float: left">
            <button onclick="location.href='getNoticeList'">뒤로</button>
        </div>
        <div style="float: right">
            <button onclick="location.href='NoticeDelete?no=<%=rDTO.getNotice_seq()%>'">삭제</button>
        </div>
    </div>
</body>
</html>