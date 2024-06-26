# SAT: Self-serve Academic Transcript
Author: Mohammad Bhuiyan  
Date: 2021-12-18  
ID: 215808420  
Email: bhui8420@mylaurier.ca

## About this Project

Students will be able to send their academic transcript to desired institutions via email.<br/><br/>
Students have the privilege to view and send academic record and change login information.<br/><br/>
Student records can be added, updated or deleted by logging in the application as Administrator.<br/><br/>
There are REST API interfaces to access, edit or add student records and send transcript via email.

## Credentials, Database, URLs
```
MySQL/ phpMyAdmin: Database: test; user name: root; password: [empty]; Tables: sat_users, sat_scores, sat_courses
SAT admin: admin@gmail.com, admin123
SAT user: login as admin and get the email and password for a user [e.g. mmohinb@gmail.com; mmb900]
Application URL: http://localhost:8080/sat/index.html
REST API: http://localhost:8080/sat/ChangePasswordForm?id=[id] e.g. id =10005
          http://localhost:8080/sat/ViewRecordForm?id=[id]
          http://localhost:8080/sat/TranscriptForm?id=[id]

```

## User Guide

1.	Run Apache and MySQL from XAMPP control panel<br/><br/>
2.	Open project in Eclipse [e.g. "C:\cp630\workspace\project"]<br/><br/>
3.	Start server Wildfly 18 [independently or within Eclipse]<br/><br/>
4.  Confirm user name and password of test database in MySQL matches with the user name and password used in /sat/src/wlu/mmb/dao/DB.java<br/><br/>
5.	Run /sat/src/wlu/mmb/dao/InitialDataRunOnce.java as “Java Application” [right click on InitialDataRunOnce.java, run as Java Application]<br/><br/>
Run it once (multiple run will not do any harm since once the tables are created and populated with data, it will not do any changes)  
Three tables will be created in test database: sat\_users; sat\_scores; sat\_courses  
The tables will be populated with data from data files sat\_users.csv, sat\_scores.csv, sat\_courses.csv  
The files contain record of 500 student  
These test data were generated by /sat/data/genData4sat.py  
Check the database in browser: http://localhost/phpmyadmin/<br/><br/>
   
6.	Export war file (/sat/target/sat.war); [in Eclipse, right click on sat >> export >> WAR file ]<br/><br/>
7.	Open browser, go to http://localhost:8080/, login wildfly, click on “Administration Console”, drag and drop the war file in the deployment window<br/><br/>
8.	Login the application in browser: http://localhost:8080/sat/index.html<br/><br/>



 


