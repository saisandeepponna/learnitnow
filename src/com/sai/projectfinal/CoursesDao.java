package com.sai.projectfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CoursesDao {
	private DataSource dataSource;
	public CoursesDao(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	public List<CoursesAndChapters> coursesAndChaptersList(int courseId) {
        int id = courseId; 
    
        Connection con = null;
        PreparedStatement stmt=null;
        
        ResultSet resultSet = null;

        List<CoursesAndChapters> coursesAndChaptersAl=new ArrayList<>();
        
        try
        {
            con = dataSource.getConnection(); 
            stmt=con.prepareStatement("select c.coursename,ch.chapterid,ch.chaptername,c.courseId from courses as c join chapters as ch on c.courseid=ch.courseid and c.courseid=?");  
          
            stmt.setInt(1,id);
            resultSet = stmt.executeQuery(); 
          
           
            while(resultSet.next()) 
            {
            	System.out.println(resultSet.getString("chaptername"));
            	coursesAndChaptersAl.add(new CoursesAndChapters(resultSet.getString("coursename"),resultSet.getInt("chapterid"),resultSet.getString("chaptername"),resultSet.getInt("courseId")));
            	
            }
            return coursesAndChaptersAl;
        }
            catch(SQLException e)
            {
               e.printStackTrace();
               
            }

        
        finally {
  		  close(con,stmt,resultSet);
  	  }
        coursesAndChaptersAl.add(new CoursesAndChapters("error",-1,"something went wrong",-1));
        return coursesAndChaptersAl;
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
	
	
	public String fullCourseUrl(String name) {

		    
	        Connection con = null;
	        PreparedStatement stmt=null;
	        ResultSet resultSet = null;

	        
	        
	        try
	        {
	            con = dataSource.getConnection(); 
	            stmt=con.prepareStatement("select coursedesc from courses where coursename=?");  
	          
	            stmt.setString(1,name);
	            resultSet = stmt.executeQuery(); 
	          
	           
	            while(resultSet.next()) 
	            {
	            	String url=resultSet.getString("coursedesc");
	            	
	            	return url;
	            }
	           
	        }
	            catch(SQLException e)
	            {
	               e.printStackTrace();
	              
	            }
	        finally {
	    		  close(con,stmt,resultSet);
	    	  }
	      
	        return "dfd"; 
	}
	
	
	public String fullChapterUrl(int chapterid,int courseid) {

	    
        Connection con = null;
        PreparedStatement stmt=null;
        ResultSet resultSet = null;

        
        
        try
        {
            con = dataSource.getConnection(); 
            stmt=con.prepareStatement("select chapterdesc from chapters where  chapterid=? and courseid=? ");  
          
            stmt.setInt(1,chapterid);
            stmt.setInt(2,courseid );
            resultSet = stmt.executeQuery(); 
          
           
            while(resultSet.next()) 
            {
            	String url=resultSet.getString("chapterdesc");
            	
            	return url;
            }
           
        }
            catch(SQLException e)
            {
               e.printStackTrace();
              
            }
      
        finally {
    		  close(con,stmt,resultSet);
    	  }
        return "dfd"; 
}
	
	
}
