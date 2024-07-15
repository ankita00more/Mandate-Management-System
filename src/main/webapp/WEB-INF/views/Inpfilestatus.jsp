<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Input file status</title>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/bootstrap.min.css"/>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/all.min.css"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/amend.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.6.3.js"></script>
<script src="${pageContext.request.contextPath}/js/include-html.js"></script>
<script src = "${pageContext.request.contextPath}/js/crypto-js.min.js"></script>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="${pageContext.request.contextPath}/css/jquery.slim.min.js"></script>
  <script src="${pageContext.request.contextPath}/css/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/css/bootstrap.bundle.min.js"></script>
<script>
	function Check(){
		
		//document.getElementById("addumrnform");
		
		// Get the selected date
	    var date = document.getElementById("date").value;

	    // Check if a date is selected
	    if (date === "") {
	        alert("Please select a date.");
	        return false; // Prevent form submission
	    } /* else {
	        // If date is selected, display the table
	        document.getElementById("tableID").style.display = "block";
	        return true;
	    }
        */
	}
</script>

<style>
.data-table{
	border:1px solid #222;
}
.data-table th{
	background:#0072bd !important;
	color:#fff;
	border:1px solid #222;
}
.data-table td{
	border:1px solid #222;
}
</style>

</head>
<body>
	<%@include file="Navigationbar1.jsp" %>
	<form  method = "post" name = "addumrnform" id = "addumrnform">
		<div id="content" >
			 <%@include file="hamburger.jsp"%>
			<center><h3 class="page-name">Input File Status</h3></center>
			<div class="container-amend" align="center">
				<div class="input-field">
					<label for="search" style = "font-size: 16px;">INPFILE STATUS:</label><br>
     				<label for="search" class="col-sm-10 card-text"> Please Select Date:(MM-dd-yyyy):<font color="red">*</font></label> 
					<input type="date" class="col-sm-10 ml-2 mr-2 " maxlength="0" size="0" id="date" required name="date"/>
					
					<button type="submit" style = "width :12%;" class="btn btn-primary center-button" value = "Submit" onclick = "return Check();">Submit</button>
				
	
	



	<div class="displayTable" style="margin-top: 10px; padding-top: 10px;padding-left: 0px;" id="tableID" align = "center">
		<table class="table table-striped table-bordered table-hover data-table" id="tableID" style="height: 80px; width:100%;">
        	<thead class = "table-primary">
        	 	<tr>
                	<th>BANK_NAME</th>
                	<th>INPFILENAME</th>
                	<th>INPUT FILE UPLOADED TO NPCI</th>
                	<th>INPUT FILE STATUS AT NPCI</th>
                 	<th>TOTAL RECORDS</th>
            	</tr>
        	</thead>
        <tbody>
        	<c:choose>
        		<c:when test="${not empty list}">
        			 <c:forEach items="${list}" var="row">
        			 	<tr>
        			 	    <td>${row[3]} </td> 
        	 				<td>${row[1]} </td>  
        	 				<td>${row[2]} </td>
        	 				<td>${row[0]} </td> 
        	 				<td>${no_of_records}</td>
        			 	</tr>
        			 </c:forEach>
        		</c:when>
        		<c:when test="${not empty message}">
        			<tr>
            			<td colspan="5">No Records Found</td>
       				 </tr>
        		</c:when>
        		<c:otherwise>
       				 <!-- <tr>
            			<td colspan="5">The list is empty</td>
       				 </tr> -->
   				</c:otherwise>
        	</c:choose>	
        </tbody>
    </table>

	
</div>


	<div style = "padding-left: 0px; padding-top: 0px;text-align: left; margin-top: 4px;">
		<h5 style="color: green;font-size: 12px;"> If INPUT file status at NPCI is Received then file is partially accepted by NPCI which means records 
                          are accepted for more clarification please contact NPCI</h5>	
                          
		<h5 style="color: green;font-size: 12px;"> IF INPUT file status at NPCI is Accepted  then file is accepted by NPCI</h5>	

		<h5 style="color: green;font-size: 12px;"> IF INPUT file status at NPCI is Rejected then file needs to be regenerate please go to option</h5> 

		<h5 style="color: RED;font-size: 12px;">UPDATE REJECTED INP FILE(please use this option after 11 AM)</h5>

		<h5 style="color: green;font-size: 12px;">Please make sure the file will be generated before 1 PM</h5>

		<br/><br/>
	</div>
</div>
				
</div>
</div>

<div>
	<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
</div>
</form>
</body>
</html>