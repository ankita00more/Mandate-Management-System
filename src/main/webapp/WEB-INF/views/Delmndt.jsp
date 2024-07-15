<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.example.demo.entity.MdtInitRequestH2h" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancel Mandate</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<script>
	var today = new Date();
	var date= today.toISOString().slice(0, 10);
	 window.onload=function(){
		/* alert("Inside onload function"); */
		let stringValue = `${customer.occurence}`;
		/* alert("Inside onload function"); */
			/* alert("Inside RCUR function"); */
			document.getElementById("Occur").value= stringValue ;
			dynamicdropdown(stringValue); 
			document.getElementById("freq").value = `${customer.frequency}`;
			document.getElementById("Accounttype").value = `${customer.creditor_account_no_type}`;
			document.getElementById("Dbcollamttype").value = `${customer.amount_type}`;
			document.getElementById("DbAcc").value = `${customer.debtor_account_type}`;
			document.getElementById("Dbcode").value = `${customer.debtor_identification}`;
			document.getElementById("Accounttype").disabled = true;
			document.getElementById("Trantype").disabled = true;
			document.getElementById("DbAcc").disabled = true;
			document.getElementById("Dbcode").disabled = true;
			document.getElementById("catcode").disabled = true;
			document.getElementById("Occur").disabled = true;
			document.getElementById("freq").disabled = true;
			document.getElementById("Dbcollamttype").disabled = true;
			
			hideQueryParams();
	} 
	 
	 function dynamicdropdown(listindex)
	 {
	  console.log(listindex);
	  if(listindex == 'RCUR'){
	 	 removeExistingOptions();
	 	 for (var i=0; i < key_recurring.length;++i){
	 	 	addOption(document.mndt_canc.freq, value_recurring[i], key_recurring[i]);
	 	 }
	  }else if(listindex == 'OOFF'){
	 	 removeExistingOptions();
	 	 for (var i=0; i < key_OOFF.length;++i){
	 	 	addOption(document.mndt_canc.freq, value_OOFF[i], key_OOFF[i]);
	 	 }
	  }
	    
	 }
	 
	 function addOption(selectbox,text,value )

	 {
	 	/* alert("Inside add option"); */
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
	 
	
	function executeOnSubmit(event)
	{
		 event.preventDefault(); // Prevents the default form submission
		 var cncl_rsn = document.getElementById("CancelReason").value;
		 
		 if(cncl_rsn=="" || cncl_rsn == "--- Select ---")
	     {
	         alert("Please select  Cancel reason Code:");
	         return false;
	     } 
		 
		    var answer = confirm("Are you sure you want to cancel mandate?");
		 	if (answer==true)
		 	{
		 	    document.mndt_canc.action = `<c:url value = '/Y25jbG1hbmRhdGU='/>`;
				document.mndt_canc.submit(); 
		 	}
		 	else
		 	{
		 	    return false;
		 	}
	}
	
	function deletemandate(){
		
	}
</script>
</head>
<body>
	<%@include file="Navigationbar1.jsp" %>
	<div id="content">
		<%@include file="hamburger.jsp"%>
		<center><h3 class="page-name">Cancel Mandate</h3></center>
   		<div class="container">
   			<form action = "/cancelMandate" style = "min-height: 366px;" method="post" id="mndt_canc" enctype="multipart/form-data" name="mndt_canc" autocomplete="off">
   				 <div class="form first">
                	<div class="details personal">
                    	<div class="fields">
    						<input type="hidden" name="deleteMandateVar" id="deleteMandateVar" value="yes"> 
							<input type="hidden" name="customerId" id="customerId" value="${customer.uniqueId}">
							<input type="hidden" name="Mndt_Canc" id="Mndt_Canc" value=""> 
							<input type="hidden" name="Dbopt" id="Dbopt" value="">
							<input type="hidden" name="SelectedMsgId" id="SelectedMsgId" value="">
                    	
                    		<!-- 1 -->
                    		<div class="input-field">
                    			<label>Creditor Name <span class="required-asterick">*</span></label>
                    			<input type="text"  required  id="Cdname" name="creditor_name" maxlength="40" 
       				 			   value = "${customer.creditor_name}" placeholder="Creditor Name" disabled style ="background-color:rgb(237, 239, 241);"
       							   onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 " 
       				 			   onkeypress='return isNumberKey1(event)'/>
                    		</div>
                    		
                    		<!-- 2 -->
                    		<div class="input-field">
                            	<label for = "CrAccno">Creditor Account No <span class="required-asterick">*</span></label>
                            	<input  required  type="text" id="CrAccno" name="creditor_account_no" disabled style = " background-color:rgb(237, 239, 241);"
      							 maxlength="34" size="25" value = "${customer.creditor_account_no}" placeholder="Creditor Account No"
      							 onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " 
      							 onkeypress='return isNumberKey1(event)'/>
                        	</div>
                        	
                        	<!-- 3 -->
                        	<div class="input-field">
                            	<label for="Accounttype">Creditor Account Type <span class="required-asterick">*</span></label>
                            	<select  required="required" id="Accounttype" style = "background-color:rgb(237, 239, 241);"  name="creditor_account_no_type">
     				 				<option selected value="">--Select--</option>
     							    <option value="SAVINGS">Credit</option>
      				 				<option value="LOAN">Loan</option>
      							</select>
                        	</div>
                        	
                        	<!-- 4 -->
                        	<div class="input-field">
                            	<label>Transaction Type <span class="required-asterick">*</span></label>
                            	<select  id="Trantype" name="transaction_type" style = "background-color:rgb(237, 239, 241);" required>
     								 <option value="DEBIT">Debit</option>
      							</select>
                      		 </div>
                      		 
                      		 <!-- 5 -->
                      		 <div class="input-field">
                           		 <label>Occurrence Type<span class="required-asterick">*</span></label>
                            	<select  id="Occur" required  name="occurence" style ="background-color:rgb(237, 239, 241);"
      								onchange="javascript:dynamicdropdown(this.options[this.selectedIndex].value);">
      								<option selected="selected" value="">--Select--</option>
      								<option value="RCUR">Recurring</option>
      								<option value="OOFF">One-off</option>
      							</select>
                      		 </div>
                      		 
                      		 <!-- 6 -->
                      		 <div class="input-field">
                            	<label>Frequency <span class="required-asterick">*</span></label>
                            	<select  required id="freq" name="frequency" style ="background-color:rgb(237, 239, 241);">
      									<option selected value="" >--Select--</option>
      									<option value="RCUR">Recurring</option>
     		    				</select>
                      		 </div>
                      		 
                      		 <!-- 7 -->
                      		 <div class="input-field">
                            	<label>First Collection Date <span class="required-asterick">*</span></label>
                            	<input type="date"  maxlength="0" size="0" onclick="disable()" style ="background-color:rgb(237, 239, 241);"
                            	value="${customer.first_collection_date}" id="firstdt" name="first_collection_date" required 
								onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " 
								disabled/>
                       		</div>
                       		
                       		<!-- 8 -->
                       		<div class="input-field">
                            	<label>Final Collection Date <span class="required-asterick">*</span></label>
                            	<input type="date" maxlength="0" size="0" onclick="disable()" style ="background-color:rgb(237, 239, 241);"
                            	value="${customer.final_collection_date}" id="finaldt" required name="final_collection_date" 
								onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 45 " 
								disabled/>
                        	</div>
                        	
                        	<!-- 9 -->
                    		<div class="input-field">
                            	<label>Amt collected from Debtor A/c <span class="required-asterick">*</span></label>
                            	<select id="Dbcollamttype" name="amount_type" style ="background-color:rgb(237, 239, 241);"
									required onchange="javascript: cleartextbox();amtlimit();">
      								<option selected="selected" value="">--Select--</option>
      								<option value="FIXED">Fixed Amount</option>
     					 			<option value="MAXIMUM">Max Amount</option>
      							</select>
                        	</div>
                        	
                        	<!-- 10 -->
                        	<div class="input-field">
                            	<label>Amount <span class="required-asterick">*</span></label>
                            	<input onkeypress='return event.charCode >= 48 && event.charCode <= 57 || event.keyCode == 46' class="form-control" 
      							type="text" id="Dbamtcoll" name="amount" maxlength="10" size="20" min="1"  placeholder="Amount"
      							required value="${customer.amount}" style ="background-color:rgb(237, 239, 241);" disabled/>
                        	</div>
                        	
                        	<!-- 11 -->
                        	<div class="input-field">
                            	<label>Debtor Name <span class="required-asterick">*</span></label>
                            	<input type="text" id="Dbname" name="debtor_name" maxlength="40" size="25" disabled style = " background-color:rgb(237, 239, 241);"
								onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 " 
								value="${customer.debtor_name}" placeholder = "Debtor Name " required style ="background-color:rgb(237, 239, 241);"/>
                        	</div>
                        	
                        	<!-- 12 -->
                        	<div class="input-field">
                            	<label>Debtor Account Number <span class="required-asterick">*</span></label>
                            	<input onkeypress='return event.charCode >= 48 && event.charCode <= 57' type="text" 
      							id="DbAccno" name="debtor_account_no" maxlength="34" size="25" min="1" style ="background-color:rgb(237, 239, 241);"
								value="${customer.debtor_account_no }"  disabled placeholder = "Debtor Account Number" required />
                        	</div>
                        	
                        	<!-- 13 -->
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
                        	
                        	<!-- 14 -->
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
                        	
                        	<!-- 15 -->
                        	<div class="input-field">
                            	<label>Debtor IFSC/MICR/IIN Code <span class="required-asterick">*</span></label>
                            	<input type="text"  required value="${customer.debtor_identification_no}" style = " background-color:rgb(237, 239, 241);"
      							id="DbNo" name="debtor_identification_no" size="11"  disabled
	   							onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||(event.charCode >=65  && event.charCode <= 90)||(event.charCode >=97  
	   							&& event.charCode <= 122)"  placeholder="Debtor IFSC/MICR/IIN Code"/>	
                        	</div>
                        	
                        	<!-- 16 -->
                        	<div class="input-field">
                            	<label>Debtor Bank Name <span class="required-asterick">*</span></label>
                           		<input type="text" onkeypress="return (event.charCode >=65  && event.charCode <= 90) || (event.charCode >=97  && event.charCode <= 122) ||  event.charCode == 32 "
      		 					placeholder="Debtor Bank Name" name="debtor_bank_name" value="${customer.debtor_bank_name}" required
      		 					disabled style = "background-color:rgb(237, 239, 241);"/>
                       		</div>
                       		
                       		<!-- 17 -->
                       		<div class="input-field">
                            	<label>Debtor Mobile Number <span class="required-asterick">*</span></label>
                            	<input type="text" maxlength="10" id="debmob" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
									value="${customer.deb_mobno}" required placeholder = "Debtor Mobile Number" name="deb_mobno" 
									disabled style ="background-color:rgb(237, 239, 241);"/>
                        	</div>
                        	
                        	<!-- 18 -->
                        	<div class="input-field">
                            	<label>Debtor Email</label>
                            	<input type="email" placeholder = "Enter Debtor Email" value="${customer.deb_emailid}" maxlength="100" id="debemail" required
     	 						name="deb_emailid" disabled  style ="background-color:rgb(237, 239, 241);"/>
                        	</div>
                        	
                        	<!-- 19 -->
                        	<div class="input-field">
                            	<label>Category Code <span class="required-asterick">*</span></label>
                            	<select required="required" id="catcode" name="category_code" style = "background-color:rgb(237, 239, 241);">
										<c:forEach items="${cat_code}" var="catcode">
											<option  value="${catcode.code}"
											${catcode.code == selected_catcode ? 'selected="selected"' : ''}>${catcode.name}</option>
										</c:forEach>
								</select>
                        	</div>
                        	
                        	<!-- 20 -->
                        	<div class="input-field">
                        		<label>Mandate Cancel Reason <span class="required-asterick">*</span></label>
                        		<select  required="required" id="CancelReason" name="reason_code">
  									<option value="">--- Select ---</option>
									<option value="C001">Cancellation on customer Request</option>
									<option value="C002">Cancellation on corporate request</option>
									<option value="C003">Account Closed</option>
									<option value="C004">Account Frozen</option>
									<option value="C005">Account InOperative</option>		
								</select>
                        	</div>
                        	
                    		
                    	</div>
                    	<div class="details ID">
                    		<button type="submit" class="nextBtn" id="Update" name="Update" value="submit"  onclick = "javascript:executeOnSubmit(event);">Submit</button>
               		    </div>
                    </div>
                 </div>
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