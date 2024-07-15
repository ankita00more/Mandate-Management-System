<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<style type="text/css">

</style>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Data For Authorization</title>
<!-- Include Bootstrap CSS -->

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
<%-- <script src="${pageContext.request.contextPath}/js/tiff.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/tiff.js"></script>


<script>
function acceptMandate() {
	var umrn = $("#umrn").val(); 
	var accept = "check"; 
	var reject = "";
	var reasonCode = "ac01";
	var data = {
		accept : accept,
		reject : reject,
		umrn : umrn,
		rsnCode : "ac01",
	};
	$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/process_M",
		data : JSON.stringify(data),
		contentType : "application/json",
		success : function(response) {
			alert('You Have accepted Data successfully');
			// Once the AJAX request is successful, submit the form
			 $("#backForm").submit();
		},
		error : function(xhr, status, error) {
			alert('Accept Button (Ajax) not working');
		}
	});
}

function rejectMandate() {
	//alert("Inside rejectMandate");
	// Show the rejection reason modal
	var modal = document.getElementById("rejection-reason-modal");
	modal.style.display = "block";
}

function updateAndRedirect() {
	   // alert("Inside updateAndRedirect");
	    var accept = "";
	    var reject = "check";
	    var umrn = document.getElementById("umrn").value;
	    var rsnCodeElement = document.getElementById("reason").value;
	    
	    console.log("rsnCodeElement:", rsnCodeElement); // Debugging statement
	    
	    if (rsnCodeElement === "" || rsnCodeElement === undefined) {
	        alert("Please select a reason.");
	        return; // Exit the function if reason is not selected
	    } else {
	        var data = {
	            accept: accept,
	            reject: reject,
	            umrn: umrn,
	            rsnCode: rsnCodeElement
	        };
	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/process_M",
	            data: JSON.stringify(data),
	            contentType: "application/json",
	            success: function(response) {
	                alert('Data Rejected successfully');
	                // Once the AJAX request is successful, submit the form
	                document.getElementById("backForm").submit();
	            },
	            error: function(xhr, status, error) {
	                alert('Reject Button (Ajax) not working');
	            }
	        });
	    }
	}


