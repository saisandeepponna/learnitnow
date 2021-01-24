<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ page import = "com.sai.projectfinal.*, java.util.*" %>


<title>Full Course</title>

  <jsp:include page="/header.jsp"/>
<body>

<% String courseName=(String)request.getAttribute("CoursesNames"); 
String url=(String)request.getAttribute("urls");
%>
<div class="container jumbotron">

<h3 class="text-center font-weight-normal text-primary" > <%=courseName %> Full Video Playlist</h3>
<center>

<div class="embed-responsive embed-responsive-16by9">
 
 <iframe class="embed-responsive-item" src="<%=url%>" width="750" height="563"></iframe>
</div>
</center>
 </div>    
</body>
<jsp:include page="/footer.html"/>
