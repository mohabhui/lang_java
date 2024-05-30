package wlu.mmb.servlets;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wlu.mmb.beans.ScoreBean;
import wlu.mmb.beans.UserBean;
import wlu.mmb.dao.ScoreDao;
import wlu.mmb.dao.UserDao;
import wlu.mmb.beans.Validator;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String department=request.getParameter("department");
		
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Message Board</title>");
		out.println("<link rel='icon' type='image/x-icon' href='images/favicon/favicon.ico'>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		
		out.println("<style>");
		out.println(".btnClass {");
				out.println("margin-top: 25px;");
				out.println("position: relative;");
				out.println("top: 20%;");
				out.println("left: 30%;");
				out.println("}");

		out.println("div {");
						out.println("background-color: #f2f2f2;");
						out.println("padding: 20px;");
						out.println("position: flex;");
						out.println("transform: translate(40%, 10%);");
						out.println("width: 600px;");
						out.println("}");
				
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='msgClass'>");
		
		if(Validator.isValid(name, email, password)) {
			UserBean bean=new UserBean(id,name, email, password, department);
			String miscVals="";
			for(int i=0; i<8; i++) {
				for(int k=0; k<5; k++) {
					String ik = String.valueOf(i)+String.valueOf(k);
					if(i==0 && k==0) {
						miscVals = request.getParameter("score"+ik);
					}else {
						if(i <8 && i>0  && k == 0) {
							miscVals = miscVals + ";" + request.getParameter("score"+ik);
						}else {
							miscVals = miscVals + "," + request.getParameter("score"+ik);
						}
						
					}
				}
			}

			ScoreBean beanM = new ScoreBean(id, miscVals);

			UserDao.update(bean);
			ScoreDao.update(beanM);
			out.print("<h4 style='color:green'><b>SUCCESSFUL!<b></h4>");

		}else {
			out.print("<h4 style='color:red'><p><b>Nothing Changed!</b></p></h4>");
			out.print("<h5 style='color:red'><p><b>Know that:</b></p></h5>");
			out.print("<ul style='list-style-type:circle; color:red'>");
				out.print("<li>Name, Email and Password should be at least 4-character long</li>");
				out.print("<li>Email should be in valid format</li>");
				out.print("<li>Name or Email cannot be used as Password</li>");
			out.print("</ul>");

		}

		out.println("</div>");
		out.println("<div>");
			out.println("<button onclick='history.back()'>Go Back</button>");
		out.println("</div>");
		out.println("<body>");
		out.close();
		response.sendRedirect("MessageBoard?id="+sid);

		
		


		
	}

}
