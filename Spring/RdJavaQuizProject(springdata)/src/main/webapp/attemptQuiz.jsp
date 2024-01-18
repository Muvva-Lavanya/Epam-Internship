<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Take Quiz</h1>
<form action="countMarks" method="post">
<input type="hidden" name="quizId" value="${quizId}" />
<table border=1 align="center">
<tr>
<th>ID</th>
<th>QUESTION TITLE</th>
<th>ANSWER</th>
</tr>
<c:forEach items="${questionsList}" var="questionList">
<tr>
<td>${questionList.id}</td>
<td> ${questionList.title}</td>
<td>
<select id="users" name="options" id="options" required="required">
<c:forEach items="${questionList.options}" var="options">
<option value="${options}">${options}</option>	
</c:forEach>
</select>
</td>
</tr>
</c:forEach>
</table>
<br><br><input type="submit" value="submit">
</form>
</body>
</html>