<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Amend Mandate</title>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/bootstrap.min.css"/>
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/all.min.css"/> 

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script>
function Check()
{
	//alert("inside return check ");
	
	var x=document.getElementById("searchType").value;
	var y=document.getElementById("divsearch").value;
	
	// Check if any radio button is selected
    var radioButtonChecked = false;
    var radioButtons = document.getElementsByName("mndtradio");
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            radioButtonChecked = true;
            break;
        }
    }

    if (!radioButtonChecked) {
        alert("Please select an option (Amend Mandate or Cancel Mandate).");
        return false;
    }
	
	var value = document.querySelector('input[name="mndtradio"]:checked').value;
	localStorage.setItem('selectedOption', value);
	//alert(value);
	
	 
	
	if(x=="Select")
	{
		window.alert("Please select proper value!!");
		return false;
	}
	else if(y=="" || y==null)
	{
		window.alert("Please enter value!!");
		return false;
	}
	else if(x=="UMRN" && y.length!=20)
	{
		window.alert("UMRN max length should be 20 digit...");
		return false;
	}
	else
	{
		//alert("Inside else part");
		if(value == "option1")
		{
			//alert("Inside option 1");
			document.ammendForm.action=`<c:url value = '/YW1lbmQ='/>`;
			document.ammendForm.submit();
			return true;
		}
		else if(value == "option2")
		{
			//alert("Inside option 2");
			document.ammendForm.action=`<c:url value = '/c2VhcmNoZGF0YQ=='/>`;
			document.ammendForm.submit();
			return true;
		}
		
	}	
}

function getValuesForMsgId(msgId)
{
	var selectedOption = localStorage.getItem('selectedOption');
	console.log("Seelected option :"+selectedOption);
	document.getElementById("SelectedMsgId").value=msgId;
	console.log("Value of messageid"+document.getElementById("SelectedMsgId").value);
	
	if(selectedOption === "option1")
	{
		console.log("<---------option1 selected--------->");
		document.ammendForm.method = "GET";
	    document.ammendForm.action= `<c:url value = '/ZmluZGFtZW5k'/>`;
		document.ammendForm.submit();
	}
	else if(selectedOption === "option2")
	{
		console.log("<---------option2 selected--------->");
		document.ammendForm.method = "GET";
		document.ammendForm.action =  `<c:url value = '/ZGVsbWFuZGF0ZQ=='/>`;
		document.ammendForm.submit();
		
	} 
	
}
</script>

</head>
<body>
	 <%@include file="Navigationbar1.jsp"%>
  	 
  <form  method="post" id="ammendForm" enctype="multipart/form-data"  name="ammendForm" autocomplete="off">
  <div id="content" >
  		 <%@include file="hamburger.jsp"%>
  		<center><h3 class="page-name">Amend Mandate</h3></center>
  		<div class="container-amend" align="center">
  			<input type="hidden" name="SelectedMsgId" id="SelectedMsgId">
          	<input type="hidden" name="differentaingParam" id="differentaingParam" value="no">
          	
  			<input  type="radio" name="mndtradio" id="amdradio" value="option1"/>
  				<label  for="amdradio">Amend Mandate</label>
  				
  			 <input  type="radio" name="mndtradio" id="cnclradio" value="option2">
                 <label  for="cnclradio">Cancel Mandate</label>
                 
             <div class="input-field">
             	<label for="search" style = "font-size: 16px;">Search By</label><br>
        		<select class="search-amend-search-by" id="searchType" name="searchType">
        			<option selected="selected" value="Select">--Select--</option>
             	   	<option value="DEBTOR_ACCOUNT_NO">DEBTOR ACCOUNT NO</option>
             		<option value="UNIQUE_ID">UNIQUE ID</option>
             	    <option value="UMRN">UMRN NUMBER</option>
        		</select>
        	</div>
        	
        	<div class="input-field">
            	<input type="text"  id="divsearch" name="divsearch" maxlength="34" size="25" placeholder = "Enter Value" class="search-amend-value"
                 onkeypress="return (event.charCode >= 48 && event.charCode <= 57) || (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122)" />
        	</div>
        	
        	 <button type="submit"  style = "width: 16%;" onclick = "return Check();">Search</button>
  			<br>
        	 <div id = "division" style="text-align: center;">
       		<h3>${messageId}</h3>
       		
       		<c:if test="${not empty messageId}">
         		<c:forEach var="temp" items="${data}">
             		<a href="javascript:getValuesForMsgId('${temp}')">${temp}</a><br><br>
         		</c:forEach>
     		</c:if>
     			
     			
       	
       </div>
        	 
   		</div>
   		 
   </div>
  
  
  
  
  </form>
  <footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
  
</body>
</html>