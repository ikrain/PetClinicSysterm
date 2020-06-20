<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/10
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>宠物信息展示</title>
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
            <form action="servlet.PetServlet?purpose=send" id="change" method="post">
            <c:forEach items="${petMess}" var="mess">
                <tr class="res-tr">
                    <td>宠物名称</td>
                    <td><input type="text" name="name" value="${mess.name}" readonly></td>
                </tr>
                <tr class="res-tr">
                    <td>类型</td>
                    <td><input type="text" name="type" value="${mess.type}" readonly></td>
                </tr>
                <tr class="res-tr">
                    <td>出生日期</td>
                    <td><input type="text" name="birthdate" value="${mess.birthDate}" readonly></td>
                </tr>
                <tr class="res-tr">
                    <td>所有人名称</td>
                    <td><input type="text" name="ownername" value="${mess.ownerName}" readonly></td>
                </tr>
            </form>
            <tr class="tr04">
                <form action="servlet.PetVisitServlet?purpose=visitList" id="visit" method="post">
                    <td>
                        <input type="hidden" form="visit" name="pet_id" value="${mess.id}">
                        <input type="hidden" name="name" value="${mess.name}">
                        <input type="hidden" name="type" value="${mess.type}">
                        <input type="hidden" name="ownerName" value="${mess.ownerName}">
                        <input type="submit" form="visit" value="阅览病例">
                    </td>
                </form>
                <td>
                    <form action="servlet.PetVisitServlet?purpose=editor" id="changeVisit" method="post">
                        <input type="hidden" form="changeVisit" name="pet_name" value="${mess.name}">
                        <input type="submit" form="changeVisit" value="增加病例">
                    </form>
                </td>
            </tr>
            <tr class="tr03">
                <td colspan="2"><input type="submit" form="change" value="修改信息"></td>
            </tr>
            </c:forEach>
            <c:forEach items="${newPetMess}" var="mess">
                <form action="servlet.PetServlet?purpose=send" id="change2" method="post">
                <tr class="res-tr">
                    <td>宠物名称</td>
                    <td><input type="text" name="name" value="${mess.name}" readonly></td>
                </tr>
                <tr class="res-tr">
                    <td>类型</td>
                    <td><input type="text" name="type" value="${mess.type}" readonly></td>
                </tr>
                <tr class="res-tr">
                    <td>出生日期</td>
                    <td><input type="text" name="birthdate" value="${mess.birthDate}" readonly></td>
                </tr>
                <tr class="res-tr">
                    <td>所有人名称</td>
                    <td><input type="text" name="ownername" value="${mess.ownerName}" readonly></td>
                </tr>
                </form>
                <tr class="tr04">
                    <form action="servlet.PetVisitServlet?purpose=visitList" id="visit2" method="post">
                        <td>
                            <input type="hidden" form="visit2" name="pet_id" value="${mess.id}">
                            <input type="hidden" name="name" value="${mess.name}">
                            <input type="hidden" name="type" value="${mess.type}">
                            <input type="hidden" name="ownerName" value="${mess.ownerName}">
                            <input type="submit" form="visit2" value="阅览病例">
                        </td>
                    </form>
                    <td>
                        <form action="servlet.PetVisitServlet?purpose=editor" id="changeVisit2" method="post">
                            <input type="hidden" form="changeVisit2" name="pet_name" value="${mess.name}">
                            <input type="submit" form="changeVisit2" value="增加病例">
                        </form>
                    </td>
                </tr>
                <tr class="tr03">
                    <td colspan="2"><input type="submit" form="change2" value="修改信息"></td>
                </tr>
            </c:forEach>
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
