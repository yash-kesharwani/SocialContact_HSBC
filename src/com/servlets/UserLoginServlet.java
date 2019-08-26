package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exceptions.InvalidUserException;
import com.services.UserService;
import com.servicesImpl.UserServiceImpl;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
		PrintWriter out=response.getWriter();
		UserService us=new UserServiceImpl();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			if(us.userLogin(username, password))
			{
				RequestDispatcher rd= request.getRequestDispatcher("UserHome.jsp");
				HttpSession session =request.getSession(true);
				System.out.println("session created!"+session.getId());
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd= request.getRequestDispatcher("Home.html");
				rd.include(request,response);
				out.println("<p style='font-size:30px; color:red; text-align:center;'>Incorrect username or password</p>");
				throw new InvalidUserException("entered username or password is incorrect!");
			}
				
		} catch (SQLException | InvalidUserException e) {
			
			e.printStackTrace();
		}
	}

}
