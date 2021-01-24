<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- The above tag imports are for jsp scriptlet and expression tags to work although I have'nt used Function tags anywhere. -->

<html>


<!-- Header remain fixed for all web pages. It is a fixed header so that while scrolling down the page it stays intact. -->



  
  <body>
  <jsp:include page="header.jsp"/>
  
<!-- I have Used Different bootstrap classes  to get the good alignment and fonts -->

<center><h1 class="intro" style="padding:1.5%;color:#ff6464">Learn "IT" Now or Never</h1></center>
<center>



<div style="background-color:#FFFFF1">

  <div class="bd-example container-xl">
    <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="images/cor2.jpg" class="d-block w-100" style="width:10%" alt="project-img">
          <div class="carousel-caption d-none d-md-block">
            <h1  class="h1"style="color:#000000; background-color:white;">On-Demand Online Courses</h1>
            
           <!-- core if tags are used here in order to check whether the user session is present or not  -->
            
             <c:if test = "${checksession==null}">
            
            <!-- all the page routing is done via controller servlet so that for unregistered users can't access the courses content via url route -->
            
            <a href="ControllerServlet?command=register"><button class="btn-lg btn-danger">Register Now</button></a>
             </c:if>
             <c:if test="${checksession!=null }">
             <button type="button">Thanks for Registering, You Can Now Access all Content</button>
             </c:if>        
        
        
        
          </div>
        </div>
        <div class="carousel-item">
          <img src="images/cor1.jpg" class="d-block w-100" style="width:10%" alt="onlinecourse-img">
          <div class="carousel-caption d-none d-md-block">

            <h1 class=" h1"style="color:#000000; background-color:white;">Take Quizzes and get Certificate.</h1>
            <p class="a"></p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="images/cour2.jpg" class="d-block w-100" style="width:10%" alt="course-img">
          <div class="carousel-caption d-none d-md-block">

            <h1 class="h1"style="color:#000000; background-color:white;">Master New Skills from Home</h1>
            <p class="a"></p>
          </div>
        </div>
        
      </div>
      <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>


<!-- cards space (Session validation is also done here)-->



<div class="jumbotron container" style="background-color:#FFFFF1;padding-bottom:0%">

<div class="container jumbotron">

<div class="bd-example">
<div class="card-deck" style="margin-bottom:4%;">
  
  <div class="card border-primary mb-3" >
  <a href="ControllerServlet?command=course&name=1">
    <img class="card-img-top" data-src="holder.js/100px180/" alt="100%x180" src="images/cour-java.png" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
    <div class="card-body " style="background-color:#f7f2e7 ;">
      <h5 class="card-title" >Java</h5>
      <p class="card-text ">-> Learn essential core java skills that are required to become a java developer.</p>
    </div>
    </a>
    
    
       
      <c:if test = "${checksession==null}">
           <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=register"><button class="card-text btn-md btn-primary" >Register to access </button></a>
    </div>
      </c:if>
      
       <c:if test = "${checksession!=null}">
    
    <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=course&name=1"><button class="card-text btn-md btn-success" >View content</button></a>
    </div>
    
      </c:if>
    
    
    
    
    
  </div>
  <div class="card border-primary mb-3">
   <a href="ControllerServlet?command=course&name=2">
    <img class="card-img-top" data-src="holder.js/100px180/" alt="100%x180" src="images/cour-db.png" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
    <div class="card-body" style="background-color:#f7f2e7 ;">
      <h5 class="card-title">Relational Database</h5>
      <p class="card-text">-> Understand SQL basics using SQL Plus Database in a simple way.</p>
     
    </div>
    </a>
      <c:if test = "${checksession==null}">
           <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=register"><button class="card-text btn-md btn-primary" >Register to access </button></a>
    </div>
      </c:if>
      
       <c:if test = "${checksession!=null}">
    
    <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=course&name=2"><button class="card-text btn-md btn-success" >View content</button></a>
    </div>
    
      </c:if>
  
  </div>
  
  
  
  <div class="card border-primary mb-3">
 <a href="ControllerServlet?command=course&name=3">
    <img class="card-img-top" data-src="holder.js/100px180/" alt="100%x180" src="images/cour-mean.png" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
    <div class="card-body" style="background-color:#f7f2e7 ;">
      <h5 class="card-title">MEAN Stack</h5>
      <p class="card-text">-> Build Full-Stack web application using Angular, NodeJS and MongoDB.</p>
      
    </div>
    </a>
    
    
      <c:if test = "${checksession==null}">
           <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=register"><button class="card-text btn-md btn-primary" >Register to access </button></a>
    </div>
      </c:if>
      
       <c:if test = "${checksession!=null}">
    
    <div class="card-footer bg-transparent border-primary">
      <a href="ControllerServlet?command=course&name=3"><button class="card-text btn-md btn-success" >View content</button></a>
    </div>
    
      </c:if>
    
    
  </div>
 
 
 
 
  
  
