//alert("enter")
function doConfirm(msg, yesFn, noFn)
{
    var confirmBox = $("#confirmBox");
    confirmBox.find(".message").text(msg);
    confirmBox.find(".yes,.no").unbind().click(function()
    {
        confirmBox.hide();
    });
    confirmBox.find(".yes").click(yesFn);
    confirmBox.find(".no").click(noFn);
    confirmBox.show();
}


(function () {
    var onload = window.onload;

    window.onload = function () {
        if (typeof onload == "function") {
            onload.apply(this, arguments);
        }

        var fields = [];
        var inputs = document.getElementsByTagName("input");
        var textareas = document.getElementsByTagName("textarea");

        for (var i = 0; i < inputs.length; i++) {
            fields.push(inputs[i]);
        }

        for (var i = 0; i < textareas.length; i++) {
            fields.push(textareas[i]);
        }

        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];

            if (typeof field.onpaste != "function" && !!field.getAttribute("onpaste")) {
                field.onpaste = eval("(function () { " + field.getAttribute("onpaste") + " })");
            }

            if (typeof field.onpaste == "function") {
                var oninput = field.oninput;

                field.oninput = function () {
                    if (typeof oninput == "function") {
                        oninput.apply(this, arguments);
                    }

                    if (typeof this.previousValue == "undefined") {
                        this.previousValue = this.value;
                    }

                    var pasted = (Math.abs(this.previousValue.length - this.value.length) > 1 && this.value != "");

                    if (pasted && !this.onpaste.apply(this, arguments)) {
                        this.value = this.previousValue;
                    }

                    this.previousValue = this.value;
                };

                if (field.addEventListener) {
                    field.addEventListener("input", field.oninput, false);
                } else if (field.attachEvent) {
                    field.attachEvent("oninput", field.oninput);
                }
            }
        }
    };
})();

