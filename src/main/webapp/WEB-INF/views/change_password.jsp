<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Change Password</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css"/>
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
  	<script>
  		function Check()
  		{
  			if (document.changepass.username.value == "") 
  			{
  				alert("Username cannot be blank");
  				document.form.username.focus();
  				return false;
  			} 
  			else if (document.changepass.password.value == "") 
  			{
  				alert("Password cannot be blank");
  				document.form.password.focus();
  				return false;

  			}
  			else if (document.changepass.confpassword.value == "") 
  			{
  				alert("Please Confirm Password");
  				document.form.confpassword.focus();
  				return false;
  			}
  			else if (document.changepass.password.value != document.changepass.confpassword.value) 
  			{
				alert('Confirm Password Not Match');
				document.ChangePasswordForm.conpassword.focus();
				return false;
			}
  			
  			document.getElementById("changepass").action = `<c:url value = '/changepass'/>`;
  			document.getElementById("changepass").submit();
  			return true;
  			
  		}
  	</script>
</head>
<body>
	<div class="change-password-container">
    	<div class="change-password-sub-container">  
    		<div class="change-password-wrapper">
    			 <header><h2>Change Password</h2></header>
    			 
    			  <form  method = "post" id="changepass" name="changepass" autocomplete="off" enctype="multipart/form-data">
    			  	<h3>${err}</h3>
    			  	<div class="field email">
        				<div class="input-area">
          					<img src="${pageContext.request.contextPath}/images/username-icon.png" class="credentials-icons">
         				    <input type="text" name="username" id = "username" placeholder="User Name" required>
       					 </div>
      				</div>
      				
      				<div class="field password">
        				<div class="input-area">
          					<img src="${pageContext.request.contextPath}/images/passcode-icon.png" class="credentials-icons">
          					<input type="password"  name="password"  id = "password" placeholder="Password" required>
       					 </div>
      				</div>
      				
      				 <div class="field confirm-password">
        				<div class="input-area">
          					<img src="images/passcode-icon.png" class="credentials-icons">
          					<input type="password"  name="confpassword"  id = "confpassword" placeholder="Confirm Password" required>
        			 	</div>
      				</div>
      				
      				<input type="submit" value="Submit" onclick = "javascript:Check();">
    			  </form>
    			  
    			  
    		</div>
    	</div>
    </div>
	

</body>
</html>