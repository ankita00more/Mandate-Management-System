<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.example.demo.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>First Navbar</title>
<script src="${pageContext.request.contextPath}/js/script.js" type="text/javascript"></script>
	<%-- <link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/bootstrap.min.css"/>
	<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/bootstrap.min1.css"/>
	<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/all.min.css"/>
	<script src="${pageContext.request.contextPath}/js/jquery.slim.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>

<style>
	

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
	<%-- <div class="header">
		<img alt="Logo" class="float-left" style="width: 5%;" src ="${pageContext.request.contextPath}/images/logo_cedge.jpg">
		<span class="mgrb-text"><%= attribute.getBankname() %></span><button  onclick="logout()" class="logout-btn">Logout</button>
	</div> --%>
	
	<%-- <nav class="navbar navbar-expand-lg navbar-light bg-light">
		<h1 class="navbar-brand text-primary"> <%= attribute.getBankname() %> </h1>
		<ul class="nav navbar-nav navbar-right custom-padding ">
			<li>
			<button type="button" value="logout" class="btn btn-danger " onclick="logout()">
				<i class="fa-solid fa-left-from-bracket"></i>Logout
			</button>
			</li>
		</ul>
	</nav> --%>

	
</body>
</html>