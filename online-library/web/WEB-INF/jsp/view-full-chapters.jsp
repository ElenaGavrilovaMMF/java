<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chapters</title>
    <style>
        body {
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
<%@include file="style-pr.jsp" %>
<div class="content">
    <a href="${pageContext.request.contextPath}/chapterDownload?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}&chapter_id=${requestScope.chapters.id}">Скачать</a>
    <div class="bl">
        <h3> ${requestScope.chapters.name}</h3>
    </div>
    <div class="bbbb">${requestScope.contents}</div><br>
    <h6>${requestScope.chapters.date}</h6>

    <form action="${pageContext.request.contextPath}/chapterDelete?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}&chapter_id=${requestScope.chapters.id}"
          method="post">
        <input type="submit" value="Удалить">
    </form>
</div>
</body>
</html>
