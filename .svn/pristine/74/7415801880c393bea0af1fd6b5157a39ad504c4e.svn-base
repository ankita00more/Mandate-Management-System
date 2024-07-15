<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MandateTransaction File status</title>
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
function validate() 
{
		var frmdate = document.getElementById("fromDate").value;
		var todate = document.getElementById("toDate").value;
		var Status = document.getElementById("product").value;

		var fromTime = new Date(frmdate).getTime();
		var toTime = new Date(todate).getTime();

		var difference = toTime - fromTime;
		var differenceInDays = difference / (1000 * 3600 * 24);

		if (differenceInDays > 3) {
			alert("Maximum 3 Days Can be Selected");

		} else if (frmdate === '') {
			alert("Please Select From Date");
		} else if (todate === '') {
			alert("Please Select To Date");
		} else if (Status === '') {
			alert("Please Select Status");
		} else if (frmdate > todate) {
			alert("From Date Cannot be Greater than To Date");

		} else if (todate < frmdate) {
			alert("To Date Cannot be Smaller than From Date");

		} else {
			return true;
		}
		return false;
}

</script>
 <!--<style>
    .table-striped tbody tr:nth-of-type(odd) {
        background-color: rgba(0,0,0,.05);
    }
    .container.mt-5 {
        max-width: 1280px;
        margin-left:auto;
        margin-right:auto;		
    }
    th {
        background-color: #0b5ed7 !important;
        color: white;
        cursor: pointer;
    }
    
    .table td, .table th {
        padding: .35rem;
        border: 1px solid #333;
        text-align:center;
        font-size:12px;
    }
    .table-bordered thead td, .table-bordered thead th {
        border-bottom-width: 1px;
    }
    .table thead th {
        vertical-align: bottom;
        border-bottom: 1px solid #333;
    }
    .pagination .active a {
        background-color: #007bff;
        color: white;
    }
    .pagination a:hover {
        background-color: #007bff;
        color: white;
    }
    .table-controls {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 0.5rem;
    }
    label {
        display: inline-block;
        margin-bottom: 0rem;
    }
    select.form-control {
        padding: .375rem .15rem;
        font-size: 12px;
    }
    label.form-label {
        font-size: 12px;
    }
    .form-control {
        height: 30px;
        font-size: 12px;
    }
    span#tableInfo {
        font-size: 12px;
    }
    .mt-3, .my-3 {
        margin-top: 0.65rem !important;
    }
    button#exportTop {
        width: 63%;
        font-size: 12px;
        font-weight: 600;
    }
    .mandate-transaction-file-status{
        margin-bottom:0rem;
    }
