<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/11
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>增加新的宠物问诊记录</title>
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
            <form action="servlet.PetVisitServlet?purpose=addVisit" id="change" method="post">
                <c:forEach items="${someMess}" var="ow">
                <tr class="res-tr">
                    <td>宠物名称</td>
                    <td>
                        <input type="text" name="petName" value="${ow.name}" readonly>
                    </td>
                </tr>
                <tr class="res-tr">
                    <td>所有人名称</td>
                    <td>
                        <input type="text" name="ownerName" value="${ow.ownerName}" readonly>
                    </td>
                </tr>
                <tr class="res-tr">
                    <td>类型</td>
                    <td>
                        <input type="text" name="type" value="${ow.type}" readonly>
                    </td>
                </tr>
                <tr class="res-tr">
                    <td>问诊日期</td>
                    <td><input type="date" name="birth_date"></td>
                </tr>
                <tr class="res-tr">
                    <td>描述</td>
                    <td><textarea name="description"></textarea></td>
                </tr>
                </c:forEach>
            </form>
            <tr class="tr03">
                <td>
                    <input type="submit" form="change" value="增加">
                </td>
                <td>
                    <form action="petsearch.jsp">
                        <input type="submit" value="返回">
                    </form>
                </td>
            </tr>
            <tr class="tr03">
                <td colspan="2">
                    <input type="reset" form="change" value="清空">
                </td>
            </tr>
            <c:forEach items="${listFb}" var="info">
                <tr class="res-mess">
                    <td colspan="2">${info.mess}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="login-bottom">©郑州轻工业大学版权所有</div>
</body>
</html>
