<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Login Page</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>	
	<div class="container" id="containerProperties">
		<img src="resources/images/loginIcon.png">
		<% if(request.getAttribute("message")!=null) {%>
		<div class="loginError">
			<p class="messageLogin">${ message }</p>
		</div>
		<% } %>
		<form action="login" method="post">
			<div class="form-input">
				<input type="text" name="username" placeholder="Enter Username" required autofocus>
			</div>
			<div class="form-input">
				<input type="password" name="password" placeholder="Enter Password" required>
			</div>
			<input class="link-user" type="submit" name="submit" value="Login"><br>
		</form>
	</div>
</body>
</html>