<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Something went wrong</title>
<link rel="icon" href= "${pageContext.request.contextPath}/images/aurora_logo.png"  type="image/x-icon" style="border-radius: 50%; overflow: hidden;"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script src="${pageContext.request.contextPath}/js/jquery-min.js"></script>
<style>
@import url('https://fonts.googleapis.com/css?family=Montserrat:300');
body{
  background: #3a3a41;
  color: #fff;
  font-family: 'Montserrat', sans-serif;
  font-size: 16px;
}
h1{
  font-size: 30vh;
}
h2 span{
  font-size: 4rem;
  font-weight: 600;
}
a:link, a:visited{
  text-decoration: none;
  color: #fff;
}
h3 a:hover{
  text-decoration: none;
  background: #fff;
  color: #3498DB;
  cursor: pointer;
}

</style>
</head>
<body>

<div class="datacontainer" style = "margin: 70px;">
<h1>:(</h1><br>
<h3><span>OOPs! </span> Something went wrong, Please Contact System Administrator</h3><br><br>
  <h4><a href="<c:url value = '/logout'/>">Return to Login Page</a></h4>
</div>
<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
</body>
</html>