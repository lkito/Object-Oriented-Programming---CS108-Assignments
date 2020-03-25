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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		AccountManager manager = (AccountManager) this.getServletContext().getAttribute("manager");
		Account acc = manager.login(userName, password);
		processLogin(request, response, acc);
	}
	
	

	
	private void processLogin(HttpServletRequest request, HttpServletResponse response, Account acc) 
			throws ServletException, IOException {
		if(acc == null) {
			request.getRequestDispatcher("/TryAgain.jsp").forward(request, response);
		} else {
			request.setAttribute("titleName", acc.getUserName());
			request.setAttribute("headerName", acc.getUserName());
			request.getRequestDispatcher("/UserWelcome.jsp").forward(request, response);
		}
	}

	
	

}
