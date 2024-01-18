<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Quiz</title>
</head>
<body align="center">
	<h1>Quiz List</h1>
	<h4>${message}</h4>
	<table border=1 align="center">
		<caption></caption>
		<tr>
			<th>ID</th>
			<th>TITLE</th>
			<th>MARKS</th>
			<th>QUESTION ID LIST</th>
			<th>ACTION1</th>
			<th>ACTION2</th>
			<th>ACTION3</th>
		</tr>
		<c:forEach items="${quizList}" var="quiz">
			<tr>
				<td><c:out value="${quiz.id}" /></td>
				<td><c:out value="${quiz.title}" /></td>
				<td><c:out value="${quiz.marks}" /></td>
				<td>
				<c:forEach items="${quiz.question }" var="q">
				<c:out value="${q.id}"/>
				</c:forEach>
				</td>
				<td><a href="deleteQuiz?id=${quiz.id }">DELETE</a></td>
				<td><a href="viewQuiz?id=${quiz.id }">UPDATE</a></td>
				<td><a href="quizQuestions?id=${quiz.id}"> VIEW</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="questionsList">Create Quiz</a>
	<br>
	<a href="adminMenu.jsp">Click here to go to Admin menu</a>
</body>
</html>