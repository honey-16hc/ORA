package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DbConnection;

public class AddProvider extends HttpServlet {

	private Connection con;
	private ResultSet rs;
	
		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			String name = request.getParameter("txtname");
            int pin = Integer.parseInt(request.getParameter("txtpin"));
			String des = request.getParameter("txtdes");
			String add = request.getParameter("txtadd");
			String city = request.getParameter("txtcity");
			String state = request.getParameter("txtstate");
			String country = request.getParameter("txtcoun");
			

			PreparedStatement ps = con.prepareStatement("INSERT INTO `provider`(`name`,`description`,`address`,`city`,`state`,`pincode`,`country`) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, des);
			ps.setString(3, add);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setInt(6, pin);
			ps.setString(7, country);
		
			ps.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
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
