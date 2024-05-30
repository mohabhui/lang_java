package wlu.mmb.beans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFtranscript{
	private int id;
	private String name, email, department, scores, PDFname;
	public PDFtranscript(int id, String name, String email, String department, String scores, String PDFname) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.scores = scores;
		this.PDFname = PDFname;

	}
	
	public String getPDFname() {
		return PDFname;
	}
	public void setPDFname(String PDFname) {
		this.PDFname = PDFname;
	}
	
   public static void genTranscript(int id, String name, String email, String department, String scores, String PDFname)
   {
      Document document = new Document();

//		List<String> list=new ArrayList<String>();
		String sid = String.valueOf(id);
//		long timestamp = new java.sql.Timestamp(System.currentTimeMillis()).getTime();
//		String ts = String.valueOf(timestamp);
//		PDFname = "transcript_" + sid + "_" + ts + ".pdf";
		try{

			//will be downloaded in "C:\enterprise\wildfly-18.0.1.Final\bin\transcript_10001_1639517381474.pdf"
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDFname));
//	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("transcript1.pdf"));

				
				
			      try
			      {

			         document.open();
			         document.add(new Paragraph("************************* ACADEMIC TRANSCRIPT *************************"));
			         document.add(new Paragraph("University of Dhaka"));
			         document.add(new Paragraph("Department of " + department));
			         document.add(new Paragraph("Student ID: " + sid));
			         document.add(new Paragraph("Student Name: " + name));
			         document.add(new Paragraph("Student Email: " + email));
			         document.add(new Paragraph("____________________________________________________________"));
			         document.add(new Paragraph(" "));
			         
			         document.add(new Paragraph("Academic Record:"));
			         document.add(new Paragraph(" "));
			         document.add(new Paragraph("Semester, Year, Crs. Code, Crs. Name, Mark %"));

			         String[] rows = scores.split(";");
			         
			         for(int i=0; i<rows.length;i++) {
			        	 document.add(new Paragraph(" "));
			        	 document.add(new Paragraph(rows[i]));
			         }
			         document.add(new Paragraph(" "));
			         document.add(new Paragraph("____________________________________________________________"));
			         
			         document.add(new Paragraph("This document is generated electronically; No signature is required"));
			         document.add(new Paragraph("REF: " + PDFname));
			         
			      } catch (DocumentException e)
			      {
			         e.printStackTrace();
			      } catch (Exception e)
			      {
			         e.printStackTrace();
			      }

			

	         document.close();
	         writer.close();
			
		}catch(Exception e){System.out.println(e);}
		


      
      
      
      
      
   }
}