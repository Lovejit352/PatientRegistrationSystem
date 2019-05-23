<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User</title>
	<link rel="stylesheet" type="text/css" href="resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	
	<%@ include file="navigation.jsp" %>

	<div class="tableDiv">
		<p class="adminP1">Current Patients</p>
		<input type="text" id="firstInput" onkeyup="myFunction()" placeholder="Enter First Name.." title="Type in first name">
		<div id = "advanceDiv">
			<a type="button" id="advanceButton" onclick="advanceOptions()">Advance Search</a>
		</div>
		<div id="advanceSearch" style="display:none">
			<input type="text" id="lastInput" onkeyup="myFunction()" placeholder="Enter Last Name.." title="Type in last name">
			<input type="text" id="emailInput" onkeyup="myFunction()" placeholder="Enter Email.." title="Type in email">
			<input type="text" id="createdByInput" onkeyup="myFunction()" placeholder="Created By.." title="Type in email">
		</div>
		<table class="table" id="myTable">
			<tr>
				<th>Patient Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Birth Date</th>
				<th>Address</th>
				<th>Contact</th>
				<th>Registered Date</th>
				<th>Created By</th>
				<th></th>
			</tr>
			<c:forEach items="${Patients}" var="patient">
			    <tr>
					<td><a href="${ pageContext.request.contextPath }/patient/updatePatient/${ patient.patientId }">${ patient.patientId }</a></td>
					<td>${ patient.firstName }</td>
					<td>${ patient.lastName }</td>
					<td>${ patient.email }</td>
					<td>${ patient.birthDate }</td>
					<td>${ patient.address }</td>
					<td>${ patient.contactNo }</td>
					<td>${ patient.registrationDate }</td>
					<td>${ patient.createdBy.firstName }</td>
					<td><a class="bookingLink" href="${ pageContext.request.contextPath }/upcomingAppointments/addAppointment/${ patient.patientId }">Book Appointment</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="btn-container">
		<a class="link-user grow" href="${ pageContext.request.contextPath }/patient/addPatient">Add Patient</a>
	</div>
	<script>
	function myFunction(){
		var firstInput = document.getElementById("firstInput").value.toLowerCase();
		var lastInput = document.getElementById("lastInput").value.toLowerCase();
		var emailInput = document.getElementById("emailInput").value.toLowerCase();
		var createdByInput = document.getElementById("createdByInput").value.toLowerCase();
		var table = document .getElementById("myTable");
		var row = table.getElementsByTagName("tr");
		var tableRows = document.getElementById("myTable").rows.length;
		for(var i=1; i<tableRows;i++){
			var resFirst = document.getElementById("myTable").rows[i].cells.item(1).innerHTML.toLowerCase();
			var resLast = document.getElementById("myTable").rows[i].cells.item(2).innerHTML.toLowerCase();
			var resEmail = document.getElementById("myTable").rows[i].cells.item(3).innerHTML.toLowerCase();
			var resCreatedBy = document.getElementById("myTable").rows[i].cells.item(8).innerHTML.toLowerCase();
			if(resFirst.includes(firstInput)==true && resLast.includes(lastInput)==true &&
					resEmail.includes(emailInput)==true && resCreatedBy.includes(createdByInput)==true){
				var rows = row[i];
				rows.style.display = "";
			}
			else{
				var rows = row[i];
				rows.style.display = 'none';
			}
		}
	}
	
	function advanceOptions(){
		var advanceOpt = document.getElementById("advanceSearch");
		var advButton = document.getElementById("advanceButton");
		var emptyDiv = document.getElementById("empty");
		
		if (advanceOpt.style.display == "none") {
			advanceOpt.style.display = "block";
			advButton.innerHTML = "Close";
		  } else {
			  advanceOpt.style.display = "none";
			  advButton.innerHTML = "Advance Search";
		  }
	}
	</script>
</body>
</html>