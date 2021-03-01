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

public class Furniture extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try {
			
			ps = con.prepareStatement("SELECT * FROM `furniture`");
			java.sql.ResultSet rs = ps.executeQuery();
			ArrayList<ListProviderr> furnitureproviderList = new ArrayList<ListProviderr>();
			while (rs.next()) {
				ListProviderr f = new ListProviderr();
				f.setId(rs.getInt("id"));
				f.setPin(rs.getInt("pincode"));
				f.setName(rs.getString("name"));
				f.setDes(rs.getString("description"));
				f.setAdd(rs.getString("address"));
				f.setCity(rs.getString("city"));
				f.setState(rs.getString("state"));
				f.setCoun(rs.getString("country"));
				
				furnitureproviderList.add(f);
			}request.setAttribute("furnitureproviderList", furnitureproviderList);
			request.getRequestDispatcher("Furniture.jsp").forward(request, response);
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
