<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Update Question</h1><form method="post" action="updateQuestion">
<input type="hidden" id="id" name="id" value="${question.id }"><br><br> 
Enter Question Title:<input type="text" id="title" name="title" value="${question.title}" required="required"><br><br>
Enter Question Options:<input type="text" id="options" name="options" value="${options}" required="required"><br><br>
Enter DifficultyLevel:<select name="difficultyLevel" id="difficultyLevel" value="${question.difficultyLevel }" required="required">
		<option value="Easy">Easy</option>	
		<option value="Medium">Medium</option>
		<option value="Hard">Hard</option>
		</select><br><br>
Enter Question Tag:<input type="text" id="tag" name="tag" value="${question.tag}" required="required"><br><br>
Enter Question Answer:<input type="text" id="answer" name="answer" value="${question.answer}" required="required"><br><br>
<input type="submit" value="update">
</form>
</body>
</html>