<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Update DifficultyLevel</h1>
<form method="post" action="updateDifficultyLevel">
Question id:<input type="number" id="id" name="id" value="${id }" required="required"><br><br>
Enter DifficultyLevel:<select id="question" name="difficultyLevel" id="difficultyLevel" required="required">
		<option value="Easy">Easy</option>	
		<option value="Medium">Medium</option>
		<option value="Hard">Hard</option>
		</select><br><br>
<input type="submit" value="update">
</form>
</body>
</html>