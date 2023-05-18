package com.servlet.join;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class joinusServlet extends HttpServlet {
	private static final String INSERT_QUERY = "INSERT INTO users(First_name,Last_name,p_number,house_no,street_no,city,state,email) VALUES(?,?,?,?,?,?,?,?)";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    PrintWriter pw = res.getWriter();

	    res.setContentType("text/html");

	    String First_name = req.getParameter("fname");
	    String Last_name = req.getParameter("lname");
	    String p_number = req.getParameter("phonenumber");
	    String house_no = req.getParameter("housenumber");
	    String street_no = req.getParameter("streetnumber");
	    String city = req.getParameter("cityname");
	    String state = req.getParameter("statename");
	    String email = req.getParameter("emailid");

	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }

	    try (Connection con = DriverManager.getConnection("jdbc:mysql:///mini_project", "root", "Wonder@18");
	        PreparedStatement ps = con.prepareStatement(INSERT_QUERY);) 
	    {
	      ps.setString(1, First_name);
	      ps.setString(2, Last_name);
	      ps.setString(3, p_number);
	      ps.setString(4, house_no);
	      ps.setString(5, street_no);
	      ps.setString(6, city);
	      ps.setString(7, state);
	      ps.setString(8, email);
	      int count = ps.executeUpdate();

	      if (count == 0) 
	      {
	        pw.println("Record not stored into database");
	      } else 
	      {
	        pw.println("Record Stored into Database");
	      }
	    } catch (SQLException se) {
	      pw.println(se.getMessage());
	      se.printStackTrace();
	    } catch (Exception e) {
	      pw.println(e.getMessage());
	      e.printStackTrace();
	    }
	    pw.close();
	  }

	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    doGet(req, resp);
	  }


}
