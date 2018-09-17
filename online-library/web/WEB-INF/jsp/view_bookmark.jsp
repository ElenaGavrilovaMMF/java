<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mark</title>
    <style>
    .content {
    margin-top: 100px;
    margin-left: 200px;
    }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="style-pr.jsp"%>
<div class="content">
<c:if test="${not empty requestScope.books}">
    <c:forEach items="${requestScope.books}" var="book">
        <div class="bl">
            <h3><a href="${pageContext.request.contextPath}/bookFullInfo?user_id=${requestScope.user_id}&book_id=${book.id}">${book.name}</a></h3>
            <strong>Жанр:</strong> ${book.genreName}<br>
            <strong>Автор:</strong> ${book.nameUser}<br>
            <strong>Содержание:</strong> ${book.description}<br>
            <form action="${pageContext.request.contextPath}/deleteBookmark?user_id=${sessionScope.currentUser.id}&book_id=${book.id}"  method="post" >
                <input type="submit" value="Удалить">
            </form>
            <h6>${book.date}</h6>
        </div>
    </c:forEach>
</c:if>
<c:if test="${empty requestScope.books}">
    <h1>Заметок нет.</h1>
</c:if>
</div>
</body>
</html>
