<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<script> 

function validate()
{ 
    
     var password = $('[name="password"]').val();
     var confirmpassword= $('[name="confirmpassword"]').val();
     
 if(password.length<6)
     { 
    	 $("#wrnmsg").show();
    	 $("#wrnmsg").html("Password must be at least 6 characters long.");
     alert("Password must be at least 6 characters long."); 
     return false; 
     } 
     else if (password!=confirmpassword)
     { 
    	 $("#wrnmsg").show();
    	 $("#wrnmsg").html("Confirm Password should match with the Password");
     alert("Confirm Password should match with the Password"); 
     return false; 
     } 
 } 


</script> 
</head>
<jsp:include page="/header.jsp"/>
<body>


<div class="container jumbotron jumbotron-fluid">

<div style=" width: 70%;margin: 0 auto;padding: 5% 5%;" class=" border border-primary mb-3 container">

    <form action="ControllerServlet" method="post" onsubmit="return validate()" autocomplete="off">
		<h2>Register</h2>
		<p class="hint-text">Create your account. It's free and only takes a minute.</p>
        <div class="form-group">
			<div class="row">
				<div class="col"><input type="text" class="form-control" name="firstname" placeholder="First Name" required="required"></div>
				<div class="col"><input type="text" class="form-control" name="lastname" placeholder="Last Name" required="required"></div>
			</div>        	
        </div>
        <div class="form-group">
        	<input type="email" class="form-control" name="email" id="mail" placeholder="Email" required="required">
            <p id="pr" class="text-danger"></p>
            <p id="pt" class="text-warning"></p>
        </div>
         
        
         <div class="form-group">
        	<input type="text" class="form-control" name="username" id="uname" placeholder="Username" required="required">
            <p id="pu" class="text-danger"></p>
            <p id="pp" class="text-warning"></p>
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="confirmpassword" placeholder="Confirm Password" required="required">
             <input type="hidden" name="command" value="register">
        </div>        
        
        <div class="text-center alert alert-warning" role="alert" id="wrnmsg">
            
        </div>
        
			<div class="form-group">
			<p style="color:red" ><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%>	</p>
			</div>
		
		<div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block" id="rgbut">Register Now</button>
        </div>
    </form>
	<div class="text-center">Already have an account? <a href="ControllerServlet?command=login">Sign in</a></div>
</div>

</div>


        
    <jsp:include page="/footer.html"/>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  
  <script src="https://code.jquery.com/jquery-3.1.1.min.js">
   </body>

   <jsp:include page="/footer.html"/>

<script type="text/javascript">
$(()=>{
	
	$("#wrnmsg").hide();
	
	$("#uname").blur(()=>{
		if($("#uname").val().length>0){
		if($("#uname").val().length>=6){
			$("#pu").html("");
			$("#pp").html("");
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"ControllerServlet",
			data:{
			    uname:$("#uname").val(),
			    command:"ajaxuname"
			},
			success:(s)=>{
				$("#pp").html(s);
				setTimeout(()=>{
					$("#pp").html("");
				},10000)
			},
			error:(e)=>{
				alert(JSON.stringify(e));
				
				$("#pp").html(JSON.stringify(e));
			}
			
		})	
		}		
		else{
			$("#pu").html("Username should contain atleast 6 characters");
			setTimeout(()=>{
				$("#pu").html("");
			},3000)
			
		}
		}
	});
	
	
	

	
	
	$("#mail").blur(()=>{
		if($("#mail").val().length>0){
		if($("#mail").val().length>=8 && $("#mail").val().includes("@") && $("#mail").val().includes(".")){
		
			$("#pr").html("");
	
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"ControllerServlet",
			data:{
			    email:$("#mail").val(),
			    command:"ajaxemail"
			},
			success:(s)=>{
				
				
				$("#pt").html(s);
				setTimeout(()=>{
					$("#pt").html("");
				},10000)
			},
			error:(e)=>{
				alert(JSON.stringify(e));
				
				$("#pr").html(JSON.stringify(e));
			}
			
		})
		
		
		
		
		}
		else{
			$("#pr").html("email length should contain atleast 8 characters with valid @ and .");
			setTimeout(()=>{
				$("#pr").html("");
			},3000)
		}
		}
	});
	
})




</script>

</html>