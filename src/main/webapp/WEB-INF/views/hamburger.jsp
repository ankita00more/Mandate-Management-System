<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@page import="com.example.demo.utilities.URLEncoderDecoder"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c2"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/loading.js"></script>
</head>
<body>

	<%
	String option = (String) session.getAttribute("option");
	System.out.println("option: " + option);
	%>

	<!-- sidebar -->
	<%
	if (option.equalsIgnoreCase("Sponsor")) {
	%>
	<div id="sidebar">
		<a href="#" class="collapsible">Mandate Operations</a>
		<div id="submenu">
			<a href="<c2:url value='/Y3JlYXRl' />">Mandate Creation</a> <a
				href="<c2:url value='/YW1lbmRfMQ==' />">Mandate Amendment / Cancellation</a>

		</div>
		<a href="#" class="collapsible">Mandate Input Files</a>
		<div id="submenu">
			<a href="<c2:url value='/YWRkVW1ybg==' />">Add/Remove UMRN in today's INP file </a> 
			<a href="<c2:url value='/YXV0aG1hbmRhdGU=' />">Authorize and Generate Transaction</a> 
			<a href="<c2:url value='/dXBkdHJlamlucA==' />">Update Rejected INP Files</a>
		</div>
		<a href="#" class="collapsible">Mandate H2H Reports</a>
		<div id="submenu">
			<a href="<c2:url value='/bW5kdHN0YXR1cw=='/>">Mandate Status</a> <a
				href="<c2:url value='/dG9kYXlzdGF0dXM=' />">Today's Mandate
				Transaction</a> <a href="<c2:url value='/dHJhbnN0YXR1cw==' />">Mandate
				Transaction File Status</a>
		</div>

	</div>
	<%
	}
	%>

	<%
	if (option.equalsIgnoreCase("Destination")) {
	%>
	<div id="sidebar">
			<a href="<c2:url value='/processMandate' />">Mandate Authorization</a> 
			<a href="<c2:url value='/cancelMandate' />">Mandate Cancellation</a>
			<a href="<c2:url value='/mandateauthorizationstatus' />">Mandate Reports</a>
			
	</div>
	<%
	}
	%>

	<br>
	<!-- menubtn -->
	<div id="menuBtn" class="menuBtn">
		&#9776; <span class="tooltiptext">Expand to see menus</span>
	</div>

	<!-- <script>
		window.onload=function()
		{
			window.history.forward(); 
		}
	</script> -->

	<script type="text/javascript">
		var menuBtn = document.getElementById('menuBtn');
		var sidebar = document.getElementById('sidebar');
		var content = document.getElementById('content');
		var footer = document.getElementById('footer');

		menuBtn.addEventListener('click', function() {
			if (sidebar.style.left === '0px') {
				sidebar.style.left = '-210px';
				content.style.marginLeft = '0';
				menuBtn.innerHTML = '&#9776;'; /* Hamburger icon */
				menuBtn.style.color = '#333';
				menuBtn.style.marginLeft = '0';
				footer.style.marginLeft = '0';
			} else {
				sidebar.style.left = '0';
				content.style.marginLeft = '210px';
				menuBtn.innerHTML = '&#10006';
				menuBtn.style.marginLeft = '210px';
				menuBtn.style.color = '#333';
				footer.style.marginLeft = '209px';
			}
		});
		var coll = document.getElementsByClassName('collapsible');
		var i;

		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener('click', function() {
				this.classList.toggle('collapsed');
				var submenu = this.nextElementSibling;
				if (submenu.style.maxHeight) {
					submenu.style.maxHeight = null;
				} else {
					submenu.style.maxHeight = submenu.scrollHeight + "px";
				}
			});
		}
	</script>


</body>
</html>