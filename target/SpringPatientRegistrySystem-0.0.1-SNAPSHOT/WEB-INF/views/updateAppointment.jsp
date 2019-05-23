<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Update Appointment</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="usercontainer" id="containerProperties">
		<% if(request.getAttribute("message")!=null) {%>
			<p class="message">${ message }</p>
		<% } %>
		<a href="${ pageContext.request.contextPath }/allAppointments"><img class="cancelImg" src="${ pageContext.request.contextPath }/resources/images/cancel.png"></a>
		<form method="post">
			<div class="user-input">
					<label>Enter Patient's Firstname: </label>
					<input type="text" name="patientfirstname" value=${ patientFirstName } disabled="disabled">
			</div>
			<div class="user-input">
				<label>Enter Patient's Lastname: </label>
				<input type="text" name="patientlastname" value=${ patientLastName } disabled="disabled">
			</div>
			<div class="user-input">
				<label>Select Doctor: </label>
					<select name=doctorId required autofocus>
					<c:forEach items="${Doctors}" var="doctor">
					  <c:choose>
							<c:when test="${appointment.doctor.doctor_id ==  doctor.doctor_id}">
							  <option selected value="${ doctor.doctor_id }">${ doctor.firstName } ${ doctor.lastName }</option>
							</c:when>
							<c:otherwise>
							  <option value="${ doctor.doctor_id }">${ doctor.firstName } ${ doctor.lastName }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
			</div>
			<div class="user-input">
				<label>Enter Appointment Date: </label>
				<input type="date" name="date" value=${ appDate }>
			</div>
			<div class="user-input">
				<label>Enter Appointment Time: </label>
				<input type="time" name="time" value=${ appTime }>
			</div>
			<input class="link-user" type="submit" name="submit" value="Save Changes"><br>
		</form>
	</div>
</body>
</html>