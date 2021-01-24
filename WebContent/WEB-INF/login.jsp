<!DOCTYPE html">
<html>
<title>Login</title>

  <jsp:include page="/header.jsp"/>
<body>

<div class="container jumbotron jumbotron-fluid">

<div style=" width: 70%;margin: 0 auto;padding: 10% 10%;" class=" border border-primary mb-3 container">

    <form action="ControllerServlet" method="post" autocomplete="off">
		<h2>Sign In</h2>

        
         <div class="form-group">
        	<input type="text" class="form-control" name="username" id="uname" placeholder="Username" required="required">
            <span id="pu" class="text-danger"></span>
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
       <input type="hidden" name="command" value="login">
        </div>
		
        
        
        <div class="form-group">
			<p style="color:red" ><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%>	</p>
			</div>
		
			
		
		<div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block" id="rgbut">Sign In</button>
        </div>
    </form>
	<div class="text-center">Don't have an account? <a href="ControllerServlet?command=register">Register Now</a></div>
</div>

</div>





     
</body>
<jsp:include page="/footer.html"/>
</html>