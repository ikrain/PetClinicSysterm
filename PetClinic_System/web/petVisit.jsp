<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/11
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宠物病例</title>
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
            <form action="" method="post">
                <c:forEach items="${visitOwner}" var="mess">
                    <tr class="res-tr">
                        <td>宠物名称</td>
                        <td><input type="text" name="name" value="${mess.name}" disabled></td>
                    </tr>
                    <tr class="res-tr">
                        <td>类型</td>
                        <td><input type="text" name="type" value="${mess.type}" disabled></td>
                    </tr>
                    <tr class="res-tr">
                        <td>所有人名称</td>
                        <td><input type="text" name="ownerName" value="${mess.ownerName}" disabled></td>
                    </tr>
                </c:forEach>
            </form>
        </table>
        <table class="res-table">
            <tr class="res-tr">
                <td>诊断时间</td>
                <td>备注</td>
            </tr>
            <c:forEach items="${listVisit}" var="visit">
            <tr class="res-tr">
                <td>${visit.visitDate}</td>
                <td>${visit.description}</td>
            </tr>
            </c:forEach>
            <c:forEach items="${listFb}" var="info">
            <tr class="res-mess">
                <td colspan="2">${info.mess}</td>
            </tr>
            </c:forEach>
            <tr class="tr03">
                <form action="petsearch.jsp" method="post">
                    <td colspan="2"><input type="submit" value="返回"></td>
                </form>
            </tr>
        </table>
    </div>
    <div class="login-bottom">©郑州轻工业大学版权所有</div>
</body>
</html>
