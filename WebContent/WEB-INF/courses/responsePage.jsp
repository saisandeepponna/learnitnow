<!DOCTYPE html">
<html>
<title>ResponsePage</title>

  <jsp:include page="/header.jsp"/>
<body>
<script>
if ( window.history.replaceState ) {
	  window.history.replaceState( null, null, window.location.href );
	}
</script>
<div class="container jumbotron">

<div class="alert alert-success">
  Thank you for taking <%=request.getAttribute("cname") %> quiz, <%=session.getAttribute("unamesession") %> .
  <hr class="col-xs-12"/>
  <h3>Your Score is : </h3><h1><%=request.getAttribute("marks") %></h1>
  <p class="text-muted">Go to your <a href="ControllerServlet?command=profile">profile</a> to know your certificate status and attempts</p>
  
</div>
</div>

     
</body>
<jsp:include page="/footer.html"/>
</html>