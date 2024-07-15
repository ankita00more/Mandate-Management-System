<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.example.demo.entity.MdtInitRequestH2h" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Amend Mandate</title>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
   
<script>

window.onload=function(){
	
	let stringValue = `${customer.occurence}`;
		//alert("Inside onload function");
		//alert("Inside RCUR function");
		document.getElementById("Occur").value= stringValue ;
		dynamicdropdown(stringValue);
		document.getElementById("freq").value = `${customer.frequency}`;
		document.getElementById("Accounttype").value = `${customer.creditor_account_no_type}`;
		document.getElementById("Dbcollamttype").value = `${customer.amount_type}`;
		document.getElementById("DbAcc").value = `${customer.debtor_account_type}`;
		document.getElementById("Dbcode").value = `${customer.debtor_identification}`;
		document.getElementById("Trantype").value = `${customer.transaction_type}`
		document.getElementById("Accounttype").disabled = true;
		document.getElementById("Trantype").disabled = true;
		document.getElementById("DbAcc").disabled = true;
		document.getElementById("Dbcode").disabled = true;
		document.getElementById("catcode").disabled = true;
		
		hideQueryParams();
}

//Image Brightness Validation for jpg image
document.addEventListener("DOMContentLoaded", function() {
  const imageInput = document.getElementById("jpg_image");

  const previewImage = document.getElementById("previewImage");
  console.log("previewImage"+previewImage);

  imageInput.addEventListener("change", function() {
    const file = imageInput.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function(event) {
        previewImage.src = event.target.result;
        validateImageBrightness(event.target.result);
      };
      reader.readAsDataURL(file);
    }
  });

  function validateImageBrightness(imageDataURL) {
    const img = new Image();
    img.src = imageDataURL;

    img.onload = function() {
      const canvas = document.createElement("canvas");
      const context = canvas.getContext("2d");
      canvas.width = img.width;
      canvas.height = img.height;
      context.drawImage(img, 0, 0, img.width, img.height);

      let brightnessSum = 0;

      const imageData = context.getImageData(0, 0, canvas.width, canvas.height).data;
      for (let i = 0; i < imageData.length; i += 4) {
        const r = imageData[i];
        const g = imageData[i + 1];
        const b = imageData[i + 2];
        // Calculate brightness using a common formula (adjust as needed)
        const brightness = (0.299 * r + 0.587 * g + 0.114 * b);
        brightnessSum += brightness;
      }

      const averageBrightness = brightnessSum / (canvas.width * canvas.height);
      console.log("averageBrightness"+averageBrightness);
      // Define a brightness threshold (adjust as needed)
      const brightnessThreshold = 10;
      const highBrightness = 255;

      if (averageBrightness < brightnessThreshold) {
        alert("Uploaded Image is too Dark. Uploaded Image Brightness is: "+averageBrightness+" pixel, Image Brightness should be Greater than:"+brightnessThreshold+" pixel");
        // Optionally, you can reset the input and preview to allow re-upload
        imageInput.value = null;
        previewImage.src = "";
      }
      if (averageBrightness > highBrightness) {
          alert("Uploaded Image is too Bright. Uploaded Image Brightness is: "+averageBrightness+ "pixel,Image Brightness should be Less than:"+highBrightness+" pixel");

    	  // Optionally, you can reset the input and preview to allow re-upload
          imageInput.value = null;
          previewImage.src = "";
        }
        
      
    };
  }
});
//CR Change

//Image brightness validation for tiff image

