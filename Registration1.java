package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DbConnection;

import java.sql.*;

public class Registration1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection con;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String contact = request.getParameter("txtcontact");
			String name = request.getParameter("txtname");
			String email = request.getParameter("txtemail");
			String password = request.getParameter("txtpassword");
			String address = request.getParameter("txtaddress");
			String city = request.getParameter("txtcity");
			String state = request.getParameter("txtstate");
			String country = request.getParameter("txtcountry");
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO `registration`(`name`,`email`,`password`,`contact`,`address`,`city`,`state`,`country`) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, contact);
			ps.setString(5, address);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, country);
			ps.executeUpdate();
			System.out.println("saved");
			request.getRequestDispatcher("Login.jsp").forward(request,response);
		} catch (Exception e) {
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
