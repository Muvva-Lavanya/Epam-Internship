<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Questions</title>
</head>
<body align="center">
	<h5>${message}</h5>
	<table border=1 align="center">
	<caption></caption>
		<tr>
			<th>ID</th>
			<th>TITLE</th>
			<th>OPTIONS</th>
			<th>DIFFICULTY LEVEL</th>
			<th>TAG </th>
			<th>ANSWER</th>
			<th>ACTION1</th>
			<th>ACTION2</th>
		</tr>
		<c:forEach items="${questionsList}" var="question">
			<tr>
				<td> <c:out value="${question.id}"/></td>
				<td>  <c:out value="${question.title}"/></td>
				<td>  <c:out value="${question.options}"/></td>
					<td>  <c:out value="${question.difficultyLevel}"/></td>
				<td>  <c:out value="${question.tag}"/></td>
				<td>  <c:out value="${question.answer}"/></td>
				<td><a href="deleteQuestion?id=${question.id}">Delete</a></td>
				<td><a href="viewQuestion?id=${question.id}">Update</a></td>
			</tr>
		</c:forEach>
	</table>
	<br><a href="questionCreation.jsp">Create Question</a>
	<br><a href="adminMenu.jsp">Click here to go to Admin menu</a>
</body>
</html>