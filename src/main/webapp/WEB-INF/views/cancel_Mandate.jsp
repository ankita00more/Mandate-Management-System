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
<script type="text/javascript">
function search() {
	var sts = document.getElementById("sts").value;
	var umrn = document.getElementById("dltMdt").value;

	if (!umrn) {
		alert("Please Provide UMRN");
	} else if (umrn) {
		document.delMdt.action = "${pageContext.request.contextPath}/getMandate?umrn="+ umrn;
		document.delMdt.submit();
	} else if (sts === 'Y') {
		chkUmrn();
	}
}

function chkUmrn() {
	
	var umrn = document.getElementById("dltMdt").value;
	var sts = document.getElementById("sts").value;

	if (sts === 'Y') {
		if (confirm('Are You Sure?')) {
			document.delMdt.action = "${pageContext.request.contextPath}/deleteMandate?umrn="
					+ umrn;
			document.delMdt.submit();
		} else {
			alert("Operation Cancel");
		}
	}
	if (sts === 'N') {
		alert("This UMRN is already Canceled.");
	}
	sts === '';
}
function Delete() {
	var sts = document.getElementById("delSts").value;
	if (sts === 'Y') {
		alert("Mandate Deleted successfuly.");
	}
	if (sts === 'N') {
		alert("Data is not available for UMRN");
	}
}
</script>
<style>
input.search-umrn-trigger {
    color: #fff;
    padding-left: 7px;
    padding-right: 7px;
    border-radius: 4px;
    border: none;
    margin-left: 15px;
    height: 35px;
    background: #32325dd6;
}
div#cancel-mandate_wrapper {
    margin-top: 20px;
}
</style>
<body>
	<%@include file="Navigationbar1.jsp"%>
	<script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
	
	 <div id="content">
            <%@include file="hamburger.jsp"%>
            <center><h3 class="page-name">Cancel Mandate</h3></center>
            
            <div class="container-amend" style="max-height:100%;" align="center">
            <form action="" name="delMdt" method="POST">
            <table align="center">
			<tr>
				<td><input type="hidden" id="sts" value="${isExist}"></td>
				<td><input type="hidden" id="delSts" value="${isDeleted}"></td>
			</tr>
		</table>
            <input type="text" style="width: 200px;height:35px;" id="dltMdt"
					 placeholder="Enter UMRN" class="search-by-umrn-textbox"><input type="button" name="searchMdt" class="search-umrn-trigger" value="Search"
					onclick="search();"></center>
	   
   
        <table class="example table table-striped table-bordered" id="cancel-mandate" style="width:100%;">
        <thead>
            <tr>
			<th>UMRN</th>
			<th>Customer Name</th>
			<th>Creditor Name</th>
			<th>Debitor Account Number</th>
			<th>First Collection Date</th>
			<th>Final Collection Date</th>
			<th>Action</th>
		</tr>
        </thead>
        <tbody>
           <c:forEach items="${Data}" var="dt">
		<tr>
			<td align="left"><input class="textBox" disabled="disabled"
				type="text" value="${dt[0]}"></td>
			<td align="left"><input class="textBox" disabled="disabled"
				type="text"
				value="<c:out value="${dt[1]}"></c:out>"></td>
			<td align="left"><input class="textBox" disabled="disabled"
				type="text"
				value="<c:out value="${dt[2]}"></c:out>"></td>
			<td align="left"><input class="textBox" disabled="disabled"
				type="text"
				value="<c:out value="${dt[3]}"></c:out>"></td>
			<td align="left"><input class="textBox" disabled="disabled"
				type="text"
				value="<c:out value="${dt[4]}"></c:out>"></td>
			<td align="left"><input class="textBox" disabled="disabled"
				type="text"
				value="<c:out value="${dt[5]}"></c:out>"></td>
			<td><input type="button" name="deleteMdt" id="deleteMdt" value="Delete"
					onclick="chkUmrn();"/></td>
					
		</tr>
		</c:forEach> 
            <!-- Add more rows as needed -->
           <!--  <tr>
			<td></td>
			<td></td>
			<td></td>
			<td>No Records Found</td>
			<td></td>
			<td></td>
			<td></td>
		</tr> -->
        </tbody>
       
    </table>
    
    </div>
    </div>
	
	
	
	
</body>
</html>