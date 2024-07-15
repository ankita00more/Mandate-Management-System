<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Data For Authorization</title>
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
<script>
 $(document).ready(function() {
		$(document).on("click", "#submitBtn", function() {
	        var totalUMRNs = $("table#data-table tbody tr").length;
	        var checkedAcceptCheckboxes = $("input[name='accept_chk']:checked").length;
	        var checkedRejectCheckboxes = $("input[name='reject_chk']:checked").length;
	        var totalCheckedCheckboxes = checkedAcceptCheckboxes + checkedRejectCheckboxes;
	        var allCheckboxesChecked = totalCheckedCheckboxes === totalUMRNs;
	        var resMsg = /*[[${ResMsg}]]*/ '';
	        if (!checkedAcceptCheckboxes && !checkedRejectCheckboxes) {
	            alert("Kindly select all Accept or Reject checkboxes!");
	            return false; 
	        }
	        else{
	        	 
	        	alert("Authorized successfully");
	        }
			
			
			var accept_chk_values = [];
			var reject_chk_values = [];
			$("input[name='accept_chk']:checked").each(function() {

				accept_chk_values.push($(this).val());

			});
			$("input[name='reject_chk']:checked").each(function() {

				reject_chk_values.push($(this).val());

			});
			$("#accept_chk").val(accept_chk_values.join(","));
			$("#reject_chk").val(reject_chk_values.join(","));
			$("#mdtForm").submit();

		});
	    function hideQueryParams() {  
	        var urlWithoutParams = window.location.pathname;
	        window.history.replaceState({}, document.title, urlWithoutParams);
	    }
	    window.onload = function() {
	        hideQueryParams();
	    };
	});
 document.getElementById('storeParamsForm').submit();
</script>

<body>
	<%@include file="Navigationbar1.jsp"%>
	<script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
	<%
	String zipfilename = request.getParameter("zipfilename");
	%>
	<div id="content">
	<%@include file="hamburger.jsp"%>
		<center><h3 class="page-name">Data for Authorization</h3></center>
		<div class="container">
		<form name="mdtForm" id="mdtForm" method="post"
			action="${pageContext.request.contextPath}/auth">
			
			<table class="example table table-striped table-bordered data-table" id="mdt-auth-table">
				<thead>
					<tr>
						<th>UMRN</th>
						<th>Account No</th>
						<th>Accept</th>
						<th>Reject</th>
						<th>Accept/Reject Reason</th>
						<th>View Details</th>
					</tr>
				</thead>
				<c:forEach items="${datalist}" var="dt">
					<tr>
						<td>${dt[0]}</td>
						<td>${dt[1]}</td>
						<td><c:choose>
								<c:when test="${dt[17] == '1' }">
									<input class="nochange unChk" style="outline: 2px solid green;"
										checked="checked" type="checkbox" disabled
										id="acc_chk${dt[0]}" value="${dt[0]}" name="accept_chk"
										disabled onclick="chk_validate(this.id,this.value);" readonly></input>
								</c:when>
								<c:otherwise>
									<input class="nochange unChk" type="checkbox"
										id="acc_chk${dt[0]}" value="${dt[0]}" disabled
										name="accept_chk" readonly
										onclick="chk_validate(this.id,this.value);"></input>
								</c:otherwise>
							</c:choose></td>

						<td><c:choose>
								<c:when test="${dt[18] == '1' }">
									<input class="nochange unChk" style="outline: 2px solid green;"
										checked="checked" type="checkbox" disabled
										id="rej_chk${dt[0]}" value="${dt[0]}" name="reject_chk"
										disabled onclick="chk_validate(this.id,this.value);" readonly></input>
								</c:when>
								<c:otherwise>
									<input class="nochange unChk" type="checkbox"
										id="rej_chk${dt[0]}" value="${dt[0]}" disabled
										name="reject_chk" readonly
										onclick="chk_validate(this.id,this.value);"></input>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${dt[17] == '1' }">
									<input class="rejection-reason-div" type="text" readonly
										value="ac01">

								</c:when>
								<c:when test="${dt[18] == '1' }">
									<input type="text" readonly class="rejection-reason-div"
										value="${dt[19]}">
								</c:when>

								<c:otherwise>

									<select style="width: 200px;" id="STATUS_RSN_CODE${dt[0]}" class="mandate-auth-select"
										name="STATUS_RSN_CODE" disabled>
										<option value="">** SELECT **</option>
										<c:if test="${not empty rsncodes}">
											<c:forEach var="reasonBean" items="${rsncodes}">
												<c:set var="statusRsnCode" value="${reasonBean[0]}" />
												<c:set var="concatCodeReason" value="${reasonBean[1]}" />
												<option value="${statusRsnCode}" title="${concatCodeReason}"
													onmouseover="${concatCodeReason}">
													<c:out value="${concatCodeReason}" />
												</option>
											</c:forEach>
										</c:if>
									</select>
								</c:otherwise>
							</c:choose></td>
						<td><a class="button button-info"
							href="${pageContext.request.contextPath}/viewDetail?typeImg=jpeg&umrn=${dt[0]}&MDT_DETAIL_FRNT_IMG_NAME=${dt[14]}&MDT_FRNT_IMG_NAME=${dt[12]}&DEBITOR_ACC_NUM=${dt[1]}">
								View Details </a>
								 <form id="storeParamsForm" method="get"
								 action="${pageContext.request.contextPath}/viewDetail">
								 <input type="hidden" name="typeImg" value="jpeg" />
								 <input type="hidden" name="umrn" value="${dt[0]}" /> 
								 <input type="hidden" name="MDT_DETAIL_FRNT_IMG_NAME" value="${dt[14]}" />
								 <input type="hidden" name="MDT_FRNT_IMG_NAME" value="${dt[12]}" /> 
								 <input type="hidden" name="DEBITOR_ACC_NUM" value="${dt[1]}" />
								 </form>
								</td>

					</tr>
				</c:forEach>
			</table>
			
			<c:if test="${not empty datalist}">
				<table width="95%">
					<center>
						<button type="submit" id="submitBtn" class="btn btn-primary"
							id="authorize-btn">Authorize</button>
					</center>
					<tr>
						<c:if test="${not empty ResMsg}">
							<td><b><c:out value="${ResMsg}" /></b></td>
						</c:if>
					</tr>
				</table>
			</c:if>
			<br> <br>
			<table width="95%">
				<input type="hidden" id="umrn" name="umrn" value="${umrn}" />
				<input type="hidden" id="rsncodes" name="rsncodes"
					value="${rsncodes}" />
				<input type="hidden" id="accept_chk" name="accept_chk"
					value="${accept_chk}" />
				<input type="hidden" id="reject_chk" name="reject_chk"
					value="${reject_chk}" />
				<tr>
					<logic:present name="ResMsg">
						<b><bean:write name="ResMsg" /></b>
					</logic:present>
				</tr>
			</table>
		</form>
		</div>
		</div>

		
</body>
<style>
table#mdt-auth-table th, td{
	vertical-align:middle;
}
table#mdt-auth-table td{
	vertical-align:middle;
}
table#mdt-auth-table td input[type="text"]{
	width:40%;
	height:25px;
}

table#mdt-auth-table tr td select {
    width: 40% !important;
    height: 29px;
    margin: 1px !important;
}
div#mdt-auth-table_wrapper {
    margin-top: 20px;
}
form#storeParamsForm {
    display: none;
}
</style>

<script>
function hideQueryParams() {  
	var urlWithoutParams = window.location.pathname;
    window.history.replaceState({}, document.title, urlWithoutParams);
}

window.onload = hideQueryParams;
</script>
</html>
