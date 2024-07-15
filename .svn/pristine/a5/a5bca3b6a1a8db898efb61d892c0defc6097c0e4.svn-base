<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Data</title>
	
	<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/bootstrap.min.css"/>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/all.min.css"/>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/dataTables.css"/>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/forButtons.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<script src="${pageContext.request.contextPath}/js/jquery-min.js"></script>
<script src="${pageContext.request.contextPath}/js/datatables.js"></script>
<script src="${pageContext.request.contextPath}/js/forButtons.js"></script>
<script src="${pageContext.request.contextPath}/js/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/button-min.js"></script>
<script src="${pageContext.request.contextPath}/js/loading.js"></script>
    
    
<script>
window.onload=function(){
	if(${generateInputsize} != 0){
		alert("GEN INPUT"+${generateInputsize});
		document.getElementById("savebtn").style.display="block";
        document.getElementById("genbtn").style.display="none";
        document.getElementById("data-table_wrapper").style.display = "block";
	}
	else{
		document.getElementById("savebtn").style.display="none";
		document.getElementById("genbtn").style.display="block";
		document.getElementById("data-table_wrapper").style.display = "none";
		document.getElementById("display").style.display = "none";
	}
}
function GenerateInput(){
	
	alert("Inside Generate Input");
	document.getElementById("note_div").style.display = 'block';
	document.getElementById("genbtn").disabled = true;
	document.getElementById("genbtn").innerHTML  = " Please Wait...! ";
	document.getElementById("transactionDataArray").value = null;
	document.getElementById("generateInputFilevar").value = "yes";
	var transactionForm = document.forms["transactionform"];
    if (transactionForm) {
        alert("Performing the action");
        transactionForm.action = `<c:url value = '/Z2VuaW5wdXQ='/>`;
        transactionForm.submit();
        alert("KINDLY WAIT FOR SOMETIME AS IT TAKES TIME TO GENERATE FILE. DO NOT CLICK ON ANY OTHER OPTION !");
    } else {
        alert("transactionForm not found!");
    }
	
	
}

function Check(){
		
		var rejectedReasonArry = [];
		var cnt = document.getElementsByName("amt");
		var max_amt= document.getElementsByName("MAX_AMOUNT");
		var temp=[];
		var error = false;
		var alert_val = false;
		
		for(var j=0; j<cnt.length; j++){
			console.log("All values are -->"+cnt[j].value);
			
			if(cnt[j].value == null || cnt[j].value == "")
	 		{
	 			if(!alert_val){
	 				window.alert("Please enter amount...");
	 				alert_val = true;
	 			}
				error = true;
			}
			else if(parseFloat(cnt[j].value)>parseFloat(max_amt[j].value))
	 		{
	 			console.log("Maximum amount is :"+ max_amt[j].value);
	 			console.log("Entered amount value -->"+cnt[j].value);
	 			var max_val = max_amt[j].value;
	 			if(!alert_val){
	 				window.alert("Please Ensure amount should not be exceed than Maximum amount "+max_val);
	 				alert_val = true;
	 			}
				
				cnt[j].value = "";
				cnt[j].focus();
				error = true;
				
			}
			else if(parseFloat(cnt[j].value) > 10000000){
	 		    console.log("Amount greater than 1 crore -->"+cnt[j].value);
	 		    //to generate alert only once
	 		    if(!alert_val){
	 		    	window.alert("Please ensure that the amount doesn't exceed 1 crores");
	 		    	alert_val = true;
	 		    }
	 			
	 			cnt[j].value = "";
				cnt[j].focus();
				error = true;
	 			
	 		} 
			else
			{
				var res = cnt[j].id.replace("amt", "");
		 		temp[j]=cnt[j].value+"-"+res;
		 		console.log("Data is "+temp[j]);
		 
			}
		}
		if(error){
  			return false;
  		}
		else{
			document.getElementById("transactionDataArray").value=temp; 
			/* document.transactionform.action="/authmandate"; */
			document.transactionform.action = `<c:url value = '/YXV0aG1hbmRhdGU='/>`;
			document.transactionform.submit(); 
			
		}
		
		
	}
