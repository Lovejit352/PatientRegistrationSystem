<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Add User</title>
	<link rel="stylesheet" type="text/css" href="../resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body class="preload"> 
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer fade-in one" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/user"><img class="cancelImg growImg" src="../resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
				<label>Enter Username: </label>
				<input type="text" name="username" required autofocus>
			</div>
			<div class="user-input">
				<label>Enter Password: </label>
				<input type="password" name="password" required>
			</div>
			<div class="user-input">
				<label>Enter Firstname: </label>
				<input type="text" name="firstname" required>
			</div>
			<div class="user-input">
				<label>Enter Lastname: </label>
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
				<label>Enter Usertype: </label>
				<select name=usertype>
				  <option value="A">Admin</option>
				  <option value="R">Receptionist</option>
				</select>
			</div>
			<input class="link-user grow" type="submit" name="submit" value="Add"><br>
		</form>
	</div>
</body>
</html>