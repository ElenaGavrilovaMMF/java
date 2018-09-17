
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create Book</title>
    <style>
        .content {
            margin-top: 100px;
            margin-left: 200px;
        }
        .err{
            color: red;
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
<form action="${pageContext.request.contextPath}/createBook?user_id=${sessionScope.currentUser.id}" enctype="multipart/form-data" method="post" >
    <h3>Введите название книги:</h3>
    <input type="text" class="text" name="name" value="${requestScope.name}"/> <br>
    <h3>Определите тип книги:</h3>
    <select name="bookWhole" value="${requestScope.bookWhole}">
            <option value="BookWholeTrue">Загрузить целиком</option>
            <option value="BookWholeFalse">Книга в процессе</option>
    </select><br>
    <h3> Выберите жанр:</h3>
        <select name="genre" value="${requestScope.genre}">
        <c:forEach var="genre" items="${requestScope.genres}">
            <option value="${genre.id}">${genre.name}</option>
        </c:forEach>
    </select><br>
    <h3>Содержание:</h3>
    <input type="text" class="text" name="description" value="${requestScope.description}"/> <br>
    <h3>в формате .txt</h3>
    <input type="file" name="file" >
    <br>
    <input type="submit" class="submit" value="Загрузить"><br>

</form>


</div>

</body>
</html>
