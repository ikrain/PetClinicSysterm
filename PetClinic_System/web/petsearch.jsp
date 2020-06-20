<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/9
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宠物信息查询</title>
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
        <table>
            <form action="servlet.PetServlet?purpose=allPet" id="all" method="post"></form>
            <form action="servlet.PetServlet?purpose=petow" method="post">
                <tr class="tr01">
                    <td>宠物名称</td>
                    <td>
                        <input type="text" name="petname">
                    </td>
                </tr>
                <tr class="tr01">
                    <td>所有人名称</td>
                    <td>
                        <input type="text" name="ownername">
                    </td>
                </tr>
                <tr class="tr02">
                    <td><input type="submit" value="查询单个"/></td>
                    <td><input type="submit" form="all" value="显示所有"/></td>
                </tr>
            </form>
                <tr class="tr03">
                    <td>
                        <form action="servlet.PetServlet?purpose=addpet" method="post">
                            <input type="submit" value="增加新宠物">
                        </form>
                    </td>
                    <td>
                        <form action="servlet.PetOwnerServlet?purpose=addperson" method="post">
                            <input type="submit" value="增加新所有人">
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
