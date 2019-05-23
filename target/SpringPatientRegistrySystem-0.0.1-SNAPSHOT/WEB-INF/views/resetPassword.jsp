<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Reset Password</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/user/updateUser/${ id }"><img class="cancelImg" src="${ pageContext.request.contextPath }/resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
				<label>Enter New Password: </label>
				<input type="text" name="newpassword" required autofocus>
			</div>
			<div class="user-input">
				<label>Confirm Password: </label>
				<input type="text" name="confirmpassword" required>
			</div>
			<input class="link-user" type="submit" name="submit" value="Save Changes"><br>
		</form>
	</div>
</body>
</html>