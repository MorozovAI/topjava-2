<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Meal</title>
</head>
<h2>${param.action == 'insert' ? 'Create meal' : 'Edit meal'}</h2>
<body>
<form method="POST" action='meals' name="frmAddMeal">
    ID : <input type="hidden" readonly="readonly" name="id"
                value="${meal.id}"/><br/>
    DateTime : <input
        type="datetime-local" name="dateTime"
        value="${meal.dateTime}"/> <br/>
    Description : <input
        type="text" name="description"
        value="${meal.description}"/><br/>
    Calories : <input
        type="number" name="calories"
        value="${meal.calories}"/> <br/>
    <input type="submit" value="Save"/>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>