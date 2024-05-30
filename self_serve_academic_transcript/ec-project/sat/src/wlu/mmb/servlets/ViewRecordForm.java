package wlu.mmb.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wlu.mmb.beans.ScoreBean;
import wlu.mmb.beans.UserBean;
import wlu.mmb.dao.ScoreDao;
import wlu.mmb.dao.UserDao;

@WebServlet("/ViewRecordForm")
public class ViewRecordForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Academic Record</title>");
		out.println("<link rel='icon' type='image/x-icon' href='images/favicon/favicon.ico'>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		
		out.println("<style>");
		out.println(".btnClass {");
				out.println("margin-top: 25px;");
				out.println("position: relative;");
				out.println("top: 20%;");
				out.println("left: 30%;");
				out.println("}");
				out.println("</style>");
							
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class='btnClass'>");
		out.println("<button onclick='history.back()'>Go Back</button>");
		out.println("</div>");
		
//		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		UserBean bean=UserDao.viewById(id);
		ScoreBean beanM=ScoreDao.viewById(id);
		
		out.print("<form action='EditUser' method='post' style='width:300px'>");
		out.print("<div class='form-group'>");
		out.print("<input type='hidden' name='id' value='"+bean.getId()+"'/>");
		out.print("<label for='name1'>Name</label>");
		out.print("<input type='text' class='form-control' value='"+bean.getName()+"' name='name' id='name1' placeholder='Name' readonly/>");
		out.print("</div>");
		out.print("<div class='form-group'>");
		out.print("<label for='email1'>Email address</label>");
		out.print("<input type='email' class='form-control' value='"+bean.getEmail()+"'  name='email' id='email1' placeholder='Email' readonly/>");
		out.print("</div>");
		out.print("<div class='form-group'>");
		out.print("<label for='password1'>Password</label>");
		out.print("<input type='password' class='form-control' value='"+bean.getPassword()+"'  name='password' id='password1' placeholder='Password' readonly/>");
		out.print("</div>  ");
		out.print("<div class='form-group'>");
		out.print("<label for='department1'>Department</label>");
		out.print("<input type='text' class='form-control' value='"+bean.getDepartment()+"'  name='department' id='department1' placeholder='department' readonly/>");
		out.print("</div>");
		
//		--------------------------------NEW--------------------------------------
//		out.print("<div class='form-group'>");
//		out.print("<label for='score1'>Score</label>");
//		out.print("<input type='text' class='form-control' value='"+beanM.getScore()+"'  name='score' id='score1' placeholder='score' style='height:200px;width:800px'/>");
//		out.print("</div>");
		
		
//		out.print("<div class='form-group'>");
//		out.print("<label for='score1'>Score</label>");
//		out.print("<textarea name='score' id='score1' placeholder='score' rows='10' cols='80'>"+beanM.getScore()+"</textarea>");
//		out.print("</div>");
		
		
//		String[] result = beanM.getScore().split(";");
//		out.print("<label>Score</label>");
//		for (int i = 0; i < result.length; i++) {
//			out.print("<div class='form-group'>");
//			out.print("<input type='text' class='form-control' value='"+result[i]+"'  name='score' id='score"+String.valueOf(i) + " placeholder='score' style='width:500px'/>");
//			out.print("</div>");
//		}
		
		String[] rows = beanM.getScore().split(";");

		out.print("<table>");
		out.print("<tr>");
			out.print("<td>Semester</td>");
			out.print("<td>Year</td>");
			out.print("<td>Code</td>");
			out.print("<td>Course Name</td>");
			out.print("<td>Marks</td>");
		out.print("</tr>");
		
		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(",");
			out.print("<tr>");

			for(int k=0; k<cols.length; k++) {
				String ik = String.valueOf(i)+String.valueOf(k);
				out.print("<td>");
					out.print("<div class='form-group'>");
					if(k==2) {
						out.print("<input type='text' class='form-control' value='"+cols[k]+"'  name='score"+ik + "' id='score"+ik + "' placeholder='score"+ik+ "' style='width:80px'readonly/>");
					}else if(k==3) {
						
						out.print("<input type='text' class='form-control' value='"+cols[k]+"'  name='score"+ik + "' id='score"+ik + "' placeholder='score"+ik+ "' style='width:300px' readonly/>");
					}else {
						
						out.print("<input type='text' class='form-control' value='"+cols[k]+"'  name='score"+ik + "' id='score"+ik + "' placeholder='score"+ik+ "' style='width:80px'  readonly/>");
						}
					out.print("</div>");

				
				out.print("</td>");
				
			}
			out.print("<tr>");

		out.print("<table>");
		}
		
		
		
		
//		------------------------------------------------------------------------------
		
		
		
//		out.print("<button type='submit' class='btn btn-primary'>Update</button>");
		out.print("</form>");
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}
}