</script>
</head>
<%@include file="Navigationbar1.jsp"%>
<script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
	<body>
	
		<form  method = "post" name = "transactionform" id = "transactionform">
			<div id="content">
				<%@include file="hamburger.jsp"%>
				<center><h3 class="page-name">Transaction Data</h3></center>
				<div class="container-amend">
					<input type = "hidden" id = "generateInputFilevar" name = "generateInputFilevar"> 
					<input type = "hidden" id = "transactionDataArray" name = "transactionDataArray">
					
					 
					 	<table id="data-table" class="example table table-striped table-bordered">
					 		<!-- table header -->
					 		<thead>
					 			<tr>
					 				<th>UNIQUE ID</th>
                					<th>UMRN</th>
                					<th>CREDITOR NAME</th>
                					<th>DEBTOR NAME</th>
                					<th>DEBTOR ACCOUNT NO</th>
                					<th>DEBTOR BANK NAME</th>
                					<th>MAX AMOUNT</th>
                					<th>AMOUNT</th>
                					<th>OCCURRENCE</th>
                					<th>FREQUENCY</th>
					 			</tr>
							 </thead>
							 
							 <!-- table content -->
					 		<tbody>
					 			<c:forEach items="${generateInput}" var="generateInput">
					 				<tr>
					 					<td>${generateInput.uniqueId}</td>
                    					<td>${generateInput.UMRN}</td>
                    					<td>${generateInput.CREDITOR_NAME}</td>
                    					<td>${generateInput.DEBTOR_NAME}</td>
                    					<td>${generateInput.DEBTOR_ACCOUNT_NO}</td>
                    					<td>${generateInput.DEBTOR_BANK_NAME}</td>
                    					<td>
                    						${generateInput.AMOUNT}
                    						<input type ="hidden" name ="MAX_AMOUNT" id ="MAX_AMOUNT${generateInput.uniqueId}" 
                    						value="${generateInput.AMOUNT}"/>
                    					</td>
                    					<td>
                        					<input type="text" id="amt${generateInput.uniqueId}" name="amt" maxlength="35" size="35" 
                        			 		value = "${generateInput.AMOUNT}" style="width:100% !important;">
                   						</td>
                    					<td>${generateInput.OCCURENCE}</td>
                    					<td>${generateInput.FREQUENCY}</td>
                    			
					 				</tr>
					 			</c:forEach>
					 		</tbody>
					 	</table>
					 
				
				<div align = "center" style="margin-top: 5px; padding-top: 15px; margin-bottom : 2px;font-size: 12px;">
					<button type = "submit" id="savebtn" style="display: none" name="save" class="btn btn-primary btn-lg" 
					onclick="return Check();">Save &amp; Update</button>
				</div>
				
				<div align = "center" style="margin-top: 5px; padding-top: 15px; margin-bottom : 2px;">
					<button type = "submit" style="display: none;font-size: 13px;" class="btn btn-primary btn-lg"  name="genbtn" id="genbtn" 
					onclick = "return GenerateInput();">Generate Input File</button> 
				</div>
			</div>
			</div>
		</form>
		
		<div id ="note_div" style="display:none; margin-left: 10px;">
			<p  style="color: red;" >
				NOTE: <br></br>
				DO NOT REFRESH PAGE AFTER CLICK ON GENERATE INPUT FILE BUTTON.ONCE FILE GENERATED PAGE WILL REDIRECT TO DOWNLOAD PAGE
			</p>
			<h6  style="color: red;"> NOTE:</h6>	
			<h6  style="color: green;"> DO NOT REFRESH PAGE AFTER CLICK ON GENERATE INPUT FILE BUTTON.</h6>
			<h6 style="color: green;"> ONCE FILE GENERATED PAGE WILL REDIRECT TO DOWNLOAD PAGE</h6>	
		</div>
		
	</body>
	<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>	
</html>