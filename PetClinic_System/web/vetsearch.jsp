<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/8
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>兽医信息查询</title>
    <link rel="stylesheet" href="allStyle.css" charset="UTF-8">
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
        <table>
            <form action="servlet.VetServlet?purpose=allVet" id="all" method="post"></form>
            <form action="vetAdd.jsp" id="addVet" method="post"></form>
            <form action="servlet.VetServlet?purpose=selectVet" method="post">
                <tr class="tr01">
                    <td>兽医名称</td>
                    <td>
                        <input type="text" name="vetname">
                    </td>
                </tr>
                <tr class="tr01">
                    <td>专业特长</td>
                    <td>
                        <input type="text" name="vetspecialist">
                    </td>
                </tr>
                <tr class="tr02">
                    <td><input type="submit" value="查询单个"/></td>
                    <td><input type="submit" form="all" value="显示全部"/></td>
                </tr>
                <tr class="tr02">
                    <td colspan="2"><input type="submit" form="addVet" value="添加兽医"/></td>
                </tr>
            </form>
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
