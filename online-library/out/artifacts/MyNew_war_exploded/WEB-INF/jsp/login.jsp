<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-color: #fff;
            font-family: arial;
        }

        .inputL {
            width: 150px;
            height: 20px;
            font-size: 10px;
        }

        p {
            font-size: 10px;
        }

        .login {
            position: fixed;
            margin-top: 2%;
            margin-left: 70px;
            left: 55%;
            width: 50%;
            font-size: 10px;
        }

        .err {
            color: red;
        }

        .bl {
            position: relative;
            width: 50%;
            padding: 1em;
            margin: 2em 10px 4em;
            background: #fff;
            -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            -webkit-box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            -moz-box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
            box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;
        }

        .bl:before,
        .bl:after {
            content: "";
            position: absolute;
            z-index: -2;
        }

        .bl p {
            font-size: 16px;
            font-weight: bold;
        }

        .content {
            margin-top: 50px;
        }


    </style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="content">
    <div class="login">
        <h1><fmt:message key="text.welcome"/></h1>
        <h4>${requestScope.registr}</h4>
        <c:if test="${empty sessionScope.currentUser}">
            <form action="${pageContext.request.contextPath}/login?user_id=${sessionScope.currentUser.id}"
                  method="post">
                <input class="inputL" type="text" name="login" placeholder="Логин"><br>
                <input class="inputL" type="password" name="password" placeholder="Пароль"><br>
                <input class="inputB" type="submit" class="submit" value="<fmt:message key="login.username"/> "/>
            </form>
            <form action="${pageContext.request.contextPath}/createUser">
                <input class="inputB" type="submit" class="submit" value="<fmt:message key="login.registration"/>"/>
            </form>
        </c:if>

        <c:if test="${not empty requestScope.errors}">
            <c:if test="${sessionScope.language eq 'en_US'}">
        <div class="err">
        <fmt:message key="text.error"/>
        </div>
            </c:if>
        <c:if test="${sessionScope.language eq 'ru_RU'}">
        <div class="err">
                <c:forEach var="error" items="${requestScope.errors}">
                    <label> <span>${error}</span></label>
                </c:forEach>
            </div>
        </c:if>
            <c:if test="${!(sessionScope.language eq 'en_US') and !(sessionScope.language eq 'ru_RU')}">
                <div class="err">
                    <fmt:message key="text.error"/>
                </div>
            </c:if>
        </c:if>

    </div>
    <form action="${pageContext.request.contextPath}/page?user_id=${sessionScope.currentUser.id}">
        <fmt:message key="text.page"/> <input type="number" name="numberPage" min="1" max="${requestScope.allpages}"
                                              value=1 width="20px">
        <fmt:message key="text.pageTo"/> ${requestScope.allpages}
        <input type="submit" value="<fmt:message key="buttom.ok"/>" width="20px">

    </form>
    <c:forEach items="${requestScope.page}" var="page">
        <div class="bl">
            <h3><a href="${pageContext.request.contextPath}/bookFullInfo?id_user=${requestScope.id_user}&id=${page.id}"
                   link="black" vlink="black" alink="black">${page.name}</a></h3><br>
            <strong><fmt:message key="book.genre"/>:</strong> ${page.genreName}<br>
            <strong><fmt:message key="book.author"/>:</strong> ${page.nameUser}<br>
            <strong><fmt:message key="book.content"/>:</strong> ${page.description}<br>
            <h6>${page.date}</h6><br>
        </div>
    </c:forEach>
    <br>

</div>
</body>
</html>
