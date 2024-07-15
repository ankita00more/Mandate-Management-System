//VALUE OF OCCURENCE

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
		 addOption(document.form1.freq, value_recurring[i], key_recurring[i]);
		 }
	 }else if(listindex == 'OOFF'){
		 removeExistingOptions();
		 for (var i=0; i < key_OOFF.length;++i){
		 addOption(document.form1.freq, value_OOFF[i], key_OOFF[i]);
		 }
	 }
}