<!-- The above tag imports are for jsp scriptlet and expression tags to work although I have'nt used Function tags anywhere. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "com.sai.projectfinal.*, java.util.*,java.lang.reflect.Array" %>

<!-- Header remain fixed for all web pages. It is a fixed header so that while scrolling down the page it stays intact. -->
  <jsp:include page="/header.jsp"/>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
    <%
quizQuestions[] aa=(quizQuestions[])request.getAttribute("quizArray");
  
%>
  <script >

 function validate(){
	
		 for(var i=0;i<aa.length;i++){
		if( $("input[name="+aa[i].getQuestionId()+"]:checked").val()==null){
			alert("Answer All Questions to Submit");
			return false;
		}
		 }
	 }
 if ( window.history.replaceState ) {
 	  window.history.replaceState( null, null, window.location.href );
 	}
  </script>

  <body >
  
  


<div class="container jumbotron">
  <h1 class="text-center text-dark font-weight-normal display-3"><%=request.getAttribute("cname") %> Quiz</h1>
  <hr class="col-xs-12" />
 
<form method="POST" action="ControllerServlet"  onsubmit="return validate()" autocomplete="off">
  <div class="container jumbotron">
<% for(int i=0;i<aa.length;i++){%>
<div class="card  mb-3">
<div class="card-header bg-secondary text-white">  <%=aa[i].getQuestions()  %>  </div>
<ul class="list-group">
 <% for(int j=0;j<aa[i].getOptions().length;j++){   %>
 <li class="list-group-item"><label><input type="radio" name=<%=aa[i].getQuestionId()%> value=<%=j%> >  <%=aa[i].getOptions()[j] %></label></li>

  <%} %>
</ul>
</div>
<%} %>
<input type="hidden" name="command" value="quiz">
<input type="hidden" name="commandCourse" value="<%= request.getAttribute("cid")%>">
<input type="hidden" name="commandCourseName" value="<%=request.getAttribute("cname") %>">
<center>
<button type="submit" class="btn btn-success btn-lg btn-block" style="margin-top:7%">SUBMIT</button>
</center>
</div>

</form>
</div>
</body>
   <!--  Footer is arranged here. Description of footer is found on footer.jsp  -->
  <jsp:include page="/footer.html"/>

  