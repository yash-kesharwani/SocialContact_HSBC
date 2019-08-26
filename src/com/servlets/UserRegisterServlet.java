package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.UserNotFoundException;
import com.services.UserRegisterService;
import com.servicesImpl.UserRegisterServiceImpl;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd=request.getRequestDispatcher("Home.html");
		PrintWriter pw=response.getWriter();
		
		String fullName=request.getParameter("fullName");
		String email=request.getParameter("email");
		long phone=Long.parseLong(request.getParameter("phone"));
		char gender=(request.getParameter("gender").charAt(0));
		//Date dob=Date.parse((request.getParameter("dob"));
		Date dob = null;
		java.util.Date temp;
		try {
			temp = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
			dob=new Date(temp.getTime());
		} catch (ParseException e) {
			
			e.printStackTrace();
		} 
		
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		int pincode=Integer.parseInt(request.getParameter("pincode"));
		String company=request.getParameter("company");
		String picture=request.getParameter("picture");
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		UserRegisterService urs=new UserRegisterServiceImpl();
		int flag;
		try {
			flag = urs.userRegister(fullName, email, phone, gender, dob, city, state, country, pincode, company, picture, userName, password);
			if(flag==1)
				pw.write("<h3 align=center><style color='red'>EMAIL/USERNAME ALREADY EXISTS!!</style></h3>");
			else if(flag==2)
				pw.write("<h3 align=center><style color='red'>AGE IS LESS THAN 18 YEARS!</style></h3>");
			else
			{
				System.out.println("+++++++++++++registeration successfull++++++++");
				rd.forward(request, response);
			}
		} catch (UserNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
