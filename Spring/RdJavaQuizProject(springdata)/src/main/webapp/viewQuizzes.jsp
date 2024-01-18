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
			<th>QUESTIONS LIST</th>
		</tr>
		<c:forEach items="${quizList}" var="quiz">
			<tr>
				<td> <c:out value="${quiz.id}"/></td>
				<td>  <c:out value="${quiz.title}"/></td>
					<td>  <c:out value="${quiz.marks}"/></td>
				<td>  <c:out value="${quiz.question}"/></td>
			</tr>
		</c:forEach>
	</table>
	<br><a href="quizLibraryMenu.jsp">Click here to go to Quiz Library menu</a>
</body>
</html>