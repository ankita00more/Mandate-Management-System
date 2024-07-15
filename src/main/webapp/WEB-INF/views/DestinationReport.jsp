<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/all.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/dataTables.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/forButtons.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">


<script src="${pageContext.request.contextPath}/js/jquery-min.js"></script>
<script src="${pageContext.request.contextPath}/js/datatables.js"></script>
<script src="${pageContext.request.contextPath}/js/forButtons.js"></script>
<script src="${pageContext.request.contextPath}/js/jszip.min.js"></script>
<script src="${pageContext.request.contextPath}/js/button-min.js"></script>
<script src="${pageContext.request.contextPath}/js/loading.js"></script>

<style>
.inputs-grid-container {
    display: inline-grid;
    grid-template-columns: 25% 65% 10%;
    column-gap: 30px;
    width: 100%;
    margin-bottom: 20px;
}
.container-toggle{
	display: grid;
    grid-template-columns: auto auto auto;
    column-gap: 20px;
}

input#date {
	width: 20px !important;
	height: 20px !important;
}
table#processMandateTable th{
	width:auto !important;
	
}
table#processMandateTable td{
	width:auto !important;
}
.search {
	width: 22px;
	height: 22px;
}

.container-toggle {
	display: none;
}
/*table#processMandateTable{
	width:min-content !important;
}*/
input[type=text], select[id=search-dropdown] {
	border: 1px solid #222;
	background-color: #fff;
	border-radius: 5px !important;
	color: #212529;
	font-size: 13px;
	font-weight: 400;
	padding: 10px;
	display: block;
	height: 36px;
	width: -webkit-fill-available;
	margin-top: 7px;
	color: #9d8f8f;
}
input[type=date]{
border: 1px solid #222 !important;
	background-color: #fff;
	border-radius: 5px !important;
	color: #212529;
	font-size: 13px;
	font-weight: 400;
	padding: 10px;
	display: block;
	height: 36px !important;
	width: -webkit-fill-available;
	margin-top: 7px;
	color: #9d8f8f;
}
input.btn.submitbtn.filter-record {
	color: #fff;
	padding-left: 7px;
	padding-right: 7px;
	border-radius: 4px;
	border: none;
	margin-top: 32px;
	height: auto;
	background: #32325dd6;
}
</style>


