<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    body {
        font: 14px 'Arial';
        margin: 0;
        padding: 0;
    }

    ul {
        position: absolute;
        top: 10%;
        left: 50%;
        transform: translate(-50%, -50%);
        margin: 0;
        padding: 0;
        display: flex;
    }
    ul li {
        list-style: none;
    }
    ul li a {
        position: ;
        display: block;
        margin: 0 10px;
        padding: 5px 10px;
        color: rgba(0, 0, 0, 0.5);
        font-size: 14px;
        text-decoration: none;
        text-transform: uppercase;
        transition: 0.5s;
        overflow: hidden;
    }
    ul li a::before {
        content: '';
        position: absolute;
        top: calc(50% - 2px);
        left: -100%;
        width: 100%;
        height: 4px;
        background: rgba(0, 0, 0, 0.5);
        transition: 0.5s;
    }
    ul li a:hover {
        color: #fff;
    }
    ul li a:hover::before {
        animation: animate .5s linear forwards;
    }

    @keyframes animate {
        0% {
            top: calc(50% - 2px);
            left: -100%;
            height: 4px;
            z-index: 1;
        }
        50% {
            top: calc(50% - 2px);
            left: 0;
            height: 4px;
            z-index: 1;
        }
        100% {
            top: 0;
            left: 0;
            height: 100%;
            z-index: -1;
        }
    }
    .submit {
        cursor: pointer;
        border: 1px solid #cecece;
        background: #f6f6f6;
        box-shadow: inset 0px 20px 20px #ffffff;
        border-radius: 8px;
        padding: 8px 14px;
        width: 120px;
    }
    .submit:hover {
        box-shadow: inset 0px -20px 20px #ffffff;
    }
    .submit:active {
        margin-top: 1px;
        margin-bottom: -1px;
        zoom: 1;
    }
    input {
        font-size: 13px;
        padding: 6px 0 4px 10px;
        border: 1px solid #cecece;
        background: #F6F6f6;
        border-radius: 8px;
    }
    input {
        padding-bottom: 5px\0;
    }
    textarea {
        /* = Убираем скролл */
        overflow: auto;

        /* = Убираем увеличение */
        resize: none;
        width: 300px;
        height: 50px;

        /* = Добавим фон, рамку, отступ*/
        background: #f6f6f6;
        border: 1px solid #cecece;
        border-radius: 8px 0 0 0;
        padding: 8px 0 8px 10px;
    }

</style>
<form action="${pageContext.request.contextPath}/language">
    <select class="btn btn-default dropdown-toggle" name="lang" onchange="submit()">
        <option value="en_US" ${sessionScope.language eq 'en_US' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${sessionScope.language eq 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>

<c:if test="${not empty sessionScope.currentUser}">

<ul>
    <li><a href="${pageContext.request.contextPath}/booksList?user_id=${sessionScope.currentUser.id}">Мой профиль</a></li>
    <li><a href="${pageContext.request.contextPath}/bookScearch?user_id=${sessionScope.currentUser.id}">Найти книгу</a></li>
    <li><a href="${pageContext.request.contextPath}/bookmarkFullInfo?user_id=${sessionScope.currentUser.id}">Книги в закладках</a></li>
    <c:if test="${sessionScope.currentUser.role == 'Администратор' or sessionScope.currentUser.role == 'Редактор'}">
        <li>
            <a href="${pageContext.request.contextPath}/adminServlet?user_id=${sessionScope.currentUser.id}">Дополнительные функии</a>
        </li>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/logout">Выход</a></li><br>
</ul>
</c:if>