function submitBackForm() {
	$("#backForm").submit();
	alert("You are going back to MDTAuth page")
}
</script>
</head>
<body>
	<%-- 	<%@include file="common/navbar.jsp"%> --%>
	<%@include file="Navigationbar1.jsp"%>
	<div class="data-auth-mandate content" id="content">
	<%@include file="hamburger.jsp"%>
	<center><h3 class="page-name">Mandate Authorization</h3></center>
		<div class="elgb-grid-container">
			<div class="accordion">
				Mandate Details <span class="accordion-icon"><img
					src="${pageContext.request.contextPath}/images/faq-icons-white.png"
					class="accordion-icon-img"></span>
			</div>
			<div class="panel" style="display: block;">
				<form method="post" name="form1" id="form1">
					<table class="accordion-table">
						<tr>
							<td><label for="umrn">UMRN:</label> <input class="textBox"
								disabled="disabled" type="text" id="umrn"
								value="<c:out value="${authDto.UMRN}"></c:out>"></td>

							<td><label for="name">Customer Name:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.custName}"></c:out>"></td>


							<td><label for="phone">Message Reference ID:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.messageId}"></c:out>"></td>

							<td><label for="phone">Payment Type:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.paymentType}"></c:out>">
							</td>

							<td><label for="phone">Currency:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="RUPEE"></c:out>"></td>
						</tr>
						<tr>
							<td><label for="phone">Fixed Amount:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.fixedAmt}"></c:out>"></td>

							<td><label for="email">Max Amount:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.maxAmt}"></c:out>"></td>

							<td><label for="email">Category:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.category}"></c:out>"></td>

							<td><label for="phone">Frequency:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.frequency}"></c:out>"></td>
							<td><label for="phone">Start Date:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.startDate}"></c:out>"></td>
						</tr>
						<tr>
							<td><label for="phone">End Date:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.endDate}"></c:out>"></td>

							<td><label for="phone">Valid Until Cancelled:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.endDate}"></c:out>"></td>

							<td><label for="phone">Creditor Name:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.creditorName}"></c:out>">
							</td>

							<td><label for="message">Customer Telephone:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.mobile}"></c:out>"></td>

							<td><label for="message">Customer Email:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.custEmail}"></c:out>"></td>
						</tr>
						<tr>
							<td><label for="message">Debtor's A/c No:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="<c:out value="${authDto.debitorAcNum}"></c:out>">
							</td>
						</tr>
					</table>
		
					 <table style="width: 40% !important;">
						<tr>
							<td style="display: flex;">
								<!-- Display JPEG image --> 
							<a href="#lightbox1"><img
									src="data:image/jpeg;base64,${JPEG}" alt="jpeg"
									class="left-side-images" style="width: 280px;">
							</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- Display TIFF image -->
							<a href="#lightbox2">
								 <div class="tiff-viewer"></div>	
								 <div class="tiff-viewer-alt" style="display:none;">
								 <img  alt="TIFF"></div>							
								</a>
							</td>
						</tr>
					</table> 
						</form>	
		</div>

			<!-- Second -->
			<div class="accordion">
				CBS Details <span class="accordion-icon"><img
					src="${pageContext.request.contextPath}/images/faq-icons-white.png"
					class="accordion-icon-img"></span>
			</div>
			<div class="panel">
				<table class="accordion-table">
					<c:forEach var="data" items="${cbsdata}">
						<tr>
							<td><label for="umrn">Account No:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="${data.ACCT_NO}"></td>

							<td><label for="name">Account Name:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="${data.ACCOUNT_NAME}"></td>

							<td><label for="phone">Account Status:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="${data.ACCT_STATUS}"></td>

							<td><label for="phone">Account Type:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="${data.ACCT_TYPE}"></td>

							<td><label for="phone">Mode of Operation:</label> <input
								class="textBox" disabled="disabled" type="text"
								value="${data.MOP}"></td>
						</tr>
					</c:forEach>
				</table>
				
				<table style="width: 100% !important;"> 
					<tr>
					
						<td style="display: flex;"><br> <a href="#lightbox3">
								<img src="data:image/jpeg;base64,${cbsdataimg0}" alt="jpeg"
								class="left-side-images" style="width: 280px;">
						</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#lightbox4"> <img
								src="data:image/jpeg;base64,${cbsdataimg1}" alt="jpeg"
								class="left-side-images" style="width: 280px;">
						</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#lightbox5"> <img
								src="data:image/jpeg;base64,${cbsdataimg2}" alt="jpeg"
								class="left-side-images" style="width: 280px;">
						</a></td>
						<td></td>
						
					</tr>
				</table>
				
</div>
			<table>
				<tr style="width: 50%;">
					<td></td>
					<td style="width: 20%;"><button id="accept" type="button"
							onclick="acceptMandate()">Accept</button></td>
					<td style="width: 20%;"><button id="reject" type="button"
							onclick="rejectMandate()">Reject</button></td>
					<td style="width: 20%;"><button type="button" id="back"
							onclick="submitBackForm()">Back</button></td>
					<td></td>
				</tr>
</table>

				<!-- The Modal -->
				<div id="rejection-reason-modal" class="rejection-reason-modal"
					style="display: none;">
					<!-- Modal content -->
					<div class="rejection-reason-modal-content">
						<span class="rejection-reason-close">&times;</span><br> <label
							for="reason">Rejection Reason:</label> <select id="reason"
							name="rsnCode">
							<c:if test="${not empty STATUS_RSN_CODE}">
								<option value="">** SELECT **</option>
								<c:forEach items="${STATUS_RSN_CODE}" var="innerArrayItem">
									<option value="${innerArrayItem[0]}">
										${innerArrayItem[0]} - ${innerArrayItem[1]}</option>
								</c:forEach>
							</c:if>
						</select> <input class="conBtn" type="submit" value="Confirm" id="submit"
							onclick="updateAndRedirect();">
					</div>
				</div>	
				
			<br>
			<br>
			<br>
			</div>
			<form id="backForm" method="post" action="${pageContext.request.contextPath}/back_mndt">
				<input type="hidden" name="zipfilename" value="${zipfilename}">
			</form>
		</div>
	<div class="lightbox" id="lightbox1">
		<a href="#lightbox2" class="next">&rsaquo;</a> <img
			src="data:image/jpeg;base64,${JPEG}" alt="Image 1"> <a
			href="#" class="close">x</a>
	</div>
	<div class="lightbox" id="lightbox2">
		<a href="#lightbox3" class="next">&rsaquo;</a> <a href="#lightbox1"
			class="prev">&lsaquo;</a> <div class="tiff-viewer1" title="Tiff"></div>
			<div class="tiff-viewer-alt"><img src="nonexistent-image.jpg" alt="TIFF"></div>
			<a href="#" class="close">x</a>
	</div>
	<div class="lightbox" id="lightbox3">
		<a href="#lightbox4" class="next">&rsaquo;</a> <a href="#lightbox2"
			class="prev">&lsaquo;</a> <img
			src="data:image/jpeg;base64,${cbsdataimg0}" alt="Image 2"> <a
			href="#" class="close">x</a>
	</div>
	<div class="lightbox" id="lightbox4">
		<a href="#lightbox5" class="next">&rsaquo;</a> <a href="#lightbox3"
			class="prev">&lsaquo;</a> <img
			src="data:image/jpeg;base64,${cbsdataimg1}" alt="Image 2"> <a
			href="#" class="close">x</a>
	</div>
	<div class="lightbox" id="lightbox5">
		<a href="#lightbox1" class="next">&rsaquo;</a> <a href="#lightbox4"
			class="prev">&lsaquo;</a> <img
			src="data:image/jpeg;base64,${cbsdataimg2}" alt="Image 2"> <a
			href="#" class="close">x</a>
	</div>
	<style>
	table.accordion-table th,td{
		text-align:left !important;
	}
	</style>
