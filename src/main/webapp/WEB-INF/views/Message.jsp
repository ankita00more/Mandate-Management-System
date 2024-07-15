<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mandate Status</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/style.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.6.3.js"></script>
<script src="${pageContext.request.contextPath}/js/include-html.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/loading.js"></script>

</head>
<body>
	<%@include file="Navigationbar1.jsp" %>
	<div id="content">
		<%@include file="hamburger.jsp"%>
		<center><h3 class="page-name">Mandate Status</h3></center>
		<div class="container" style="height:492px;"> 
			<form id = "message" name = "message" style = "margin-left: 66px; margin-top: 40px">
 				<div class="form first">
		 			<div class="details personal" >
             			 <div class="fields">
             			 	<input type="hidden" name = "divsearch" id = "divsearch" value = "">
							<input type ="hidden" name = "searchType" id = "searchType" value = "">
							<input type = "hidden" name = "SelectedMsgId" id = "SelectedMsgId" value = "">


							<br/><br/>
							<h4 align = "center"> ${Unique_id}</h4>
             			 
             			 </div>
             	  	</div>
             	</div>
        	</form>
        </div>
    
    </div>
	<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
</body>
</html>