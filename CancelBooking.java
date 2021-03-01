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

public class CancelBooking extends HttpServlet {
	private Connection con;
	private PreparedStatement ps;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(false);
			String usid = session.getAttribute("userid").toString();
			if(request.getParameter("btncancel")!=null)
			{
				ps = con.prepareStatement("UPDATE `request` set `status`=0 WHERE `id`=?");
				ps.setString(1, usid);
				ps.executeUpdate();
			}
	}

		 catch (Exception e) {
				e.printStackTrace();
			}
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

