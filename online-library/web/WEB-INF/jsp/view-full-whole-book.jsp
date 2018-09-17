<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Full book</title>
    <style>
        body{
            font-size: medium;
        }
        .content {
            margin-top: 100px;
            margin-left: 0px;
        }
.bbbb{
    font-size: medium;
    margin-left: 100px;
    width: 700px;
}
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="style-pr.jsp"%>
<div class="content">
<div class="bl">
    <h2>${requestScope.book.name}</h2>
    <strong>Автор:</strong> ${requestScope.book.nameUser}<br>
    <strong>Жанр:</strong> ${requestScope.book.genreName}<br>
    <strong>Содержание:</strong> ${requestScope.book.description}<br>
    <h6>${requestScope.book.date}</h6>
</div>
    <form action="${pageContext.request.contextPath}/bookWholeFullInfo?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}" method="post">
        Выберите страницу: <input type="number" name="numberPage" value="${requestScope.numberPage}" min="1" max="${requestScope.allpages}"> до ${requestScope.allpages}
        <input type="submit" value="Перейти">
    </form>
    <div class="bbbb">${requestScope.page}</div><br>
<form action="${pageContext.request.contextPath}/bookWholeFullInfo?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}" method="post">
    Выберите страницу: <input type="number" name="numberPage" value="${requestScope.numberPage}" min="1" max="${requestScope.allpages}"> до ${requestScope.allpages}
    <input type="submit" value="Перейти">
</form>
</body>
</html>
