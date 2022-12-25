package com.details;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class pay extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		  
		String mobile=(String) req.getSession().getAttribute("mobile");
		 
		
		
		
      	
	    PrintWriter out = res.getWriter();
	    
	   
	    try {
	     
	        // loading drivers for mysql
	        Class.forName("com.mysql.jdbc.Driver");
	         
	        //creating connection with the database
	        Connection con = DriverManager.getConnection
	                    ("jdbc:mysql://localhost:3306/yoga_db","root","praneeth");

	        PreparedStatement ps = con.prepareStatement
	                    ("insert into payment(paymentid,paymentstatus,paymentdate,mobile) values(NULL,?,?,?)");
           long millis=System.currentTimeMillis();  
	        
	        
	        java.sql.Date date1 = new java.sql.Date(millis);  
	        ps.setString(1, "paid");
	        ps.setDate(2,date1);
	        ps.setString(3, mobile);
	        	        
	       int i=ps.executeUpdate(); 
	        
	        if(i > 0) {
	        	        	 
	        	out.println("<h1 style='text-align:center'>"+
	        	            "Payment done</h1>");
	        }
	    }
	     catch(Exception se) {
	            se.printStackTrace();
	        }
	    }
        
	}

