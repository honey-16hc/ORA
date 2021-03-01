package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DbConnection;
import beans.ListProviderr;
import beans.MyBooking;

public class MyBookings extends HttpServlet {
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
			if(session != null)
			{
				ps = con.prepareStatement("SELECT * FROM `request` WHERE `id`=? AND `status`=1 ");
			ps.setString(1, usid);
			java.sql.ResultSet rs = ps.executeQuery();
			ArrayList<MyBooking> bookingList = new ArrayList<MyBooking>();
			while (rs.next()) {
				MyBooking b = new MyBooking();
				b.setCust_id(rs.getString("Customer_id"));
				b.setDes(rs.getString("Description"));
				b.setReq_date(rs.getString("Request_date"));
				b.setTitle(rs.getString("Title"));
				bookingList.add(b);
			}
			request.setAttribute("bookingList", bookingList);
			request.getRequestDispatcher("MyBooking.jsp").forward(request, response);
			
			}
			else {
				request.getRequestDispatcher("Login.jsp").forward(request,response);
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
