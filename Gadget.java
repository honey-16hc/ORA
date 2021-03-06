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

public class Gadget extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try {
			
			ps = con.prepareStatement("SELECT * FROM `gadget`");
			java.sql.ResultSet rs = ps.executeQuery();
			ArrayList<ListProviderr> gadgetproviderList = new ArrayList<ListProviderr>();
			while (rs.next()) {
				ListProviderr g = new ListProviderr();
				g.setId(rs.getInt("id"));
				g.setPin(rs.getInt("pincode"));
				g.setName(rs.getString("name"));
				g.setDes(rs.getString("description"));
				g.setAdd(rs.getString("address"));
				g.setCity(rs.getString("city"));
				g.setState(rs.getString("state"));
				g.setCoun(rs.getString("country"));
				
				gadgetproviderList.add(g);
			}request.setAttribute("gadgetproviderList", gadgetproviderList);
			request.getRequestDispatcher("GadgetServiceman.jsp").forward(request, response);
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