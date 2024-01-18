package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jdi.connect.spi.Connection;
@WebServlet("/register")

public class RegisterServlet extends HttpServlet {
private static String query="insert into bookdata(BOOKNAME,BOOKEDITION,BOOKPRICE)values(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float price = Float.parseFloat(req.getParameter("bookPrice"));
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mainproject","root","Admin@123");
			PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, bookName);
		ps.setString(2, bookEdition);
		ps.setFloat(3, price);
		int count=ps.executeUpdate();
		if(count==1) {
			pw.println("<h2>record is registered</h2>");
		}
		else {
			pw.println("<h2>record is not registered</h2>");
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
		e.printStackTrace();
		pw.println("<h1> " + e.getMessage()+"</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='bookList'>BookList</a>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
}
