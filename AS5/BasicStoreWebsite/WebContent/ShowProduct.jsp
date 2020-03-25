<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Item, models.ItemDataBase"%>
<!DOCTYPE html>
<%
	ItemDataBase data = (ItemDataBase) this.getServletContext().getAttribute("data");
	Item it = data.getItem(request.getParameter("id"));
%>
<html>
<head>
<meta charset="UTF-8">
<title> <% out.append(it.getName());%> </title>
</head>
<body>

	<h1> <%out.append(it.getName()); %> </h1>
	<img src="<%=request.getContextPath() + "/store-images/"+ it.getImageFile()%>" alt="<%=it.getName()%>">
	<br>
		
	<form action="ShopServlet" method="POST">
	    $<%=it.getPrice()%> <input name="productID" type="hidden" value="<%=it.getId()%>"/>
		<input type="submit" value="Add to Cart"/>
	</form> 
	
	
		
</body>
</html>