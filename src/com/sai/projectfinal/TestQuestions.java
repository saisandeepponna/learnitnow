package com.sai.projectfinal;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public enum TestQuestions {
	QUESTIONS_RETREIVAL ;
	
	
	   private quizQuestions [] quizQuestionsArray=new quizQuestions [10];
		private TestQuestions() {
			System.out.println("Object Created");
			try {
			this.quizQuestionsArray=retrieveObjects();
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public static quizQuestions[] retreiveQuestions() throws IOException {
			System.out.println("static method called");
			return QUESTIONS_RETREIVAL.quizQuestionsArray;
		}
		
		private quizQuestions[] retrieveObjects() throws IOException {
		
		 System.out.println("OBJECTS RETRIEVAL");
			File f=new File("./File");
			String h=f.getCanonicalPath();
			System.out.println(h);
			
			
		        try (FileInputStream fileIn = new FileInputStream(f);ObjectInputStream objectIn = new ObjectInputStream(fileIn)){
		        	
		        	try {
		        		
		            while(true) {
		            	for(int i=0;i<quizQuestionsArray.length;i++) {
		            		
		            		Object obj = objectIn.readObject();
		            			quizQuestionsArray[i]=((quizQuestions)obj);
		            	
		            	}	
		            }
		        	}catch(EOFException e) {
		        		
		        	}
		        
		        }
			    
			     catch (ClassNotFoundException e) {
			                e.printStackTrace();
			            }
	                           return quizQuestionsArray;
		       
		    }	 
			
		
	

}
