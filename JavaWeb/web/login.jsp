
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <%
        /*//从cookie中拿出存储的值
        Cookie[] cookies = request.getCookies();
        String account = "";
        String password = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("acccoke")){
                account = cookie.getValue();
            }
            if(cookie.getName().equals("pwdcoke")){
                password = cookie.getValue();
            }
        }*/
        String account = (String) request.getAttribute("account");
        String password = (String) request.getAttribute("password");
    %>
    <form action="loginServlet" method="post">
        账号：
        <input type="text" name="num" value="<% out.print(account); %>"><br/>
        密码：
        <input type="password" name="password" value="<%out.print(password);%>"><br/>
        <input type="checkbox" name="ck" value="ck">记住密码<br/>
        <input type="submit" value="登录">
    </form>
</body>
</html>
