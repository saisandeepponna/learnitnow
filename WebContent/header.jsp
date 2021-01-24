<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
  <head>
    <!-- meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="style.css"/>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Russo+One&display=swap" rel="stylesheet">
<!-- font awesome -->
<script src="https://kit.fontawesome.com/4b612e4086.js" crossorigin="anonymous"></script>
    <title>Online Courses Portal</title>
  </head>
<body>


<!--  Fixed Nav Bar, Session Validation again to close register and login functionality after a user has signed in -->

<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top " >
  <a class="navbar-brand" href="home.jsp"> <strong class="logo">LearnItNow</strong></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav ml-md-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Courses Offered
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="ControllerServlet?command=course&name=1">Java</a>
          <a class="dropdown-item" href="ControllerServlet?command=course&name=2">RDBMS</a>
          <a class="dropdown-item" href="ControllerServlet?command=course&name=3">MEAN Stack</a>
          <a class="dropdown-item" href="ControllerServlet?command=course&name=4">C++</a>
          <a class="dropdown-item" href="ControllerServlet?command=course&name=5">Data Structures</a>
          <a class="dropdown-item" href="ControllerServlet?command=course&name=6">AWS Cloud</a>
        </div>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="about.jsp">About</a>
      </li>
      
      
      <c:if test = "${checksession==null}">
         <li class="nav-item">
        <a class="nav-link" href="ControllerServlet?command=register">Register</a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="ControllerServlet?command=login">Login</a>
      </li>
      </c:if>
      
   
       <c:if test = "${checksession!=null}">
       
       <li class="nav-item">
        <a class="nav-link" href="ControllerServlet?command=profile">My Profile</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="ControllerServlet?command=logout">Logout</a>
      </li>
     
      </c:if>
     
     
     
     
    </ul>
  </div>
</nav>
<!-- To keep header position relative and at the top of the home page -->
<div style="display: inline-block;
    height: 60px;
    width: 130px;
    position: relative;">
</div>




</body>
</html>