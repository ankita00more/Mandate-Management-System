<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Rejected INP File</title>

	<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/bootstrap.min.css"/>
	<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/all.min.css"/>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.3.js"></script>
	<script src="${pageContext.request.contextPath}/js/include-html.js"></script>
	<script src = "${pageContext.request.contextPath}/js/crypto-js.min.js"></script>

	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<script src="${pageContext.request.contextPath}/css/jquery.slim.min.js"></script>
  	<script src="${pageContext.request.contextPath}/css/popper.min.js"></script>
  	<script src="${pageContext.request.contextPath}/css/bootstrap.bundle.min.js"></script>


  
  
  <script>
  	function Check()
  	{
  		var dateval = document.getElementById("date").value;
  		 // Prevents the default form submission
  		if(!dateval || dateval === " ")
  		{
  			alert("Please select Date");
  			return false;
  		}
  		else
  		{
  			document.getElementById("updaterejinp").action = `<c:url value = '/dXBkdHJlamlucA=='/>`;
  			document.getElementById("updaterejinp").submit();
  			return true;
  		}
  		
  	}
 </script>
  
  
</head>
<body>
	<%@include file="Navigationbar1.jsp" %>	
	<form method = "post" name = "updaterejinp" id = "updaterejinp">
		<div id="content" >
			 <%@include file="hamburger.jsp"%>
			<center><h3 class="page-name">Update Rejected INP File</h3> </center>
			
			<div class="container-amend" align="center">  
     			<div class="input-field">
     				<label for="search" style = "font-size: 16px;">INPFILE STATUS:</label><br>
     				<label for="date" class="col-sm-10 card-text"> Please Select Date:<font color="red">*</font></label> 
					<input type="date" class="col-sm-10 ml-2 mr-2" id="date" required name="date" max="<%=java.time.LocalDate.now()%>"/> 
					<button type="submit" style = "width :12%;" class="btn btn-primary center-button" value = "Submit" onclick = "return Check();">Submit</button>
					<br><h4 align ="center" style="color: red;font-size:12px;">PLEASE USE THIS OPTION AFTER 11.AM AND IF YOUR 
						FILE IS REJECTED </h4>
		  			<h5 align ="center" style="color: green;font-size:12px;">Use this option the INP FILE is rejected, bank can only update the 
								data if INP file is rejected</h5>
								
					<h5 style="color: green">
						<% 
							String err = (String)request.getAttribute("err"); 
		    			if (err != null) {
							out.println(err);
			    			System.out.println("ss="+err);
						}
						 System.out.println("ss ="+err);
						 %>
					</h5>
     			</div>
     		</div>
		</div>
		<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
	</form>
	
</body>
</html>