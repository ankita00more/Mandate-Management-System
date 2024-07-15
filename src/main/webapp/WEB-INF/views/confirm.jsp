<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmation</title>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>


<%@include file="Navigationbar1.jsp" %>
	<div id="content">
		<%@include file="hamburger.jsp"%>
		<div class="container" style = "align-items : center;">
			<form id = "message" name = "message" style = "margin-left: 66px;">
 				<div class="form first">
		 			<div class="details personal" >
             			 <div class="fields">
							<br/><br/>
							<h2 align = "center"> ${message}</h2><br>
							<c:url var="downloadUrl" value="/logout"/>
							<h2 align = "center"><a href = "${downloadUrl}">Click Here To Login</a></h2>
             			 
             			 </div>
             	  	</div>
             	</div>
        	</form>
        </div>
    </div>
	<footer id="footer">Copyright � C-EDGE Technologies Ltd. | ACH Debit Interface � 2024</footer>
</body>
</html>