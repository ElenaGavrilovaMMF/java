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
<%@include file="header.jsp"%>
<%@include file="style-pr.jsp"%>

<div class="content">
<h3>Мои книги:</h3>
    <form action="${pageContext.request.contextPath}/createBook?user_id=${sessionScope.currentUser.id}">
        <input type="submit" class="submit"  value="Создать книгу">
    </form>
<c:forEach items="${requestScope.books}" var="book">
    <div class="bl">
        <h3>  <a href="${pageContext.request.contextPath}/bookFullInfo?user_id=${sessionScope.currentUser.id}&book_id=${book.id}">${book.name}</a></h3><br>
    <strong>Жанр:</strong> ${book.genreName}<br>
        <strong>Автор:</strong> ${book.nameUser}<br>
        <strong>Содержание:</strong> ${book.description}<br>
        <h6>${book.date}</h6><br>
    </div>
</c:forEach>
</div>
</body>
</html>
