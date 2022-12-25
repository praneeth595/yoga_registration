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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Batch
 */
@WebServlet(name = "Batch1", urlPatterns = { "/Batch1" })
public class Batch extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType ("text/html");
        PrintWriter out = res.getWriter ();
        ArrayList<String> err=new ArrayList<>();
      
        String sess = req.getParameter ("session");
        
        if(sess!=null) {
        try {
            
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
             
            //creating connection with the database
            Connection con = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/yoga_db","root","praneeth");
            
             
             
            
           PreparedStatement ps=con.prepareStatement("select date_joined from yoga_form where batch=?");
           ps.setString(1,sess );
           
           ResultSet  rs = ps.executeQuery();
           
           if(rs.next()) {
        	   
        	   LocalDate date = LocalDate.parse(rs.getString("date_joined"));  
        	   Period period = Period.between(LocalDate.now(),date);
        	   int mon=period.getMonths();
        	   mon=3;
        	   if(mon>=1) {
        		   System.out.println("updated");
        		   ps=con.prepareStatement("update yoga_form set batch=?");
        		   ps.setString(6, sess);
        		   
        	   }
        	   else {
        		   out.println("unable to change the session!!!");
        	   }
           }
              
            con.close();  
            
           
        }
         catch(Exception se) {
                se.printStackTrace();
            }
        }
        
        
        
	}

}
