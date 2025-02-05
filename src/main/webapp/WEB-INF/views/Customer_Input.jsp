<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.example.demo.entity.MdtInitRequestH2h" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Mandate</title>
<link rel="icon" href= "${pageContext.request.contextPath}/images/aurora_logo.png"  type="image/x-icon" style="border-radius: 50%; overflow: hidden;"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script>
var today = new Date();
var date= today.toISOString().slice(0, 10);



window.onload=function()
{

	document.getElementById("Bdate").value=date;
	document.getElementById("Bdate").disabled = true;
	
	

}

function ShowHidechck1(untlcancel)
{  
	if(untlcancel.checked == true)
	{
		document.getElementById("finaldt").disabled = true;
		document.getElementById("finaldt").value = "";
	
	}
	else
	{
		document.getElementById("finaldt").disabled = false;
		document.getElementById("finaldt").value = "";
	}
	/* var dvPassport1 = document.getElementById("sp2");
	   	dvPassport1.style.display = untlcancel.checked ? "none" : "block"; */
      
}

function ValidateFileUpload() {

	var fuData = document.getElementById('file1');
	var FileUploadPath = fuData.value;

	if (FileUploadPath == '') {
		alert("Please upload an image");

	} else {
		
		

		var Extension = FileUploadPath.substring(
				FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

		if (Extension == "jpeg" || Extension == "jpg"
				|| Extension == "JPEG" || Extension == "JPG") {

			var reader = new FileReader();
			reader.readAsDataURL(fuData.files[0]);
			reader.onload = function(e) {
				var image = new Image();
				image.src = e.target.result;
				image.onload = function() {
					var height = this.height;
					console.log("height>>" + height);
					var width = this.width;
					console.log("width>>" + width);

					if (width > 4000 || height > 2000) {

						alert("image height and width should be less than allowed height and width");
						document.getElementById('file1').value = "";

						return false;
					}

				}
			};

			if (fuData.files && fuData.files[0]) {

				var size = fuData.files[0].size;

				if (size > 100000) {
					alert("Maximum file size exceeds");
					document.getElementById('file1').value = "";
					return false;

				} else {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('#blah').attr('src', e.target.result);

					}

					reader.readAsDataURL(fuData.files[0]);

				}
			}

		}

		else {
			alert("Photo only allows file types of JPG and JPEG. ");
			document.getElementById('file1').value = "";
			return false;
		}
	}
}

function ValidateFileUpload1() {

	var fuData = document.getElementById('file2');
	var FileUploadPath = fuData.value;

	if (FileUploadPath == '') {
		alert("Please upload an image");

	} else {
		var Extension = FileUploadPath.substring(
				FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

		if (Extension == "tiff" || Extension == "tif"
				|| Extension == "TIFF" || Extension == "TIF") {

			var reader = new FileReader();
			reader.readAsDataURL(fuData.files[0]);
			reader.onload = function(e) {
				var image = new Image();
				image.src = e.target.result;
				image.onload = function() {
					var height = this.height;
					console.log("height>>" + height);
					var width = this.width;
					console.log("width>>" + width);

					if (width > 4000 || height > 2000) {

						alert("image height and width should be less than allowed height and width");
						document.getElementById('file1').value = "";

						return false;
					}

				}
			};

			if (fuData.files && fuData.files[0]) {

				var size = fuData.files[0].size;

				if (size > 100000) {
					alert("Maximum file size exceeds");
					document.getElementById('file2').value = "";
					return false;

				} else {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('#blah').attr('src', e.target.result);

					}

					reader.readAsDataURL(fuData.files[0]);

				}
			}

		}

		else {
			alert("Photo only allows file types of TIF and TIFF. ");
			document.getElementById('file2').value = "";
			return false;
		}
	}

}

//Value for frequency and occurence
function addOption(selectbox,text,value )

{
	var optn = document.createElement("OPTION");

	optn.text = text;

	optn.value = value;

	selectbox.options.add(optn);

}   

