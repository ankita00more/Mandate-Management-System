<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Download INP File</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
<!-- <link rel="stylesheet" href="css/style.css"> -->

<script type="text/javascript">
	function Download(){
		alert("Click on download button");
		/* document.downloadform.action="/download"; */
		document.downloadform.action= `<c:url value = '/ZG93bmxvYWQ='/>`;
		document.downloadform.submit();
	}
</script>
</head>
<body>
	<%@include file="Navigationbar1.jsp" %>
	 <form method = "post" name = "downloadform" id = "downloadform">
  		<div id="content" >
  		 	<%@include file="hamburger.jsp"%>
  		 	<center><h3 class="page-name">Download INP File</h3></center>
  		 	<div class="container-amend" align="center">
  		 		<div class="input-field">
             		<label for="search" style = "font-size: 16px;">Download INPUT(INP) File: </label><br>
             		<label for="search" style = "font-size: 16px;">Select Date <span class="required-asterick">*</span></label><br>
             		 <input type="date"  class="col-sm-10 ml-2 mr-2" maxlength="0" size="0" id="downloaddt" required name="downloaddt"/>
             		 <button type="submit" style = "width: 20%;"  class="btn btn-primary center-button" onclick="return Download();">
           		     					Submit
          	    	</button>
          	    	
          	    	<div style = "margin-bottom: 15px; margin-top: 20px;" class="row justify-content-center">
						<div align = "center" class="col-md-6">
						<table >
						<%
							String inpfile = "";
							String contextpath = "";
							if((String)request.getAttribute("AllFileNames")!=null){
								String allfilenames = (String)request.getAttribute("AllFileNames");
								String srcpath = (String)request.getAttribute("srcdir");
								String[] filearray = allfilenames.split(",");
								for (int i=0;i<filearray.length;i++){
									/* contextpath = request.getContextPath();
									System.out.println("Context path will be -->"+contextpath); */
									inpfile = filearray[i];	
									
									
					%>
					
						<tr></tr>
						<tr></tr>
						<tr></tr>
						<tr>
							 <td>
							 		<c:url var="downloadUrl" value="/downloadfile">
    									<c:param name="filename" value="<%=inpfile%>" />
    									<c:param name="path" value="<%=srcpath%>" />
 								    </c:url>
 									 <a href="${downloadUrl}" target="_blank"><%=inpfile%></a>
							 </td>
						</tr>
					<%  
								}
					}
					%>
				  
						
				</table>
			</div>
		</div>
             		 
        		</div>
        		
  		 		
						<div class="row justify-content-center">
							
				
				
							<%
				 				String err = (String)request.getAttribute("err");
								if (err != null) {
									out.println(err);
								}
								if((String)request.getAttribute("seldate")!=null){
					   					 String seldate = (String)request.getAttribute("seldate");
					    				 HttpSession session1 =  request.getSession();
					    				 session1.setAttribute("seldt",seldate);
					    	
								}
							%>	
							</div>
						</div>
				</div> 
  		 	
  	</form>
  		 
	 
		
	
	<footer id="footer">Copyright &#169; C-EDGE Technologies Ltd. | ACH Debit Interface &#169; 2024</footer>
</body>
</html>