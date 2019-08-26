package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.AdminService;
import com.servicesImpl.AdminServiceImpl;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username,password;
		PrintWriter pw=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("AdminHome.jsp");
		username=request.getParameter("username");
		password=request.getParameter("password");
		
		AdminService a=new AdminServiceImpl();
		if(a.adminLogin(username, password))
		{
			rd.forward(request, response);
		}
		else
			pw.write("<H2>INVALID USERNAME OR PASSWORD!</H2>");
	}

}
