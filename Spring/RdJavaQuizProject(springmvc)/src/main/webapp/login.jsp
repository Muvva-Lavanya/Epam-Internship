<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body align="center">
	<h1>LOGIN PAGE</h1><br><br><br>
	<form method="post" action="login">
		Choose UserType:<select id="users" name="userType" id="userType" required="required">
		<option value="admin">Admin</option>	
		<option value="user">User</option>
		</select><br><br>
		Enter username: <input type="text" id="username" name="username" required="required"><br><br>
		Enter password: <input type="password" id="password" name="password" required="required"><br><br> 
		<input type="submit" value="Login">
	</form>
</body>
</html>