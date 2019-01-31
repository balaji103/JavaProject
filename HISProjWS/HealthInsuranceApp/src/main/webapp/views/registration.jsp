<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>

	
	 $(function() {
		$("#email").blur(function(){
			var enteredEmail = $("#email").val();
			 $.ajax({
				url: window.location+"/checkEmail", 
				data: "email="+enteredEmail,
				success: function(result){
					if(result=='DUPLICATE'){
						alert("Email---------------->.");
						
			             $("#emailMsg").html(DUPLICATE);
			             $("#email").focus();
					}
					//alert("Email-->."+enteredEmail);
			  }}); 
			  
			});
		
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date()
		});
	}); 
</script>

<title>Registration Form</title>
</head>
<body>
	${SUCCESS}
	<form:form action="userReg" method="post" modelAttribute="formModel" name="userRegForm" id="userRegForm">
		<div align="center">
			<table>
				<tr>
					<td>First Name :</td>
					<td><form:input path="firstName" /></td>
				</tr>

				<tr>
					<td>Last Name :</td>
					<td><form:input path="lastName" /></td>
				</tr>

				<tr>
					<td>Email :</td>
					<td><form:input path="userEmail" id="email"/>
					<font color="red"><span id="emailMsg"></span></font>
					</td>
				</tr>

				<tr>
					<td>Password :</td>
					<td><form:password path="userPwd" /></td>
				</tr>

				<tr>
					<td>Phone :</td>
					<td><form:input path="userPhno" /></td>
				</tr>

				<tr>
					<td>DOB :</td>
					<td><form:input path="userDob" id="datepicker"/></td>
				</tr>

				<tr>
					<td>User Role :</td>
					<td><form:select path="userRole" items="${userRole}" /></td>
				</tr>

				<tr>
					<td><input type="reset" value="Reset" /></td>
					<td><input type="submit" value="Register" /></td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>