<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/9
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宠物信息查询结果</title>
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
        <table class="res-table" style="margin: auto">
            <tr class="res-tr">
                <td>宠物名称</td>
                <td>所有人名称</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${petList}" var="mess">
                <tr class="res-tr">
                    <td><a href="servlet.PetServlet?petname=${mess.name}&purpose=see">${mess.name}</a></td>
                    <td><a href="servlet.PetOwnerServlet?ownername=${mess.ownerName}&purpose=see">${mess.ownerName}</a></td>
                    <td>
                        <form action="servlet.PetServlet?purpose=delete" method="post">
                            <input type="hidden" name="petName" value="${mess.name}">
                            <input type="hidden" name="ownerName" value="${mess.ownerName}">
                            <input type="submit" value="删除">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr class="tr02">
                <form action="petsearch.jsp">
                    <td colspan="3" style="padding-top: 40px"><input type="submit" value="重新查询"></td>
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
