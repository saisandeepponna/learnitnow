<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ page import = "com.sai.projectfinal.*, java.util.*" %>

<!DOCTYPE html">
<html>
<title>Profile</title>
  <jsp:include page="/header.jsp"/>
<body>
<%
List<UserProfile> ls=(List<UserProfile>)session.getAttribute("userResponses");
%>
<div class="container jumbotron ">
<div class="alert alert-light">
<div class="text-center ">
<h4>HI, <span class="h2 text-dark"> <%=session.getAttribute("unamesession") %></span></h4> 
<p class="text-muted"><small>You can now access all courses. Visit <a href="home.jsp">Homepage </a></small></p>
</div>
<hr class="col-xs-12" />
<%if(ls.size()==0) {%>
<p class="h6 text-center"><em>Watch courses and take quiz to get the result and certificate here</em></p>
<% } else{%>
<p class="h5">Certificates : </p>
     <%if(((List<UserProfile>)session.getAttribute("forcert")).size()==0){ %>
     <div class="alert alert-warning text-center">
   You didn't score enough marks in any course quiz to receive a certificate
      </div>
       
     <%} else{%>
       <div class="alert alert-info ">
         <small>Get your Learn"IT"now certificate here : </small>
         <form action="ControllerServlet" method="POST">
        <input type="hidden" name="command" value="certificate">
        <button type="submit" class="btn btn-success btn-md ">Download Certificate</button>
         </form>
      </div>
      <%} %>

<hr class="col-xs-12" />
<p class="h6">Result card for all courses: </p>
<table class="table border border-dark">
  <thead class="thead-dark">
    <tr>
    <th scope="col">Course Name</th>
      <th scope="col">Marks</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
    </tr>
  </thead>

<% for(int i=0;i<ls.size();i++){ %>
      <tbody>
    <tr>
       <td> <%=ls.get(i).getCourseName() %></td>
      <td><%= ls.get(i).getMarks()   %></td>
      <td><%= ls.get(i).getDate() %></td>
      <td><%= ls.get(i).getTime() %></td>
    </tr>
  </tbody>
   
<%}; %>
</table>

<%} %>

</div>
</div> 
</body>
<jsp:include page="/footer.html"/>
</html>