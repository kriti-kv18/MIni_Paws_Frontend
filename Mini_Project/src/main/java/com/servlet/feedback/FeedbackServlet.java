package com.servlet.feedback;

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

@WebServlet("/submit")
public class FeedbackServlet extends HttpServlet{
	private static final String INSERT_QUERY = "INSERT INTO feedback(feedback) VALUES(?)";

	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    PrintWriter pw = res.getWriter();

	    res.setContentType("text/html");

	    String feedback = req.getParameter("feedback");

	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }

	    try (Connection con = DriverManager.getConnection("jdbc:mysql:///form", "root", "Wonder@18");
	        PreparedStatement ps = con.prepareStatement(INSERT_QUERY);) 
	    {
	      ps.setString(1, feedback);
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
