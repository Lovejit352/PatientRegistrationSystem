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
	
	<div class="tableDetailsDiv">
		<p class="adminP1">Personal Information</p>
		<table class="tableDetails">
			<tr>
				<th colspan="4" style="font-size: 26px;">Details</th>
			</tr>
			<tr>
				<td class="labelAccount">Username</td>
				<td>${ username }</td>
				<td class="labelAccount">Firstname</td>
				<td>${ firstname }</td>
			</tr>
			<tr>
				<td class="labelAccount">Lastname</td>
				<td>${ lastname }</td>
				<td class="labelAccount">Contact</td>
				<td>${ contact }</td>
			</tr>
			<tr>
				<td class="labelAccount">Email</td>
				<td>${ email }</td>
				<td class="labelAccount">Address</td>
				<td>${ address }</td>
			</tr>
			<tr>
				<td class="labelAccount">Usertype</td>
				<td colspan="4">${ usertype }</td>
			</tr>
		</table>
	</div>
	<div class="btn-container">
		<a class="link-user grow" href="${ pageContext.request.contextPath }/myAccount/editAccount"><span>Edit Details</span></a>&nbsp;&nbsp;
		<a class="link-user grow" href="${ pageContext.request.contextPath }/myAccount/changePassword"><span>Change Password</span></a>
	</div>
</body>
</html>