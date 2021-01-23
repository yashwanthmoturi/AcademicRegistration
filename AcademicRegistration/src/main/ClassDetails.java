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

public class ClassDetails extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String cl=request.getParameter("class");
	    try{
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection con = 
	            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
	            String s="select * from studentlist where class=?";
	            PreparedStatement ps=con.prepareStatement(s);
	            ps.setString(1, cl);
	            ResultSet rs=ps.executeQuery();
	            out.println("<html>\r\n" + 
	            		"<head>\r\n" + 
	            		"<style>\r\n" + 
	            		"h1 {\r\n" + 
	            		"  text-align: center;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n"
	            		+ "table {\r\n" + 
	            		"  font-family: arial, sans-serif;\r\n" + 
	            		"  border-collapse: collapse;\r\n" + 
	            		"  width: 100%;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		"td, th {\r\n" + 
	            		"  border: 1px solid #dddddd;\r\n" + 
	            		"  text-align: left;\r\n" + 
	            		"  padding: 8px;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		"tr:nth-child(even) {\r\n" + 
	            		"  background-color: #dddddd;\r\n" + 
	            		"}"
	            		+ "</style>" + 
	            		"</head>"
	            		+ "<body>");
	            out.println("<h1>Class : "+cl+"</h1>");
	            String s1="select * from studentlist where class=?";
	            PreparedStatement ps1=con.prepareStatement(s1);
	            ps1.setString(1, cl);
	            ResultSet rs1=ps1.executeQuery();
	            out.println("<table>\r\n"
	            		+ "<tr>" + 
	            		"<th>Idno</th>\r\n"
	            		+ "<th>Student list</th>"
	            		+ "</tr>");
	            int count=0;
	            while(rs1.next())
	            {
	            	count++;
	            //out.println("<h3>"+rs1.getString(1)+"</h3>");
	            out.println("<tr><td>"+count+"</td>"
	            		+ "<td>"+rs1.getString(1)+"</td>"
	            		+ "</tr>");
	            }
	            out.println("</table>");
	            out.println("<h1>Subjects and faculty</h1>");
	            out.println("<table>\r\n"
	            		+ "<tr>" + 
	            		"<th>Subject</th>\r\n"
	            		+ "<th>Faculty</th>"
	            		+ "</tr>");
	            String s2="select * from classandsub where class=?";
	            PreparedStatement ps2=con.prepareStatement(s2);
	            ps2.setString(1, cl);
	            ResultSet rs2=ps2.executeQuery();
	            String a;
	            String s3;
	            PreparedStatement ps3;
	            ResultSet rs3;
	            while(rs2.next()) {
	            	a=rs2.getString(2);
	            	s3="select * from teachandsub where subject=?";
	            	ps3=con.prepareStatement(s3);
	            	ps3.setString(1, a);
	            	rs3=ps3.executeQuery();
	            	while(rs3.next())
	            	{
	            		out.println("<tr><td>"+rs3.getString(2)+"</td>"
	    	            		+ "<td>"+rs3.getString(1)+"</td>"
	    	            		+ "</tr>");
	            	}
	            }
	            out.println("</table>");
	            out.println("</body>"
	            		+ "</html>");
	  }
	    catch(Exception e)
	    {
	      
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
