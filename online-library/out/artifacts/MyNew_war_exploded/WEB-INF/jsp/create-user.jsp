<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.03.2018
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create User</title>
    <style>
        .err{
            color: red;
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Регистрация пользователя</h1>
<c:if test="${not empty requestScope.errors}">
    <div class="err">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error}</span><br>
        </c:forEach>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/createUser" method="post">
    <h3>Введите email: </h3>
    <input type="text" name="login" value="${requestScope.login}"/>
    <h3>Введите пароль: </h3>
    <input type="password" name="passwordFirst" value="${requestScope.passwordFirst}"/>
    <h3>Повторите пароль: </h3>
    <input type="password" name="passwordSecond" value="${requestScope.passwordSecond}"/>
    <h3>Введите имя пользователя: </h3>
    <input type="text" name="name" value="${requestScope.name}"/> <br>

    <input type="submit" value="Зарегистрироваться">
</form>

</body>
</html>
