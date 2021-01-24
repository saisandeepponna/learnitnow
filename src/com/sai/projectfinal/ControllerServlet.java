package com.sai.projectfinal;

import java.util.List;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//for json
import com.google.gson.Gson;


import java.io.*;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
//Document Object
import com.itextpdf.text.Document;
//For adding content into PDF document
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;





@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Resource(name="jdbc/saifinal")
    private DataSource datasource;
	
	
    public ControllerServlet() {
        super();
      
    } 
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
	//To log all the parameters in the server side
		try{
			
			
		System.out.println("hello there");
		System.out.println(request.getMethod());
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		    }
		
		//I have used switch-case statement to respond to particular commands user has provided
			String theCommand=request.getParameter("command");
			if(theCommand==null) {
				theCommand="home";
			}
			switch(theCommand) {
			case "home":
				request.getRequestDispatcher("home.jsp").forward(request, response);
				break;
			case "login":
				HttpSession sessionCheck1 = request.getSession();
				if (sessionCheck1.getAttribute("checksession")!=null) {
					request.getRequestDispatcher("WEB-INF/LoginHome.jsp").forward(request, response);
				}else 
					request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
					
			
				break;
			case "register":
				HttpSession sessionCheck2 = request.getSession();
				if (sessionCheck2.getAttribute("checksession")!=null) {
					request.getRequestDispatcher("WEB-INF/LoginHome.jsp").forward(request, response);
				}else 
				request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
				break;
			case "logout":
				 HttpSession session=request.getSession();  
		            session.invalidate(); 
		            session = request.getSession(false);
				response.sendRedirect("home.jsp");
				break;
			case "course":
				coursesSwitch(request,response);
				break;
			case "coursefull":
		        System.out.println(request.getParameter("name"));
		        fullCourse(request,response);
				break;
			case "chaptersfull":
				fullChapters(request,response);
				break;
			case "quiz":
				quizTest(request,response);
				break;
			case "profile":
				profileCheck(request,response);
				break;
			default:
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			
		
	}
		catch(Exception e) {
		e.printStackTrace();
	}
		
		
		
	}


//ajax calls are to be managed in doPost method. Parsing the  json data
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		
		System.out.println(request.getMethod());
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		
		
		
		String command=request.getParameter("command");
		switch(command) {
		case "login":	     
			     if(loginCheck(request,response)) {
			    	 HttpSession newSession = request.getSession(true);
			    	 newSession.setMaxInactiveInterval(500);
			    	 newSession.setAttribute("checksession", true);
			    	 newSession.setAttribute("unamesession", request.getAttribute("userName")); 
			    	 newSession.setAttribute("uidsession", request.getAttribute("userid")); 
			    	 request.getRequestDispatcher("home.jsp").forward(request, response);
				       System.out.println("Session Id : " + newSession.getId() + "<br>");
				       System.out.println((String)request.getAttribute("userName"));
				       System.out.println((String)request.getAttribute("userid"));
			     }

				       else
				      request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			break;
		case "register":
			System.out.println("regiter call from front-end");
			     if(registerUser(request,response))
			    	 request.getRequestDispatcher("WEB-INF/RegistrationSuccess.jsp").forward(request, response);
			     else
			    	 request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
			break;
		case "ajaxuname":
			String msg="Username available";
			if(ajaxUsernameCheck(request,response)) {
				String json = new Gson().toJson(msg);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
			}else {
				msg="Username already exists, choose another name";
				String json = new Gson().toJson(msg);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
			}
			
			break;
		case "ajaxemail":
			String emailmsg="Email available to register";
			if(ajaxEmailCheck(request,response)) {
				String json = new Gson().toJson(emailmsg);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
			}else {
				emailmsg="Email already registered, <a class=\"nav-link\" href=\"ControllerServlet?command=login\">Login</a>";
				String json = new Gson().toJson(emailmsg);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
			}
			break;

		case "quiz":
			quizValidation(request,response);
			break;
		case "certificate":
			certificateValidation(request,response);
			break;		
	    default:
	    	request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
	}
	


	private void quizValidation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession sessionCheck1 = request.getSession();
		if (sessionCheck1.getAttribute("checksession")!=null) {
			
			QuestionDao qs=new QuestionDao(Integer.parseInt(request.getParameter("commandCourse")),datasource);
			quizQuestions questionsFull[]=qs.getQuizQuestionsArray();
			int correctAnswers=0;
			  for(quizQuestions q:questionsFull) {
				  if(q.getAnswers()==Integer.parseInt(request.getParameter(Integer.toString(q.getQuestionId())))) {
					  correctAnswers++;
				  }
			  }
			  UserResponsesDao urd=new UserResponsesDao(datasource);
			 String msg= urd.sendUserResponse(Integer.parseInt((String)sessionCheck1.getAttribute("uidsession")),Integer.parseInt(request.getParameter("commandCourse")) , correctAnswers);
			 System.out.println(msg);
			 if(msg.equals("SUCCESS")) {
				 request.setAttribute("marks",correctAnswers);
				 request.setAttribute("cname",request.getParameter("commandCourseName") );
				// processRequest(request,response);
				 request.getRequestDispatcher("WEB-INF/courses/responsePage.jsp").forward(request, response);
			 }else { 
				request.getRequestDispatcher("home.jsp").forward(request, response);
			 }
			 
			 
		} else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		
	    }	
}

	
	
	private void certificateValidation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionCheck1 = request.getSession();
		if (sessionCheck1.getAttribute("checksession")!=null) { 
			System.out.println(((List<UserResponseRecordForCertificate>)sessionCheck1.getAttribute("forcert")).get(0).getHighestMarks());
			processRequest(request,response);
		}else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
	
     }

