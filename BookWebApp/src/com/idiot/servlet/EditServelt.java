package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServelt extends HttpServlet {
	private static String query="update bookdata set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String bookName=req.getParameter("bookName");
		String bookEditon=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookprice"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mainproject","root","Admin@123");
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,bookName);
			ps.setString(2,bookEditon);
			ps.setFloat(3,bookPrice);
			ps.setInt(4, id);
			
			int count=ps.executeUpdate();
			if(count==1) {
				pw.print("<h2>successfully edited</h2>");
			}else {
				pw.print("<h2>Not edited</h2>");
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
