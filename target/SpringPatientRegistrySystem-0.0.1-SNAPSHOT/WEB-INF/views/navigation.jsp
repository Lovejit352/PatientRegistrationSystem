<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.fdmgroup.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<div class="main">
		<p>The Clinic</p>
		<div class="navLinks">
			<%	User user = (User)session.getAttribute("loggedUser");
				int typeId = user.getUserType().getTypeId();
				if(typeId==0){
			%>
			<a class="links" href="${ pageContext.request.contextPath }/user">User</a>
			<%	}%>
			<a class="links" href="${ pageContext.request.contextPath }/patient">Patient</a>
			<a class="links" href="${ pageContext.request.contextPath }/upcomingAppointments">Appointment</a>
			<a class="links" href="${ pageContext.request.contextPath }/logout">Log Out</a>
			<a class="links" id="username" href="${ pageContext.request.contextPath }/myAccount">${ sessionScope.loggedUser.firstName }</a>
		</div>
	</div>
	<div class="main" id="main1">
		<div class="navLinks" id="addLinks">
			<a class="links" href="${ pageContext.request.contextPath }/user/addUser">Add User</a>
			<a class="links" href="${ pageContext.request.contextPath }/patient/addPatient">Add Patient</a>
			<a class="links" href="${ pageContext.request.contextPath }/upcomingAppointments/addAppointment">Add Appointment</a>
			<a class="links" href="allAppointments">View All Appointments</a>
		</div>
	</div>	
</body>
</html>