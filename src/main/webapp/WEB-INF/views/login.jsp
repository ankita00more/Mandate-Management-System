<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.example.demo.entity.Message"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Form</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css"/>
  <script src="${pageContext.request.contextPath}/js/crypto-js.min.js"></script>
  <script>
 
	function validateForm() {
		if (document.login.username.value == "") {
			alert("Username cannot be blank");
			document.form.username.focus();
			return false;
		} else if (document.login.password.value == "") {
			alert("Password cannot be blank");
			document.form.password.focus();
			return false;

		}

	}
	
	function Check(event)
	{
		event.preventDefault(); // Prevents the default form submission
		var optval = document.getElementById("opt").value;
		console.log("Optionvalue"+optval);
		if (document.getElementById("username").value === "") {
			alert("Username cannot be blank");
			document.getElementById("username").focus();
			return false;
		} 
		else if (document.getElementById("password").value === "") 
		{
			alert("Password cannot be blank");
			document.getElementById("password").focus();
			return false;

		}
		else if(optval === "")
		{
			alert("Please select valid options either sponsor or destination");
			return false;
		}
		else
		{ 
			 var oldpass = document.getElementById("password").value;
			 console.log("value"+oldpass);
			 
			 var myPassword = "MMSPORTAL_2024";
			 
			 var encryptedval = CryptoJS.AES.encrypt(oldpass,myPassword).toString();
			 
			 document.getElementById("password").value = encryptedval;
            
			document.getElementById("login").action = `<c:url value = '/login'/>`;
			document.getElementById("login").submit();
			return true;
			
		}  
		
	}
</script>
  <style>
  body{
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("${pageContext.request.contextPath}/images/habib-bank.jpg");

  background-size: cover;
  background-attachment: fixed;
}
body::before{
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-color: rgba(0, 0, 0, 0.5);
}
@import url('css/font-family.css');
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
  </style>
</head>
<body>
	<%-- <h1>${errorMsg}</h1> --%>
	
	

	<div class="container-login">
		<div class="sub-container" id="sub-container-2">
			 <div class="mandate-para">
			 	<h1 class="mandate-header">Mandate Management System</h1><br/>
			 	
			 	<h4 class="mandate-header">The National Automated Clearing House (NACH) mandate was released by the National Payments Corporation of India (NPCI). 
			 	NPCI implemented a web-based solution to enable direct debit and direct credit transactions across businesses and consumers 
			 	while eluding the high priced credit card payment networks and processing fee of paper based checks.
				NACH, helps in direct saving of transaction fees ensuing profits for the stakeholders involved. 
				It is mainly used for making high volume, low value debit/credit transactions that are recurring in nature. 
				It supports two types of transactions one is Direct Credit and other is Direct Debit.</h4>
			 	
			 </div>
		</div>
		<div class="sub-container"> 
			<div class="wrapper">
				<c:choose>
					<c:when test="${not empty msg}">
					 <p style="text-align:center; color:red; font-weight: bold;">
       						 <c:out value="${msg}" />
    				 </p>
				    </c:when>
				    <c:otherwise>
				    </c:otherwise>
				</c:choose>
				<header style="color: #fff">Log In</header>
				<!-- onSubmit="return validateForm()" -->
				 <form  method = "post" id="login" name="login" autocomplete="off">
				 	<input type = "hidden" id= "encvalue" name = "encvalue" value= "mrgb"/>
					<div class="field email">
							<div class="input-area">
								<img src="${pageContext.request.contextPath}/images/username-icon.png" class="credentials-icons">
         						 <input type="text" name="username" id = "username" placeholder="Username">
							</div>
					</div>
					
					<div class="field password">
							<div class="input-area">
          							<img src="${pageContext.request.contextPath}/images/passcode-icon.png" class="credentials-icons">
          							<input type="password"  name="password"  id = "password" placeholder="Password">
        					</div>
					</div>
					<div class="field usertype">
						<div class="input-area">
						<select id="opt" name = "opt">
							<option selected value = "">Select Login Type</option>
							<option value="Sponsor">Sponsor</option>
							<option value="Destination">Destination</option>
<!-- 							<option value="E-Mandate">E-Mandate</option> -->
						</select>
						</div>
					</div>
					
					<!-- login changes -->
					
				    <!-- login changes -->
					
					 <input type="submit" value="Submit" onclick = "javascript:Check(event);">
				 </form>
			</div>
		</div>
		 
	</div>

</body>
</html>