<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.03.2018
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create Chapter</title>
    <style>
        .content {
            margin-top: 100px;
            margin-left: 200px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<div class="content">
    <c:if test="${not empty requestScope.errors}">
        <div class="err">
            <c:forEach var="error" items="${requestScope.errors}">
                <label> <span>${error}</span></label>
            </c:forEach>
        </div>
    </c:if>
<form action="${pageContext.request.contextPath}/createChapter?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}" method="post" >
    <h3>Введите название главы:</h3>
    <input type="text" name="name" value="${requestScope.name}"/>
    <h3>Введите главу:</h3>
    <textarea name="text" value="${requestScope.text}"></textarea>
    <%--<input type="text" name="text" value="${requestScope.text}"/> <br>--%>
    <input type="submit" value="Загрузить">
</form>
</div>

</body>
</html>
