package wlu.mmb.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wlu.mmb.beans.UserBean;
import wlu.mmb.dao.ScoreDao;
import wlu.mmb.dao.UserDao;
import wlu.mmb.beans.Validator;
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add User</title>");
		out.println("<link rel='icon' type='image/x-icon' href='images/favicon/favicon.ico'>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
//		String department=request.getParameter("department");

//		UserBean bean=new UserBean(name, email, password, department);
		
/*		if(name != "name" && name.length()>3 && email !="email" && email.length()>3 && password !="password" && password.length()>3) {
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(email);
			boolean matches = matcher.matches();
			if(matches) {
				UserBean bean=new UserBean(name, email, password, "Microbiology");//Department is fixed
				UserDao.save(bean);
				ScoreDao.save();
				out.print("<h4 style='color:green'>SUCCESS!</h4>");
			}else {
				out.print("<h4 style='color:red'>Invalid Email; Nothing Changed!</h4>");
			}
			
		}else {
			out.print("<h4 style='color:red'>Invalid Name, Email or Password</h4>");
			out.print("<h4 style='color:red'>Min 4-character is required in each field</h4>");
			out.print("<h4 style='color:red'>Nothing Changed!</h4>");
		}*/

		if(Validator.isValid(name, email, password)) {
			UserBean bean=new UserBean(name, email, password, "Microbiology");//Department is fixed
			UserDao.save(bean);
			ScoreDao.save();
			out.print("<h4 style='color:green'><b>SUCCESSFUL!<b></h4>");
			request.getRequestDispatcher("adduserform.html").include(request, response);
			out.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
		}else {
			out.print("<h4 style='color:red'><b>Nothing Changed!</b></h4>");
			out.print("<h5 style='color:red'><b>Know that:</b></h5>");
			out.print("<ul style='list-style-type:circle; color:red'>");
				out.print("<li>Name, Email and Password should be at least 4-character long</li>");
				out.print("<li>Email should be in valid format</li>");
				out.print("<li>Name or Email cannot be used as Password</li>");
			out.print("</ul>");
			out.println("<button onclick='history.back()'>Go Back</button>");
		}
		
		out.close();
		
		
		

	}

}
