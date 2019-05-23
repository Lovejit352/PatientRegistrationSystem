<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdmgroup.model.UserType" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Update User</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>

	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/user"><img class="cancelImg" src="${ pageContext.request.contextPath }/resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
				<label>Enter Firstname: </label>
				<input type="text" name="firstname" value=${ firstName } required autofocus>
			</div>
			<div class="user-input">
				<label>Enter Lastname: </label>
				<input type="text" name="lastname" value=${ lastName } required>
			</div>
			<div class="user-input">
				<label>Enter Contact: </label>
				<input type="number" name="contact" value=${ contact } required>
			</div>
			<div class="user-input">
				<label>Enter Email: </label>
				<input type="email" name="email" value=${ email } required>
			</div>
			<div class="user-input">
					<label>Enter Address: </label>
					<textarea style="vertical-align: middle;" rows="4" cols="25" name="address" required>${ address }</textarea>
				</div>
			<div class="user-input">
				<label>Enter Usertype: </label>
				<select name=usertype>
				  <%
				  	for(UserType type: UserType.values()){
				  		if(type==request.getAttribute("userType"))
				  			out.println("<option value="+type+" selected>"+type.getDescription()+"</option>");
				  		else
				  			out.println("<option value="+type+">"+type.getDescription()+"</option>");
				  	}
				  %>
				</select>
			</div>
			<input class="link-user" type="submit" name="submit" value="Save Changes"><br>
		</form>
	</div>
	
	<div class="space"> </div>
	<div class="btn-container">
		<a class="link-user" href="${ pageContext.request.contextPath }/user/updateUser/resetPassword/${ id }">Reset Password</a>
	</div>
</body>
</html>