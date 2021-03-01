package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DbConnection;

public class ServiceRequest extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String cust_id = request.getParameter("txtcust_id");
			String des = request.getParameter("txtdes");
			String title = request.getParameter("txttitle");
			String date = request.getParameter("txtdate");
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO `request`(`Customer_id`,`Title`,`Description`,`Request_date`) VALUES(?,?,?,?)");
			ps.setString(1, cust_id);
			ps.setString(2, des);
			ps.setString(3, title);
			ps.setString(4, date);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if(request.getAttribute("userid") == ""){ 
				request.getRequestDispatcher("Login.jsp")
						.forward(request, response);
			}else{
				request.getRequestDispatcher("slider.jsp").forward(request,
						response);
			}
		} else {
			request.getRequestDispatcher("Login.jsp").forward(request,
					response);
		}
	}

	public void init() throws ServletException {
		try {
			con = DbConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}