var value_recurring = new Array("Daily","Weekly","Monthly","Quarterly","Semi-annualy","Yearly","Bimonthly");
		 
var key_recurring = new Array("DAIL","WEEK","MNTH","QURT","MIAN","YEAR","BIMN");
		 
var value_OOFF = new Array("Adhoc");
		 
var key_OOFF = new Array("ADHO"); 

function removeExistingOptions(){
	var select=document.getElementById('freq');
	var i;
	for ( i=select.length-1;i>0;i--) {
	     select.remove(i);
	}
}

 function dynamicdropdown(listindex)
{
	 
	 if(listindex == 'RCUR'){
		 removeExistingOptions();
		 for (var i=0; i < key_recurring.length;++i){
		 addOption(document.createMandate.freq, value_recurring[i], key_recurring[i]);
		 }
	 }else if(listindex == 'OOFF'){
		 removeExistingOptions();
		 for (var i=0; i < key_OOFF.length;++i){
		 addOption(document.createMandate.freq, value_OOFF[i], key_OOFF[i]);
		 }
	 }
}
 
//Change length based on Debtor IFSC/MICR/IIN
function changeMaxLength()
{
	var codetxt = document.getElementById('DbNo');
	var code =document.getElementById('Dbcode');
	if (code.selectedIndex == 0)
	{
		codetxt.maxLength=11;
	}
	else if (code.selectedIndex == 1)
	{
		codetxt.maxLength=11;
	}
	else if (code.selectedIndex == 2) 
	{
		codetxt.maxLength=9;
	}
	else if (code.selectedIndex == 3) 
	{
		codetxt.maxLength=6;
	}
	
}

function cleartextbox() 
{
	document.getElementById("DbNo").value = "";
}

