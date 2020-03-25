package controllers;
import models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomePage
 */
@WebServlet({"/HomePage", "/ShopServlet"})
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDataBase data = (ItemDataBase) this.getServletContext().getAttribute("data");
		request.setAttribute("items", data.getAllItems());
		if(request.getSession().getAttribute("cart") == null) 
			request.getSession().setAttribute("cart", new ShoppingCart());
		request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		ItemDataBase data = (ItemDataBase) request.getServletContext().getAttribute("data");
		String id = (String) request.getParameter("productID");
		if(id != null) {
			if(cart.containsItem(id)) {
				cart.setItemAmount(id, cart.getAmount(id) + 1);
			}
			cart.addItem(data.getItem(id));
		} else {
			Enumeration<String> params = request.getParameterNames();
			while (params.hasMoreElements()) {
		        String curParam = params.nextElement();
		        cart.setItemAmount(curParam, Integer.parseInt(request.getParameter(curParam)));
			}
			cart.purgeUnusedItems();
		}
		request.getRequestDispatcher("/Cart.jsp").forward(request, response);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
