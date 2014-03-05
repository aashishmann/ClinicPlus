<%@page import="Util.Constants"%>
<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="js/clinicAjax/searchPatient.js"></script>
<%
	User spUser = (User) session.getAttribute(Constants.USERINFO);
	if (spUser == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<div class="row">
	<form class="form-horizontal well" role="form" id="spForm">
		<div class="form-group">
			<label class="col-sm-2 control-label">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Patient id"
					id="spPatientId">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Patient email"
					id="spPatientEmail">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Name</label>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					placeholder="Patient first name" id="spPatientFName">
			</div>
			<div class="col-sm-5">
				<input type="text" class="form-control"
					placeholder="Patient last name" id="spPatientLName">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Contact</label>
			<div class="col-sm-10">
				<input type="text" class="form-control"
					placeholder="Patient contact" id="spPatientContact">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button class="btn btn-primary" id="spSearch">Search</button>
			</div>
			<div class="col-sm-2">
				<button class="btn btn-default" id="spClear">Clear</button>
			</div>
		</div>
	</form>
</div>
<div class="row" id="spResults">
	<div id="spResultPanel">
		<div class="panel-heading">Results</div>
		<div class="panel-body" id="spResultBody"></div>
	</div>
</div>
<div class="alert alert-danger" id="spAlert1"></div>