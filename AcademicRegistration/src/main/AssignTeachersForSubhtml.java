package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssignTeachersForSubhtml extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    try {
	    out.println("<html>\r\n" + 
	        "<head>\r\n" + 
	        "<title>Registration</title>\r\n" + 
	        "<style>\r\n" + 
	        "#texttype{\r\n" + 
	        "width:270px;\r\n" + 
	        "height:30px;\r\n" + 
	        "}\r\n" + 
	        "mark { \r\n" + 
	        "  background-color: blue;\r\n" + 
	        "  color: white;\r\n" + 
	        "}\r\n" + 
	        ".button {\r\n" + 
	        "  background-color: #66ffff; \r\n" + 
	        "  border: none;\r\n" + 
	        "  color: white;\r\n" + 
	        "  padding: 20px;\r\n" + 
	        "  text-align: center;\r\n" + 
	        "  text-decoration: none;\r\n" + 
	        "  display: inline-block;\r\n" + 
	        "  font-size: 16px;\r\n" + 
	        "  margin: 4px 2px;\r\n" + 
	        "  cursor: pointer;\r\n" + 
	        "  border-radius: 12px;\r\n" + 
	        "}\r\n" + 
	        ".button1 {\r\n" + 
	        "  background-color: #000; \r\n" + 
	        "  border: none;\r\n" + 
	        "  color: #66ffff;\r\n" + 
	        "  padding: 20px;\r\n" + 
	        "  text-align: center;\r\n" + 
	        "  text-decoration: none;\r\n" + 
	        "  display: inline-block;\r\n" + 
	        "  font-size: 16px;\r\n" + 
	        "  margin: 4px 2px;\r\n" + 
	        "  cursor: pointer;\r\n" + 
	        "  border-radius: 12px;\r\n" + 
	        "}\r\n" + 
	        "body {\r\n" + 
	        "  background-opacity: 0.2;\r\n" + 
	        "\r\n" + 
	        "  background-image: url(\"https://images.unsplash.com/3/doctype-hi-res.jpg?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb\");\r\n" + 
	        " \r\n" + 
	        " background-repeat: no-repeat;\r\n" + 
	        "  background-size: 100% 100%;\r\n" + 
	        "   \r\n" + 
	        "  \r\n" + 
	        "}\r\n" + 
	        "\r\n" + 
	        "</style>\r\n" + 
	        "</head>  \r\n" + 
	        "<body>\r\n" + 
	        "<div>\r\n" + 
	        "<center>\r\n");
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con =  
	        DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
	        String s="select * from teachers";
	        PreparedStatement ps=con.prepareStatement(s);
	        ResultSet rs=ps.executeQuery();
	        out.println("<br><br><br><br><br><br>\r\n" + 
	                "<form action=\"AssignTeachersForSub\" method=\"post\">\r\n");
	        out.println("<h1><mark>Select the Teacher to assign subject: </mark><br><br><select name=\"teacher\" style=\"width: 150px;height:30px\">");
	        while(rs.next())
	        {
	        out.println("<option value="+rs.getString(1)+" >"+rs.getString(1)+"</option>");
	        }
	        out.println("</select>");
	        String s1="select * from subjects";
	        PreparedStatement ps1=con.prepareStatement(s1);
	        ResultSet rs1=ps1.executeQuery();
	        out.println("<h1><mark>Select the Subject: </mark><br><br><select name=\"subject\" style=\"width: 150px;height:30px\">");
	        while(rs1.next())
	        {
	        out.println("<option value="+rs1.getString(1)+" >"+rs1.getString(1)+"</option>");
	        }
	        
	        out.println(
	        "</select>");
	        
	        out.println("<br><br><input class=\"button\" type=\"submit\" style=\"text-shadow:1px 1px 0 #444;color:#000\" value=\"Submit\">\r\n" + 
	        "</form>\r\n" + 
	        "<form action=\"HomePage.html\" method=\"post\">\r\n" + 
	        "<input class=\"button1\" type=\"submit\" style=\"text-shadow:1px 1px 0 #444;color:#FFFFFF\" value=\"Go back\">\r\n" + 
	        "</form>\r\n" + 
	        "</center>\r\n" + 
	        "</div>\r\n" + 
	        "</body>\r\n" + 
	        "</html>");
	        
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
