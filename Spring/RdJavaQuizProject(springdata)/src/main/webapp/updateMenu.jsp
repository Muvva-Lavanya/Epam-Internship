<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
  localStorage.setItem('id',${id});
</script>
</head>
<body align="center">
<h1>Update Menu</h1>
<a href="updateTitle.jsp ">1.Update Title</a><br><br>
<a href="updateOption.jsp">2.Update Option</a><br><br>
<a href="updateDifficultyLevel.jsp">3.Update DifficultyLevel</a><br><br>
<a href="updateTag.jsp">4.Update Tag</a><br><br>
<a href="updateAnswer.jsp">5.Update Answer</a><br><br>
<a href="questionLibraryMenu.jsp">6.Go back to Question library menu</a><br><br>

</body>
</html>