<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Change Password</title>
	<link rel="stylesheet" type="text/css" href="resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/editAccount"><img class="cancelImg" src="resources/images/cancel.png"></a>
		<form action="changePassword" method="post">
			<div class="user-input">
				<label>Enter Old Password: </label>
				<input type="password" name="oldPassword" required autofocus>
			</div>
			<div class="user-input">
				<label>Enter New Password: </label>
				<input type="password" name="newPassword" required>
			</div>
			<div class="user-input">
				<label>Confirm Password: </label>
				<input type="password" name="confirmPassword" required>
			</div>
			<input class="link-user" type="submit" name="submit" value="Save Changes"><br>
		</form>
	</div>
</body>
</html>