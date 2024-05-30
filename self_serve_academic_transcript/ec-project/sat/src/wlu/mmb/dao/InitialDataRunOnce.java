package wlu.mmb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 The main method create three tables (if not already exist) in test database: sat_users, sat_courses, sat_scores
 If table sat_users is empty, data from csv files (sat_users.csv, sat_courses.csv, sat_scores.csv) are loaded in the above three tables
 Run this class once to load data in test tables. Multiple runs will not affect the tables since data are loaded only if table sat_users is empty
*/
public class InitialDataRunOnce {
	
	// Create table in test database if table not exist
	public static void createTable(String createSQL) throws Exception{
	    Connection connection = null;
	    Statement statement = null;
		try {
            connection = DB.getCon();
            statement = connection.createStatement();
            statement.execute(createSQL);
            System.out.println("Successfully executed: " + createSQL);

        } catch (SQLException e) { // Handle errors for JDBC
            e.printStackTrace();
        } catch (Exception e) {   // Handle errors for Class.forName
            e.printStackTrace();
        } finally { // finally block used to close resources
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
		
	}
	
//	insert data from csv file to the table
	public static void insertData(String insertSQL, String csvFilePath) throws Exception{
	    Connection connection = null;
	    Statement statement = null;
	    String sql;
	    
	    connection = DB.getCon();
        statement = connection.createStatement();
        
	    try {
            connection = DB.getCon();
            String query = insertSQL;
            PreparedStatement ps = connection.prepareStatement(query);
            
	        File dataFile = new File(csvFilePath);
	        Scanner myReader = new Scanner(dataFile);
	        int rowNum = 0;
	        while (myReader.hasNextLine()) {
	          String line = myReader.nextLine();
	          rowNum++;

//	          System.out.println(line);
              String[] tokens = line.split(",");
              int n = 1;
              for(String token : tokens)
              {
                  //Print all tokens
                  //System.out.println(token);
            	  ps.setString(n, token);
            	  n++;
              }
              ps.execute();
	        }
	        System.out.println("Successfully executed: " + insertSQL);
	        System.out.println("Total number of rows inserted: " + String.valueOf(rowNum));
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("Error Reading File");
	        e.printStackTrace();
	      }
	    statement.close();
		
	}
	
	
	
    public static void main(String[] args) throws Exception {
	    Connection connection = null;
	    Statement statement = null;
	    String sql;
    	
        sql = "CREATE TABLE IF NOT EXISTS sat_users ("
        		+ "id INT(11) AUTO_INCREMENT, "
        		+ "name VARCHAR(40) NOT NULL, "
        		+ "email VARCHAR(40) NOT NULL, "
        		+ "password VARCHAR(255) NOT NULL, "
        		+ "department VARCHAR(40) NOT NULL,"
        		+ "PRIMARY KEY (id)) ";
        
        createTable(sql);
        
        sql = "CREATE TABLE IF NOT EXISTS sat_scores ("
        		+ "id INT(11) NOT NULL, "
        		+ "semester VARCHAR(40) NOT NULL, "
        		+ "semesterYear INT(11) NOT NULL, "
        		+ "crscode VARCHAR(40) NOT NULL, "
        		+ "mark INT(11) NOT NULL)";            
        
        createTable(sql);
        
        sql = "CREATE TABLE IF NOT EXISTS sat_courses ("
        		+ "crscode VARCHAR(40) NOT NULL UNIQUE, "
        		+ "crsname VARCHAR(40) NOT NULL UNIQUE)";  

        createTable(sql);
        

        connection = DB.getCon();
        statement = connection.createStatement();
        
        // checking if ResultSet is empty 
        sql = "SELECT * FROM sat_users";
        
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
        	System.out.println("Table 'sat_users' is not empty; Exiting the method"); 
        	return;
         }else {
        	  
         }
        
        sql = "ALTER TABLE sat_users AUTO_INCREMENT = 10001";
        statement.execute(sql);
        
        sql = "INSERT INTO sat_users(name, email, password, department) VALUES (?, ?, ?, ?)";
        insertData(sql, "./data/sat_users.csv");
        
        sql = "INSERT INTO sat_courses(crscode, crsname) VALUES (?, ?)";
        insertData(sql, "./data/sat_courses.csv");
        
        sql = "INSERT INTO sat_scores(id, semester, semesterYear, crscode, mark) VALUES (?, ?, ?, ?, ?)";
        insertData(sql, "./data/sat_scores.csv");

         statement.close();   
	        
    }//Class main ends
    
}//InitialDataRunOnce