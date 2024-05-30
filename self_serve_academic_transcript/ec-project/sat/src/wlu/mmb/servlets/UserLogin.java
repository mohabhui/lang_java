package wlu.mmb.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wlu.mmb.dao.DB;
import wlu.mmb.dao.UserDao;
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>User Section</title>");
		out.println("<link rel='icon' type='image/x-icon' href='images/favicon/favicon.ico'>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
			out.println("<style>");
			out.println(".active img{");
			out.println("width: 100%;");
			out.println("}");
			out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String currId = null;
		if(UserDao.authenticate(email, password)){
			HttpSession session=request.getSession();
			session.setAttribute("email",email);
			
//			
			try {
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("SELECT id FROM `sat_users` WHERE (email=? and password=?) limit 1");
				ps.setString(1,email);
				ps.setString(2,password);
				ResultSet rset = ps.executeQuery();
				rset.next();
				currId = String.valueOf(rset.getInt(1));
			}catch(Exception e){System.out.println(e);}

			
			request.getRequestDispatcher("navuser.html").include(request, response);
			

			out.println("<div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>");
			out.println("<ul class='nav navbar-nav'>");
			out.println("<li>");
					out.println("<table style='color:white'>");
							out.println("<tr><td style='text-align: center; vertical-align: middle;'>UNIVERSITY OF DHAKA</td></tr>");
					out.println("<tr><td style='text-align: center; vertical-align: middle;'>Dept. of Microbilogy</td></tr>");
					out.println("</table>");
							out.println("</li>");
			out.println("<li><a href='index.html'>Home</a></li>");
			out.println("<li><a href='ViewRecordForm?id="+ currId +"'>View Record</a></li>");
			out.println("<li><a href='ChangePasswordForm?id="+ currId +"'>Change Password</a></li>");
			out.println("<li><a href='TranscriptForm?id="+ currId +"'>Email Transcript</a></li>");
			out.println("<li><a href='LogoutUser'>Logout</a></li>");
			out.println("</ul>");
			out.println("</div><!-- /.navbar-collapse -->");
			out.println("</div><!-- /.container-fluid -->");
			out.println("</nav>");
			request.getRequestDispatcher("usercarousel.html").include(request, response);
			
		}else{
			request.getRequestDispatcher("navhome.html").include(request, response);
			out.println("<div class='container'>");
			out.println("<h3>Username or password error</h3>");
			request.getRequestDispatcher("userloginform.html").include(request, response);
			out.println("</div>");
		}
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
