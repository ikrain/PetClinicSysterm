<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.Vet" %>
<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/9
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>兽医特长查询结果</title>
    <link rel="stylesheet" href="allStyle.css">
</head>
<body>
    <div class="login-top">
        <a class="quit" href="servlet.QuitServlet">退出</a>
        <h1>宠物诊所应用</h1>
        <ul class="ul-sty">
            <li><a href="vetsearch.jsp">兽医</a></li>
            <li><a href="petsearch.jsp">宠物</a></li>
        </ul>
    </div>
    <div class="login-center">
        <table class="res-table">
            <tr class="res-tr">
                <td>兽医名称</td>
                <td>专业特长</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${list}" var="mess">
                <tr class="res-tr">
                    <td>${mess.vetName}</td>
                    <td>${mess.specName}</td>
                    <td>
                        <form action="servlet.VetServlet?purpose=delete" method="post">
                            <input type="hidden" name="vet" value="${mess.vetName}">
                            <input type="hidden" name="spec" value="${mess.specName}">
                            <input type="submit" value="删除">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr class="tr02">
                <form action="vetsearch.jsp">
                    <td colspan="3"><input type="submit" value="重新查询"></td>
                </form>
            </tr>
            <c:forEach items="${listFb}" var="info">
                <tr class="res-mess">
                    <td colspan="3">${info.mess}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="login-bottom">©郑州轻工业大学版权所有</div>
</body>
</html>
