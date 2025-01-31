<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.example.demo.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>First Navbar</title>
<script src="${pageContext.request.contextPath}/js/disable-copy-paste.js"></script>
<style>
	span.welcome-name {
 float: right;
 margin-right: 8%;
 margin-top: 10px;
}

</style>
</head>

<script type="text/javascript">
function logout(){
	if (confirm('Are you sure you want to logout?')) {
		
		window.location.href = `<c1:url value = '/logout'/>`;
	} else {
		return false;
	}
	
}
</script>

<body>	
	<%
		User attribute = (User) session.getAttribute("user");
	%>
	<!-- header -->
	<div class="header">
		<img alt="Logo" class="float-left" style="width: 5%;" src ="${pageContext.request.contextPath}/images/logo_cedge.jpg">
		<span class="mgrb-text"><%= session.getAttribute("bankfullname") %></span><span class="welcome-name">
		<%-- <b>Welcome <%= attribute.getBankname() %></b> --%></span><button  onclick="logout()" class="logout-btn">Logout</button>
	</div>
	<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>  
	
</body>
</html>