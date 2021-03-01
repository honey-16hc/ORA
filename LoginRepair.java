package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DbConnection;

import java.sql.*;

public class LoginRepair extends HttpServlet {

	private Connection con;
	private ResultSet rs;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("txtemail");
			String password = request.getParameter("txtpassword");
		
			PreparedStatement ps = con.prepareStatement("SELECT * FROM `registration` WHERE `email`=? AND `password`=?");
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userid", rs.getInt("id"));
				session.setAttribute("email", rs.getString("email"));
				request.getRequestDispatcher("userdashboard.jsp").forward(request,response);
				// Session creation and redirect to dashboard page
			} else {
				request.getRequestDispatcher("Login.jsp").forward(request,response);
				// Login denied redirect to login page blank
			}
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
