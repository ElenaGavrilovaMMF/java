<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.03.2018
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Full book</title>
    <style>
        .content {
            margin-top: 100px;
            margin-left: 0px;
        }
        h1{
            color: red;
        }
        .comment{
            margin-left: 15px;
        }

    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="style-pr.jsp"%>
<div class="content">

<c:if test="${requestScope.ban !=null }">
    <h1>Книга в черном списке c ${requestScope.ban.date}!</h1>
</c:if>


<c:if test="${requestScope.ban !=null and (sessionScope.currentUser.role == 'Администратор' or sessionScope.currentUser.role == 'Редактор')}">

        <c:if test="${sessionScope.currentUser.role == 'Администратор' or sessionScope.currentUser.role == 'Редактор'}">

        <form action="${pageContext.request.contextPath}/banUn?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book.id}"
                  method="post">
                <input type="submit" value="Разблокировать книгу">
            </form>
        </c:if>
    </c:if>

    <div class="bl">
        <h2>${requestScope.book.name}</h2>
        <strong>Автор:</strong> ${requestScope.book.nameUser}<br>
        <strong>Жанр:</strong> ${requestScope.book.genreName}<br>
        <strong>Содержание:</strong> ${requestScope.book.description}<br>
       <h6>${requestScope.book.date}</h6>
    </div>
    <br>
<div class="comment">
    <c:if test="${requestScope.ban ==null}">
        <form action="${pageContext.request.contextPath}/createBookmark?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}"
              method="post" id="inp">
            <input type="submit" value="Добавить в избранное">
        </form>

        <c:if test="${sessionScope.currentUser.role == 'Администратор' or sessionScope.currentUser.role == 'Редактор'}">
            <form action="${pageContext.request.contextPath}/ban?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book.id}"
                  method="post">
                <input type="submit" value="Заблокировать книгу">
            </form>
        </c:if>
</div>

        <c:if test="${requestScope.book.bookWhole == 'TRUE' or requestScope.book.bookWhole == true}">
            <a href="${pageContext.request.contextPath}/bookDownload?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}">Скачать
                книгу </a></br>
            <a href="${pageContext.request.contextPath}/bookWholeFullInfo?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}">Читать
                книгу </a>
        </c:if>
        <c:if test="${requestScope.book.bookWhole == 'FALSE' or requestScope.book.bookWhole == false}">

            <c:forEach items="${requestScope.chapters}" var="chapter">
                <h4><a href="${pageContext.request.contextPath}/chapterFullInfo?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}&chapter_id=${chapter.id}">${chapter.name}</a></h4>
            </c:forEach>
            <c:if test="${sessionScope.currentUser.id == requestScope.book_user_id}">
                <h4><a href="${pageContext.request.contextPath}/createChapter?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}">Добавить
                главу</a></h4>
            </c:if>
        </c:if>
    </c:if>
</div>
        <h3>Добавить комментарий:</h3>
        <form action="${pageContext.request.contextPath}/createComment?user_id=${sessionScope.currentUser.id}&book_id=${requestScope.book_id}"
              method="post">
            <textarea name="comment"> </textarea><br>
            <input type="submit" value="Отправить">
        </form>
        <c:if test="${not empty requestScope.comments}">
            <c:forEach items="${requestScope.comments}" var="comment">
                <hr/>
                <div class="comment" >
                <h5>${comment.nameUser}</h5>
                ${comment.text}<br>
                <h6>${comment.date}</h6>
                <br>
                </div>
            </c:forEach>
        </c:if>



</div>
</body>
</html>
