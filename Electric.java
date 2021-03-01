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

public class Electric extends HttpServlet {
	private Connection con;
	private PreparedStatement ps;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try {
			
			ps = con.prepareStatement("SELECT * FROM `electric`");
			java.sql.ResultSet rs = ps.executeQuery();
			ArrayList<ListProviderr> electricproviderList = new ArrayList<ListProviderr>();
			while (rs.next()) {
				ListProviderr e = new ListProviderr();
				e.setId(rs.getInt("id"));
				e.setPin(rs.getInt("pincode"));
				e.setName(rs.getString("name"));
				e.setDes(rs.getString("description"));
				e.setAdd(rs.getString("address"));
				e.setCity(rs.getString("city"));
				e.setState(rs.getString("state"));
				e.setCoun(rs.getString("country"));
				
				electricproviderList.add(e);
			}request.setAttribute("electricproviderList", electricproviderList);
			request.getRequestDispatcher("Electric.jsp").forward(request, response);
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