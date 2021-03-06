<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/20
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加兽医</title>
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
        <form action="servlet.VetServlet?purpose=addVet" method="post">
            <tr class="res-tr">
                <td>兽医名称</td>
                <td><input type="text" name="vetName"></td>
            </tr>
            <tr class="res-tr">
                <td>专业特长</td>
                <td>
                    <%--<select name="vetSpec" id="">--%>
                        <%--<c:forEach items="${}" var="spec">--%>
                            <%--<option value="${spec.}"></option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                    <input type="text" name="vetSpec"></td>
            </tr>
            <tr class="tr03">
                <td>
                    <input type="submit" value="增加"/>
        </form>
        </td>
        <td>
            <form action="vetsearch.jsp" method="post">
                <input type="submit" value="返回"/>
            </form>
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
