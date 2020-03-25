<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Create Account </title>
</head>
<body>
<h1> The name ${name} is already in use </h1>
<p> Please enter another name and password </p>

<form name = "AccountCreatorform" action=AccountCreator method = "post">
	<p> 
		Enter User Name : <input type = "text" name = "username">
		<br>
		Enter Password: <input type = "password" name = "password"> 
		<button type = "submit"> login</button>
		<br>
	</p>
</form>

</body>
</html>