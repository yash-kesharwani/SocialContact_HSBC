package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Admin;
import com.exceptions.UserNotFoundException;
import com.services.AdminService;
import com.servicesImpl.AdminServiceImpl;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
		AdminService as=new AdminServiceImpl();
		int sysInfo[]=new int[2];
		Admin admin=new Admin();
		Map<Integer, String> toDisable=null, toDelete=null;

		try {
			sysInfo=as.getSystemInfo();
			admin=as.getAdminInfo();
			toDisable=as.getToDisable();
			toDelete=as.getToDelete();
			request.setAttribute("adminInfo", admin);
			request.setAttribute("sysInfo", sysInfo);
			request.setAttribute("toDelete", toDelete);
			request.setAttribute("toDisable", toDisable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			int userIdToDlete=(int) request.getAttribute("userIdToDelete");
			int UserIdToDisable=(int)request.getAttribute("userIdToDisable");
		
	}

}
