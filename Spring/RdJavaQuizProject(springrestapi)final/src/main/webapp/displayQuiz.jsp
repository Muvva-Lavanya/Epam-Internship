<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h4>${message}</h4>
<h1>Questions List</h1>
	<table border=1>
	<caption></caption>
		<tr>
			<th>ID</th>
			<th>NUMBER OF QUESTIONS</th>
			<th>TITLE</th>
			<th>TOTAL MARKS</th>
			<th>QUESTIONS LIST </th>
		</tr>
		<c:forEach items="${quiz}" var="quiz">
			<tr>
				<td> <c:out value="${quiz.id}"/></td>
				<td>  <c:out value="${quiz.numberOfQuestions}"/></td>
				<td>  <c:out value="${quiz.title}"/></td>
					<td>  <c:out value="${quiz.totalMarks}"/></td>
				<td>  <c:out value="${quiz.question}"/></td>
			</tr>
		</c:forEach>
	</table>
	<br><a href="quizlibrarymenu.jsp">Click here to go to Quiz Library menu</a>
</body>
</html>