document.addEventListener("DOMContentLoaded", function() {
  const imageInput = document.getElementById("tiff_image");

  const previewImage = document.getElementById("previewImage");
  console.log("previewImage"+previewImage);

  imageInput.addEventListener("change", function() {
    const file = imageInput.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function(event) {
        previewImage.src = event.target.result;
        validateImageBrightness(event.target.result);
      };
      reader.readAsDataURL(file);
    }
  });

  function validateImageBrightness(imageDataURL) {
    const img = new Image();
    img.src = imageDataURL;

    img.onload = function() {
      const canvas = document.createElement("canvas");
      const context = canvas.getContext("2d");
      canvas.width = img.width;
      canvas.height = img.height;
      context.drawImage(img, 0, 0, img.width, img.height);

      let brightnessSum = 0;

      const imageData = context.getImageData(0, 0, canvas.width, canvas.height).data;
      for (let i = 0; i < imageData.length; i += 4) {
        const r = imageData[i];
        const g = imageData[i + 1];
        const b = imageData[i + 2];
        // Calculate brightness using a common formula (adjust as needed)
        const brightness = (0.299 * r + 0.587 * g + 0.114 * b);
        brightnessSum += brightness;
      }

      const averageBrightness = brightnessSum / (canvas.width * canvas.height);
      console.log("averageBrightness"+averageBrightness);
      // Define a brightness threshold (adjust as needed)
      const brightnessThreshold = 10;
      const highBrightness = 255;

      if (averageBrightness < brightnessThreshold) {
        alert("Uploaded Image is too Dark. Uploaded Image Brightness is: "+averageBrightness+" pixel, Image Brightness should be Greater than:"+brightnessThreshold+" pixel");
        // Optionally, you can reset the input and preview to allow re-upload
        imageInput.value = null;
        previewImage.src = "";
      }
      if (averageBrightness > highBrightness) {
          alert("Uploaded Image is too Bright. Uploaded Image Brightness is: "+averageBrightness+ "pixel,Image Brightness should be Less than:"+highBrightness+" pixel");

    	  // Optionally, you can reset the input and preview to allow re-upload
          imageInput.value = null;
          previewImage.src = "";
        }
        
      
    };
  }
});

//CR Change



function disable()
{
	//Changes --> 17/01/2023
    document.getElementById("DbAcc").disabled = false;
    document.getElementById("Dbcode").disabled = false;
    document.getElementById("debtor_bank_name").disabled = false;
    document.getElementById("DbNo").disabled= false;
	document.getElementById("catcode").disabled=false;
	document.getElementById("Trantype").disabled =false;
	document.getElementById("Accounttype").disabled=false;
	document.getElementById("Dbname").disabled  = false; 
    //Changes --> 17/01/2023
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

	var fuData = document.getElementById('jpg_image');
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
						document.getElementById('jpg_image').value = "";

						return false;
					}

				}
			};

			if (fuData.files && fuData.files[0]) {

				var size = fuData.files[0].size;

				if (size > 100000) {
					alert("Maximum file size exceeds");
					document.getElementById('jpg_image').value = "";
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
			document.getElementById('jpg_image').value = "";
			return false;
		}
	}
}

function ValidateFileUpload1() {

	var fuData = document.getElementById('tiff_image');
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
						document.getElementById('tiff_image').value = "";

						return false;
					}

				}
			};

			if (fuData.files && fuData.files[0]) {

				var size = fuData.files[0].size;

				if (size > 100000) {
					alert("Maximum file size exceeds");
					document.getElementById('tiff_image').value = "";
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
			document.getElementById('tiff_image').value = "";
			return false;
		}
	}

}


function addOption(selectbox,text,value )

