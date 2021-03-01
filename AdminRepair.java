package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DbConnection;

public class AdminRepair extends HttpServlet {

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
			String password = request.getParameter("txtpassword");

			PreparedStatement ps = con.prepareStatement("SELECT * FROM `admin` WHERE `User_Name`=? AND `Password`=? AND status=1");
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				HttpSession session = request.getSession(true);
				session.setAttribute("userid", rs.getInt("id"));
				session.setAttribute("username", rs.getString("User_Name"));
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}
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
