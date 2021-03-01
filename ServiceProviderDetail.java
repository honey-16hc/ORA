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

public class ServiceProviderDetail extends HttpServlet {
	private Connection con;
	private PreparedStatement ps;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ps = con.prepareStatement("SELECT * FROM `provider` WHERE id='"
					+ id + "'");
			java.sql.ResultSet rs = ps.executeQuery();
			ListProviderr providerList = new ListProviderr();
			if (rs.next()) {
				providerList.setId(rs.getInt("id"));
				providerList.setPin(rs.getInt("pincode"));
				providerList.setName(rs.getString("name"));
				providerList.setDes(rs.getString("description"));
				providerList.setAdd(rs.getString("address"));
				providerList.setCity(rs.getString("city"));
				providerList.setState(rs.getString("state"));
				providerList.setCoun(rs.getString("country"));

			}
			request.setAttribute("provider", providerList);
			request.getRequestDispatcher("ServiceProviderDetail.jsp").forward(
					request, response);
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
