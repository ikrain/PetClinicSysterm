<%--
  Created by IntelliJ IDEA.
  User: cc
  Date: 2020/6/8
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>宠物诊所系统登录</title>
    <link rel="stylesheet" href="allStyle.css">
  </head>
  <body>
    <div class="login-top">
        <h1>宠物诊所应用</h1>
    </div>
    <div class="login-center">
        <table>
            <form action="servlet.EmpServlet" method="post">
                <tr class="tr01">
                    <td>用户名</td>
                    <td>
                        <input type="text" name="username">
                    </td>
                </tr>
                <tr class="tr01">
                    <td>密 &nbsp; 码</td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr class="tr02">
                    <td><input type="submit" value="登录"/></td>
                    <td><input type="reset" value="重置"/></td>
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
