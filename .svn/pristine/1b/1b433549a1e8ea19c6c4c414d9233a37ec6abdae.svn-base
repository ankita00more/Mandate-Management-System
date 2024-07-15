<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
 <style>
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
</style>
</head>
<body>
    <%@include file="Navigationbar1.jsp" %>
    <script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
    <form method="post" name="transtatus" id="transtatus">
        <div id="content">
            <%@include file="hamburger.jsp"%>
            <center><h3 class="page-name">Mandate Transaction File Status</h3></center>
            
            <div class="container-amend" style="max-height:100%;" align="center">
                <div class="input-field" style="display: flex; align-items: center; justify-content: center;">
                    <div style="display: flex; flex-direction: column; align-items: center; margin-right: 10px;">
                        <label for="date" style="width: 107% !important;" class="card-text">Mandate Transaction Date :</label>
                        <input type="date" style="width: 143% !important;margin-right: 10%;" maxlength="0" size="0" id="date" name="date"/>
                    </div>  
                    
                    <div class="col-sm-1" style="margin: 0 10px;margin-top: 1%">
                        <span style="color: red; font-weight: bold;">-- OR --</span>
                    </div>
                    
                    <div style="display: flex; flex-direction: column; align-items: center; margin-left: 10px;">
                        <label for="creditor_acc_no" style="width: 107% !important;" class="card-text">Enter Creditor Account No :</label>
                        <input type="text" style="width: 143% !important;margin-left: 10%;" id="creditor_acc_no" name="creditor_acc_no"/>
                    </div>
                    
                    <div style="display: flex; flex-direction: column; align-items: center; margin-left: 10px;"> 
                        <button type="submit" style="align-items: center; margin-left: 101%;height: 35px;width: 93% !important;font-size: 13px;" class="btn btn-primary center-button" value="Submit" onclick="return Check();">Submit</button>
                    </div>
                </div>
                
                <!-- Table and controls, initially hidden -->
                <div id="tableContainer" style="margin-top: 2rem;">
                    
                    <table class="example table table-striped table-bordered mandate-transaction-file-status" id="myTable" style="height: 80px; width:100%;">
                        <thead>
                            <tr>
                                <th>Unique Id</th>
                                <th>Creditor Account No</th>
                                <th>UMRN</th>
                                <th>Debtor Account</th>
                                <th>Amount</th>
                                <th>INP File Name</th>
                                <th>Creation Date</th>
                                <th>RES File Name</th>
                                <th>Reason Code</th>
                                <th>SAL File Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty list}">
                                    <c:forEach items="${list}" var="row">
                                        <tr>
                                            <td>${row[0]}</td> 
                                            <td>${row[3]}</td>  
                                            <td>${row[4]}</td>
                                            <td>${row[5]}</td> 
                                            <td>${row[8]}</td>
                                            <td>${row[1]}</td>
                                            <td>${row[2]}</td>
                                            <td>${row[6]}</td>
                                            <td>${row[7]}</td>
                                            <td>${row[9]}</td>
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
                    <div class="table-controls"> 
                        <div>
                            <span id="tableInfo"></span>
                        </div>
                        <div>
                            <div id="pagination" class="mt-3"></div>
                        </div>
                    </div> 
                </div> 
            </div>
        </div>
    </form>

    <script>
        function Check() {
            var date = document.getElementById("date").value;
            var creditor_acc_no = document.getElementById("creditor_acc_no").value;

            if (date === "" && creditor_acc_no === "") {
                alert("Please enter either a Mandate Transaction Date or Creditor Account No.");
                return false;
            }
        }

        /* document.getElementById('exportTop').addEventListener('click', function (event) {
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
        document.getElementById('entriesTop').dispatchEvent(new Event('change'));

        // Add other scripts for search and pagination if required */
    </script>
</body>
</html>
