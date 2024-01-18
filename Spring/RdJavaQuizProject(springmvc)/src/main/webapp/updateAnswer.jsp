<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Update Answer</h1>
<form method="post" action="updateAnswer">
Question id:<input type="number" id="id" name="id" value="${id } " required="required"><br><br>
Enter Answer:<input type="text" id="answer" name="answer" required="required"><br><br>
<input type="submit" value="update">
</form>
</body>
</html>