//Code after Submitting the form
function Onsubmit()
{
	
	if (document.getElementById("Cdname").value == "") 
	{
		alert("Please Enter Creditor Name!");
		document.getElementById("Cdname").focus();
		return false;
	}
	
	if (document.getElementById("CrAccno").value == "") 
	{
		alert("Please Enter Creditor Acct No!");
		document.getElementById("CrAccno").focus();
		return false;
	}
	
	
	if (document.getElementById("Accounttype").value == "") 
	{
		alert("Please select Account Type");
		return false;
	}
	
	if (document.getElementById("Mdate").value == "") 
	{
		alert("Please enter Date of Mandate!");
		document.getElementById("firstdt").focus();
		return false;
	}
	
	if (document.getElementById("Trantype").value == "") 
	{
		alert("Please select Transaction Type");
		return false;
	}
	
	
	if (document.getElementById("Occur").value == "") 
	{
		alert("Please select Occurrence Type");
		return false;
	}
	
	if (document.getElementById("freq").value == "") 
	{
		alert("Please select Frequency");
		return false;
	}
	
	if (document.getElementById("firstdt").value == "") {
		alert("Please enter First Collection Date!");
		document.getElementById("firstdt").focus();
		return false;
	}
	
	
	
	var StartDate = document.getElementById('firstdt').value;
	//FIRST COLLECTION DATE
	var sDate = new Date(StartDate);
	console.log("First Collection Date >> "+sDate);
	
	var EndDate = document.getElementById('finaldt').value;
	//FINAL COLLECTION DATE
	var eDate = new Date(EndDate);
	console.log("Final Collection Date >> "+eDate);
	
	var Mdate = document.getElementById('Mdate').value;
	//DATE OF MANDATE
	var MDate = new Date(Mdate);
	console.log("MDate:"+MDate);
	
	var Bdate = document.getElementById('Bdate').value;
	var BDate = new Date(Bdate);
	console.log("BDate:"+BDate);
	
	var today = new Date();
	console.log("today"+today)
	if(MDate>today)
	{
		alert("Mandate Future date is not allowed (Date of Mandate)");
		document.getElementById('Mdate').value = "";
		return false;
		
	}
	
	//First Collection Date should always be greater than the Business Date
	var start = sDate;
	var date_today = new Date();
	start.setHours(0,0,0,0);
	date_today.setHours(0,0,0,0);
	
    var s1 = start.getTime();
	var s2 = date_today.getTime()
	
	console.log("Start Date >>>"+start);
	console.log("Today's Date >>> "+date_today); 
	
	//comparing both the dates
	if(start < date_today){
		alert("First Collection Date should always be greater than the Business Date");
		document.getElementById('firstdt').value = "";
		return false;
	}
	
	 if(sDate<=MDate)
	 {
			alert("First Collection date should always be greater than Mandate date(Date of Mandate)");
			document.getElementById('firstdt').value = "";
			return false;
	 }
	 
	 console.log("BDate" + BDate);
	 var comp = (BDate - MDate);
	 var TotalDays = Math.ceil(comp / (1000 * 3600 * 24));
	 console.log("TotalDays" + TotalDays);
	 
	//Diff between mandate and business date should not be greater than 120
		if (TotalDays > 120) 
		{
			alert("Range Between Mandate Date and Current Bussiness Date exceeds(current Bussiness date and Mandate date)");
			document.getElementById('Mdate').value = "";
			return false;
		}
	
		if (StartDate != '' && EndDate != '') {
			if (eDate < sDate || eDate < today || eDate == today) {
				alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date");
				document.getElementById('finaldt').value = "";
				return false;
			}
		}
		
		if (document.getElementById("Dbcollamttype").value == "") {
			alert("Please select Amount Collected from Debtor A/c");
			return false;
		}
		
		var input = document.getElementById("Dbamtcoll").value;
		if (input == 0) 
		{
			alert("Please enter proper amount!!");
			document.getElementById('Dbamtcoll').value = "";
			document.getElementById("Dbamtcoll").focus();
			return false;
		} 
		else if (document.getElementById("Dbamtcoll").value == "") {
			alert("Please Enter Amount.");
			document.getElementById("Dbamtcoll").focus();
			return false;
		}
		
		if (document.getElementById("Dbname").value == "") {
			alert("Please Enter Debtor  Name!");
			document.getElementById("Dbname").focus();
			return false;
		}
		
		
		 
		 if (document.getElementById("DbAccno").value == "") 
		 {
				alert("Please Enter Debtor Acct No!");
				document.getElementById("DbAccno").focus();
				return false;
		}
		 
		 if (document.getElementById("DbAcc").value == "") 
		 {
				alert("Please select Debtor Account Type:");
				document.getElementById("DbAcc").focus();
				return false;
		 }
		 
		 if(document.getElementById("Dbcode").value == "")
		 {
			 alert("Please select Debtor IFSC/MICR/IIN");
			 return false;
	     }
		 
		 
		 
		 if(document.getElementById("DbNo").value == "")
		 {
			 alert("Debtor IFSC/MICR/IIN Code cannot be blank");
			 return false;
	     }
		 
		 
		//Debtor IFSC/IIN/MICR VALUE CHECK
		
		var code = document.getElementById("Dbcode").value;
		var star = document.getElementById("DbNo").value;
		
		
		if (code == "IFSC") {

			var reg = /[A-Z|a-z]{4}[0][a-zA-Z0-9]{6}$/;
			
			if (star == " " || star == null) 
			{
				alert("Please select Debtor IFSC/MICR/IIN Code:");
				return false;
			}

			if (star.match(reg))
			{
				if (star.match(reg) && (star.length < 11)) {
					alert(" You Entered Wrong IFSC Code \n\n Please enter 11 digit Debtor IFSC Code");
					return false;
				}

			} 
			
			else {
				alert(" You Entered Wrong IFSC Code \n\n Please enter 11 digit Debtor IFSC Code");
				return false;
			}

		}
		else if (code == "MICR") 
		{

			var numbers = /^[0-9]+$/;

			if (star == " " || star == null) {
				alert("Please select Debtor IFSC/MICR/IIN Code:");
				return false;

			}
			if (star.match(numbers)) {

				if (star.match(numbers) && (star.length < 9)) {

					alert("Please Enter 9 Digit MICR");

					return false;
				}

			} else {
				alert("Please Enter 9 Digit Only");
				return false;
			}

		}
		
		else if (code == "IIN") 
		{

			var numbers = /^[0-9]+$/;
			if (star == " " || star == null)
			{
				alert("Please select Debtor IFSC/MICR/IIN Code:");
				return false;

			}
			if (star.match(numbers)) {

				if (star.match(numbers) && (star.length < 6)) {

					alert("Please Enter 6 Digit IIN");

					return false;
				}

			} else {
				alert("Please Enter 6 Digit Only");
				return false;
			}

		}
		else 
		{
			alert("Please select Debtor IFSC/MICR/IIN Code:");
			return false;
		}
		
		 
		//validations
		
		if (document.getElementById("debtor_bank_name").value == "") 
		{
			alert("Please Enter Debtor Bank Name!");
			document.getElementById("debtor_bank_name").focus();
			return false;
		}
		
		//Make mobile number mandatory
		if (document.getElementById("debmob").value == "") 
		{
				alert("Please enter mobile number!!");
				document.getElementById("debmob").focus();
				return false;
		}
		
		var filename = document.getElementById("file1").value;
		if (filename == "") {
			alert("Please select an Image to upload:");
			return false;
		}

		var filename12 = document.getElementById("file2").value;
		if (filename12 == "") {
			alert("Please select an Image to upload:");
			return false;
		}

		var strUser71 = document.getElementById("catcode").value;
		if (strUser71 == "") {
			alert("Please select Category Code:");
			return false;
		}

		var d = document.getElementById("firstdt").value;
		var d1 = new Date(d);
		var weekday = new Array(7);
		weekday[0] = "Sunday";
		weekday[1] = "Monday";
		weekday[2] = "Tuesday";
		weekday[3] = "Wednesday";
		weekday[4] = "Thursday";
		weekday[5] = "Friday";
		weekday[6] = "Saturday";

		var n = weekday[d1.getDay()];
		
		//SMS CHANGES
		var sms_flag = `${user.sms_flag}`;
		console.log("SMS FLAG -->"+sms_flag);
		
		
	
		if(sms_flag === 'Y')
		{
			
			console.log("SMS FLAG IS Y");
			//Get year difference between the two dates 
			 var yeardiff =  eDate.getFullYear() - sDate.getFullYear();
			 console.log("Year Differenece is :"+yeardiff);
			 
			//month part
			 var m1 = (eDate.getMonth()+1);
			 console.log("Month part of the end date :"+m1);
			 var m2 = (sDate.getMonth()+1);
			 console.log("Month part of start date :"+m2);
			 
			//date part
			 var d1 = eDate.getDate();
			 console.log("Date part of the end date :"+ d1);
			 var d2 = sDate.getDate();
			 console.log("Date part of the start date :"+ d2);
			 
			 if (StartDate != '' && EndDate != '') 
			 {
					if (eDate < sDate || eDate < today || eDate == today) {
						alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date");
						document.getElementById('finaldt').value = "";
						return false;
					}
			}
				
			if(StartDate!= '' && EndDate!= '' && (today>eDate || sDate>eDate))
			{
			 		alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date.Today"+today+"FirstDate"+sDate+"LastDate"+eDate);
			 		document.getElementById('finaldt').value = "";
			 		return false;
			}
			 
			//IF THE DIFF BETWEEN BOTH THE DATES IS EQUAL TO 30
			 if(yeardiff == 40)
			 {
				 
				 //IF MONTH PART AND DATE PART ARE SAME
				 if(m1 == m2 )
				 {
					 //IF BOTH THE DATES ARE NOT SAME
					 if(d1 == d2)
					 {
					       console.log("Difference between both is exactly 40 years");
					 }
					 //IF THE DATE PART OF THE END DATE IS GREATER THAN THE DATE PART OF START DATE
					 else if(d1 > d2)
					 {
						 
							 alert("Difference between first and final collection date should not be more than 40 years");
							 document.getElementById('finaldt').value = "";
		   				 	 return false;
					 }	
				 }
				 //IF MONTH PART OF THE END DATE IS GREATER THAN THE MONTH PART OF START DATE
				 else if(m1 > m2)
				 {
						 alert ("Difference between first and final collection date should not be more than 40 years");
						 document.getElementById('finaldt').value = "";
						 return false;
				}
					 
				 
				 
			 }
			 //IF THE DIFFERENCE BETWEEN BOTH THE DATES IS 30 YEARS
			 else if(yeardiff > 40)
			 {
					 alert ("Difference between first and final collection date should not be more than 40 years");
					 document.getElementById('finaldt').value = "";
					 return false;
			 }
			
			 if(document.getElementById("finaldt").value == "")
			 {
		    	    alert("Please Enter Final Collection Date!");
		      		document.getElementById("finaldt").focus();
		      		return false;
		     } 
			 
			 var amttype = document.getElementById("Dbcollamttype");
		     var index = amttype.selectedIndex;
		     
		     var input = document.getElementById("Dbamtcoll").value;
	  		 var amt = parseFloat(input);
	  		 console.log("Amount is :"+amt);
	  		 
	  		if(amt > 10000000)
	  		{
	 			alert("Maximum amount limit is 1 crores");
	 			document.getElementById("Dbamtcoll").value = '';
	 			return false;
	 		}
		}
		else if(sms_flag === 'N')
		{
			console.log("SMS FLAG IS N");
			
			if (!this.createMandate.until_cancel.checked) 
    		{
    			if (document.getElementById("finaldt").value == "") {
    				alert("Please Enter Final Collection Date!");
    				document.getElementById("finaldt").focus();
    				return false;
    			} 
    		

    			if(StartDate!= '' && EndDate!= '' && (today>eDate || sDate>eDate))
    			{
    			 		alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date.Today"+today+"FirstDate"+sDate+"LastDate"+eDate);
    			 		document.getElementById('finaldt').value = "";
    			 		return false;
    			} 

    			if (StartDate != '' && EndDate != '') {
    				if (eDate < sDate || eDate < today || eDate == today) {
    					alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date");
    					document.getElementById('finaldt').value = "";
    					return false;
    				}
    			}
    		
    		}
    	    else 
    		{
    			document.getElementById("finaldt").value = "";
    			
    		} 
		}
		
		//SMS CHANGES
  		var retVal = confirm("Do you want to continue ?");
		if (retVal == true) 
		{

			document.getElementById("createMandate").action = `<c:url value = '/Y3JlYXRl'/>`;
			document.getElementById("createMandate").submit();
			
			return true;
		}
		else
		{
			return false;
		}
		 
		

}



