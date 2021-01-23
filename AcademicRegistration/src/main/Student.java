package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Student extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String na = request.getParameter("stdntname");
	    String cl=request.getParameter("class");
	    String count=request.getParameter("count");
	    int c=Integer.parseInt(count);
	    try{
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con =  
	            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
	            String s="select * from studentlist where student=? and class=?";
	            PreparedStatement ps=con.prepareStatement(s);
	            ps.setString(1,na);
	            ps.setString(2, cl);
	            ResultSet rs=ps.executeQuery();
	            if(rs.next())
	            {
	              RequestDispatcher rd = request.getRequestDispatcher("Studenthtml");
	               rd.include(request, response);
	              out.println("Same student already exist provide full name of student.");
	              int a=1/0;
	            }
	            if(c>0)
	            {
	            	String ss="select * from classes where name=?";
	            	PreparedStatement pss=con.prepareStatement(ss);
	            	pss.setString(1, cl);
	            	ResultSet rss=ps.executeQuery();
	            	if(rss.next()) {
	            	String st=rss.getString(2);
	            	int cc=Integer.parseInt(st);
	            	if(cc<c)
	            	{
	            		RequestDispatcher rd = request.getRequestDispatcher("Studenthtml");
	 	               rd.include(request, response);
	 	              out.println("Selected class is full!!.");
	 	              int a=1/0;
	            	}
	            	}
	            }
	            String s2="insert into studentlist values(?,?)";
	            PreparedStatement ps1=con.prepareStatement(s2);
	            ps1.setString(1, na);
	            ps1.setString(2, cl);
	            ps1.executeUpdate();
	            RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
	           rd.include(request, response);
	            out.println("Student resistered successfully!!!");
	  }
	    catch(Exception e)
	    {
	      out.println(e);
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
