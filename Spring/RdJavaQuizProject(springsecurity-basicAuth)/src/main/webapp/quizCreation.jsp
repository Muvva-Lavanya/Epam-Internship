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
	<h1>Create Quiz</h1>
	<form action="createQuiz" method="POST">
		Enter Quiz Title:<input type="text" name="title" id="title"><br>
		<br> Enter Weightage:<input type="number" name="marks" id="marks"><br>
		<br> <input type="submit" value="Create">
		<table border="2" align="center">
			<tr>
				<th>SELECT</th>
				<th>ID</th>
				<th>TITLE</th>
				<th>OPTIONS</th>
				<th>DIFFICULTYLEVEL</th>
				<th>TAG</th>
				<th>ANSWER</th>
				<c:forEach items="${questionList}" var="q">
					<tr>
						<td><input type="checkbox" name="questionIdList"
							value="${q.id}"></td>
						<td><c:out value="${q.id}" /></td>
						<td><c:out value="${q.title}" /></td>
						<td><c:out value="${q.options}" /></td>
						<td><c:out value="${q.difficultyLevel}" /></td> 
						<td><c:out value="${q.tag}" /></td>
						<td><c:out value="${q.answer}" /></td>

					</tr>
				</c:forEach>
		</table>
	</form>
</body>
</html>