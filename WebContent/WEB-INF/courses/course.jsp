<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ page import = "com.sai.projectfinal.*, java.util.*" %>
<!DOCTYPE html">
<html>
<title>Course</title>

  <jsp:include page="/header.jsp"/>
<body>
<div class="container jumbotron">
<%
List<CoursesAndChapters> ls=(List<CoursesAndChapters>)request.getAttribute("ListOfChapters");
CoursesAndChapters[] ar=new CoursesAndChapters[ls.size()];
ar=ls.toArray(ar);
if(ls.size()==0){
	ls.add(new CoursesAndChapters("No course found",0,"Not set",0));
}
%>
<h1 class="text-center text-dark font-weight-normal display-3"><%=ls.get(0).getCourseName() %></h1>

<h5 class="text-center text-dark small text-muted">
Watch Entire Course <a href="ControllerServlet?command=coursefull&name=<%=ls.get(0).getCourseName() %>">here</a></h5>
<hr class="col-xs-12" />
</br>
<p style="margin-left:2%"><strong>Chapters in this Course</strong></p>


<ul class="list-group">
  <%for(int i = 0; i < ar.length; i++){%>
     <li class="list-group-item"><a href="ControllerServlet?command=chaptersfull&chapterid=<%=ls.get(i).getChapterId() %>&courseid=<%= ls.get(i).getCourseId() %>&chapternames=<%= ls.get(i).getChapterName() %>&coname=<%=ls.get(i).getCourseName()%>">  <%=ar[i].getChapterId() %>. <%=ar[i].getChapterName() %></a></li>
 <% } %>

</ul>


</br>

<p class="text-center h5">Pass <%=ls.get(0).getCourseName() %> Quiz and get certified.</p>

 <a href="ControllerServlet?command=quiz&name=<%= ls.get(0).getCourseId() %>&coursename=<%=ls.get(0).getCourseName()%>"><button class="btn-success btn-lg btn-block"><%=ls.get(0).getCourseName() %> QUIZ</button></a>



</div>

     
</body>
<jsp:include page="/footer.html"/>
</html>