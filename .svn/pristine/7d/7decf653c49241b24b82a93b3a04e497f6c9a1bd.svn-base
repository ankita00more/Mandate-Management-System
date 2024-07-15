<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Todays Mandate Transaction</title>
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
.table{
margin-bottom:0rem;
}
.table-bordered thead td, .table-bordered thead th {
    border-bottom-width: 1px;
}

.table thead th {
    vertical-align: bottom;
    border-bottom: 1px solid #333;
    background:#0b5ed7 !important;
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
input#searchTop {
    height: 30px;
    margin-top: 0px;
}
button.btn{
	width: 63%;
    font-size: 12px;
    font-weight: 600;
    height:31px;
}
.mandate-transaction-file-status{
margin-bottom:0rem;
}
select#product {
    height: 33px;
    border-radius: 5px;
    border: 1px solid #aaaaaa;
}
</style> -->
</head>
<body>
    <%@include file="Navigationbar1.jsp" %>
    <script src="${pageContext.request.contextPath}/js/datatable-ud-script.js"></script>
    <form method="post" name="addumrnform" id="addumrnform">
        <div id="content">
            <%@include file="hamburger.jsp"%>
            <center><h3 class="page-name">Today's Mandate Transaction</h3></center>
            <div class="container-amend today-mandate-transaction" align="center">
                
                    <table class="example table table-striped table-bordered table-hover data-table" id="tableID" style="height: 80px; width:100%;">
                        <thead>
                            <tr>
                                <th>Unique Id</th>
                                <th>Inp File Name</th>
                                <th>Creation Date</th>
                                <th>Creditor Account No</th>
                                <th>UMRN</th>
                                <th>Debtor Account No</th>
                                <th>Res File Name</th>
                                <th>Reason Code</th>
                                <th>Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty list}">
                                    <c:forEach items="${list}" var="row">
                                        <tr>
                                            <td>${row[0]}</td>
                                            <td>${row[1]}</td>
                                            <td>${row[2]}</td>
                                            <td>${row[3]}</td>
                                            <td>${row[4]}</td>
                                            <td>${row[5]}</td>
                                            <td>${row[6]}</td>
                                            <td>${row[7]}</td>
                                            <td>${row[8]}</td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${not empty message}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>No Records Found</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                   <!--  <tr>
                                    <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>No Records Found</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr> -->
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                    
           
            </div>
        </div>
       
            <footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
        </div>
    </form>
   
</body>
</html>
