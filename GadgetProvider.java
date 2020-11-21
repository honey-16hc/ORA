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

public class GadgetProvider extends HttpServlet {

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
            int aadhar = Integer.parseInt(request.getParameter("txtaadhar"));
            int con = Integer.parseInt(request.getParameter("txtcontact"));
			String email = request.getParameter("txtemail");
			String pass = request.getParameter("txtpassword");
			String add = request.getParameter("txtaddress");
			String city = request.getParameter("txtcity");
			String state = request.getParameter("txtstate");
			String country = request.getParameter("txtcountry");
			String des = request.getParameter("txtdes");
			String spec = request.getParameter("txtspec");
			String work = request.getParameter("txtwork-ex");

			PreparedStatement ps = con.prepareStatement("INSERT INTO `gadgetprovider`(`name`,`aadhar`,`contact`,`email`,`password`,`address`,`city`,`state`,`country`,`description`,`specialist`,`work-ex`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, aadhar);
			ps.setInt(3, con);
			ps.setString(4, email);
			ps.setString(5, pass);
			ps.setString(6, add);
			ps.setString(7, city);
			ps.setString(8, state);
			ps.setString(9, country);
			ps.setString(10, des);
			ps.setString(11, spec);
			ps.setString(12, work);
		
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