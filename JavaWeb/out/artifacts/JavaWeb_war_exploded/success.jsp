
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function test(){
            var res = confirm("您确定要退出吗？")
            if (res){
                location.replace("quitServlet")
            }
        }
    </script>
</head>
<body>
登录成功！
    <input type="button" name="quit" value="退出登录" onclick="test()">

</body>
</html>
