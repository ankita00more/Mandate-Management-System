<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<style type="text/css">
.zoomin img {
	height: 300px;
	width: 500px;
	-webkit-transition: all 2s ease;
	-moz-transition: all 2s ease;
	-ms-transition: all 2s ease;
	transition: all 2s ease;
}

.zoomin img:hover {
	width: 800px;
	height: 600px;
}
</style>


<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Data For Authorization</title>
<!-- Include Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function ShowHideDivNew() {
		alert("DRop down of rjection reason")
		var radio_val = $("input[name='accept']:checked").val();

		if (radio_val == 'reject') {

			document.getElementById("reject_div").style.display = "block";
		} else {

			document.getElementById("reject_div").style.display = "none";
		}
	}

	jQuery(document).ready(function($) {
		// Test jQuery functionality
		console.log("jQuery is working!");
	});

	var jq = jQuery.noConflict();

	function updateAndRedirect(umrn) {
		alert("Inside update function 1");
		var accept = "unckeck";
		var reject = "unckeck";
		var rsnCode = "00";

		var radio_val = jq("input[name='accept']:checked").val();
		console.log("Radio Value:", radio_val);

		if (radio_val === 'accept') {
			accept = 'check';
		} else if (radio_val === 'reject') {
			reject = 'check';
		}

		var selectedReason = jq("#reject_div select").val();
		console.log("Selected Reason Code:", selectedReason);

		if (selectedReason === "" || selectedReason === undefined) {
			alert("Please select a reason.");
			return; // Exit the function if reason is not selected
		}

		console.log("accept--->", accept);
		console.log("reject--->", reject);
		console.log("rsnCode--->", selectedReason);

		var data = {
			accept : accept,
			reject : reject,
			umrn : umrn,
			rsnCode : selectedReason
		};

		console.log("Data before AJAX request:", data); // Add this line for debugging

		jq.ajax({
			type : "POST",
			url : "/processMandate",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				alert('Ajax working');
				// Handle success response
			},
			error : function(xhr, status, error) {
				alert('Ajax not working');
				// Handle error response
			}
		});
	}

	function chk_validate(id, cnt) {
		if ($('input[name="reject_chk"]').is(":checked")) {
			$('select[name="STATUS_RSN_CODE"]').attr('disabled', false);
			document.getElementById('STATUS_RSN_CODE' + cnt).value = ''
		} else {
			$('select[name="STATUS_RSN_CODE"]').attr('disabled', true);
			document.getElementById('STATUS_RSN_CODE' + cnt).value = 'ac01';
		}
		if (id == 'acc_chk' + cnt) {

			//var val= document.getElementById('rej_chk'+cnt).value;
			//alert(val);
			document.getElementById('rej_chk' + cnt).checked = false;
			document.getElementById('STATUS_RSN_CODE' + cnt).value = 'ac01';
			document.getElementById('STATUS_RSN_CODE' + cnt).disabled = true;
		} else if (id == 'rej_chk' + cnt) {

			document.getElementById('acc_chk' + cnt).checked = false;
			document.getElementById('STATUS_RSN_CODE' + cnt).value = '';
			document.getElementById('STATUS_RSN_CODE' + cnt).disabled = false;
		}

	}

	function validate_all_cheks() {
		var cnt = document.getElementsByName("accept_chk");
		var rej = document.getElementsByName("reject_chk");
		var flg = 0;
		for (var j = 0; j < cnt.length; j++) {
			//if(cnt[j].type == "checkbox" && rej[j].type == "checkbox") { 
			if (cnt[j].checked == false && rej[j].checked == false) {
				flg = 1;
			}
		}

		var rsn = document.getElementsByName("STATUS_RSN_CODE");
		//alert(rsn.length);
		for (var j = 0; j < rsn.length; j++) {
			//alert("rej[j].checked:"+rej[j].checked+" rsn[j].value:"+rsn[j].value);
			if (rej[j].checked == true && rsn[j].value == "") {
				alert("Please select Reject Reason.");
				return false;
			}
		}
		/* document.viewForm.action="/ACH_DEBIT_H2H_UAT/Mdtaccept.do?opt=accept_mdt&umrn="+umrn; */
		document.viewForm.action = "/MMS_H2H_DESTINATION/Mdtaccept.do?opt=accept_mdt&umrn="
				+ umrn;
		document.viewForm.submit();

	}

	function chkall(source) {
		var acc = document.getElementsByName("accept_chk");
		var rej = document.getElementsByName("reject_chk");
		var rsn = document.getElementsByName("STATUS_RSN_CODE");
		//alert(acc);
		for (var i = 0; i < acc.length; i++) {
			if (acc[i].type == "checkbox") {
				if (acc[i].checked == false && rej[i].checked == false) {
					acc[i].checked = source.checked;
					rej[i].checked = false;
					rsn[i].value = 'AC01';
					rsn[i].disabled = true;

				} else if (acc[i].checked == false && rej[i].checked == true) {
					rej[i].checked = false;
					acc[i].checked = false;
					rsn[i].value = '';
					rsn[i].disabled = false;
				} else if (acc[i].checked == true && rej[i].checked == false) {
					rej[i].checked = false;
					acc[i].checked = false;
					rsn[i].value = 'AC01';
					rsn[i].disabled = true;
				}
				//else if(acc[i].checked == true){
				//acc[i].checked = false;
				//rej[i].checked = false;
				//} 
				if (acc[i].checked == false) {
					acc[i].checked = source.checked;
					rej[i].checked = false;
					rsn[i].value = 'AC01';
					rsn[i].disabled = false;
				}
				if (acc[i].checked == false && rej[i].checked == false) {
					acc[i].checked = source.checked;
					rej[i].checked = false;
					rsn[i].value = 'AC01';
					rsn[i].disabled = false;

				}

			}
		}
	}
	function chk_all_1(id) {
		//alert('in chk all'+cnt);
		if (document.getElementById('chk_acc').checked == true) {
			//alert('in it chked true!!');
			document.getElementById('acc_chk').checked = true;
			//document.getElementById('acc_rej'+j).checked = false;

			if (document.getElementById('chk_acc').checked = false) {

				document.getElementById('acc_chk').checked = false;

			}

		} else {

			document.getElementById('acc_chk').checked = false;

		}
	}
	
	function submitBackForm() {
        document.getElementById("backForm").submit();
    }

	function ss(id) {
		var feild = document.getElementById('chk_acc');
		//alert('sdf');
		//alert('in ss' +field.length);
		for (i = 0; i < field.length; i++) {
			field[i].checked = true;
		}
		/*if(document.getElementById('chk_acc').checked == true){
			alert('in it chked true!!');
				document.getElementById('acc_chk').checked = true;
				//document.getElementById('acc_rej'+j).checked = false;
			
			}else{
				
					document.getElementById('acc_chk').checked = false;
				
			}*/
	}

	function showTip(ddl) {
		document.getElementById("DropDownList1").title = ddl;
	}

	function oprnPopupToCompareSignature(umrn, MDT_ZIP_FILE_NAME, COLLCTN_AMT,
			DEBITOR_ACC_NUM, mdt_detail_frnt_img_name, mdt_frnt_img_name) {
		//document.form1.umrn.value = umrn;
		testwindow = window.open(
				"${pageContext.request.contextPath}/provideUMRNData?umrn="
						+ umrn + "&MDT_ZIP_FILE_NAME=" + MDT_ZIP_FILE_NAME
						+ "&COLLCTN_AMT=" + COLLCTN_AMT + "&DEBITOR_ACC_NUM="
						+ DEBITOR_ACC_NUM + "&mdt_detail_frnt_img_name="
						+ mdt_detail_frnt_img_name + "&mdt_frnt_img_name="
						+ mdt_frnt_img_name, "mywindow",
				"location=1,status=1,scrollbars=1,width=300,height=300");
		testwindow1 = window.open(
				"${pageContext.request.contextPath}/provideUMRNData&mdt_frnt_img_name="
						+ mdt_frnt_img_name,
				"location=1,status=1,scrollbars=1,width=700,height=700");

		testwindow.moveTo(0, 0);
		if (window.focus) {
			testwindow.focus()

		}
		return false;
	}
