<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Profile</title>

    <style>
        .content {
            margin-top: 100px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="style-pr.jsp" %>
<div class="content">
<h3>Добавить жанр </h3>
<form action="${pageContext.request.contextPath}/createGenre?user_id=${sessionScope.currentUser.id}" method="post">
    <input type="text" name="genre"><br>
    <input type="submit" name="Сохранить">
</form>
${requestScope.result}
<br>
<h3>Добавить редактора (Поиск по email)</h3>
<form action="${pageContext.request.contextPath}/userScearch?user_id=${sessionScope.currentUser.id}" method="post">
    <input type="text" name="user"><br>
    <input type="submit" name="Найти">
</form>
${requestScope.result2}
<c:if test="${requestScope.user != null}">
    ${requestScope.user.name}<br>
    ${requestScope.user.login}<br>
    ${requestScope.user.role.name}<br>
    <c:if test="${sessionScope.currentUser.role == 'Администратор'}">
        <form action="${pageContext.request.contextPath}/removeRole?user_id=${sessionScope.currentUser.id}&user=${requestScope.user.id}"
              method="post">
            <select name="role" value=" ${requestScope.user.role.id}">
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role.id}">${role.name}</option>
                </c:forEach>
            </select><br>
            <input type="submit" name="Именить">
        </form>
    </c:if>
    <br>
    ${requestScope.result3}<br>
</c:if>

</div>

</body>
</html>