{
	//alert("Inside add option");
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
 console.log(listindex);
 if(listindex == 'RCUR'){
	 removeExistingOptions();
	 for (var i=0; i < key_recurring.length;++i){
	 	addOption(document.amendmandate.freq, value_recurring[i], key_recurring[i]);
	 }
 }else if(listindex == 'OOFF'){
	 removeExistingOptions();
	 for (var i=0; i < key_OOFF.length;++i){
	 	addOption(document.amendmandate.freq, value_OOFF[i], key_OOFF[i]);
	 }
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
		alert("Please select date of mandate");
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
		if (input == 0) {
			alert("Amount should be greater than 0");
			document.getElementById('Dbamtcoll').value = "";
			document.getElementById("Dbamtcoll").focus();
			return false;
		} 
		else if (document.getElementById("Dbamtcoll").value == "")
		{
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
		 
		//Make mobile number mandatory
		if (document.getElementById("debmob").value == "") 
		{
				alert("Please enter mobile number!!");
				document.getElementById("debmob").focus();
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
		else {
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
		
		var filename = document.getElementById("tiff_image").value;
		if (filename == "") {
			alert("Please select an Image to upload:");
			return false;
		}

		var filename12 = document.getElementById("jpg_image").value;
		if (filename12 == "") {
			alert("Please select an Image to upload:");
			return false;
		}

		var strUser71 = document.getElementById("catcode").value;
		if (strUser71 == "") {
			alert("Please select Category Code:");
			return false;
		}
		
		var rsn_code = document.getElementById("Reason").value;
		if(rsn_code == "")
		{
			alert("Please select an appropriate ReasonCode ");
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
			 
			//IF THE DIFF BETWEEN BOTH THE DATES IS EQUAL TO 30
			 if(yeardiff == 30)
			 {
				 
				 //IF MONTH PART AND DATE PART ARE SAME
				 if(m1 == m2 )
				 {
					 //IF BOTH THE DATES ARE NOT SAME
					 if(d1 == d2)
					 {
					       console.log("Difference between both is exactly 30 years");
					 }
					 //IF THE DATE PART OF THE END DATE IS GREATER THAN THE DATE PART OF START DATE
					 else if(d1 > d2)
					 {
						 
							 alert("Difference between first and final collection date should not be more than 30 years");
							 document.getElementById('finaldt').value = "";
		   				 	 return false;
					 }	
				 }
				 //IF MONTH PART OF THE END DATE IS GREATER THAN THE MONTH PART OF START DATE
				 else if(m1 > m2)
				 {
						 alert ("Difference between first and final collection date should not be more than 30 years");
						 document.getElementById('finaldt').value = "";
						 return false;
				}
					 
				 
				 
			 }
			 //IF THE DIFFERENCE BETWEEN BOTH THE DATES IS 30 YEARS
			 else if(yeardiff > 30)
			 {
					 alert ("Difference between first and final collection date should not be more than 30 years");
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
		else
		{
			console.log("SMS FLAG IS N");
			
			if (!this.amendmandate.until_cancel.checked) 
    		  {
    			if (document.getElementById("finaldt").value == "") {
    				alert("Please Re Enter Final Collection Date!");
    				document.getElementById("finaldt").focus();
    				return false;
    			} 
    		

    			 	if(StartDate!= '' && EndDate!= '' && (today>eDate || sDate>eDate)){
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
		if(retVal == true)
		{
			
			document.getElementById("DbAcc").disabled = false;
	   	 	document.getElementById("Dbcode").disabled = false;
	    	document.getElementById("debtor_bank_name").disabled = false;
	    	document.getElementById("DbNo").disabled= false;
			document.getElementById("catcode").disabled=false;
			document.getElementById("Trantype").disabled =false;
			document.getElementById("Accounttype").disabled=false;
			document.getElementById("Dbname").disabled  = false; 
		
			document.getElementById("amendmandate").action = `<c:url value = '/ZmluZGFtZW5k'/>`;
			document.getElementById("amendmandate").submit();
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
<%@include file="Navigationbar1.jsp"%>
	
	<div id="content">
		<%@include file="hamburger.jsp"%>
		<h3 align = "center" class="page-name">Amend Mandate</h3>
		<div class="container">
			<form  method="post" id="amendmandate" enctype="multipart/form-data" name="amendmandate" autocomplete="off">
 				<div class="form first">
		 			<div class="details personal">
             			 <div class="fields">
             			 <input type="hidden" name="differentaingParam" id="differentaingParam" value="yes"> 
 						 <input type="hidden" name="SelectedMsgId" id="SelectedMsgId" value = "${customer.uniqueId}">
 						 <input type="hidden" name="Umrnval" id="Umrnval" value = "${customer.umrn}">
 						 <input type="hidden" name = "divsearch" id = "divsearch" value = "">
						 <input type ="hidden" name = "searchType" id = "searchType" value = "">
              			 <!-- first -->
                   		 	<div class="input-field">
                           		 <label for="Cdname">Creditor Name <span class="required-asterick">*</span></label>
                           		 <input type="text"  required  id="Cdname" name="creditor_name" maxlength="40" 
       				 			   value = "${customer.creditor_name}" placeholder="Creditor Name"
       							   onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 " 
       				 			   />
                        	</div>
                        	
                     	 <!-- second -->
                        	<div class="input-field">
                            	<label for = "CrAccno">Creditor Account No <span class="required-asterick">*</span></label>
                            	<input  required  type="text" id="CrAccno" name="creditor_account_no" 
      							 maxlength="34" size="25" value = "${customer.creditor_account_no}" placeholder="Creditor Account No"
      							 onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                        	</div>
                        	
                       <!-- third -->
                        	<div class="input-field">
                            	<label for="Accounttype">Creditor Account Type <span class="required-asterick">*</span></label>
                            	<select  required="required" id="Accounttype" style = " background-color:rgb(237, 239, 241);"  name="creditor_account_no_type">
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
                       		 
                       <!-- fifth-->		 
                       		<div class="input-field">
                            	<label>Date of Mandate <span class="required-asterick">*</span></label>
                            	<input type="date"  id="Mdate" name="date_of_mandate" maxlength="0" size="0" required
								 value="${customer.date_of_mandate}" placeholder="Enter Date of Mandate"
								 onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                        	</div>
                        	
                       <!-- sixth-->
                      		 <div class="input-field">
                            	<label>Transaction Type <span class="required-asterick">*</span></label>
                            	<select  id="Trantype" name="transaction_type" style = " background-color:rgb(237, 239, 241);" required>
     								 <option value="DEBIT">Debit</option>
      							</select>
                      		 </div>
                       
                        <!--seventh-->
                       		<div class="input-field">
                           		 <label>Occurrence Type<span class="required-asterick">*</span></label>
                            	<select  id="Occur" required  name="occurence" 
      								onchange="javascript:dynamicdropdown(this.options[this.selectedIndex].value);">
      								<option selected="selected" value="">--Select--</option>
      								<option value="RCUR">Recurring</option>
      								<option value="OOFF">One-off</option>
      							</select>
                      		 </div>
                       
                       <!--eighth-->
                       		<div class="input-field">
                            	<label>Frequency <span class="required-asterick">*</span></label>
                            	<select  required id="freq" name="frequency">
      									<option selected value="" >--Select--</option>
      									<option value="RCUR">Recurring</option>
     		    				</select>
                      		 </div>
                       
                      		 <div class="input-field">
                            	<label>First Collection Date <span class="required-asterick">*</span></label>
                            	<input type="date"  maxlength="0" size="0" onclick="disable()" 
                            	value="${customer.first_collection_date}" id="firstdt" name="first_collection_date" required 
								onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                       		</div>
                       
                       		<div class="input-field">
                            	<label>Final Collection Date <span class="required-asterick">*</span></label>
                            	<input type="date" maxlength="0" size="0" onclick="disable()" 
                            	value="${customer.final_collection_date}" id="finaldt" required name="final_collection_date" 
								onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " />
                        	</div>
                        
                        	<div class="input-field">
                            	<label>Amt collected from Debtor A/c <span class="required-asterick">*</span></label>
                            	<select id="Dbcollamttype" name="amount_type" 
									required onchange="javascript: cleartextbox();amtlimit();">
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
                            	<input type="text" id="Dbname" name="debtor_name" maxlength="40" size="25" disabled style = " background-color:rgb(237, 239, 241);"
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
                            	<select  id="DbAcc" style = " background-color:rgb(237, 239, 241);" name="debtor_account_type">
     		 						<option selected="selected" value="">--Select--</option>
      		 						<option value="SAVINGS">SAVINGS</option>
     		 						<option value="CURRENT">CURRENT</option>
     		 						<option value="CC">CC</option>
    	     						<option value="OTHER">OTHER</option>
      							</select>
                        	</div>
                        
                        	<div class="input-field">
                            	<label>Debtor IFSC/MICR/IIN <span class="required-asterick">*</span></label>
                            	<select  id="Dbcode" name="debtor_identification" style = "background-color:rgb(237, 239, 241);" 
                            	 onchange="javascript: cleartextbox(); changeMaxLength();">
      								<option selected="selected" value="">--Select--</option>
     		    					<option value="IFSC">IFSC</option>
     		    					<option value="MICR">MICR</option>
     		   					    <option value="IIN">IIN</option>
     							 </select>
                        	</div>
                        
                        	<div class="input-field">
                            	<label>Debtor IFSC/MICR/IIN Code <span class="required-asterick">*</span></label>
                            	<input type="text"  required value="${customer.debtor_identification_no}" style = " background-color:rgb(237, 239, 241);"
      							id="DbNo" name="debtor_identification_no" size="11" disabled
	   							onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||(event.charCode >=65  && event.charCode <= 90)||(event.charCode >=97  
	   							&& event.charCode <= 122)"  placeholder="Debtor IFSC/MICR/IIN Code"/>	
                        	</div>
                        
                        	<div class="input-field">
                            	<label>Debtor Bank Name <span class="required-asterick">*</span></label>
                           		<input type="text" onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 "
      		 					placeholder="Debtor Bank Name" id = "debtor_bank_name" name="debtor_bank_name" value="${customer.debtor_bank_name}" required
      		 					disabled style = "background-color:rgb(237, 239, 241);"/>
                       		</div>
                       		
                       		<div class="input-field">
                            	<label>Debtor Mobile Number <span class="required-asterick">*</span></label>
                            	<input type="text" maxlength="10" id="debmob" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
									value="${customer.deb_mobno}" required placeholder = "Debtor Mobile Number" name="deb_mobno" />
                        	</div>
                        
                        	<div class="input-field">
                            	<label>Debtor Email</label>
                            	<input type="email" placeholder = "Enter Debtor Email" value="${customer.deb_emailid}" maxlength="100" id="debemail" required
     	 						name="deb_emailid"/>
                        	</div>
                        
                        	<div class="input-field">
                           		<label>Upload Mandate Image(JPEG/JPG) <span class="required-asterick">*</span></label>
                            	<input type="file" name="jpg_image" id="jpg_image" required onchange="javascript:ValidateFileUpload();" />
                        	</div>
                        
                        	<div class="input-field">
                            	<label>Upload Mandate Image(TIFF/TIF) <span class="required-asterick">*</span></label>
                            	<input type="file" name="tiff_image" id="tiff_image" required onchange="javascript:ValidateFileUpload1();" />
                       		 </div>
                        
                        	<div class="input-field">
                            	<label>Category Code <span class="required-asterick">*</span></label>
                            	<select required="required" id="catcode" name="category_code" style = "background-color:rgb(237, 239, 241);">

										<option selected="selected" value="">--Please Select--</option>
										<c:forEach items="${cat_code}" var="catcode">
											<option value="${catcode.code}"
											${catcode.code == selected_catcode ? 'selected="selected"' : ''}>${catcode.name}</option>
										</c:forEach>
								</select>
                        	</div>
                        	
                        	<div class="input-field">
                        		<label>Amend Reason: <span class="required-asterick">*</span></label>
                        		<select id="Reason"  name="reason_code">
      								<option value="">--- Select ---</option>
									<option value="A001">On Customer request.</option>
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
                        	
                        	<!-- <div class="input-field"> <img id="previewImage" src="#" alt=""></div> 	 -->
               </div>
               <div class="details ID">
                    <button type="submit" class="nextBtn" id="Update" name="Update" value="submit"  onclick = "return Onsubmit();">Submit</button>
                </div> 
         </div>
     </div>
     
    
     	<c:choose>
     		<c:when test="${not empty error}">
     			<h3 align = "center"><c:out value = "${error}"></c:out></h3>
     		</c:when>
     	</c:choose>
      
 
 
	 </form>
			
 </div>
</div>

<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
<script>
function hideQueryParams() { 
    // Get the current URL without query parameters
    var urlWithoutParams = window.location.pathname;
    
    // Replace the current URL in the browser history with the one without query parameters
    window.history.replaceState({}, document.title, urlWithoutParams);
}
</script>


</body>
</html>