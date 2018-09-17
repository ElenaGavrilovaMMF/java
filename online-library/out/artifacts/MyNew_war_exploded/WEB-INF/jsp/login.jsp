<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-color: #fff;
            font-family:arial;
        }

        .inputL {
            width: 150px;
            height: 20px;
            font-size: 10px;
        }

        .inputB {
            width: 60px;
            font-size: 10px;
            height: 20px;
        }

        p {
            font-size: 10px;
        }

        .login {
            position: fixed;
            margin-left: 100px;
            left: 55%;
            top: 2%;
            width: 50%;
            font-size: 10px;
        }
        .err{
            color: red;
        }

        .bl {
            position:relative;
            width:50%;
            padding:1em;
            margin:2em 10px 4em;
            background:#fff;
            -webkit-box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            -moz-box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            box-shadow:0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            -webkit-box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            -moz-box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
        }

        .bl:before,
        .bl:after {
            content:"";
            position:absolute;
            z-index:-2;
        }

        .bl p {
            font-size:16px;
            font-weight:bold;
        }




    </style>
</head>
<body>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language:'en_US'}"/>
<fmt:bundle basename="login"/>
<fmt:message key="login.message"/>
<a href="${pageContext.request.contextPath}/language?lang=ru_RU">Русский</a>
<a href="${pageContext.request.contextPath}/language?lang=en_US">English</a><br>


<div class="login">
    <h1>Добро пожаловать!</h1>
    <%@include file="header.jsp" %><br>
    <c:if test="${empty sessionScope.currentUser}">
    <form action="${pageContext.request.contextPath}/login?user_id=${sessionScope.currentUser.id}" method="post">
        <input class="inputL" type="email" name="login" placeholder="Логин"><br>
        <input class="inputL" type="password" name="password" placeholder="Пароль"><br>
        <input class="inputB" type="submit" class="submit" value="<fmt:message key="login.message"/>">
        <form action="${pageContext.request.contextPath}/createUser">
            <input class="inputB" type="submit" class="submit" value="<fmt:message key="login.message"/>">
        </form>
        </c:if>

    </form>
    <c:if test="${not empty requestScope.errors}">
        <div class="err">
            <c:forEach var="error" items="${requestScope.errors}">
                <label> <span>${error}</span></label>
            </c:forEach>
        </div>
    </c:if>
</div>
<form action="${pageContext.request.contextPath}/page">
    Выберите страницу: <input type="number" name="numberPage" min="1" max="${requestScope.allpages}" value=1 width="20px">
    до ${requestScope.allpages}
    <input type="submit" value="Перейти" width="20px">

</form>
<c:forEach items="${requestScope.page}" var="page">
    <div class="bl">
        <h3><a href="${pageContext.request.contextPath}/bookFullInfo?id_user=${requestScope.id_user}&id=${page.id}"
               link="black" vlink="black" alink="black">${page.name}</a></h3><br>
        <strong>Жанр:</strong> ${page.genreName}<br>
        <strong>Автор:</strong> ${page.nameUser}<br>
        <strong>Содержание:</strong> ${page.description}<br>
        <h6>${page.date}</h6><br>
    </div>
</c:forEach>
<br>


</body>
</html>
