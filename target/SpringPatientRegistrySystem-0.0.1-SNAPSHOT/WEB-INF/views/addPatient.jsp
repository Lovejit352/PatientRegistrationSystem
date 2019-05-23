<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Add Patient</title>
	<link rel="stylesheet" type="text/css" href="resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/patient"><img class="cancelImg" src="../resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
				<label>Enter Firstname: </label>
				<input type="text" name="firstname" required autofocus>
			</div>
			<div class="user-input">
				<label>Enter LastName: </label>
				<input type="text" name="lastname" required>
			</div>
			<div class="user-input">
				<label>Enter Contact: </label>
				<input type="number" name="contact" required>
			</div>
			<div class="user-input">
				<label>Enter Email: </label>
				<input type="email" name="email" required>
			</div>
			<div class="user-input">
				<label>Enter Address: </label>
				<textarea style="vertical-align: middle;" rows="4" cols="25" name="address" required></textarea>
			</div>
			<div class="user-input">
				<label>Enter Birthdate: </label>
				<input type="date" name="birthdate" required>
			</div>
			<input class="link-user" type="submit" name="submit" value="Add"><br>
		</form>
	</div>
</body>
</html>