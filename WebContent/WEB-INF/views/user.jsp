<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User</title>
	<link rel="stylesheet" type="text/css" href="resources/style/project.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	
	<%@ include file="navigation.jsp" %>
	
	<div class="tableDiv">
		<p class="adminP1">Current Users</p>
		<input type="text" id="firstInput" onkeyup="myFunction()" placeholder="Enter First Name.." title="Type in first name">
		<div id = "advanceDiv">
			<a type="button" id="advanceButton" onclick="advanceOptions()">Advance Search</a>
		</div>
		<div id="advanceSearch" style="display:none">
			<input type="text" id="lastInput" onkeyup="myFunction()" placeholder="Enter Last Name.." title="Type in last name">
			<input type="text" id="unameInput" onkeyup="myFunction()" placeholder="Enter Username.." title="Type in username name">
			<input type="text" id="emailInput" onkeyup="myFunction()" placeholder="Enter Email.." title="Type in email">
		</div>
		<table class="table" id="myTable">
			<tr>
				<th>User Id</th>
				<th>User Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Contact</th>
				<th>Email</th>
				<th>Address</th>
				<th>User Type</th>
				<th>Created By</th>
			</tr>
			<c:forEach items="${Users}" var="user">
			    <tr>
					<td><a href="${ pageContext.request.contextPath }/user/updateUser/${ user.userId }">${ user.userId }</a></td>
					<td id="uName">${ user.userName }</td>
					<td class="listR">${ user.firstName }</td>
					<td>${ user.lastName }</td>
					<td>${ user.contactNo }</td>
					<td>${ user.email }</td>
					<td>${ user.address }</td>
					<td>${ user.userType.description }</td>
					<td>${ user.createdBy.firstName }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="btn-container">
		<a class="link-user grow" href="${ pageContext.request.contextPath }/user/addUser">Add User</a>
	</div>
	<script>
	function myFunction(){
		var firstInput = document.getElementById("firstInput").value.toLowerCase();
		var lastInput = document.getElementById("lastInput").value.toLowerCase();
		var unameInput = document.getElementById("unameInput").value.toLowerCase();
		var emailInput = document.getElementById("emailInput").value.toLowerCase();
		var table = document .getElementById("myTable");
		var row = table.getElementsByTagName("tr");
		var tableRows = document.getElementById("myTable").rows.length;
		for(var i=1; i<tableRows;i++){
			var resFirst = document.getElementById("myTable").rows[i].cells.item(2).innerHTML.toLowerCase();
			var resLast = document.getElementById("myTable").rows[i].cells.item(3).innerHTML.toLowerCase();
			var resUname = document.getElementById("myTable").rows[i].cells.item(1).innerHTML.toLowerCase();
			var resEmail = document.getElementById("myTable").rows[i].cells.item(5).innerHTML.toLowerCase();
			if(resFirst.includes(firstInput)==true && resLast.includes(lastInput)==true && resUname.includes(unameInput)==true &&
					resEmail.includes(emailInput)==true){
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