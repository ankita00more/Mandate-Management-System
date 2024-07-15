<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add UMRN</title>

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
<script type = "text/javascript">
	function Check(){
		console.log("Inside check function of addumrn ...");
		
		var radioButtonChecked = false;
	    var radioButtons = document.getElementsByName("mndtradio");
	    for (var i = 0; i < radioButtons.length; i++) {
	        if (radioButtons[i].checked) {
	            radioButtonChecked = true;
	            break;
	        }
	    }

	    if (!radioButtonChecked) {
	        alert("Please select an option (Add Umrn or Delete Umrn).");
	        return false;
	    }
	    
	    var value = document.querySelector('input[name="mndtradio"]:checked').value;
	    console.log("Value selected:"+value);
	    
		var umrn = document.getElementById("umrn").value;
		
		if(!umrn || umrn == "")
		{
			window.alert("Please enter umrn");
			return false;
		}
		if(umrn.length != 20){
			
			window.alert("UMRN max length should be 20 digit...");
			return false;
		}
		else if(value == "option1"){
			alert("Inside addumrn form ...");
			document.getElementById("addumrnform").action = `<c:url value = '/YWRkVW1ybg=='/>`;
			document.getElementById("addumrnform").submit();
			return true;
		}
		else if(value == "option2"){
			document.getElementById("addumrnform").action = `<c:url value = '/ZGVsVW1ybg=='/>`;
			document.getElementById("addumrnform").submit();
			return true;
		}
	}
</script>
</head>
<style>
input#umrn{
	width:25% !important
}
</style>
<body>
	<%@include file="Navigationbar1.jsp"%>
	
	<form method = "post" name = "addumrnform" id = "addumrnform">
		<div id="content" >
  		 <%@include file="hamburger.jsp"%>
  		<center><h3 class="page-name">Add UMRN OR Delete UMRN</h3></center>
  		<div class="container-amend" align="center"> 
  		<input  type="radio" name="mndtradio" id="amdradio" value="option1"/>
			<label  for="amdradio">Add UMRN</label>
			
		 <input  type="radio" name="mndtradio" id="cnclradio" value="option2">
         <label  for="cnclradio">Delete UMRN</label>
             <div class="input-field">
             	<label for="search" class="col-sm-10 card-text"> UMRN:<font color="red">*</font></label> 
        		<input type="text"  class="col-sm-10 ml-2 mr-2 "  id="umrn" required name="umrn"/>
        		
        		<button type="submit" style = "width :12%;" class="btn btn-primary center-button" value = "Submit" onclick = "return Check();">Submit</button>
        	</div>
        	
        	<br>
        	<h6 class="text-primary" style="font-size:12px;">
					There are case if some transactions of UMRN are present or some transactions may not be present
					in today's transaction but bank wants to remove/add 
	 				UMRN you can use this option
			</h6>
			<h6 class="text-primary" style="font-size:12px;">
				(This options  will be useful only if bank does not want UMRN in today's transaction / wants to add UMRN in today's transaction
					We are maintaining all the data removed/added  by bank)
			</h6>
				
        	
        			<h5 style="color: green;margin-top:10px;">
						 		<c:if test="${not empty UMRN}">
						 			${UMRN}
						 		</c:if> 
						 		
				   </h5>
        </div>
        	
        	
        				
						 	
							
						
		</div>
        	 

</form>
<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
</body>
</html>