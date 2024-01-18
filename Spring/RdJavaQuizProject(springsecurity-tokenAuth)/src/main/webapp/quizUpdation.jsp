<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
	<h1>Update Quiz</h1>
	<form method="post" action="updateQuiz">
		<input type="hidden" id="id" name="id" value="${quiz.id}"
			required="required"><br> <br> Enter Quiz title:<input
			type="text" id="title" name="title" value="${quiz.title }"
			required="required"><br> <br> Enter Quiz marks:<input
			type="number" id="marks" name="marks" value="${quiz.marks }"
			required="required"><br> <br> <input type="submit"
			value="update">
		<table border="2" align="center">
			<tr>
				<th>SELECT</th>
				<th>ID</th>
				<th>TITLE</th>
				<th>OPTIONS</th>
				<th>DIFFICULTYLEVEL</th>
				<th>TAG</th>
				<th>ANSWER</th>
				<c:forEach items="${questionsList}" var="q">
					<tr>
						<td><input type="checkbox" name="questionIds" value="${q.id}"
						<c:if test="${quiz.question.contains(q)}">checked</c:if>>
						</td>
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