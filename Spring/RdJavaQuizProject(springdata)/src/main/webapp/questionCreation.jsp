<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1 >Create Question</h1>
	<form method="post" action="createQuestion">
	Enter Title : <input type="text" name="title" id="title" required="required"><br><br>
	Enter Options: <input type="text" name="options" id="options" required="required"><br><br>
	Enter DifficultyLevel:<select id="question" name="difficultyLevel" id="difficultyLevel" required="required">
		<option value="Easy">Easy</option>	
		<option value="Medium">Medium</option>
		<option value="Hard">Hard</option>
		</select><br><br>
	Enter tag : <input type="text" name="tag" id="tag" required="required"><br><br>
	Enter Answer: <input type="text" name="answer" id="answer" required="required"><br><br>
	<input type="submit" value="CREATE">
	</form>
</body>
</html>