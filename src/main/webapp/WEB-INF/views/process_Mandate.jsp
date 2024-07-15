<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Process Mandate</title>
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
</head>
<body>
	<%@include file="Navigationbar1.jsp"%>
	<script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
	<div id="content">
	<%@include file="hamburger.jsp"%>
		<center><h3 class="page-name">Mandate Authorization</h3></center>
<div class="container">
		<form method="post" id="form1" name="form1" style="margin-top:20px;">
			<table id="processMandateTable" class="example table table-striped table-bordered data-table" style="width:100%;">
				<thead>
					<tr>
						<th>Creation Date</th>
						<th>Transaction type</th>
						<th>File Name</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${not empty allfiles}">
							<c:forEach items="${allfiles}" var="row">
								<tr>
								<c:url var="url" value="/mandateAuth">
								<c:param name="zipfilename" value="${row[0]}" />
								</c:url>
									
									<td>${row[2]}</td>
									<td>${row[1]}</td>
									<td><a href="${url}">${row[0]}</a></td>
								</tr>
							</c:forEach>
							</c:when>
							<c:otherwise>
							 </c:otherwise>
</c:choose>
				</tbody>
			</table>
		
			
		</form>
		</div>
	</div>
	</center>
	
	<script>
		$(document).ready(function() {
			$("*:contains('T')").each(function() {
				var text = $(this).text();
				if (/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}/.test(text)) {
					var dateOnly = text.split('T')[0];
					$(this).text(dateOnly);
				}
			});
		});
	</script>
	
	<script>
		function hideQueryParams() {
			var urlWithoutParams = window.location.pathname;
			window.history.replaceState({}, document.title, urlWithoutParams);
		}

		window.onload = hideQueryParams;
	</script>
</body>
</html>