window.onload=function(){
	
	
	if(document.getElementById("SelectedMsgId").value!=null && document.getElementById("SelectedMsgId").value!="" && document.getElementById("SelectedMsgId").value!="null")
	{		
// 		document.getElementById("mohit").style.display="none";
		document.getElementById("mohit").style.display="none";
		document.getElementById("Cdname").disabled=true;
        document.getElementById("CrAccno").disabled=true;
		document.getElementById("Trantype").disabled=true;
		document.getElementById("Accounttype").disabled=true;
		document.getElementById("firstdt").disabled = false;
		document.getElementById("sp1").style.display="none";
		document.getElementById("finaldt").disabled = true;
		document.getElementById("sp2").style.display="none";
		/*document.getElementById("untlcancel").disabled = true;*/
		document.getElementById("debmob").disabled = true;
		document.getElementById("debemail").disabled = true;
		document.getElementById("Dbname").disabled = true;
		document.getElementById("DbAccno").disabled = true;
		document.getElementById("DbAcc").disabled = true;
		document.getElementById("Dbcode").disabled = true;
		document.getElementById("DbBank").disabled = true;
		document.getElementById("DbNo").disabled=true;
		document.getElementById("catcode").disabled=true;
		
		document.getElementById("Cdname").value = "<%=cus1.getCREDITOR_NAME()%>";
	    document.getElementById("CrAccno").value = "<%=cus1.getCREDITOR_ACCOUNT_NO()%>";
		document.getElementById("firstdt").value = "<%=cus1.getFIRST_COLLECTION_DATE()%>";
		document.getElementById("finaldt").value="<%=cus1.getFINAL_COLLECTION_DATE()%>";
		document.getElementById("untlcancel").value="<%=cus1.getUNTIL_CANCEL()%>";
		document.getElementById("debmob").value="<%=cus1.getDEBTOR_MOBILE_NO()%>";
		document.getElementById("debemail").value="<%=cus1.getDEBTOR_EMAIL_ID()%>";
		document.getElementById("Dbname").value="<%=cus1.getDEBTOR_NAME()%>";
		document.getElementById("DbAccno").value="<%=cus1.getDEBTOR_ACCOUNT_NO()%>";
		document.getElementById("DbBank").value="<%=cus1.getDEBTOR_BANK_NAME()%>";
		document.getElementById("DbNo").value="<%=cus1.getDEBTOR_IDENTIFICATION_NO()%>";
		document.getElementById("Trantype").value="<%=cus1.getTRANSACTION_TYPE()%>"; 
		document.getElementById("Accounttype").value="<%=cus1.getCREDITOR_ACCOUNT_NO_TYPE()%>";
		document.getElementById("Occur").value="<%=cus1.getOCCURENCE()%>";
		document.getElementById("catcode").value="<%=cus1.getCategory_Code()%>";
		dynamicdropdown('<%=cus1.getOCCURENCE()%>');
		document.getElementById("freq").value = "<%=cus1.getFREQUENCY()%>";
		document.getElementById("DbAcc").value="<%=cus1.getDEBTOR_ACCOUNT_TYPE()%>";
		document.getElementById("Dbcode").value="<%=cus1.getDEBTOR_IDENTIFICATION()%>";
		document.getElementById("Dbcollamttype").value="<%=cus1.getAMOUNT_TYPE()%>";
		document.getElementById("Dbamtcoll").value="<%=cus1.getAMOUNT()%>";
		
	}else if(document.getElementById("SelectedMsgId").value==null || document.getElementById("SelectedMsgId").value=="" || document.getElementById("SelectedMsgId").value=="null") {
 	        document.getElementById("mohit").style.display="block";
	        document.getElementById("Cdname").disabled=false;
	        document.getElementById("CrAccno").disabled=false;
	        document.getElementById("Trantype").disabled=false;  
	        document.getElementById("Accounttype").disabled=false;
	        document.getElementById("firstdt").disabled = false;
	        document.getElementById("sp1").style.display="block";
	        document.getElementById("finaldt").disabled = false;
	        document.getElementById("sp2").style.display="block";
	        /*document.getElementById("untlcancel").disabled = false;*/
			document.getElementById("debmob").disabled = false;
			document.getElementById("debemail").disabled = false;
	        document.getElementById("Dbname").disabled = false;
	        document.getElementById("DbAccno").disabled = false;
	        document.getElementById("DbAcc").disabled = false;
	        document.getElementById("Dbcode").disabled = false;
	        document.getElementById("DbBank").disabled = false;
	        document.getElementById("DbNo").disabled=false;
	        document.getElementById("catcode").disabled=false;
	        //document.getElementById("Reason").style.display="none";
	        //document.getElementById("spanReason").disabled=true;
	        //document.getElementById("Reason").disabled=true;
	        //document.getElementById("spanReason").style.display="none";
	        
	        document.getElementById("Cdname").value = "";
		    document.getElementById("CrAccno").value = "";
			document.getElementById("firstdt").value = "";
			document.getElementById("finaldt").value="";
			document.getElementById("untlcancel").value= "";
			document.getElementById("debmob").value=""; 
			document.getElementById("debemail").value="";
			document.getElementById("Dbname").value="";
			document.getElementById("DbAccno").value="";
			document.getElementById("DbBank").value="";
			document.getElementById("DbNo").value="";
			document.getElementById("Trantype").value=""; 
			document.getElementById("Accounttype").value="";
			document.getElementById("Occur").value="";
			document.getElementById("catcode").value="";
			dynamicdropdown('');
			document.getElementById("freq").value = "";
			document.getElementById("DbAcc").value="";
			document.getElementById("Dbcode").value="";
			document.getElementById("Dbcollamttype").value="";
			document.getElementById("Dbamtcoll").value="";
	}
	
}

//changes --> 29/09/2023
/*function change_date(){
	//Start Date
	var StartDate= document.getElementById('firstdt').value;
	var sDate = new Date(StartDate);
	console.log("Start Date :"+sDate);
	//Today's date 
	var date_today  = new Date();
	console.log("Today's Date"+date_today );
	sDate.setHours(0,0,0,0);
	date_today.setHours(0,0,0,0);
	
	//compare date
	if(sDate < date_today){
		alert("First Collection Date should always be greater than the Business Date");
		return false;
	}
	
	
}*/
//changes --> 29/09/2023