</style> -->
</head>
<body>
    <%@include file="Navigationbar1.jsp" %>
    <script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
    <form method="post" name="transtatus" id="transtatus">
        <div id="content">
            <%@include file="hamburger.jsp"%>
            <h3 align = "center" class="page-name">Mandate Status</h3>
            
            <div class="container-amend" style="max-height:100%;" align="center">
                <div class="input-field" style="display: flex; align-items: center; justify-content: center;">
                    <div style="display: flex; flex-direction: column; align-items: center; margin-right: 10px;">
                        <label style="width: 107% !important;" class="card-text">From Date :</label>
						<input type="date" name="fromDate" maxlength="0" size="0" class="form-control form_datetime" id="fromDate" 
						 	placeholder="MM/DD/YYYY" style="width: 143% !important;margin-right: 10%;" />
                    </div>  
                    
                    <div style="display: flex; flex-direction: column; align-items: center; margin-right: 10px;margin-left: 71px;">
                        <label style="width: 107% !important;" class="card-text">To Date:</label> 
						<input type="date" name="toDate" maxlength="0" size="0" class="form-control form_datetime" id="toDate"
							placeholder="MM/DD/YYYY" style="width: 143% !important;margin-left: 10%;" />
                    </div>
                    
                    <div style="display: flex; flex-direction: column; align-items: center; margin-left: 99px;">
                        <label class="header-font" style="width: 107% !important;" class="card-text">Please select status(ACK/RES): </label> 
							<select name="product" id="product" class="form-control input-box" style="width: 143% !important;margin-left: 10%;" >
									<option selected="selected" value="" class="hideli">Select</option>
									<option value="ACK">Mandate ACK From NPCI</option>
									<option value="RES">Mandate RES From Destination Bank</option>

							</select>
                    </div>
                    
                    <div style="display: flex; flex-direction: column; align-items: center; margin-left: 10px;"> 
                        <button type="submit" id="valueSubmit" style="align-items: center; margin-left: 101%;height: 33px;width: 119% !important;font-size: 13px;" 
                        class="btn btn-primary center-button" 
					    value = "Submit" onclick = "return validate();">Submit</button>
                    </div>
                </div>
                
                <!-- Table and controls, initially hidden -->
                <div id="tableContainer" style="margin-top: 2rem;">
                    
                    
                    <table class="example table table-striped table-bordered mandate-transaction-file-status" id="myTable" style="height: 80px; width:100%;">
                        <thead>
                            <tr>
                					<th>Unique Id</th>
									<th>First Collection Date</th>
									<th>Final Collection Date</th>
									<th>Creditor Account No</th>
									<th>Amount</th>
									<th>UMRN</th>
									<th>Debtor Account No</th>

									<c:if test="${productType eq 'ACK'}">
										<th>Mandate Status from NPCI</th>
									</c:if>

									<c:if test="${productType eq 'RES'}">
										<th>Mandate RES from Destination Bank</th>
										<th>Reject Reason</th>
										<th>Reason Description </th>
									</c:if>
            				</tr>
                        </thead>
                        <tbody>
                           <c:choose>
        							<c:when test="${not empty initdata}">
        			 					<c:forEach items="${initdata}" var="initdata">
        			 						<tr>
        			 	    					<td>${initdata.uniqueId}</td> 
        	 									<%-- <td>${initdata.first_collection_date}</td> --%>   
        	 									<%-- <td>${initdata.final_collection_date}</td> --%>
        	 									<td><fmt:formatDate value="${initdata.first_collection_date}" pattern="dd-MM-yy"/></td>
        	 									<td><fmt:formatDate value="${initdata.final_collection_date}" pattern="dd-MM-yy"/></td>
        	 									<td>${initdata.creditor_account_no}</td>
        	 									<td>${initdata.amount}</td>
        	 									<td>${initdata.umrn}</td>
        	 									<td>${initdata.debtor_account_no}</td>
        	 									<c:if test="${productType eq 'ACK'}">
														<td>${initdata.status}</td>
												</c:if>
												<c:if test="${productType eq 'RES'}">
														<td>${initdata.benificiary_status}</td>
														<td>${initdata.reject_reason}</td>
														<td>${initdata.reasonDesc}</td>
												</c:if>
												
        			 						</tr>
        								 </c:forEach>
        							</c:when>
        							<c:when test="${not empty message}">
        								<tr>
            								<td></td>
            								<td></td>
            								<td></td>
            								<td>No Records Found</td>
            								<td></td>
            								<td></td>
            								<td></td>
            								
            								
            								
       								    </tr>
        							</c:when>
        							<c:otherwise>
       									 <!-- <tr>
            							<td colspan="5">The list is empty</td>
       				 					</tr> -->
   									</c:otherwise>
        						</c:choose>	
                        </tbody>
                    </table>
                    
                </div> 
            </div>
       
        </div>
    </form>

    <script>
    
       /*  document.getElementById('exportTop').addEventListener('click', function (event) {
            event.preventDefault(); // Prevent the form from submitting
            var table = document.getElementById('myTable');
            var workbook = new ExcelJS.Workbook();
            var worksheet = workbook.addWorksheet('Sheet1');

            // Add column headers
            var columns = [];
            for (var i = 0; i < table.rows[0].cells.length; i++) {
                columns.push({ header: table.rows[0].cells[i].innerText, key: table.rows[0].cells[i].innerText });
            }
            worksheet.columns = columns;

            // Add rows
            for (var i = 1; i < table.rows.length; i++) {
                var row = {};
                for (var j = 0; j < table.rows[i].cells.length; j++) {
                    row[table.rows[0].cells[j].innerText] = table.rows[i].cells[j].innerText;
                }
                worksheet.addRow(row);
            }

            workbook.xlsx.writeBuffer().then(function (data) {
                var blob = new Blob([data], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
                var url = window.URL.createObjectURL(blob);
                var a = document.createElement('a');
                a.href = url;
                a.download = 'MandateTransactionFileStatus.xlsx';
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
                window.URL.revokeObjectURL(url);
            });
        });
        
        document.getElementById('searchTop').addEventListener('keydown', function (event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Prevent the form from submitting
                var searchValue = event.target.value.toLowerCase();
                var table = document.getElementById('myTable');
                var rows = table.getElementsByTagName('tr');

                for (var i = 1; i < rows.length; i++) { // Skip the header row
                    var cells = rows[i].getElementsByTagName('td');
                    var found = false;
                    for (var j = 0; j < cells.length; j++) {
                        if (cells[j].innerText.toLowerCase().includes(searchValue)) {
                            found = true;
                            break;
                        }
                    }
                    rows[i].style.display = found ? '' : 'none';
                }
            }
        });

        document.getElementById('entriesTop').addEventListener('change', function () {
            var entries = parseInt(this.value);
            var table = document.getElementById('myTable');
            var rows = table.getElementsByTagName('tr');

            for (var i = 1; i < rows.length; i++) { // Skip the header row
                rows[i].style.display = i <= entries || entries === -1 ? '' : 'none';
            }
        });

        // Initialize table display based on the dropdown value
        document.getElementById('entriesTop').dispatchEvent(new Event('change'));*/

        // Add other scripts for search and pagination if required
    </script> 
    
</body>
</html>
