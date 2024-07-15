 
 
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
 
 function submitForm() {

		if (document.getElementById("Cdname").value == "") {
			alert("Please Enter Creditor Name!");
			document.getElementById("Cdname").focus();
			return false;
		}

		if (document.getElementById("CrAccno").value == "") {
			alert("Please Enter Creditor Acct No!");
			document.getElementById("CrAccno").focus();
			return false;
		}

		var strUser1 = document.getElementById("Trantype").value;

		if (strUser1 == "") {
			alert("Please select Transaction Type");
			return false;
		}

		var strUsert = document.getElementById("Accounttype").value;

		if (strUsert == "") {
			alert("Please select Creditor Transaction  Type");
			return false;
		}

		var strUser2 = document.getElementById("Occur").value;

		if (strUser2 == "") {
			alert("Please select Occurrence Type");
			return false;
		}

		var strUser3 = document.getElementById("freq").value;

		if (strUser3 == "") {
			alert("Please select Frequency");
			return false;
		}

		if (document.getElementById("firstdt").value == "") {
			alert("Please Re Enter First Collection Date!");
			document.getElementById("firstdt").focus();
			return false;
		}
		
		/* //Mobile number Mandatory
		//CHANGES --> 12/09/2023
		if (document.getElementById("debmob").value == "") {
			alert("Please Enter Mobile Number!");
			document.getElementById("debmob").focus();
			return false;
		}
		
		//CHANGES --> 12/09/2023 */
		
/*
		var StartDate = document.getElementById('firstdt').value;
		console.log("First Collection Date  USER>> "+StartDate);
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
		if(MDate>today){
			alert("Mandate Future date is not allowed(Date of Mandate)");
			document.getElementById('Mdate').value = "";
			return false;
			
		}
		//CHANGES --> 13/09/2023
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
		
		
		
		//Compare Date
		/* if(start.getTime() < date_today.getTime()){
			
			alert("First collection date should always be greater than business date");
			document.getElementById('firstdt').value = "";
			return false;
			
		} */
		
		
		//CHANGES --> 13/09/2023
		
		//CHANGES 12/09/2023--> 
		//Get year difference between the two dates 
		
		/*
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
		 if(yeardiff == 30){
			 
			 //IF MONTH PART AND DATE PART ARE SAME
			 if(m1 == m2 ){
				 //IF BOTH THE DATES ARE NOT SAME
				 if(d1 == d2){
				       console.log("Difference between both is exactly 30 years");
				 }
				 //IF THE DATE PART OF THE END DATE IS GREATER THAN THE DATE PART OF START DATE
				 else if(d1 > d2){
					 
						 alert("Final Collection date cannot be more than 30 years than first collection date");
						 document.getElementById('finaldt').value = "";
	    				 return false;
				 }	
			 }
			 //IF MONTH PART OF THE END DATE IS GREATER THAN THE MONTH PART OF START DATE
			 else if(m1 > m2){
					 alert ("Final Collection date cannot be more than 30 years than first collection date");
					 document.getElementById('finaldt').value = "";
    				 return false;
				 }
				 
			 
			 
		 }
		 //IF THE DIFFERENCE BETWEEN BOTH THE DATES IS 30 YEARS
		 else if(yeardiff > 30){
				 alert ("Final Collection date cannot be more than 30 years than first collection date");
				 document.getElementById('finaldt').value = "";
				 return false;
		}
		 
		
		
		//CHANGES -->
		
		if(sDate<=MDate){
			alert("First Collection date should always be greater than Mandate date(Date of Mandate)");
			document.getElementById('firstdt').value = "";
			return false;
		}
		
		console.log("BDate" + BDate);
		var comp = (BDate - MDate);
		var TotalDays = Math.ceil(comp / (1000 * 3600 * 24));
		console.log("TotalDays" + TotalDays);

		//Diff between mandate and business date should not be greater than 120
		if (TotalDays > 120) {
			alert("Range Between Mandate Date and Current Bussiness Date exceeds(current Bussiness date and Mandate date)");
			document.getElementById('Mdate').value = "";
			return false;
		}
		
	
		/*  var interval = new Date();
		 interval.setDate(today.getDate()+1);
		 var dtDate = document.getElementById("firstdt").value;
		 var iDate = new Date(interval);
		 
		 var m = String(today.getMonth()+1);
		 var d = String(today.getDate());
		 if(d.length<2){
		 	d="0"+d;
		 }
		 var y = today.getFullYear();
		 today = m+'-'+d+'-'+y;
		 */
		 
		 //CHANGES -->12/09/2023
		/* if (!this.form1.untlcancel.checked) {
			if (document.getElementById("finaldt").value == "") {
				alert("Please Re Enter Final Collection Date!");
				document.getElementById("finaldt").focus();
				return false;
			} */
		//CHANGES -->12/09/2023

			/* 	if(StartDate!= '' && EndDate!= '' && (today>eDate || sDate>eDate)){
			 		alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date.Today"+today+"FirstDate"+sDate+"LastDate"+eDate);
			 		document.getElementById('finaldt').value = "";
			 		return false;
			 	} */
/*
			if (StartDate != '' && EndDate != '') {
				if (eDate < sDate || eDate < today || eDate == today) {
					alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date");
					document.getElementById('finaldt').value = "";
					return false;
				}
			}
		//CHANGES 11/09/2023-->
		/* } else {
			document.getElementById("finaldt").value = "";
		} */
		//CHANGES 11/09/2023-->
	/*	
		var strUser9 = document.getElementById("Dbcollamttype").value;
		//changes --> 05/10/2023
		/* var select_index= strUser9.selectedIndex;
		console.log("Selected index is --> "+select_index);
		if(select_index == 1){
			var amount = document.getElementById("Dbamtcoll").value;
			if(amount > 10000000){
				alert("Amount limit is 1 crore!!");
			}
		}
		// *//* changes --> 05/10/2023 */
/*		
		if (strUser9 == "") {
			alert("Please select Collection Amount:");
			return false;
		}

		var input = document.getElementById("Dbamtcoll").value;
		if (input == 0) {
			alert("Amount should be greater than 0");
			document.getElementById('Dbamtcoll').value = "";
			document.getElementById("Dbamtcoll").focus();
			return false;
		} else if (document.getElementById("Dbamtcoll").value == "") {
			alert("Please Enter Amount.");
			document.getElementById("Dbamtcoll").focus();
			return false;
		}

		if (document.getElementById("Dbname").value == "") {
			alert("Please Enter Debtor  Name!");
			document.getElementById("Dbname").focus();
			return false;
		}
		
		 if(document.getElementById("finaldt").value == ""){
	    	    alert("Please Enter Final Collection Date!");
	      		document.getElementById("finaldt").focus();
	      		
	      		return false;
	    	   
	       } 

		if (document.getElementById("DbAccno").value == "") {
			alert("Please Enter Debtor Acct No!");
			document.getElementById("DbAccno").focus();
			return false;
		}

		if (document.getElementById("DbAcc").value == "") {
			alert("Please select Debtor Account Type:");
			document.getElementById("DbAcc").focus();
			return false;
		}

		//Make mobile number mandatory
		if (document.getElementById("debmob").value == "") {
			alert("Please enter mobile number!!");
			document.getElementById("debmob").focus();
			return false;
		}

		/* if (document.getElementById("debmob").value == "") {
			alert("Please Enter Debtor Mobile No!");
			document.getElementById("debmob").focus();
			return false;
		} */

		/* if (document.getElementById("debemail").value == "") {
			alert("Please Enter Debtor Email ID!");
			document.getElementById("debemail").focus();
			return false;
		} */
/*
		var strUser5 = document.getElementById("Dbcode").value;
		var star = document.getElementById("DbNo").value;

		if (strUser5 == "IFSC") {

			var reg = /[A-Z|a-z]{4}[0][a-zA-Z0-9]{6}$/;
			;

			if (star == " " || star == null) {
				alert("Please select Debtor IFSC/MICR/IIN Code:");
				return false;

			}

			if (star.match(reg)) {

				if (star.match(reg) && (star.length < 11)) {
					alert(" You Entered Wrong IFSC Code \n\n Please enter 11 digit Debtor IFSC Code");
					return false;
				}

			} else {
				alert(" You Entered Wrong IFSC Code \n\n Please enter 11 digit Debtor IFSC Code");
				return false;
			}

		} else if (strUser5 == "MICR") {

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

		} else if (strUser5 == "IIN") {

			var numbers = /^[0-9]+$/;

			if (star == " " || star == null) {

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

		if (document.getElementById("DbBank").value == "") {
			alert("Please Enter Debtor Bank Name!");
			document.getElementById("DbBank").focus();
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

		/* document.getElementById('Update').onclick = function() {
			//             	   alert("button was clicked " + (count++) + " times");
			alert("button was clicked");
		}; */
		
		 //CHANGES --> 06/10/2023
		 /*
        var amttype = document.getElementById("Dbcollamttype");
     	var index = amttype.selectedIndex;
     	console.log("value index "+index);
     	//If option selected is Max Amount
     	if(index === 2){
     		var input = document.getElementById("Dbamtcoll").value;
     		var amt = parseFloat(input);
     		console.log("Amount is :"+amt);
     		
     		//If amt id greater than 1 cr
     		if(amt > 10000000){
     			alert("Maximum amount limit is 1 crores");
     			document.getElementById("Dbamtcoll").value = '';
     			return false;
     		}
     		
     	}
		//CHANGES --> 06/10/2023

		var retVal = confirm("Do you want to continue ?");
		if (retVal == true) {
			document.getElementById("form1").action = 'submit_File';
			//CHANGES --> 13/09/2023
			/* if (document.getElementById("untlcancel").value = "on") {
				document.getElementById("untlcancel").value = ""
			} */
			//CHANGES --> 13/09/2023
		/*	document.getElementById("form1").submit();
/*
			return true;
		}
		//CHANGES --> 13/09/2023	
		/* else {
			//document.write ("User does not want to continue!");
			//return false;
		} */
		//CHANGES --> 13/09/2023
	} 