function ValidateFileUpload() {

	var fuData = document.getElementById('file1');
	var FileUploadPath = fuData.value;


	if (FileUploadPath == '') {
	    alert("Please upload an image");

	} else {
	    var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();



	    if (Extension == "jpeg" || Extension == "jpg" || Extension == "JPEG" || Extension == "JPG") {


	            if (fuData.files && fuData.files[0]) {

	                var size = fuData.files[0].size;

	                if(size > 75000){
	                    alert("Maximum file size exceeds");
	                    document.getElementById('file1').value = "";
	                     return false; 
	                    
	                }else{
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
	    var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();



	    if (Extension == "tiff" || Extension == "tif" || Extension == "TIFF" || Extension == "TIF") {


	            if (fuData.files && fuData.files[0]) {

	                var size = fuData.files[0].size;

	                if(size > 75000){
	                    alert("Maximum file size exceeds");
	                    document.getElementById('file2').value = "";
	                     return false; 
	                    
	                }else{
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

function cleartextbox() {
    document.getElementById("Dbamtcoll").value = "";
    
    
}

function cleartextbox1() {

document.getElementById("DbNo").value = "";

}
function indexChanged()
{
var txt=document.getElementById('DbNo');
var ddl=document.getElementById('Dbcode');

if (ddl.selectedIndex == 0)
{
txt.maxLength=11;
}else if (ddl.selectedIndex == 1){
txt.maxLength=11;
}else if (ddl.selectedIndex == 2) {
txt.maxLength=9;
}else if (ddl.selectedIndex == 3) {
txt.maxLength=6;
}
}
function IsEmpty(){
if(document.forms['form1'].question.value === "")
{
alert("empty");
return false;
}
return true;
}
function changeMaxLength()
{
indexChanged();
}
function fun_AllowOnlyAmountAndDot(Dbamtcoll)
{
if(event.keyCode > 47 && event.keyCode < 58 || event.keyCode == 46)
{
var txtbx=document.getElementById(Dbamtcoll);
var amount = document.getElementById(Dbamtcoll).value;
var present=0;
var count=0;

if(amount.indexOf(".",present)||amount.indexOf(".",present+1));
{
// alert('0');
}

/*if(amount.length==2)
{
if(event.keyCode != 46)
return false;onkeypress="return (event.charCode >= 48 && event.charCode <= 57) ||  event.charCode == 46 "
}*/
do
{
present=amount.indexOf(".",present);
if(present!=-1)
{
count++;
present++;
}
}
while(present!=-1);
if(present==-1 && amount.length==0 && event.keyCode == 46)
{
event.keyCode=0;
//alert("Wrong position of decimal point not  allowed !!");
return false;
}

if(count>=1 && event.keyCode == 46)
{

event.keyCode=0;
//alert("Only one decimal point is allowed !!");
return false;
}
if(count==1)
{
var lastdigits=amount.substring(amount.indexOf(".")+1,amount.length);
if(lastdigits.length>=2)
       {
         //alert("Two decimal places only allowed");
         event.keyCode=0;
         return false;
         }
}
return true;
}
else
{
event.keyCode=0;
//alert("Only Numbers with dot allowed !!");
return false;
}


}

function isNumberKey1(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}


function validateInput() {
    var val = document.getElementById("text1").value;
    return /[A-Z]{2}\d{3}/i.test(val);
}


function ShowHidechck1(untlcancel)
{  
	/*if(untlcancel.checked == true){
		document.getElementById("finaldt").disabled = true;
    	//$("#finaldt").prop('disabled', true);
		document.getElementById("finaldt").value = "";
	
	}
	else{
		document.getElementById("finaldt").disabled = false;
		//$("#finaldt").prop('disabled', false);
		document.getElementById("finaldt").value = "";
	}*/
	var dvPassport1 = document.getElementById("sp2");
	   	dvPassport1.style.display = untlcancel.checked ? "none" : "block";
      
}

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


//changes --> 06/10/2023
function inputlimit(){
	var drop = document.getElementById("Dbcollamttype");
    var drop_index = drop.selectedIndex;
    var amtinput = document.getElementById("Dbamtcoll");
    
    //If selected value is Max Amount
    if(drop_index == 2){
    		//set max amount to 1 crores
    		amtinput.max = 10000000;
    }
    else{
    		//remove max attribute
    		amtinput.removeAttribute('max');
    }
}

function checklimit(){
	var amttype = document.getElementById("Dbcollamttype");
	var index = amttype.selectedIndex;
	
	//If option selected is Max Amount
	//if(index == 2 ){
		var input = document.getElementById("Dbamtcoll");
		var amt = parseFloat(input);
		
		//If amt id greater than 1 cr
		if(amt > 10000000){
			alert("Maximum amount limit is 1 crores");
			document.getElementById("Dbamtcoll").value = '';
			return false;
		}
		
	//}
}
//changes --> 06/10/2023 

 function dynamicdropdown(listindex)
{
	 if(listindex == 'RCUR'){
		 removeExistingOptions();
		 for (var i=0; i < key_recurring.length;++i){
		 addOption(document.form1.freq, value_recurring[i], key_recurring[i]);
		 }
	 }else if(listindex == 'OOFF'){
		 removeExistingOptions();
		 for (var i=0; i < key_OOFF.length;++i){
		 addOption(document.form1.freq, value_OOFF[i], key_OOFF[i]);
		 }
	 }
}
 
 
 function submitForm(){

		if(document.getElementById("Cdname").value == ""){
			alert("Please Enter Creditor Name!");
			document.getElementById("Cdname").focus();
			return false;
		}
		
		if(document.getElementById("CrAccno").value == "" ){
			alert("Please Enter Creditor Acct No!");
			document.getElementById("CrAccno").focus();
			return false;
		}
		
	         var strUser1 = document.getElementById("Trantype").value;
	       
	          if(strUser1== "")
	         {
	             alert("Please select Transaction Type");
	             return false; 
	         }
	          
	          var strUsert = document.getElementById("Accounttype").value;
	          
	          if(strUsert== "")
	         {
	             alert("Please select Creditor Transaction  Type");
	             return false; 
	         }
	          
	     
	          var strUser2 = document.getElementById("Occur").value;
	          
	           if(strUser2=="")
	          {
	              alert("Please select Occurrence Type");
	              return false; 
	          }
	           
	          
	           var strUser3 = document.getElementById("freq").value;
	           
	            if(strUser3=="")
	           {
	               alert("Please select Frequency");
	               return false; 
	           }
	            
	            if(document.getElementById("firstdt").value == "" ){
	        		alert("Please Re Enter First Collection Date!");
	        		document.getElementById("firstdt").focus();
	        		return false;
	        	} 
	        	
	        	var StartDate= document.getElementById('firstdt').value;
	            var sDate = new Date(StartDate);
				var EndDate= document.getElementById('finaldt').value;
	            var eDate = new Date(EndDate);
	            
	            var today = new Date();
	            var interval = new Date();
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
	            
	           /*//if(!this.form1.untlcancel.checked){
	            	//if(document.getElementById("finaldt").value == "" ){
	            		//alert("Please Re Enter Final Collection Date!");
	            		//document.getElementById("finaldt").focus();
	            		//return false;
	            	//}
	            */
	            	if(StartDate!= '' && EndDate!= '' && (today>eDate || sDate>eDate)){
	             		alert("Please ensure that the Final Collection Date is greater than First Collection Date and Today's Date.Today"+today+"FirstDate"+sDate+"LastDate"+eDate);
	             		document.getElementById('finaldt').value = "";
	             		return false;
	             	}
	            }
	           /* else{*/
	        		/*document.getElementById("finaldt").value = "";*/	
	            
	             var strUser9 = document.getElementById("Dbcollamttype").value;
	              if(strUser9=="")
	             {
	                 alert("Please select Collection Amount:");
	                 return false; 
	             }
	             
	              var input = document.getElementById("Dbamtcoll").value;
	              if(input== 0 ){
	                  alert("Amount should be greater than 0");
	                  document.getElementById('Dbamtcoll').value = "";
	                  document.getElementById("Dbamtcoll").focus();
	          		   return false;
	             }
	              else if(document.getElementById("Dbamtcoll").value == ""){
	        		alert("Please Enter Amount.");
	        		document.getElementById("Dbamtcoll").focus();
	        		return false;
	        	}
	        	
	        	if(document.getElementById("Dbname").value == ""){
	        		alert("Please Enter Debtor  Name!");
	        		document.getElementById("Dbname").focus();
	        		return false;
	        	}
	        	
	        	//Make mobile number mandatory
	            if(document.getElementById("debmob").value == ""){
	            	alert("Please enter mobile number!!");
	            	document.getElementById("debmob").focus();
	            	return false;
	            }
	        	
	        	if(document.getElementById("DbAccno").value == "" ){
	        		alert("Please Enter Debtor Acct No!");
	        		document.getElementById("DbAccno").focus();
	        		return false;
	        	} 
	            
	             if(document.getElementById("DbAcc").value == "")
	            {
	                alert("Please select Debtor Account Type:");
	                document.getElementById("DbAcc").focus();
	                return false; 
	            }
	             
	              /* if(document.getElementById("debmob").value == "" ){
	         		alert("Please Enter Debtor Mobile No!");
	         		document.getElementById("debmob").focus();
	         		return false;
	         	} */
	             
	               /* if(document.getElementById("debemail").value == "" ){
	          		alert("Please Enter Debtor Email ID!");
	          		document.getElementById("debemail").focus();
	          		return false;
	          	}
	 */  
	             
	            
	             
	             var strUser5 = document.getElementById("Dbcode").value;
	             var star = document.getElementById("DbNo").value;
	             
	              if(strUser5=="IFSC" )
	             {
	            	  
	            	  var reg = /[A-Z|a-z]{4}[0][a-zA-Z0-9]{6}$/;;
	            	  
	                  if(star ==" " || star == null )
	                 {
	                     /*  alert("Please select Debtor IFSC/MICR/IIN Code:") ;
	                     return false;  */
	                       
	                 }
	                  
	                  if(star.match(reg)){
	            		  
	                      if(star.match(reg)&&(star.length<11 )) {
	                  	  alert(" You Entered Wrong IFSC Code \n\n Please enter 11 digit Debtor IFSC Code");
	                  	return false;
	                     }
	                      
	              	
	              	  }
	              	  else{
	              		alert(" You Entered Wrong IFSC Code \n\n Please enter 11 digit Debtor IFSC Code");
	              		  return false;
	              	  }
	                  
	             }
	              else if(strUser5=="MICR"){
	            	  
	            	  var numbers = /^[0-9]+$/;
	            	  
	            	  if(star ==" " || star == null )
	                  {
	                      /*  alert("Please select Debtor IFSC/MICR/IIN Code:") ;
	                      return false;  */
	                        
	                  }
	            	  if(star.match(numbers)){
	            		  
	                    if(star.match(numbers)&&(star.length<9 )) {
	                	  
	                	                 	 
	                	   alert("Please Enter 9 Digit MICR") ;
	                	
	                	return false;
	                   }
	                    
	            	
	            	  }
	            	  else{
	            		  alert("Please Enter 9 Digit Only") ;
	            		  return false;
	            	  }
	                   
	               
	              }
	              else if(strUser5=="IIN"){
	            	  
	            	  var numbers = /^[0-9]+$/;
	            	  
	            	  if(star ==" " || star == null )
	                  {
	                      
	                        
	                  }
	            	  if(star.match(numbers)){
	            		  
	                      if(star.match(numbers)&&(star.length<6 )) {
	                  	  
	                  	                 	 
	                  	   alert("Please Enter 6 Digit IIN") ;
	                  	
	                  	return false;
	                     }
	                      
	              	
	              	  }
	              	  else{
	              		  alert("Please Enter 6 Digit Only") ;
	              		  return false;
	              	  }
		     
	              }
	            
	                else{
	              	  alert("Please select Debtor IFSC/MICR/IIN Code:");
	          	      return false;
	                } 
	              
	              if(document.getElementById("DbBank").value == ""){
	          		alert("Please Enter Debtor Bank Name!");
	          		document.getElementById("DbBank").focus();
	          		return false;
	          	}
	          	
	              var filename = document.getElementById("file1").value;
	              if(filename=="")
	              {
	                  alert("Please select an Image to upload:");
	                  return false; 
	              }
	              
	              var filename12 = document.getElementById("file2").value;
	              if(filename12=="")
	              {
	                  alert("Please select an Image to upload:");
	                  return false; 
	              }
	              
	              var strUser71 = document.getElementById("catcode").value;
	              if(strUser71=="")
	              {
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
	                
	               						/*  document.getElementById('Update').onclick = function() {
	                	//             	   alert("button was clicked " + (count++) + " times");
	               	             	   alert("button was clicked");
	               	             	}; */
	               	             	
	               	             
	               	             var retVal = confirm("Do you want to continue ?");
	               	             if( retVal == true ){
	         	                    document.getElementById("form1").action='submit_File';
	         	                    /*if(document.getElementById("untlcancel").value="on"){
	         	                    	document.getElementById("untlcancel").value="";
	         	                    }*/
	         	                    
	               	             
	               	                document.getElementById("form1").submit();
	               	             
	         	                    
	               	                return true;
	               	             }
	               	             else{
	               	                //document.write ("User does not want to continue!");
	               	                //return false;
	               	             } 
	              
	