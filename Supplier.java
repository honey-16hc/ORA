package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DbConnection;

public class Supplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
			}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
	          
			String name = request.getParameter("txtname");
			String email = request.getParameter("txtemail");
			String pass = request.getParameter("txtpassword");
			String add = request.getParameter("txtaddress");
			String city = request.getParameter("txtcity");
			String state = request.getParameter("txtstate");
			String coun = request.getParameter("txtcountry");
			String des = request.getParameter("txtdes");
			String aadhar = request.getParameter("txtaadhar");
			String con = request.getParameter("txtcontact");
			PreparedStatement ps = con.prepareStatement("INSERT INTO `supplier`(`name`,`aadhar`,`password`,`email`,`contact`,`city`,`state`,`country`,`address`,`description`) VALUES(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, aadhar);
			ps.setString(3, pass);
			ps.setString(4, email);
			ps.setString(5, con);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, coun);
			ps.setString(9, add);
			ps.setString(10, des);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("confirm.jsp").forward(request,
				response);
		
	}


	public void init() throws ServletException {
		try {
			con = DbConnection.getConnection();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}