<%--
  Created by IntelliJ IDEA.
  User: huawei
  Date: 2021/3/11
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程信息</title>
    <script src="${path}/js/jquery-3.5.1.js" type="text/javascript"></script>
    <script charset="UTF-8">
        //全选，变色方法
        $(function () {
            //隔行变色
            $("tr:even").toggleClass("d");
            $("tr:odd").toggleClass("s");
            //全选
            $("#allchoose").click(function () {
                var check = $(":checkbox[name='id']");
                check.prop("checked", this.checked);
                $(this).parents("tr").toggleClass("xz");
                if ($(this).prop("checked")){
                    check.parents("tr").addClass("xz");
                }else{
                    check.parents("tr").removeClass("xz");
                }
            });
            //单行选中高亮
            $(":checkbox[name='id']:not(:checked)").click(function () {
                $(this).parents("tr").toggleClass("xz");
            });
        });

        //删除单个课程
        function deleteCourse(id){
            if (confirm("您确定要删除吗")){
                location.assign("course?mark=deleteCourse&id="+id);
            }
        }
        //批量删除课程
        function delBatchcourse(){
            //验证至少选中一个
            var ckobj = $("input[name='id']:checked");
            if (ckobj.length == 0){
                alert("请至少选择一个课程!")
            }else{
                //获得选中的多个复选框学生的学号
                var ids = "";
                if (confirm("您确定要删除吗")){
                    for (i=0;i<ckobj.length;i++){
                        ids += ckobj[i].value;
                        if (i !== ckobj.length-1){
                            ids += ",";
                        }
                    }
                    location.assign("course?mark=deleteCourse&id="+ids);
                }
            }
        }
        function updateCourse(id){
            location.assign("course?mark=updateCourse&id="+id);
        }
    </script>
    <style>
        .d{
            background-color: lightskyblue;
        }
        .s{
            background-color: dodgerblue;
        }
        .xz{
            background-color: greenyellow;
        }
        a{
            text-decoration: none;
        }
        #reset{
            color: gold;
        }
        #remove{
            color: darkred;
        }
        div{
            margin: auto;
        }
    </style>
</head>
<body>
<div>
    <a href="course?mark=toAddCourse" target="workspace"><input type="button" value="新增"></a>
    <input type="button" value="批量删除" onclick="delBatchcourse()">
    <table border="1" cellspacing="0" width="40%">
        <tr>
            <td>序号</td>
            <td><input type="checkbox" id="allchoose"></td>
            <td>课程号</td>
            <td>课程名</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${coursrList}" var="cour" varStatus="a">
            <tr>
                <td>${a.count}</td>
                <td><input type="checkbox" name="id" value="${cour.id}"></td>
                <td>${cour.id}</td>
                <td>${cour.name}</td>
                <td>
                    <a id="reset" href="course?mark=toUpdateCourse&id=${cour.id}" target="workspace" onclick="updateCourse(${cour.id})">修改</a>
                    <a id="remove" href="javaScript:void(0)" onclick="deleteCourse(${cour.id})">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>