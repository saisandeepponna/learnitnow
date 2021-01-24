package com.sai.projectfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class QuestionDao {
	  
	   private DataSource dataSource;
	   private quizQuestions [] quizQuestionsArray;
	   public QuestionDao(int cid,DataSource ds) {
			System.out.println("Object Created");
			this.dataSource=ds;
			this.quizQuestionsArray = new quizQuestions [10];
			switch(cid) {
			case 1:
				this.quizQuestionsArray=retrieveObjects(1);
				break;
			case 2:
				this.quizQuestionsArray=retrieveObjects(2);
				break;
			case 3:
				this.quizQuestionsArray=retrieveObjects(3);
				break;
			case 4:
				this.quizQuestionsArray=retrieveObjects(4);
				break;
			case 5:
				this.quizQuestionsArray=retrieveObjects(5);
				break;
			case 6:
				this.quizQuestionsArray=retrieveObjects(6);
				break;
				
			}
			
		}

		public quizQuestions[] getQuizQuestionsArray() {
			return quizQuestionsArray;
		}

		private quizQuestions[] retrieveObjects(int cid)  {
			 int id = cid; 
			 Connection con = null; 
			 PreparedStatement stmt=null;
			 ResultSet resultSet = null;
		        try
		        {
		            con = dataSource.getConnection(); 
		            stmt=con.prepareStatement("select q.questionid,q.question,o.option_text,o.correct_option from questions as q join options as o on q.questionid=o.questionid where q.courseid=?");  
		          
		            stmt.setInt(1,id);
		            resultSet = stmt.executeQuery();
		            int i=0;
		            String [] options=new String[4];
		            int correct=0;
		            int q=0;
		            while(resultSet.next()) 
		            {
		            	//System.out.println(resultSet.getString("option_text"));
		            	options[i]=resultSet.getString("option_text");
		            	if(resultSet.getInt("correct_option")==1) {
		            		correct=i;
		            	} //correct option saved as index count
		            	
		            	i++;
		             if(i%4==0) {
		            	 String quest=resultSet.getString("question");
		            	 int questId=resultSet.getInt("questionid");
		            	 quizQuestionsArray[q]=new quizQuestions(quest,options,correct,questId);
		               	q++; 
		                i=0;
		               	options=new String[4];
		               	correct=0;	
		             }
		            	
		            	
		            }
		            return quizQuestionsArray;
		        }
		            catch(SQLException e)
		            {
		               e.printStackTrace();
		               
		            }
		        finally {
		    		  close(con,stmt,resultSet);
		    	  }

		       
		        return quizQuestionsArray;
			
		
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
		



}
		
	


