<%@page import="Util.Constants"%>
<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="bootstrap/css/bootstrap.css" />
<title>Clinic Plus</title>
</head>
<body>
	<%
		User user = (User) session.getAttribute(Constants.USERINFO);
		if (user == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		session.setAttribute(Constants.CURRWORKSPACE, Constants.ROLES.RECEPTIONIST);
	%>
	<div>
		<%@ include file="/navbar.jsp"%>
	</div>
	<div class="container">
		<div>
			<ul class="nav nav-tabs">
				<li class="active"><a href="#home" data-toggle="tab">Home</a></li>
				<li><a href="#Appointment" data-toggle="tab">Appointment</a></li>
				<li><a href="#AddPatient" data-toggle="tab">Add Patient</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane fade in active" id="home">HOME</div>
				<div class="tab-pane fade" id="Appointment">
					<div class="row">
						<div class="col-md-6">
							<div style="padding-right: 2px;">
								<br><%@ include file="/Subpages/searchpatient.jsp"%>
							</div>
						</div>
						<div class="col-md-6">
							<div style="padding-left: 2px;">
								<br><%@ include file="/Subpages/appointment.jsp"%>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="AddPatient">dfdfdsaf</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</body>
</html>