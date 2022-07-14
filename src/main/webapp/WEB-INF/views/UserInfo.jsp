<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>회원가입</title>

</head>
<body >
        <center><h1>회원가입</h1></center>
        <form action="getInsertUser" method="post">
           <center> <fieldset>
            이름 : <input name="name" type="text"/> <br>
            아이디 : <input name="id" type="text"/> <br>
            비밀번호 : <input name="pwd" type="password"/> <br>
            이메일 : <input name="email" type="email"/> <br>
            <button type="submit" class="btn btn-primary">전송</button>
            </fieldset></center>
        </form>
</body>
</html>