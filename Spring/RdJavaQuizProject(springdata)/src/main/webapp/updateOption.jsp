<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
<h1 >Update Option</h1>
<form method="post" action="updateOption" >
Question id:<input type="number" id="id" name="id" value="${id } " required="required"><br><br>
Enter Option Index:<input type="number" id="index" name="index" required="required"><br><br>
Enter value:<input type="text" id="value" name="value"><br><br>
<input type="submit" value="update">
</form>
</body>
</html>