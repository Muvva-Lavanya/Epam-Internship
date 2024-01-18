<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Questions</title>
</head>
<body align="center">
	<h1>Questions List</h1>
	<table border=1 align="center">
	<caption></caption>
		<tr>
			<th>ID</th>
			<th>TITLE</th>
			<th>OPTIONS</th>
			<th>DIFFICULTY LEVEL</th>
			<th>TAG </th>
			<th>ANSWER</th>
		</tr>
		<c:forEach items="${questionList}" var="question">
			<tr>
				<td> <c:out value="${question.id}"/></td>
				<td>  <c:out value="${question.title}"/></td>
				<td>  <c:out value="${question.options}"/></td>
					<td>  <c:out value="${question.difficultyLevel}"/></td>
				<td>  <c:out value="${question.tag}"/></td>
				<td>  <c:out value="${question.answer}"/></td>
			</tr>
		</c:forEach>
	</table>
	<br><a href="viewAllQuizzes">Click here to go to QuizLibrary menu</a>
</body>
</html>