<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellspacing="0">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    <c:forEach var="meal" items="${mealsTo}">
        <tr style="color: ${meal.excess? 'green' : 'red'}">
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>


</table>
</body>
</html>