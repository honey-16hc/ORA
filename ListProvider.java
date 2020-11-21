package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DbConnection;
import beans.ListProviderr;

import java.sql.*;

public class ListProvider extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ps = con.prepareStatement("SELECT * FROM `provider`");
			java.sql.ResultSet rs = ps.executeQuery();
			ArrayList<ListProviderr> providerList = new ArrayList<ListProviderr>();
			while (rs.next()) {
				ListProviderr s = new ListProviderr();
				s.setId(rs.getInt("id"));
				s.setPin(rs.getInt("pincode"));
				s.setName(rs.getString("name"));
				s.setDes(rs.getString("description"));
				s.setAdd(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCoun(rs.getString("country"));
				
				providerList.add(s);
			}
			request.setAttribute("providerList", providerList);
			request.getRequestDispatcher("ListProvider.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		con = DbConnection.getConnection();
	}

}
