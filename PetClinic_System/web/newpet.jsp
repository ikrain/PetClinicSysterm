<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/11
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>增加新宠物</title>
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
            <form action="servlet.PetServlet?purpose=insert" method="post">
                <tr class="res-tr">
                    <td>所有人名称</td>
                    <td>
                        <select name="ownerSt">
                            <c:forEach items="${ownerSelect}" var="owner">
                                <option value="${owner.name}">${owner.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="res-tr">
                    <td>宠物名称</td>
                    <td><input type="text" name="petname"></td>
                </tr>
                <tr class="res-tr">
                    <td>类型</td>
                    <td>
                        <select name="typeSt">
                            <c:forEach items="${typeSelect}" var="ts">
                                <option value="${ts.type}">${ts.type}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="res-tr">
                    <td>出生日期</td>
                    <td><input type="date" name="birthdate"></td>
                </tr>
                <tr class="tr03">
                    <td>
                        <input type="submit" value="添加">
            </form>
            </td>
            <td>
                <form action="petsearch.jsp" method="post">
                    <input type="submit" value="返回">
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
