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
<h1>Available Quizzes are</h1>
<table border="1" align="center">
<caption></caption>
<tr>
<th>ID</th>
<th>TITLE</th>
<th>MARKS</th>
<th>ACTION</th>
</tr>
<c:forEach items="${ quizList}" var="quizList">
<tr>
<td>${quizList.id}</td>
<td>${quizList.title }</td>
<td>${quizList.marks }</td>
<td><a href="attemptQuiz?quizId=${quizList.id}">Attempt Quiz</a>
</tr>
</c:forEach>
</table>
</body>
</html>