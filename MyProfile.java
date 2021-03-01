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

import beans.ListProviderr;
import beans.MyProfiles;
import Database.DbConnection;

public class MyProfile extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String eml = session.getAttribute("email").toString();
if(request.getParameter("btnupdate")!=null)
{
			ps = con.prepareStatement("UPDATE `registration` set email=?,contact=?,name=?,password=?,address=?,city=?,state=?,country=? WHERE `email`=?");
			ps.setString(1, request.getParameter("txtemail"));
			ps.setString(2, request.getParameter("txtcon"));
			ps.setString(3, request.getParameter("txtname"));
			ps.setString(4, request.getParameter("txtpass"));
			ps.setString(5, request.getParameter("txtadd"));
			ps.setString(6, request.getParameter("txtcity"));
			ps.setString(7, request.getParameter("txtstate"));
			ps.setString(8, request.getParameter("txtcoun"));
			ps.setString(9, eml);
			ps.executeUpdate();
}

			ps = con.prepareStatement("SELECT * from `registration` WHERE `email` =?");

			ps.setString(1, eml);

			rs = ps.executeQuery();

			ArrayList<MyProfiles> profileList = new ArrayList<MyProfiles>();
			if (rs.next()) {
				MyProfiles m = new MyProfiles();
				m.setId(rs.getInt("id"));
				m.setEmail(rs.getString("email"));
				m.setCon(rs.getString("contact"));
				m.setName(rs.getString("name"));
				m.setPass(rs.getString("password"));
				m.setAdd(rs.getString("address"));
				m.setCity(rs.getString("city"));
				m.setState(rs.getString("state"));
				m.setCoun(rs.getString("country"));

				profileList.add(m);
			}
			request.setAttribute("profileList", profileList);
			request.getRequestDispatcher("MyProfile.jsp").forward(request,
					response);
		} catch (Exception e) {
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
