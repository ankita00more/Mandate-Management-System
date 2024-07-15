<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancel Mandate</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css" />
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
function Validatetheinput(){
	alert("Inside validate input ..");
	var str = document.getElementById("Mndt_Canc").value;
    if(str=="")
   {
       alert("Please select :");
       return false; 
   }
	
   else if(document.getElementById("Dbopt").value == ""){
		alert("Please Enter a valid input!");
		document.getElementById("Dbopt").focus();
		return false;
	}
   else{
	   /* document.mandate.action = '/searchdata'; */
	   document.mandate.action = `<c:url value = '/searchdata'/>`;
	   document.mandate.submit(); 
	   
   }

}

function getValuesForMsgId(msgId)
{
	document.getElementById("SelectedMsgId").value=msgId;
	/* document.mandate.action = '/delmandate'; */
	document.mandate.action =  `<c:url value = '/delmandate'/>`;
	document.mandate.submit();  
	
}
</script>
</head>

<body>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>

<!-- <h1 style ="display: flex;justify-content: center; align-items: center;"> Cancel Mandate</h1> -->
<%@include file="Navigationbar1.jsp" %>


    <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6"> 
      <!-- action ="/cancelMandate" -->
        <form  method="post" id="mandate" enctype="multipart/form-data" name="mandate" autocomplete="off">
        
          <div class="form-group">
          	<input type="hidden" name="SelectedMsgId" id="SelectedMsgId">
          	<input type="hidden" name="deleteMandateVar" id="deleteMandateVar" value=""> 
            <input type="hidden" name="customerId" id="customerId" value="${customer.uniqueId}">
            <label for="Mndt_Canc">Search By</label><br>
            <select class="form-select" id="Mndt_Canc" name="Mndt_Canc">
              <option selected="selected" value="">--Select--</option>
              <option value="DEBTOR_ACCOUNT_NO">DEBTOR ACCOUNT NO</option>
              <option value="UNIQUE_ID">UNIQUE ID</option>
              <option value="UMRN">UMRN NUMBER</option>
            </select>
          </div>
          <div class="form-group">
            <label for="Dbopt">Enter Value</label><br>
            <input type="text" class="form-control" id="Dbopt" name="Dbopt" maxlength="34" size="25"
              onkeypress="return (event.charCode >= 48 && event.charCode <= 57) || (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122)" />
          </div>
          <button type="submit" class="btn btn-primary center-button" onclick="Validatetheinput()">
           Search
          </button>
        </form>
       </div>
    </div>
  </div> 
  
  <div id = "division" style="text-align: center;">
  
			<h3>${messageId}</h3>
			
			<%
   			if(request.getAttribute("messageId") != null){
      			 ArrayList<String> uniqueId = (ArrayList<String>) request.getAttribute("data");

       			for (String temp : uniqueId) {
			%>
   					<a href="javascript:getValuesForMsgId('<%=temp%>')"><%=temp%></a><br><br>
			<%
       			}
   			}
			%>
  	
  </div>
  
  <footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>


</body>
</html>