</script>

</head>
<body>
<%-- 	<%@include file="Navigationbar1.jsp"%> --%>
<%-- 	<%@include file="Navigationbar.jsp"%> --%>
	<div class="container mt-3">
		<h1>Veiw Details of Customer</h1>
		<form action="viewDetails" method="post" name="viewDetails"
			id="viewdetails">
			<div id="dialog" title="View Detail" align="center">
				<div style="width: 100%; hieght: 100%">
					<div style="float: right; width: 50%">
						<table bordercolor="#cceeff" style="overflow-x: auto;">
							<tr>
								<td>UMRN</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" id="umrn"
									value="<c:out value="${authDto.UMRN}"></c:out>"></td>
							</tr>
							<tr>
								<td>Customer Name</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.custName}"></c:out>"></td>
							</tr>
							<tr>
								<td>Messege Refrence ID</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.messageId}"></c:out>"></td>
							</tr>
							<tr>
								<td>Payment type</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.paymentType}"></c:out>"></td>
							</tr>
							<tr>
								<td>Currency</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="RUPEE"></c:out>"></td>
							</tr>
							<tr>
								<td>Fixed Amount</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.fixedAmt}"></c:out>"></td>
							</tr>
							<tr>
								<td>Max Amount</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.maxAmt}"></c:out>"></td>
							</tr>
							<tr>
								<td>Category</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.category}"></c:out>"></td>
							</tr>

							<tr>
								<td>frequency</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.frequency}"></c:out>"></td>
							</tr>
							<tr>
								<td>Start Date</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.startDate}"></c:out>"></td>
							</tr>
							<tr>
								<td>End Date</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.endDate}"></c:out>"></td>
							</tr>
							<tr>
								<td>Valied Until cancelled</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.endDate}"></c:out>"></td>
							</tr>
							<tr>
								<td>Creditor Name</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.creditorName}"></c:out>"></td>
							</tr>

							<tr>
								<td>Customer Telephone</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text" value="<c:out value="${authDto.mobile}"></c:out>"></td>
							</tr>
							<tr>
								<td>Customer Email</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.custEmail}"></c:out>"></td>
							</tr>
							<tr>
								<td>Debitor Account No.</td>
								<td align="left"><input class="textBox" disabled="disabled"
									type="text"
									value="<c:out value="${authDto.debitorAcNum}"></c:out>"></td>
							</tr>
							<tr>
								<td align="right">&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;CBS DETAILS</td>
							</tr>

							<c:forEach var="data" items="${cbsdata}">
								<tr>
									<td>Account No.</td>
									<td align="left"><input class="textBox"
										disabled="disabled" type="text" value="${data.ACCT_NO}">
									</td>
								</tr>
								<tr>
									<td>Account Name</td>
									<td align="left"><input class="textBox"
										disabled="disabled" type="text" value="${data.ACCOUNT_NAME}"></td>
								</tr>
								<tr>
									<td>Account Status</td>
									<td align="left"><input class="textBox"
										disabled="disabled" type="text" value="${data.ACCT_STATUS}"></td>
								</tr>
								<tr>
									<td>Account Type</td>
									<td align="left"><input class="textBox"
										disabled="disabled" type="text" value="${data.ACCT_TYPE}"></td>
								</tr>
								<tr>
									<td>Mode of Operation</td>
									<td align="left"><input class="textBox"
										disabled="disabled" type="text" value="${data.MOP}"></td>
								</tr>
							</c:forEach>
							<tr>
								<td><input type="radio" id="chkYes" name="accept"
									onclick="ShowHideDivNew();" value="accept" />Accept</td>
								<td><input type="radio" id="chkNo" name="accept"
									onclick="ShowHideDivNew();" value="reject" />Reject</td>

							</tr>
							<tr>
								<td>
									<div style="display: none" id="reject_div">
										<select>
											<c:if test="${not empty STATUS_RSN_CODE}">
												<option value="">** SELECT **</option>
												<c:forEach items="${STATUS_RSN_CODE}" var="innerArrayItem">
													<option value="${innerArrayItem[0]}">
														${innerArrayItem[0]} - ${innerArrayItem[1]}</option>
												</c:forEach>
											</c:if>
										</select>
									</div> <!-- 									<div style="display: none" id="reject_div"> -->
									<!-- 										<select> --> <!-- 											Hardcoded value -->
									<!-- 											<option value="C003">C003 - Account Closed</option> -->
									<!-- 										</select> --> <!-- 									</div> -->

								</td>
							</tr>
							<tr>
								<!-- 																<td style="padding-top: 20px;"><input class="conBtn" -->
								<%-- 																	type="button" value="Confirm" id="${authDto.UMRN}" --%>
								<!-- 																	onclick="updateAndRedirect(this.id);"></td> -->

								<form id="viewdetails" method="post">

									<!-- Form fields -->
								<td style="padding-top: 20px;"><input class="conBtn"
									type="button" value="Confirm" id="${authDto.UMRN}"
									onclick="updateAndRedirect(this.id);"></td>
								</form>
								<td>
									<form id="backForm" method="post"
										action="${pageContext.request.contextPath}/back_mndt">
										<input type="hidden" name="zipfilename" value="${zipfilename}">
									</form> <a href="#" onclick="submitBackForm()">Back</a>
								</td>
								<%-- <form method="post" action="/accept">
										<input type="hidden" name="zipfilename" value="${zipfilename}" />
										<button type="submit">Back</button>
									</form> --%>
						</table>
					</div>



					<div class="zoomin" style="float: left; width: 50%;">
						<img width="500px;" alt="jpeg"
							src="data:image/jpeg;base64,${gifImg}" border="1"
							style="border-color: red">
						<c:out value=""></c:out>
						<%-- <c:out value=".JPEG"></c:out>  --%>
						<img width="500px;" alt="Tiff"
							src="data:image/jpeg;base64,${gifImg}" border="1"
							style="border-color: red" vspace="10">
						<c:out value=""></c:out>


						<h1>Customer Signature</h1>
						<img width="500px;" alt="jpeg" src="${cbsdataimg0}" border="1"
							style="border-color: red" vspace="10">
						<c:out value=""></c:out>
						<br> <img width="500px;" alt="jpeg" src="${cbsdataimg1}"
							border="1" style="border-color: red" vspace="10">
						<c:out value=""></c:out>
						<br> <img width="500px;" alt="jpeg" src="${cbsdataimg2}"
							border="1" style="border-color: red" vspace="10">
						<c:out value=""></c:out>

					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Include Bootstrap JS and Popper.js -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>
