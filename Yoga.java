package com.yoga;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Yoga
 */

public class Yoga extends HttpServlet {
public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		
	res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    ArrayList<String> err=new ArrayList<>();
    String fullname = req.getParameter("fullname");
    String email = req.getParameter("email");
    // mobile number
    String mobile = req.getParameter("mobile");
    //gender
    String dob = req.getParameter("dob");
    String sess=req.getParameter("session");
    //date of birth
  
    LocalDate date = LocalDate.parse(dob);  
    Period period = Period.between(date, LocalDate.now());
    int age=period.getYears();
    
    // password
    if(mobile.length()!=10) {
    	err.add(mobile);
    }
    if(age<18 || age >65 ) {
    	err.add("age");
    }
    String pass1=req.getParameter("pass1");
    String pass2=req.getParameter("pass2");
    if(!pass1.equals(pass2)) {
    	err.add("password");
    	
    }
    if(err.size()!=0) {
        RequestDispatcher rd=req.getRequestDispatcher("/index.html");  
    	
    	rd.forward(req, res);
    }
    else {
   
    try {
     
        // loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");
         
        //creating connection with the database
        Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/yoga_db","root","praneeth");

        PreparedStatement ps = con.prepareStatement
                    ("insert into yoga_form(yid,fname,email,mobile,age,pass,batch,date_joined) values(NULL,?,?,?,?,?,?,?)");
        
        ps.setString(1, fullname);
        
        ps.setString(2, email);
        ps.setString(3,mobile);
    
        ps.setInt(4, age);
        ps.setString(5,pass1);
        ps.setString(6,sess);
        
        
        long millis=System.currentTimeMillis();  
        
        
        java.sql.Date date1 = new java.sql.Date(millis);       
           
        
        ps.setDate(7,date1);

        int i = ps.executeUpdate();
        
        if(i > 0) {
        	RequestDispatcher rd=req.getRequestDispatcher("/login.html");  
        	
        	rd.forward(req, res);        	 
        	out.println("<h1 style='text-align:center'>"+
        	            "Registration Sucessul</h1>");
        }
    }
     catch(Exception se) {
            se.printStackTrace();
        }
    }
    
   }
    
}