</script>
</head>

<body>
	<%@include file = "Navigationbar1.jsp" %>
	
<!-- other content -->
	<div id="content">
		<%@include file="hamburger.jsp"%>
		<h3 align = "center" class="page-name">Create Mandate</h3>
		<div class="container">
			<form method="post" id="createMandate" enctype="multipart/form-data" name="createMandate" autocomplete="off">
				<%-- <input type="hidden" name="token" value="${token}"/> --%>
				<div class="form first">
					 <div class="details personal">
                   		 <div class="fields">
                   		<!-- first -->
                   		 	<div class="input-field">
                           		 <label for="Cdname">Creditor Name <span class="required-asterick">*</span></label>
                           		 <input type="text"  required  id="Cdname" name="creditor_name" maxlength="40" 
       				 			   value = "${customer.creditor_name}" placeholder="Creditor Name"
       							   onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 " 
       				 			   onkeypress='return isNumberKey1(event)'/>
                        	</div>
                        <!-- second -->
                        	<div class="input-field">
                            	<label for = "CrAccno">Creditor Account No <span class="required-asterick">*</span></label>
                            	<input  required  type="text" id="CrAccno" name="creditor_account_no" 
      							 maxlength="34" size="25" value = "${customer.creditor_account_no}" placeholder="Creditor Account No"
      							 onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " 
      							 />
                        	</div>
                        <!-- third -->
                        	<div class="input-field">
                            	<label for="Accounttype">Creditor Account Type <span class="required-asterick">*</span></label>
                            	<select  required="required" id="Accounttype" name="creditor_account_no_type">
     				 				<option selected value="">--Select--</option>
     							    <option value="SAVINGS">Credit</option>
      				 				<option value="LOAN">Loan</option>
      							</select>
                        	</div>
                         <!-- fourth-->	
                        	<div class="input-field">
                            	<label for = "Bdate">Business Date(Current Date) <span class="required-asterick">*</span></label>
                            	<input type="date" class="form-control" id="Bdate" name="creation_date" maxlength="0" size="0" 
                            	 readonly="readonly" value="${customer.creation_date}"	/>
                       		 </div>
                       		 
                       		 <div class="input-field">
                            	<label>Date of Mandate <span class="required-asterick">*</span></label>
                            	<input type="date"  id="Mdate" name="date_of_mandate" maxlength="0" size="0" required
								 value="${customer.date_of_mandate}" placeholder="Enter Date of Mandate" readonly
								 class = "form_datetime datepicker"
								 onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Transaction Type <span class="required-asterick">*</span></label>
                            	<select  id="Trantype" name="transaction_type" required>
     								 <option value="DEBIT">Debit</option>
      							</select>
                       		 </div>
                       		 
                       		 <div class="input-field">
                           		 <label>Occurrence <span class="required-asterick">*</span></label>
                            	<select  id="Occur" required  name="occurence" 
      								onchange="javascript:dynamicdropdown(this.options[this.selectedIndex].value);">
      								<option selected="selected" value="">--Select--</option>
      								<option value="RCUR">Recurring</option>
      								<option value="OOFF">One-off</option>
      							</select>
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Frequency <span class="required-asterick">*</span></label>
                            	<select  required id="freq" name="frequency">
      									<option selected value="" >--Select--</option>
      									<option value="RCUR">Recurring</option>
     		    				</select>
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>First Collection Date <span class="required-asterick">*</span></label>
                            	<input type="date"  maxlength="0" size="0" 
                            	value="${customer.first_collection_date}" id="firstdt" name="first_collection_date" required 
								onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Final Collection Date <span class="required-asterick">*</span></label>
                            	<input type="date" maxlength="0" size="0" 
                            	value="${customer.final_collection_date}" id="finaldt" required name="final_collection_date" 
								onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Amt collected from Debtor Account <span class="required-asterick">*</span></label>
                            	<select id="Dbcollamttype" name="amount_type" 
									required onchange="javascript: cleartextbox();">
      								<option selected="selected" value="">--Select--</option>
      								<option value="FIXED">Fixed Amount</option>
     					 			<option value="MAXIMUM">Max Amount</option>
      							</select>
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Amount <span class="required-asterick">*</span></label>
                            	<input onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.keyCode == 46' class="form-control" 
      							type="text" id="Dbamtcoll" name="amount" maxlength="10" size="20" min="1"  placeholder="Amount"
      							required value="${customer.amount}"/>
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Debtor Name <span class="required-asterick">*</span></label>
                            	<input type="text" id="Dbname" name="debtor_name" maxlength="40" size="25" 
								onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 " 
								value="${customer.debtor_name}" placeholder = "Debtor Name " required />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Debtor Account Number <span class="required-asterick">*</span></label>
                            	<input onkeypress='return event.charCode >= 48 && event.charCode <= 57' type="text" 
      							id="DbAccno" name="debtor_account_no" maxlength="34" size="25" min="1"
								value="${customer.debtor_account_no }"  placeholder = "Debtor Account Number" required />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Debtor Account Type <span class="required-asterick">*</span></label>
                            	<select  id="DbAcc" name="debtor_account_type">
     		 						<option selected="selected" value="">--Select--</option>
      		 						<option value="SAVINGS">SAVINGS</option>
     		 						<option value="CURRENT">CURRENT</option>
     		 						<option value="CC">CC</option>
    	     						<option value="OTHER">OTHER</option>
      							</select>
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Debtor IFSC/MICR/IIN <span class="required-asterick">*</span></label>
                            	<select  id="Dbcode" name="debtor_identification" onchange="javascript: cleartextbox(); changeMaxLength();">
      								<option selected="selected" value="">--Select--</option>
     		    					<option value="IFSC">IFSC</option>
     		    					<option value="MICR">MICR</option>
     		   					    <option value="IIN">IIN</option>
     							 </select>
                        	</div>
                        	
                        	 <div class="input-field">
                            	<label>Debtor IFSC/MICR/IIN Code <span class="required-asterick">*</span></label>
                            	<input type="text"  required value="${customer.debtor_identification_no}" 
      							id="DbNo" name="debtor_identification_no" size="11" 
	   							onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||(event.charCode >=65  && event.charCode <= 90)||(event.charCode >=97  
	   							&& event.charCode <= 122)"  placeholder="Debtor IFSC/MICR/IIN Code"/>	
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Debtor Bank Name <span class="required-asterick">*</span></label>
                           		<input type="text" onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 "
      		 					placeholder="Debtor Bank Name" name="debtor_bank_name" id = "debtor_bank_name" value="${customer.debtor_bank_name}" required/>
                       		</div>
                       		 
                        	<div class="input-field">
                            	<label>Debtor Mobile Number <span class="required-asterick">*</span></label>
                            	<input type="text" maxlength="10" id="debmob" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
									value="${customer.deb_mobno}" required placeholder = "Debtor Mobile Number" name="deb_mobno" />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Debtor Email</label>
                            	<input type="email" placeholder = "Enter Debtor Email" value="${customer.deb_emailid}" maxlength="100" id="debemail" 
     	 						name="deb_emailid"/>
                        	</div>
                        	
                        	<div class="input-field">
                           		<label>Upload Mandate Image(JPEG/JPG) <span class="required-asterick">*</span></label>
                            	<input type="file" name="jpg_image" id="file1" required onchange="javascript:ValidateFileUpload();" />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Upload Mandate Image(TIFF/TIF) <span class="required-asterick">*</span></label>
                            	<input type="file" name="tiff_image" id="file2" required onchange="javascript:ValidateFileUpload1();" />
                        	</div>
                        	
                        	<div class="input-field">
                            	<label>Category Code <span class="required-asterick">*</span></label>
                            	<select required="required" id="catcode" name="category_code">

										<option selected="selected" value="">--Please Select--</option>
										<c:forEach items="${cat_code}" var="catcode">
											<option value="${catcode.code}"
											${catcode.code == selected_catcode ? 'selected="selected"' : ''}>${catcode.name}</option>
										</c:forEach>
								</select>
                        	</div>
                        	
                        	<div class = "input-field">
                        		<div id = "input_field" class="input-field-checkbox"> 
                        	
                        		<c:if test="${user.sms_flag eq 'N' }">
                        			<input type="checkbox" name="until_cancel" class="Fnlc"
									id="until_cancel" onclick = "ShowHidechck1(this);"><label style = "font-size: 14px;vertical-align:super;">&nbsp;&nbsp;Until Cancel</label>
                        		</c:if>
                        	
                        		</div>
                        	
                        	</div>
							<div class="input-field"></div>
                   		 
                   		 </div>
                   		 <div class="details ID">
                    		<button type="submit" class="nextBtn" id="Update" name="Update" value="submit"  onclick = " return Onsubmit();">Submit</button>
                		 </div> 
                   	  </div>
				</div>
			</form>
		</div>
		
		
	</div>
	<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
	<script> 
	 
    $(document).ready(function ($) 
    { 
    	$(".datepicker").datepicker({  
	        autoclose: true,  
	        todayHighlight: true,
	        format: 'dd-mm-yyyy',
	        clearBtn: true // Optional: Adds a clear button to remove the date
	    });
	 }); 
	</script> 
	<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
</body>
</html>
