package login;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class Register extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String name= request.getParameter("name");
        String email=request.getParameter("email");
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con =  
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String s="select * from admin_details where username=?";
            PreparedStatement ps2=con.prepareStatement(s);
            ps2.setString(1,uname);
            ResultSet rs=ps2.executeQuery();
            if(rs.next())
            {
         	   RequestDispatcher rd = request.getRequestDispatcher("register.html");
        		   rd.include(request, response);
         	   out.println("UserName already Exists");
         	   int a=1/0;
            }
            String s2="insert into admin_details values(?,?,?,?)";
            PreparedStatement ps1=con.prepareStatement(s2);
            ps1.setString(1, uname);
            ps1.setString(2, pwd);
            ps1.setString(3, name);
            ps1.setString(4, email);
            ps1.executeUpdate();
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
    		   rd.include(request, response);
            out.println("Registered Sucessfully!!!");
	}
    catch(Exception e)
    {
 	   out.println(e);
    }
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
