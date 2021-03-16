<%--
  Created by IntelliJ IDEA.
  User: huawei
  Date: 2021/3/11
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>添加课程</title>
    <style>
        div{
            margin-left: 400px;
            display: inline-block;
            background-color: dodgerblue;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<br><br>
<div>
    <form action="course" method="post">
        <table border="0" cellspacing="0">
            <input type="hidden" name="mark" value="add">
            <br>
            <tr>
                <td>&nbsp;课程名:</td>
                <td><label><input type="text" name="name"></label></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <label><input type="submit" value="保存"></label>
                    <label><input type="reset" value="重置"></label>
                    <a href="course?mark=list"><label><input type="button" value="返回"></label></a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>