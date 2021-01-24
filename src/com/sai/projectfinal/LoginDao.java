package com.sai.projectfinal;
 
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.sql.DataSource;

import com.sai.projectfinal.LoginBean;

 
public class LoginDao {
	private DataSource dataSource;
	public LoginDao(DataSource dataSource){
		this.dataSource=dataSource;
	}
     public String authenticateUser(LoginBean loginBean) throws NoSuchAlgorithmException, InvalidKeySpecException
     {
         String userName = loginBean.getUserName(); 
         String password = loginBean.getPassword();
 
         Connection con = null;
         Statement statement = null;
         ResultSet resultSet = null;
 
 
         try
         {
             con = dataSource.getConnection(); 
             statement = con.createStatement(); 
             resultSet = statement.executeQuery("select userName,password,uid from users"); 
 
             while(resultSet.next()) 
             {
             String userNameDB = resultSet.getString("userName"); 
              String passwordDB = resultSet.getString("password");
              String uid=resultSet.getString("uid");
               if(userName.equals(userNameDB) && validatePassword(password,passwordDB))
               {
                  return "SUCCESS"+"-"+uid; 
               }
             }}
             catch(SQLException e)
             {
                e.printStackTrace();
               
             }
         finally {
     		  close(con,statement,resultSet);
     	  }
         return "Invalid user-credentials"; 
            
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
     
     
     
     
     private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	        String[] parts = storedPassword.split(":");
	        int iterations = Integer.parseInt(parts[0]);
	        byte[] salt = fromHex(parts[1]);
	        byte[] hash = fromHex(parts[2]);
	         
	        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] testHash = skf.generateSecret(spec).getEncoded();
	         
	        int diff = hash.length ^ testHash.length;
	        for(int i = 0; i < hash.length && i < testHash.length; i++)
	        {
	            diff |= hash[i] ^ testHash[i];
	        }
	        return diff == 0;
	    }
	    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	    {
	        byte[] bytes = new byte[hex.length() / 2];
	        for(int i = 0; i<bytes.length ;i++)
	        {
	            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
	        }
	        return bytes;
	    }
	
     }