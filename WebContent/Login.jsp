<%@page import="Util.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="bootstrap/css/bootstrap.css" />
<title>Login</title>
</head>
<body>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="well" action="login" method="post">
					<div class="form-group">
						<label>Clinic ID</label> <input type="text" class="form-control"
							placeholder="Clinic id" name="clinicID"
							<%if (request.getAttribute(Constants.CLINICID) != null) {%>
							value="<%=request.getAttribute(Constants.CLINICID)%><%}%>">
					</div>
					<div class="form-group">
						<label>Email address</label> <input type="text"
							class="form-control" placeholder="Email" name="email">
					</div>
					<div class="form-group">
						<label class="control-label">Password</label> <input
							type="password" class="form-control" placeholder="Password"
							name="password">
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
					<button type="submit" class="btn">Clear</button>
					<%
						if (request.getAttribute(Constants.IS_LOGINERROR) != null
								&& request.getAttribute(Constants.IS_LOGINERROR).toString()
										.equals("true")) {
					%>
					<font color="red">Invalid credentials</font>
					<%
						}
					%>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</body>
</body>

</html>