<script>
document.addEventListener("DOMContentLoaded", function () {
    var tiffData = 'data:image/tiff;base64,${TIFF}';
    console.log("TIFF Data:", tiffData); // Debugging statement
    if (tiffData) {
        fetch(tiffData)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch TIFF image');
                }
                return response.arrayBuffer();
            })
            .then(buffer => {
                console.log("Buffer:", buffer); // Debugging statement
                try {
                    var tiff = new Tiff({buffer: buffer});
                    if (tiff) {
                        var canvas = tiff.toCanvas();
                        if (canvas) {
                            console.log("Canvas:", canvas); // Debugging statement
                            canvas.classList.add("left-side-images");
                            canvas.setAttribute('id', 'tiff-image-1');
                            canvas.style.width = "280px";
                            var tiffViewer = document.querySelector(".tiff-viewer");
                            if (tiffViewer) {
                                tiffViewer.appendChild(canvas);
                            } else {
                                console.error("Element with class 'tiff-viewer' not found.");
                            }
                        } else {
                            console.error("Failed to convert TIFF to canvas.");
                        }
                    } else {
                        console.error("Failed to create TIFF object.");
                    }
                } catch (e) {
                    console.error('Error creating TIFF object:', e);
                }
            })
            .catch(e => console.error('Error loading TIFF image:', e));
    } else {
        alert("Tiff Image is not Present");
    }
});
</script>

<script>
document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
        var tiffViewer = document.querySelector(".tiff-viewer");
        var tiff_text = document.querySelector(".tiff-viewer-alt");
        // Check if the div is empty
        if (tiffViewer && tiffViewer.innerHTML.trim() === '') {
            tiff_text.style.display = "block";
        } else {
            tiff_text.style.display = "none";
        }
    }, 1000);
});
</script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#accept").on("click", function() {
// 				confirm("Are You sure you want to accept?");
			});
		});
	</script>
	<script type="text/javascript">
		// Get the modal
		var modal = document.getElementById("rejection-reason-modal");

		// Get the button that opens the modal
		var btn = document.getElementById("reject");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("rejection-reason-close")[0];

		span.onclick = function() {
			modal.style.display = "none";
		}

		// Prevent modal from closing when clicking outside of it
		modal.onclick = function(event) {
			event.stopPropagation();
		}

		// Close the modal when clicking outside of it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	<script>
		$(document).ready(
				function() {
					// Add click event listener to accordion buttons
					$(".accordion").click(
							function() {
								// Toggle active class to highlight the button and rotate the icon
								$(this).toggleClass("active");
								$(this).find(".accordion-icon").toggleClass(
										"fa-angle-down fa-angle-down");

								// Toggle the panel visibility
								var panel = $(this).next();
								if (panel.css("display") === "block") {
									panel.css("display", "none");
								} else {
									panel.css("display", "block");
								}
							});
				});
				// Function to hide query parameters from URL after page load
				function hideQueryParams() {  
		//			alert("Hello");
					var urlWithoutParams = window.location.pathname;
				    window.history.replaceState({}, document.title, urlWithoutParams);
				}
				// Call the function to hide query parameters after page load
				window.onload = hideQueryParams;
	</script>
</body>
</html>