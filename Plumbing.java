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

public class Plumbing extends HttpServlet {
	private Connection con;
	private PreparedStatement ps;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try {
			
			ps = con.prepareStatement("SELECT * FROM `plumbing`");
			java.sql.ResultSet rs = ps.executeQuery();
			ArrayList<ListProviderr> plumbingproviderList = new ArrayList<ListProviderr>();
			while (rs.next()) {
				ListProviderr p = new ListProviderr();
				p.setId(rs.getInt("id"));
				p.setPin(rs.getInt("pincode"));
				p.setName(rs.getString("name"));
				p.setDes(rs.getString("description"));
				p.setAdd(rs.getString("address"));
				p.setCity(rs.getString("city"));
				p.setState(rs.getString("state"));
				p.setCoun(rs.getString("country"));
				
				plumbingproviderList.add(p);
			}request.setAttribute("plumbingproviderList", plumbingproviderList);
			request.getRequestDispatcher("Plumbing.jsp").forward(request, response);
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		try{
			con = DbConnection.getConnection();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}