package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.AccountManager;
import models.Account;

/**
 * Servlet implementation class AccountCreator
 */
@WebServlet("/AccountCreator")
public class AccountCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreator() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		AccountManager manager = (AccountManager) this.getServletContext().getAttribute("manager");
		if(!manager.containsUser(userName)) {
			manager.register(userName, password);
			request.setAttribute("titleName", userName);
			request.setAttribute("headerName", userName);
			request.getRequestDispatcher("/UserWelcome.jsp").forward(request, response);
		} else {
			request.setAttribute("name", userName);
			request.getRequestDispatcher("/NameAlreadyInUse.jsp").forward(request, response);
		}
	}
	
	
	
	
	
	
	
	
	
}
