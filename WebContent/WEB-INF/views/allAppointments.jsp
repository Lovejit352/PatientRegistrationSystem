<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	
	<%@ include file="navigation.jsp" %>

	<div class="tableDiv">
		<p class="adminP1">All Appointments</p>
		<input type="text" id="patientfirstInput" onkeyup="myFunction()" placeholder="Enter Patient First Name.." title="Type in patient first name">
		<div id = "advanceDiv">
			<a type="button" id="advanceButton" onclick="advanceOptions()">Advance Search</a>
		</div>
		<div id="advanceSearch" style="display:none">
			<input type="text" id="doctorfirstInput" onkeyup="myFunction()" placeholder="Enter Doctor First Name.." title="Type in doctor first name">
			<input type="text" id="patientlastInput" onkeyup="myFunction()" placeholder="Enter Patient Last Name.." title="Type in patient last name"><br>
			<p class="dateLabel">By Date:-</p>
			<input style="margin-top: 8px;" type="date" id="fromdateinput" onblur="myFunction()" date-placeholder="From" title="Type in from"> - 
			<input style="margin-top: 8px;" type="date" id="todateinput" onblur="myFunction()" date-placeholder="To" title="Type in to">
		</div>
		<div class="floatDiv">
			<a href="${ pageContext.request.contextPath }/upcomingAppointments"><img class="backIconImg" src="resources/images/backIcon.png"></a>
		</div>
		<table class="table" id="myTable">
			<tr>
				<th>Appointment Id</th>
				<th>Doctor First Name</th>
				<th>Doctor Last Name</th>
				<th>Patient First Name</th>
				<th>Patient Last Name</th>
				<th>Appointment Date</th>
				<th>Appointment Time</th>
			</tr>
			<c:forEach items="${Appointments}" var="appointment">
			    <tr>
					<td><a href="${ pageContext.request.contextPath }/allAppointments/updateAppointment/${ appointment.appointmentId }">${ appointment.appointmentId }</a></td>
					<td>${ appointment.doctor.firstName }</td>
					<td>${ appointment.doctor.lastName }</td>
					<td>${ appointment.patient.firstName }</td>
					<td>${ appointment.patient.lastName }</td>
					<td>${ appointment.appointmentDate }</td>
					<td>${ appointment.appointmentTime }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="btn-container">
		<a class="link-user grow" href="${ pageContext.request.contextPath }/upcomingAppointments">Go Back</a>
	</div>
	
	<script>
	function myFunction(){
		var patientfirstInput = document.getElementById("patientfirstInput").value.toLowerCase();
		var patientlastInput = document.getElementById("patientlastInput").value.toLowerCase();
		var doctorfirstInput = document.getElementById("doctorfirstInput").value.toLowerCase();
		var fromdate = document.getElementById("fromdateinput").value;
		var todate = document.getElementById("todateinput").value;
		var fromdate = document.getElementById("fromdateinput").value;
		var todate = document.getElementById("todateinput").value;
		var table = document .getElementById("myTable");
		var row = table.getElementsByTagName("tr");
		var tableRows = document.getElementById("myTable").rows.length;
		for(var i=1; i<tableRows;i++){
			var respatientFirst = document.getElementById("myTable").rows[i].cells.item(3).innerHTML.toLowerCase();
			var respatientLast = document.getElementById("myTable").rows[i].cells.item(4).innerHTML.toLowerCase();
			var resdoctorFirst = document.getElementById("myTable").rows[i].cells.item(1).innerHTML.toLowerCase();
			var resDate = document.getElementById("myTable").rows[i].cells.item(5).innerHTML;
			if(todate=="" && fromdate!=""){
				if(respatientFirst.includes(patientfirstInput)==true && respatientLast.includes(patientlastInput)==true &&
						resdoctorFirst.includes(doctorfirstInput)==true && new Date(resDate).getTime()>=new Date(fromdate).getTime()){
					var rows = row[i];
					rows.style.display = "";
				}
				else{
					var rows = row[i];
					rows.style.display = 'none';
				}
			}
			else if(todate!="" && fromdate==""){
				if(new Date(resDate).getTime()<=new Date(todate).getTime() && respatientFirst.includes(patientfirstInput)==true && 
						respatientLast.includes(patientlastInput)==true && resdoctorFirst.includes(doctorfirstInput)==true){
					var rows = row[i];
					rows.style.display = "";
				}
				else{
					var rows = row[i];
					rows.style.display = 'none';
				}
			}
			else if(todate!="" && fromdate!=""){
				if(new Date(resDate).getTime()>=new Date(fromdate).getTime() && new Date(resDate).getTime()<=new Date(todate).getTime()
						&& respatientFirst.includes(patientfirstInput)==true && respatientLast.includes(patientlastInput)==true &&
						resdoctorFirst.includes(doctorfirstInput)==true){
					var rows = row[i];
					rows.style.display = "";
				}
				else{
					var rows = row[i];
					rows.style.display = 'none';
				}
			}
			else{
				if(respatientFirst.includes(patientfirstInput)==true && respatientLast.includes(patientlastInput)==true &&
						resdoctorFirst.includes(doctorfirstInput)==true){
					var rows = row[i];
					rows.style.display = "";
				}
				else{
					var rows = row[i];
					rows.style.display = 'none';
				}
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