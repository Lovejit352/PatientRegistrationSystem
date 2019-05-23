<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Add Appointment</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer fade-in one" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/patient"><img class="cancelImg growImg" src="${ pageContext.request.contextPath }/resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
				<label>Enter Patient's Firstname: </label>
				<input type="text" name="patientfirstname" value=${ patFirstName } disabled="disabled">
			</div>
			<div class="user-input">
				<label>Enter Patient's Lastname: </label>
				<input type="text" name="patientlastname" value=${ patLastName } disabled="disabled">
			</div>
			<div class="user-input">
				<label>Select Doctor: </label>
					<select name=doctorId required>
					<c:forEach items="${Doctors}" var="doctor">
					  <option value="${ doctor.doctor_id }">${ doctor.firstName } ${ doctor.lastName }</option>
					</c:forEach>
					</select>
			</div>
			<div class="user-input">
				<label>Enter Appointment Date: </label>
				<input type="date" name="appointmentDate" required>
			</div>
			<div class="user-input">
				<label>Enter Appointment Time: </label>
				<input type="time" name="appointmentTime" required>
			</div>
			<input class="link-user grow" type="submit" name="submit" value="Add"><br>
		</form>
	</div>
</body>
</html>