<%@ page import="models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Student Store </title>
</head>
<body>

<h1> Student Store </h1>
<p> Items Available: </p>

<c:forEach items="${items}" var="item">
    <tr>
        <td> 
        	<li>
        		<a href= "ShowProduct.jsp?id=${item.getId()}" > ${item.getName()} </a>
        	</li>
        </td>
    </tr>
</c:forEach>


</body>
</html>