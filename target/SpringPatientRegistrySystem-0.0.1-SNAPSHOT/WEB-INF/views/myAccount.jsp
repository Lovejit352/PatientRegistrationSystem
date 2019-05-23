<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>My Account</title>
	<link rel="stylesheet" type="text/css" href="resources/style/project.css">
	<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
</head>
<body>
	<%@ include file="navigation.jsp" %>
	
	<div class="tableDiv">
		<p class="adminP1">Personal Information</p>
		<table class="table">
			<tr>
				<th colspan="4" >Details</th>
			</tr>
			<tr>
				<td class="label">Username</td>
				<td>${ username }</td>
				<td class="label">Firstname</td>
				<td>${ firstname }</td>
			</tr>
			<tr>
				<td class="label">Lastname</td>
				<td>${ lastname }</td>
				<td class="label">Contact</td>
				<td>${ contact }</td>
			</tr>
			<tr>
				<td class="label">Email</td>
				<td>${ email }</td>
				<td class="label">Address</td>
				<td>${ address }</td>
			</tr>
			<tr>
				<td class="label">Usertype</td>
				<td colspan="4">${ usertype }</td>
			</tr>
		</table>
	</div>
	<div class="space"> </div>
	<div class="btn-container">
		<a class="link-user" href="${ pageContext.request.contextPath }/myAccount/editAccount">Edit Details</a>
		<a class="link-user" href="${ pageContext.request.contextPath }/myAccount/changePassword">Change Password</a>
	</div>
</body>
</html>