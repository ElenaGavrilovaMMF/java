
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Search Book</title>
    <style>
    .content {
    margin-top: 100px;
    margin-left: 0px;
    }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="style-pr.jsp"%>
<div class="content">
<form action="${pageContext.request.contextPath}/bookScearch?user_id=${sessionScope.currentUser.id}" method="post">
    <strong>Поиск:</strong>
    <input type="text" name="name" value="${requestScope.name}"/> <br>
    <strong>По жанру:</strong>
    <select value="${requestScope.genre}" name="genre">
        <option value="0">Выбрать</option>
        <c:forEach var="genre" items="${requestScope.genres}" >
            <option value="${genre.id}">${genre.name}</option>
        </c:forEach>
    </select><br>
<input type="submit" value="Поиск">
</form>
<c:if test="${not empty requestScope.books}">
    <c:forEach items="${requestScope.books}" var="book">
    <div class="bl">
    <h3><a href="${pageContext.request.contextPath}/bookFullInfo?user_id=${requestScope.user_id}&book_id=${book.id}">${book.name}</a></h3>
        <strong>Жанр:</strong> ${book.genreName}<br>
        <strong>Автор:</strong> ${book.nameUser}<br>
        <strong>Содержание:</strong> ${book.description}<br>
        <h6>${book.date}</h6>
        <br>
    </div>
    </c:forEach>
</c:if>
<c:if test="${empty requestScope.books}">
   <h1> Ничего не найдено!</h1>
</c:if>
</div>
</body>
</html>
