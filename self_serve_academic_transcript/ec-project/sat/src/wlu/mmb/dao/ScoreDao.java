package wlu.mmb.dao;

import java.io.PrintStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import wlu.mmb.beans.ScoreBean;
import wlu.mmb.beans.UserBean;

public class ScoreDao {
	
	//This save function is called when user is first created;
	//For every student, there are 8 row for 8 courses.
	//These rows are populated with id, course code and course name (id, crscode and crsname)
	//when a new student is created and cannot be amended but the 
	//rest 3 columns (semester, semesterYear, mark) remain empty and can be updated later.
	public static int save(){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("SELECT id FROM sat_users ORDER BY ID DESC LIMIT 1");
			ResultSet rset = ps.executeQuery();
			rset.next();
			int newId = rset.getInt(1);
			PreparedStatement ps1=con.prepareStatement("SELECT * from sat_courses");
			ResultSet rs=ps1.executeQuery();
			while (rs.next()) {
				PreparedStatement ps2=con.prepareStatement("insert into sat_scores(id,semester,semesterYear,crscode,mark) values(?,?,?,?,?)");
				ps2.setInt(1,  newId);
				ps2.setString(2,"-");
				ps2.setInt(3,0);
				ps2.setString(4,rs.getString(1));
				ps2.setInt(5,0);
//				PrintStream outStream = new PrintStream(new File("C:\\cp630\\workspace\\project\\outFile.txt"));
//				System.setOut(outStream);
//				System.out.println("MOHIN");
//				System.out.println(newId);
//				System.out.println(rs.getString(1));

				status = ps2.executeUpdate();
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		

		
		return status;
	}
	
	
	public static int update(ScoreBean bean){
		int status=0;
		try{
			String semester, semesterYear, crscode, crsname;
			int mark;
			int id = bean.getId();
			
			Connection con=DB.getCon();

			String[] rows = bean.getScore().split(";");
			
			for(int r=0; r<rows.length; r++) {
				
				String[] cols = rows[r].split(",");
				
				semester = cols[0];
				semesterYear = cols[1];
				crscode = cols[2];
				crsname = cols[3];
				mark = Integer.valueOf(cols[4]);
				
				PreparedStatement ps=con.prepareStatement("update sat_scores set semester=?, semesterYear=?, mark=? where (id=? and crscode=?)");
				ps.setString(1,semester);
				ps.setString(2,semesterYear);
				ps.setInt(3,mark);
				ps.setInt(4,id);
				ps.setString(5,crscode);
				status=ps.executeUpdate();
					
			}//end of for loop
			
			
			


			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	

	
	public static ScoreBean viewById(int id){
		ScoreBean bean=new ScoreBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("SELECT t1.id, group_concat(t1.semester,',', t1.semesterYear,',', t1.crscode,',', t2.crsname,',', t1.mark separator ';') as result FROM sat_scores as t1 INNER JOIN sat_courses as t2 ON t1.crscode = t2.crscode where t1.id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setId(rs.getInt(1));
				bean.setScore(rs.getString(2));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	
}