</div>
</div>





<div class="bd-example">
<div class="card-deck">
  
  <div class="card border-primary mb-3" >
  <a href="ControllerServlet?command=course&name=4">
    <img class="card-img-top" data-src="holder.js/100px180/" alt="100%x180" src="images/cour-cpp.png" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
    <div class="card-body " style="background-color:#f7f2e7 ;">
      <h5 class="card-title" >Step into c++</h5>
      <p class="card-text ">-> Obtain Modern C++ Object-Oriented Programming skills.</p>
    </div>
    </a>
      <c:if test = "${checksession==null}">
           <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=register"><button class="card-text btn-md btn-primary" >Register to access </button></a>
    </div>
      </c:if>
      
       <c:if test = "${checksession!=null}">
    
    <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=course&name=4"><button class="card-text btn-md btn-success" >View content</button></a>
    </div>
    
      </c:if>
    
    
    
    
    
    
  </div>
  <div class="card border-primary mb-3">
   <a href="ControllerServlet?command=course&name=5">
    <img class="card-img-top" data-src="holder.js/100px180/" alt="100%x180" src="images/cour-ds.png" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
    <div class="card-body" style="background-color:#f7f2e7 ;">
      <h5 class="card-title">Data Structures</h5>
      <p class="card-text">-> Learn various Popular Data Structures and their Algorithms.</p>
      
    </div>
    </a>
      <c:if test = "${checksession==null}">
           <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=register"><button class="card-text btn-md btn-primary" >Register to access </button></a>
    </div>
      </c:if>
      
       <c:if test = "${checksession!=null}">
    
    <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=course&name=5"><button class="card-text btn-md btn-success" >View content</button></a>
    </div>
    
      </c:if>
    
  </div>
  <div class="card border-primary mb-3">
   <a href="ControllerServlet?command=course&name=6">
    <img class="card-img-top" data-src="holder.js/100px180/" alt="100%x180" src="images/cour-aws.png" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
    <div class="card-body" style="background-color:#f7f2e7 ;">
      <h5 class="card-title">AWS Cloud</h5>
      <p class="card-text">-> Learn about cloud technologies and Amazon Web Services.</p>
      
    </div>
    </a>
      <c:if test = "${checksession==null}">
           <div class="card-footer bg-transparent border-primary">
       <a href="ControllerServlet?command=register"><button class="card-text btn-md btn-primary" >Register to access </button></a>
    </div>
      </c:if>
      
       <c:if test = "${checksession!=null}">
    
    <div class="card-footer bg-transparent border-primary">
    <a href="ControllerServlet?command=course&name=6"><button class="card-text btn-md btn-success" >View content</button></a>
    </div>
    
      </c:if>
    
  </div>
 
  
  
</div>
</div>





</div>












</div>



<!-- I have used font awesome fonts to look beautiful and creative. Need to add their url script in header  -->


  <section  style="background-color: #FFFFF1;position:relative;" >
<div class="container-fluid" style="padding: 0% 15%;" >
  <div class="row">


    <div class="col-lg-4 col-md-12" style="padding:5%;">
      <center>
   
      <i class="fas fa-laptop-code fa-4x" style="padding-bottom:20px;"></i>
    <h3 class="h33">Learn coding</h3>
    <p class="pp">Easy and informative.</p>
  </center>
</div>
<div class="col-lg-4 col-md-12" style="padding:5%;">
<center>
<i class="fas fa-laptop-house fa-4x" style="padding-bottom:20px;"></i>
    <h3 class="h33">Access anywhere</h3>
    <p class="pp">Easy to use.</p>
  </center>
  </div>
<div class="col-lg-4 col-md-12" style="padding:5%;">
  <center>
  <i class="fas fa-chalkboard-teacher fa-4x" style="padding-bottom:20px;"></i>
    <h3 class="h33">Learn from experts.</h3>
    <p class="pp">Industry standard coding practices.</p>
      </center>
  </div>
    </div>
</div>

  </section>








</div>


  
  </body>
  
  <!--  Footer is arranged here. Description of footer is found on footer.jsp  -->
  <jsp:include page="footer.html"/>
</html>
