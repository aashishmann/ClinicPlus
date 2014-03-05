<%@page import="Util.Constants"%>
<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="js/clinicAjax/appointment.js"></script>
<%
	User appUser = (User) session.getAttribute(Constants.USERINFO);
	if (appUser == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<div class="row">
	<div class="panel panel-primary" id="appPanelPatientInfo">
		<div class="panel-heading">
			<h3 class="panel-title">Patient Information</h3>
		</div>
		<div class="panel-body">
			<div class="form-group row">
				<div class="col-sm-6">
					<input type="text" class="form-control" placeholder="Patient id"
						id="appPatientId">
				</div>
			</div>
			<div id="appPatientDetails">
				<div class="row">
					<hr>
				</div>
				<div class="row">
					<label class="col-sm-3">Patient Id</label> <label class="col-sm-7"
						id="appPatientLabelId">-</label>
				</div>
				<div class="row">
					<label class="col-sm-3">Email</label> <label class="col-sm-7"
						id="appEmailId">-</label>
				</div>
				<div class="row">
					<label class="col-sm-3">Name</label> <label class="col-sm-3"
						id="appPatientFName">-</label><label class="col-sm-4"
						id="appPatientLName">-</label>
				</div>
				<div class="row">
					<label class="col-sm-3">Contact</label> <label class="col-sm-7"
						id="appPatientContact">-</label>
				</div>
				<div class="row">
					<label class="col-sm-3">Date Of Birth</label> <label
						class="col-sm-7" id="appPatientDOB">-</label>
				</div>
				<div class="row">
					<label class="col-sm-3">Address</label> <label class="col-sm-7"
						id="appPatientAddress">-</label>
				</div>
				<div class="row">
					<hr>
				</div>
				<div class="row">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">Charge</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="Charge"
									id="appPatientAppointment">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Doctor</label>
							<div class="col-sm-9">
								<select class="form-control">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<button class="btn btn-primary" id="appintment">Give
				Appointment</button>
			<button class="btn btn-primary" id="appLoad">Load Details</button>
			<button class="btn btn-default" id="appClear">Clear</button>
		</div>
	</div>
</div>
<div class="alert" id="appAlert1"></div>