package com.details;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	       res.setContentType ("text/html");
	        PrintWriter out = res.getWriter ();
	        ArrayList<String> err=new ArrayList<>();
	      
	        String num = req.getParameter ("mobile");
	        String pass=req.getParameter("pass1");       
	        try {
	            
	            // loading drivers for mysql
	            Class.forName("com.mysql.jdbc.Driver");
	             
	            //creating connection with the database
	            Connection con = DriverManager.getConnection
	                        ("jdbc:mysql://localhost:3306/yoga_db","root","praneeth");
	            
	             
	             
	            
	            PreparedStatement ps=con.prepareStatement("select * from yoga_form where mobile=?");
	            ps.setString(1, num);
	            ResultSet  rs = ps.executeQuery();
	             
	              if(rs.next()) {
	            	  if(rs.getString("pass").equals(pass) && rs.getString("mobile").equals(num)){
	            		 
	            		  
	            		 

	                     req.setAttribute("mobile", num);
	                    
	            		  RequestDispatcher rd=req.getRequestDispatcher("/payment.jsp");  
	            	      	
	            	      rd.forward(req, res);
	                      
	  	              	  
	  	              	  
	            		
	            	  }
	            	  else {
	            		  RequestDispatcher rd=req.getRequestDispatcher("/index.html");  
	  	              	
		              	  rd.forward(req, res);

	            	  }
	              }
	              else {
	            	  RequestDispatcher rd=req.getRequestDispatcher("/index.html");  
	              	
	              	  rd.forward(req, res);
	              }
	             con.close();  
	            
	           
	        }
	         catch(Exception se) {
	                se.printStackTrace();
	            }
	  
	    }
		
	}

