<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Create Quiz</h1>
<form action="createQuiz" method="POST">
Enter Quiz Title:<input type="text" name="title" id="title"><br><br>
Enter Weightage:<input type="number" name="marks" id="marks"><br><br>
Enter Question Ids:<input type="text" name="questionIdList" id="questionIdList"><br><br>
<input type="submit" value="Create">
</form>
</body>
</html>