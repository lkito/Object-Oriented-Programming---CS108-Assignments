<%@ page import="models.ShoppingCart, models.ItemDataBase, models.Item"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Shopping Cart </title>
</head>
<body>

	<h1> Shopping Cart </h1>
	
	<form action="ShopServlet" method="POST">
	
	    <% 
	    	ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
			List<Item> items = cart.getAllItems();
			List<Integer> itemAmount = cart.getAllAmounts();
			for(int i = 0; i < items.size(); i++){
				out.println("<li> <input type ='number' value='" + itemAmount.get(i)
						+ "' name='" + items.get(i).getId() + "'/>"
		                + items.get(i).getName() + ", " + items.get(i).getPrice() + "</li>");
			}
	    %>
	    Total: $<%=cart.getTotalPrice()%> <input type="submit" value="Update Cart"/>
	    
	</form> 
	
	<a href="HomePage">Continue Shopping</a>

</body>
</html>