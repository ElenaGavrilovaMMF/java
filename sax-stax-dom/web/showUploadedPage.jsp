<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Type of xml</title>
</head>
<body>
<div>
    <table border="1">
        <tr>
            <th>name</th>
            <th>soil</th>
            <th>origin</th>
            <th colspan="3">
                <table>
                    <tr>
                        <th colspan="3">visual</th>
                    </tr>
                    <tr>
                        <td>stem</td>
                        <td>sheet</td>
                        <td>size(sm)</td>
                    </tr>
                </table>
            </th>
            <th colspan="3">
                <table>
                    <tr>
                        <th colspan="3">growing</th>
                    </tr>
                    <tr>
                        <td>temperature(C)</td>
                        <td>lighting</td>
                        <td>irrigation(ml)</td>
                    </tr>
                </table>
            </th>
            <th>multiplying</th>
            <th>date of landing</th>
        </tr>

        <c:forEach items="${entities}" var="entity">
            <tr>
                <td>${entity.name}</td>
                <td>${entity.soil}</td>
                <td>${entity.origin}</td>
                <td>${entity.visual.stem}</td>
                <td>${entity.visual.sheet}</td>
                <td>${entity.visual.size}</td>
                <td>${entity.growing.temperature}</td>
                <td>${entity.growing.lighting}</td>
                <td>${entity.growing.irrigation}</td>
                <td>${entity.multiplying}</td>
                <td>${entity.date}</td>
            </tr>
        </c:forEach>

    </table>
</div>
<div>
    <table>
        <tr>
            <c:forEach items="${pages}" var="page">
                <td>
                    <form action="page" method="GET">
                        <input type="hidden" name="command" value="getPage">
                        <input type="hidden" name="number" value=${page}>
                        <input type="submit" value=${page}>
                    </form>
                </td>
            </c:forEach>
        </tr>
    </table>
</div>

</div>
</body>
</html>