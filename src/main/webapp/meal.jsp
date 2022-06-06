<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Add/update meal</title>
</head>
<body>
<form method="POST" action='meals' name="frmAddMeal">
    ID : <input type="text" readonly="readonly" name="id"
                value="${meal.id}"/><br/>
    DateTime : <input
        type="datetime-local" name="dateTime"
        value="${meal.dateTime}"/> <br/>
    Description : <input
        type="text" name="description"
        value="${meal.description}"/><br/>
    Calories : <input
        type=" text" name="calories"
        value="${meal.calories}"/> <br/>
    <input type="submit" value="Save"/>
</form>
</body>
</html>