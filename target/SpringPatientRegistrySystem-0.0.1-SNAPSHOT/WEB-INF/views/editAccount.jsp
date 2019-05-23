<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Edit Account</title>
	<link rel="stylesheet" type="text/css" href="resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/myAccount"><img class="cancelImg" src="../resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
				<label>Enter Firstname: </label>
				<input type="text" name="firstname" value="${ efirstname }" required autofocus>
			</div>
			<div class="user-input">
				<label>Enter Lastname: </label>
				<input type="text" name="lastname" value="${ elastname }" required>
			</div>
			<div class="user-input">
				<label>Enter Contact: </label>
				<input type="number" name="contact" value="${ econtact }" required>
			</div>
			<div class="user-input">
				<label>Enter Email: </label>
				<input type="email" name="email" value="${ eemail }" required>
			</div>
			<div class="user-input">
				<label>Enter Address: </label>
				<input type="text" name="address" value="${ eaddress }" required>
			</div>
			<input class="link-user" type="submit" name="submit" value="Save Changes"><br>
		</form>
	</div>
</body>
</html>