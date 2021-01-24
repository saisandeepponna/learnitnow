<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ page import = "com.sai.projectfinal.*, java.util.*" %>
<!DOCTYPE html">
<html>
<title>CHAPTER WISE ACCESS</title>

  <jsp:include page="/header.jsp"/>
<body>
<div class="container jumbotron">
<% String chapName=(String)request.getAttribute("chapternamess"); 
String url=(String)request.getAttribute("chapterurls");
String courName=(String)request.getAttribute("coursenamess");
%>
<center><h1 ><%=courName %></h1></center>
<h4 class="text-center font-weight-normal text-primary" > <%=chapName %>  Chapter Video</h4>

<div class="container jumbotron embed-responsive embed-responsive-16by9">
<iframe class="embed-responsive-item" src="<%=url%>" ></iframe>
</div>
</div>

     
</body>
<jsp:include page="/footer.html"/>
</html>