</head>
<script>
	$(document).ready(function() {
		$('select[name="search"]').on('change', function() {
			$('.container-toggle').hide();
			$('#' + $(this).val()).css("display", "grid");
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#date-container").hide();
		$("#account-container").hide();
		$("#umrn-container").hide();

		$("input[name='search']").click(function() {
			$("#date-container").hide();
			$("#account-container").hide();
			$("#umrn-container").hide();

			if ($(this).val() == 'date_radio') {
				$("#date-container").show();
			} else if ($(this).val() == 'acc_no_radio') {
				$("#account-container").show();
			} else if ($(this).val() == 'umrn_radio') {
				$("#umrn-container").show();
			}
		});
	});
</script>
<script>
	jQuery(document).ready(function($) {
		$("td *").each(function() {
			var text = $(this).text();
			if (/^\d{2}:\d{2}:\d{2}$/.test(text)) {
				$(this).css("display", "none");
			}
		});
	});
</script>
<script>
function showImage(base64Image, umrn) {
    var imageDiv = document.getElementById('image-' + umrn);
    var imageWindow = window.open();
    if (imageDiv.style.display === 'none') {
        imageDiv.innerHTML = '<img src="data:image/jpeg;base64,' + base64Image + '" alt="Image">';
        imageWindow.document.write('<html><body bgcolor="#333" style="align-content:center;"><center><img src="data:image/jpeg;base64,' + base64Image + '" /></center></body></html>');
        /* imageDiv.style.display = 'block'; */
    } else {
        imageDiv.style.display = 'none';
    }
}
</script>
</head>
<body>
	<%@include file="Navigationbar1.jsp"%>
	
	  <script>
	  jQuery(document).ready(function($) {
		    var table = $('.example').DataTable({
		      "ordering": true,
		      "scrollX": true,
		      "lengthMenu": [5, 10, 25, 50, 100],
		      dom: 'lBfrtip',
		      buttons: [
		        {
		          text: 'Export All',
		          extend: 'excelHtml5',
		          customize: function(xlsx) {
		            var sheet = xlsx.xl.worksheets['sheet1.xml'];
		          }
		        },
		        'pdfHtml5'
		      ]
		    });
			
		    $('.example').on('page.dt', function() {
		      $('html, body').animate({
		        scrollTop: $('.example').offset().top
		      }, 'slow');
		    });
		  });
	</script>

	<div id="content">
		<%@include file="hamburger.jsp"%>
		<center>
			<h3 class="page-name">Mandate Reports</h3>
		</center>
		<form action="#" method="post">
			<div class="container-amend">

				<div class="inputs-grid-container">
	<div class="inputs-grid">
    <label for="search-dropdown">Search By:</label>
    <select id="search-dropdown" name="search" class="form-inputs">
    	<option value="">Search By:</option>
        <option value="date_radio">Date</option>
        <option value="acc_no_radio">Account No</option>
        <option value="umrn_radio">UMRN</option>
    </select>
</div>
<div class="inputs-grid">
<form onsubmit="return validate();" method="post">

					<div class="container-toggle" id="date_radio">
						<div class="container-toggle-grid">
							<label for="from-date">Please Select Date:</label> <input
								type="date" name="from-date"
								class="form-control form-inputs form_datetime" id="from-date"
								required>
					     </div>	
					     <div class="container-toggle-grid">
							<label for="from-date">Please Select Date:</label> <input
								type="date" id="to-date" name="to-date"
								class="form-control form-inputs form_datetime" required>
						 </div>

						 <div class="container-toggle-grid">

							<input type="submit" value="Submit"
								class="btn submitbtn filter-record">
						</div>
					</div>

				</form>
				<form onsubmit="return validate();" method="post">
				<div id="acc_no_radio" class="container-toggle">
					<div class="container-toggle-grid">
						<label for="account-number">Account Number:</label> <input
							type="text" id="account-number" name="account-number"
							class="form-control form-inputs" value="">
					</div>
					<input type="hidden" name="search" value="acc_no_radio">
					<div class="container-toggle-grid" style="margin-top: auto;">
						<input type="submit" value="Submit"
							class="btn submitbtn filter-record" />
					</div>
				</div>

			</form>
			
			<form onsubmit="return validate();" method="post">


			<div class="container-toggle" id="umrn_radio">
				<div class="container-toggle-grid">
					<label for="from-date">UMRN:</label> <input type="text"
						id="umrn-number" name="umrn-number"
						class="form-control form-inputs">
				</div>
				<input type="hidden" name="search" value="umrn_radio">
				<div class="container-toggle-grid">

					<input type="submit" value="Submit"
						class="btn submitbtn filter-record">
				</div>
			</div>
		</form>
				
</div>
				</div>



				

				



				<form method="post" id="form1" name="form1">


					<table id="processMandateTable"
						class="example table table-striped table-bordered">
						<thead>
							<tr>
								<th>Creation Time</th>
								<th>UMRN</th>
								<th>Msg Id</th>
								<th>Mandate Category</th>
								<th>Status</th>
								<th>Reject Reason</th>
								<th>Debtor Account No</th>
								<th>Creditor Name</th>
								<th>Max Amount</th>
								<th>First Collection Date</th>
								<th>Final Collection Date</th>
								<th>Customer Name</th>
								<th>Acc Image Status</th>
								<th>Acc Type</th>
								<th>Frequency</th>
								<th>Fixed Amount</th>
								<th>MDT Zip File Name</th>
								<th>Modified Time</th>
								<th>View Image</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty list}">
									<c:forEach items="${list}" var="row">
										<tr>
											<td>${row.create_time}</td>
											<td>${row.umrn}</td>
											<td>${row.msgId}</td>
											<td>${row.mdtCat}</td>
											<td>${row.status}</td>
											<td>${row.rejectReason}</td>
											<td>${row.DEBITOR_ACC_NUM}</td>
											<td>${row.CREDITOR_NAME}</td>
											<td>${row.MAX_AMT}</td>
											<td>${row.FRST_COLLCTN_DT}</td>
											<td>${row.FNL_COLLCTN_DT}</td>
											<td>${row.CUST_NAME}</td>
											<td>${row.acct_img_status}</td>
											<td>${row.acct_type}</td>
											<td>${row.FREQUENCY}</td>
											<td>${row.fixedAmt}</td>
											<td>${row.MDT_ZIP_FILE_NAME}</td>
											<td>${row.MODIFIED_TIME}</td>	
											<td>
									           <a href="data:image/jpeg;base64,${row.jpgImage}" onclick="showImage('${row.jpgImage}', '${row.umrn}')" target="_blank">View Image</a>
									    <div id="image-${row.umrn}" style="display:none;">
									   	
									        <%-- <img src="data:image/jpeg;base64,${row.jpgImage}" >  --%>
									             </td> 
											
										</tr>

									</c:forEach>
								</c:when>
								<c:when test="${not empty message}">

								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>

				</form>
			</div>
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