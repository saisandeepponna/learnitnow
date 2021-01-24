package com.sai.projectfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

public class UserResponsesDao {
	private DataSource dataSource;
	public UserResponsesDao(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	
    public String sendUserResponse(int uid,int cid,int marks){
    
        Connection con = null;
        PreparedStatement stmt=null;       
        ResultSet resultSet = null;
        
        try
        {
            con = dataSource.getConnection(); 
            stmt=con.prepareStatement("insert into user_responses(userid,courseid,marks) values(?,?,?)");  
            stmt.setInt(1,uid);
            stmt.setInt(2,cid);
            stmt.setInt(3,marks);
            int i= stmt.executeUpdate();
            if(i!=0)
            return "SUCCESS";
        }
            catch(SQLException e)
            {
               e.printStackTrace();
               
            }

        
        finally {
  		  close(con,stmt,resultSet);
  	  }
        
        return "Some error occured, Try again later";
    }
    
    

	private void close(Connection myconn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myconn!=null) {
				myconn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<UserProfile> retreiveUserResponses(int uid) {
		 Connection con = null;
	        PreparedStatement stmt=null;       
	        ResultSet resultSet = null;
	        List<UserProfile> up=new ArrayList<>();
	        try
	        {
	            con = dataSource.getConnection(); 
	            stmt=con.prepareStatement("select ur.userid,ur.courseid,ur.marks,ur.date_time_exam,c.coursename from user_responses as ur join courses as c on ur.courseid=c.courseid where ur.userid=?");  
	            stmt.setInt(1,uid);
	            resultSet = stmt.executeQuery();
	            
	            while(resultSet.next()) 
	            {
	            	String cname=resultSet.getString("coursename");
	            	int marks=resultSet.getInt("marks");
	            	java.sql.Date d=resultSet.getDate("date_time_exam");
	            	java.sql.Time t=resultSet.getTime("date_time_exam");
	            	up.add(new UserProfile(cname, marks, d, t));
	            }
	            return up;
	        }
	            catch(SQLException e)
	            {
	               e.printStackTrace();
	               
	            }

	        
	        finally {
	  		  close(con,stmt,resultSet);
	  	  }
	        new UserProfile("UnReachable", -1, new java.sql.Date(1223L),new java.sql.Time(1223L));
	        return up;
	}

	
	
}