private void profileCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession sessionCheck1 = request.getSession();
	if (sessionCheck1.getAttribute("checksession")!=null) { 
		UserResponsesDao urd=new UserResponsesDao(datasource);
		System.out.println(urd.retreiveUserResponses(Integer.parseInt((String)sessionCheck1.getAttribute("uidsession"))));
		
		List<UserProfile>ls=urd.retreiveUserResponses(Integer.parseInt((String)sessionCheck1.getAttribute("uidsession")));
		List<UserResponseRecordForCertificate> cert=new ArrayList<>();
		ls.forEach((e)->{
			if(e.getMarks()>=7){
				cert.add(new UserResponseRecordForCertificate(e.getCourseName(),e.getMarks(),e.getDate(),e.getTime()));
			}
		});
		sessionCheck1.setAttribute("forcert",cert);
		sessionCheck1.setAttribute("userResponses",ls);
		
		request.getRequestDispatcher("WEB-INF/LoginHome.jsp").forward(request, response);
	}else {
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}
	
}
	
	
	
	
	private boolean loginCheck(HttpServletRequest request, HttpServletResponse response) {
		 String userName = request.getParameter("username");
	        String password = request.getParameter("password");
	         boolean verified=false;
	        LoginBean loginBean = new LoginBean(); 
	 
	        loginBean.setUserName(userName); 
	         loginBean.setPassword(password);
	 
	        LoginDao loginDao = new LoginDao(datasource); 
	 
	        String userValidate;
			try {
				userValidate = loginDao.authenticateUser(loginBean);
				System.out.println(userValidate);
				String suc1=userValidate.split("-")[0];
				String suc2=userValidate.split("-")[1];
				System.out.println("uid"+suc2);
				if(suc1.equals("SUCCESS")) 
		         {
					verified=true; 
		             request.setAttribute("userName", userName);
		             request.setAttribute("userid", suc2);
		             return verified;
		            
		         }
		         else
		         {
		        	 userValidate="Invalid User Credentials";
		             request.setAttribute("errMessage", userValidate); 
		             return verified;
		            
		         }
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			
				e.printStackTrace();
			}
			return verified; 

	}
	
	
	private boolean registerUser(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        boolean successRegistration=false;
        
        RegisterBean registerBean = new RegisterBean();
       
        registerBean.setFirstName(firstName);
        registerBean.setLastName(lastName);
        registerBean.setEmail(email);
        registerBean.setUserName(userName);
        registerBean.setPassword(password); 
      
        RegisterDao registerDao = new RegisterDao(datasource);
        
        String userRegistered;
		try {
			userRegistered = registerDao.registerUser(registerBean);
			 if(userRegistered.equals("SUCCESS"))   
	         {
				 successRegistration=true;
				 return successRegistration;
	         }
	         else   
	         {
	            request.setAttribute("errMessage", userRegistered);
	              return successRegistration;
	            
	         }
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
		
			e.printStackTrace();
		}
		return successRegistration;
        
	}
	
	//courses
	private void coursesSwitch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionCheck1 = request.getSession();
		if (sessionCheck1.getAttribute("checksession")!=null) {
			int name=Integer.parseInt(request.getParameter("name"));
	        CoursesDao cd=new CoursesDao(datasource);
	        List<CoursesAndChapters> ls=cd.coursesAndChaptersList(name);
	        System.out.println(ls);
	        request.setAttribute("ListOfChapters", ls);
			request.getRequestDispatcher("WEB-INF/courses/course.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
	}
	
	
	//ajax checks
	public boolean ajaxUsernameCheck(HttpServletRequest request, HttpServletResponse response) {

		String username=request.getParameter("uname");
		 RegisterDao rd=new RegisterDao(datasource);
		if(rd.checkUserExists(username).equalsIgnoreCase("USERNAME ALREADY EXISTS")) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	private boolean ajaxEmailCheck(HttpServletRequest request, HttpServletResponse response) {
		String email=request.getParameter("email");
		 RegisterDao rd=new RegisterDao(datasource);
		if(rd.checkEmailExists(email).equalsIgnoreCase("EMAIL ALREADY EXISTS")) {
			return false;
		}else {
			return true;
		}
	}

	
	
	//course full
	private void fullCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionCheck1 = request.getSession();
		if (sessionCheck1.getAttribute("checksession")!=null) {
			String n=request.getParameter("name");
			CoursesDao cd=new CoursesDao(datasource);
			String nn=cd.fullCourseUrl(n);
			System.out.println(nn);
			System.out.println(n+"regterjavaaaaaaaaaa");
			request.setAttribute("CoursesNames",n );
			request.setAttribute("urls",nn);
			request.getRequestDispatcher("WEB-INF/courses/course1full.jsp").forward(request, response);
	       
			
		} else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		
	}
	
	
	    }
	
	
	//chapters full page
	private void fullChapters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionCheck1 = request.getSession();
		if (sessionCheck1.getAttribute("checksession")!=null) {
			int chapterid=Integer.parseInt(request.getParameter("chapterid"));
			int courseid=Integer.parseInt(request.getParameter("courseid"));
		
			String chapname=request.getParameter("chapternames");
			String courname=request.getParameter("coname");
			CoursesDao cd=new CoursesDao(datasource);
			String chapterurl=cd.fullChapterUrl(chapterid,courseid);
			System.out.println(courseid+"correct course id");
			System.out.println(chapterid+"correct chapter id");
			System.out.println(chapname+"jfdghjfgh");
			request.setAttribute("chapterIds",chapterid);
			request.setAttribute("courseIds", courseid);
			request.setAttribute("chapterurls",chapterurl);
			request.setAttribute("chapternamess",chapname);
			request.setAttribute("coursenamess",courname);
			request.getRequestDispatcher("WEB-INF/courses/chaptersfull.jsp").forward(request, response);	
		} else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		
	    }
	}
	

	//quiz route
	private void quizTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionCheck1 = request.getSession();
		if (sessionCheck1.getAttribute("checksession")!=null) {
			request.setAttribute("cname",(String)request.getParameter("coursename"));
			request.setAttribute("cid",(String)request.getParameter("name"));
			QuestionDao qs=new QuestionDao(Integer.parseInt(request.getParameter("name")),datasource);
			quizQuestions questionsFull[]=qs.getQuizQuestionsArray();
			request.setAttribute("quizArray", questionsFull);		
			request.getRequestDispatcher("WEB-INF/courses/quiz.jsp").forward(request, response);	
		} else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		
	    }
	
	
	 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//certificate
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Set content type to application 
        //browser will open the document only if this is set
        response.setContentType("application/pdf");
        //Get the output stream for writing PDF object        
        OutputStream out=response.getOutputStream();
        try {
          //  Document document = new Document();
        	Document document = new Document(PageSize.A4, 0f, 0f, 0f, 0f);           
            PdfWriter.getInstance(document, out);
            
          //headings for certificate
            Font blue = new Font(FontFamily.HELVETICA, 30, Font.NORMAL, BaseColor.BLUE);
            Chunk blueText = new Chunk("Learn'IT'now", blue);
            Paragraph p = new Paragraph(blueText);
            p.setAlignment(Element.ALIGN_CENTER);
           
     
            Font red = new Font(FontFamily.HELVETICA, 20, Font.NORMAL, BaseColor.RED);
            Chunk redText = new Chunk("Certificate", red);        
            Paragraph p1 = new Paragraph(redText);
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(30f);
     

            HttpSession sessionCheck1 = request.getSession();
            Font gray = new Font(FontFamily.TIMES_ROMAN, 25, Font.BOLDITALIC, BaseColor.BLACK);
            Chunk grayText = new Chunk((String)sessionCheck1.getAttribute("unamesession"), gray);
            Paragraph p2 = new Paragraph(grayText);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(5f);
            
            
            Font lgray = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY);
            Chunk lgrayText = new Chunk("has successfully completed quiz for the following courses", lgray);
            Paragraph p3 = new Paragraph(lgrayText);
            p3.setAlignment(Element.ALIGN_CENTER);
            p3.setSpacingAfter(10f);
          
            DottedLineSeparator separator = new DottedLineSeparator();
            separator.setPercentage(59500f / 523f);
            Chunk linebreak = new Chunk(separator);
            
            
            Paragraph p4 = new Paragraph("");
            p4.setAlignment(Element.ALIGN_CENTER);
            p4.setSpacingAfter(15f);
            
            ArrayList<UserResponseRecordForCertificate> userCert =(ArrayList<UserResponseRecordForCertificate>)sessionCheck1.getAttribute("forcert");
            String [] courseName=new String[] {"Java","RDBMS","MEAN Stack","CPP","Data Structures","AWS Cloud"};
            int[] highestScore=new int[]{0,0,0,0,0,0};
            int[] attempts=new int[]{0,0,0,0,0,0};
            System.out.println(userCert.get(0));
            
            userCert.forEach((e)->{
            	switch(e.getCourseName()) {
            	case "Java":
            		attempts[0]++;
            		if(highestScore[0]<e.getHighestMarks()) {
            		highestScore[0]=e.getHighestMarks();
            		}
            		break;
            	case "RDBMS":
            		attempts[1]++;
            		if(highestScore[1]<e.getHighestMarks()) {
            		highestScore[1]=e.getHighestMarks();
            		}
            		break;
            	case "MEAN Stack":
            		attempts[2]++;
            		if(highestScore[2]<e.getHighestMarks()) {
            		highestScore[2]=e.getHighestMarks();
            		}
            		break;
            	case "cpp":
            		attempts[3]++;
            		if(highestScore[3]<e.getHighestMarks()) {
            		highestScore[3]=e.getHighestMarks();
            		}
            		break;
            	case "Data Structures":
            		attempts[4]++;
            		if(highestScore[4]<e.getHighestMarks()) {
            		highestScore[4]=e.getHighestMarks();
            		}
            		break;
            	case "AWS Cloud":
            		attempts[5]++;
            		if(highestScore[5]<e.getHighestMarks()) {
            		highestScore[5]=e.getHighestMarks();
            		}
            		break;
            	default:
            		break;
            	}
            });
            
            document.open();
            document.add(p);
            document.add(p1);
            document.add(p2);
            document.add(p3);
            document.add(linebreak);
            document.add(p4);
             for(int i=0;i<highestScore.length;i++) {
            	if(highestScore[i]>0) {
            		Font l1 = new Font(FontFamily.TIMES_ROMAN, 20, Font.NORMAL, BaseColor.RED);
                    Chunk l1Text = new Chunk("     "+courseName[i]+" : ", l1);
                    Paragraph p5 = new Paragraph(l1Text);
                    p5.setAlignment(Element.ANNOTATION);
                   p5.setSpacingAfter(3f);
                   document.add(p5);            
                   Font l2 = new Font(FontFamily.COURIER, 14, Font.BOLD, BaseColor.MAGENTA);
                   Chunk l2Text = new Chunk("          Highest Score is '"+highestScore[i]+"' and No. of Passed Attempts are '"+attempts[i]+"'", l2);
                   Paragraph p6 = new Paragraph(l2Text);
                   p6.setAlignment(Element.ANNOTATION);
                  p6.setSpacingAfter(8f);
                  document.add(p6);
            	}
             }
             DottedLineSeparator separator1 = new DottedLineSeparator();
             separator.setPercentage(50000f / 500f);
             Chunk linebreak1 = new Chunk(separator1);
             document.add(linebreak1);
             
             Font l3 = new Font(FontFamily.HELVETICA, 20, Font.ITALIC, BaseColor.DARK_GRAY);
             Chunk l3Text = new Chunk("This Website is designed by Sai Sandeep Ponna", l3);
             Paragraph p6 = new Paragraph(l3Text);
             p6.setAlignment(Element.ALIGN_CENTER);
            p6.setSpacingAfter(3f);
            document.add(p6);
 
            document.close();
        }
                catch (DocumentException exc){
                throw new IOException(exc.getMessage());
                }
        finally {            
            out.close();
        }
    }

	

	
	
}
	
