<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<h1> Welcome to Homework 5! </h1>
<p> Please log in: </p>

<form name = "loginform" action=Login method = "post">
	<p> 
		Enter User Name : <input type = "text" name = "username">
		<br>
		Enter Password: <input type = "password" name = "password"> 
		<button type = "submit"> login</button>
		<br>
	</p>
</form>

<a href="<%=request.getContextPath()%>/AccountCreator"> Create New Account </a>

</body>
</html>