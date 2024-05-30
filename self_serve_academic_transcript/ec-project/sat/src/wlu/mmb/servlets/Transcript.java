package wlu.mmb.servlets;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wlu.mmb.beans.PDFtranscript;
import wlu.mmb.beans.Postman;

@WebServlet("/Transcript")
public class Transcript extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String name=request.getParameter("name");
		String email=request.getParameter("email");
//		String password=request.getParameter("password");
		String department=request.getParameter("department");
		String toEmail=request.getParameter("toEmail");
		
		//---------------------------------------------

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
		
		

		long timestamp = new java.sql.Timestamp(System.currentTimeMillis()).getTime();
		String ts = String.valueOf(timestamp);
		String PDFname = "transcript_" + sid + "_" + ts + ".pdf";
		
		
		
		
		PDFtranscript.genTranscript(id, name, email, department, miscVals, PDFname);
		
		
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "mygmail@gmail.com"; // actual gmail
        String password = "mypassword";//app password of the gmail (Not the regular password) [Google "how to use gmail as smtp server"]
 
        // message info
//        String mailTo = "bhui8420@mylaurier.ca";
        String mailTo = toEmail;
        String subject = PDFname;
        String message = "Please find attached transcript";

 
        // attachments
        String[] attachFiles = new String[1]; // Change this number as per number of attachment
        attachFiles[0] = PDFname;
//        attachFiles[0] = "e:/Test/Picture.png";
//        attachFiles[1] = "e:/Test/Music.mp3";
//        attachFiles[2] = "e:/Test/Video.mp4";
 
        try {
            Postman.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
		
		
		
		response.sendRedirect("ViewRecordForm?id="+sid);
//		response.sendRedirect("ViewUser");
		
		
//		PrintStream outStream = new PrintStream(new File("C:\\cp630\\workspace\\project\\outFile.txt"));
//		System.setOut(outStream);
//		System.out.println(beanM.getId());
//		System.out.println(beanM.getScore());
		
	}

}
