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

public class Contact extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;


	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
          
			String name = request.getParameter("txtname");
			String email = request.getParameter("txtemail");
			String sub = request.getParameter("txtsubject");
			String com = request.getParameter("txtcomment");
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO `contact`(`name`,`email`,`subject`,`comment`) VALUES(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, sub);
			ps.setString(4, com);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("HomePage.jsp").forward(request,
				response);
		
	}


	public void init() throws ServletException {
		try {
			con = DbConnection.getConnection();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}