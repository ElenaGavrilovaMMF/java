<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.03.2018
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Books</title>
</head>
<body>

<%@include file="header.jsp"%>

<c:forEach items="${requestScope.books}" var="book">
    <a href="${pageContext.request.contextPath}/bookFullInfo?user_id=${sessionScope.currentUser.id}&id=${book.id}">${book.name}</a><br>

    Жанр: ${book.genreName}<br>
    Автор: ${book.nameUser}<br>
    Содержание: ${book.description}<br>
    ${book.date}<br>
    <br>
</c:forEach>
</body>
</html>
