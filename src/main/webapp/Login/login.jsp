<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    <form action="LoginServlet" method="post">
        <div>
            <label for="username">아이디:</label>
            <input type="text" name="username" id="username" required><br><br>
        </div>

        <div>
            <label for="password">비밀번호:</label>
            <input type="password" name="password" id="password" required><br><br>
        </div>

        <div class="checkbox">
            <input id="remember" type="checkbox" />
            <label for="remember">Remember me on this computer</label><br><br>
        </div>

        <div class="action_btns">
            <div class="one_half">
                <a href="#" class="btn back_btn"><i class="fa fa-angle-double-left"></i> 뒤로가기</a>
            </div>
            <div class="one_half last">
                <button type="submit" class="btn btn_red">로그인</button>
            </div>
        </div>
    </form>

    <a href="#" class="forgot_password">비밀번호를 잊어버리셨나요??</a>

    <c:if test="${param.error == 'true'}">
        <p style="color: red;">로그인 정보가 잘못되었습니다. 다시 시도해 주세요.</p>
    </c:if>
</body>
</html>
