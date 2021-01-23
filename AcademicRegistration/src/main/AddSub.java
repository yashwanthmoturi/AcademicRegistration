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

public class AddSub extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String newsub = request.getParameter("newsub");
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s="select * from subjects where sub=?";
            PreparedStatement ps=con.prepareStatement(s);
            ps.setString(1,newsub);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
         	   RequestDispatcher rd = request.getRequestDispatcher("AddSub.html");
        		   rd.include(request, response);
         	   out.println("This subject already Exist to add another.");
         	   int a=1/0;
            }
            String s2="insert into subjects values(?)";
            PreparedStatement ps1=con.prepareStatement(s2);
            ps1.setString(1, newsub);
            ps1.executeUpdate();
            RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
    		   rd.include(request, response);
            out.println("Subject added Sucessfully!!!");
	}
    catch(Exception e)
    {
 	   
    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
