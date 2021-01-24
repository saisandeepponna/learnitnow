	package com.sai.projectfinal;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.sql.DataSource;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sai.projectfinal.RegisterBean;

 
public class RegisterDao { 
	private DataSource dataSource;
	public RegisterDao(DataSource dataSource){
		this.dataSource=dataSource;
	}
     public String registerUser(RegisterBean registerBean) throws NoSuchAlgorithmException, InvalidKeySpecException
     {
         String firstName = registerBean.getFirstName();
         String lastName = registerBean.getLastName();
         String email = registerBean.getEmail();
         String userName = registerBean.getUserName();
         String password = generateStrongPasswordHash(registerBean.getPassword());
         
         Connection con = null;
         PreparedStatement preparedStatement = null;  
         try
         {
             con = dataSource.getConnection();
             String query = "insert into users(firstName,lastName,Email,userName,password) values (?,?,?,?,?)";
             preparedStatement = con.prepareStatement(query); 
             preparedStatement.setString(1, firstName);
             preparedStatement.setString(2, lastName);
             preparedStatement.setString(3, email);
             preparedStatement.setString(4, userName);
             preparedStatement.setString(5, password);
             
             int i= preparedStatement.executeUpdate();
             
             if (i!=0)  //Just to ensure data has been inserted into the database
             return "SUCCESS"; 
         }
         catch(MySQLIntegrityConstraintViolationException e) {
        	 System.out.println(e.getMessage());
        	 return "username or email exists, Choose another one";
         }
         catch(SQLException e)
         {
            e.printStackTrace();
         }    
         
         
         finally {
    		  closeOne(con,preparedStatement);
    	  }
         
  
         return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
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
     
     
     
     private void closeOne(Connection myconn, Statement myStmt) {
 		try {
 			
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
     
     
     
     
     
     
     
     
     
     //Using PBKDF2WithHmacSHA1 algorithm for storing password in to Database
     private static String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
     {
         int iterations = 1000;
         char[] chars = password.toCharArray();
         byte[] salt = getSalt();
          
         PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
         SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
         byte[] hash = skf.generateSecret(spec).getEncoded();
         return iterations + ":" + toHex(salt) + ":" + toHex(hash);
     }
      
     private static byte[] getSalt() throws NoSuchAlgorithmException
     {
         SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
         byte[] salt = new byte[16];
         sr.nextBytes(salt);
         return salt;
     }
      
     private static String toHex(byte[] array) throws NoSuchAlgorithmException
     {
         BigInteger bi = new BigInteger(1, array);
         String hex = bi.toString(16);
         int paddingLength = (array.length * 2) - hex.length();
         if(paddingLength > 0)
         {
             return String.format("%0"  +paddingLength + "d", 0) + hex;
         }else{
             return hex;
         }
     }
     
     
     
     //For Ajax calls
     
     public  String checkUserExists(String s) {
         
         String userName = s;
        
         Connection con = null;
         Statement statement = null;
         ResultSet resultSet = null;


         try
         {
             con = dataSource.getConnection(); 
             statement = con.createStatement(); 
             resultSet = statement.executeQuery("select userName from users"); 

             while(resultSet.next()) 
             {
             String userNameDB = resultSet.getString("userName"); 

               if(userName.equals(userNameDB))
               {
                  return "USERNAME ALREADY EXISTS"; 
               }
             }}
             catch(SQLException e)
             {
                e.printStackTrace();
             }
         finally {
    		  close(con,statement,resultSet);
    	  }
             return "USERNAME AVAILABLE"; 
     }
     
     
     public  String checkEmailExists(String s) {
         
         String email = s;
        
         Connection con = null;
         Statement statement = null;
         ResultSet resultSet = null;


         try
         {
             con = dataSource.getConnection(); 
             statement = con.createStatement(); 
             resultSet = statement.executeQuery("select email from users"); 

             while(resultSet.next()) 
             {
             String emailDB = resultSet.getString("email"); 

               if(email.equals(emailDB))
               {
                  return "EMAIL ALREADY EXISTS"; 
               }
             }}
             catch(SQLException e)
             {
                e.printStackTrace();
             }
         finally {
    		  close(con,statement,resultSet);
    	  }
             return "EMAIL AVAILABLE"; 
     }
     
     
     
      
}

