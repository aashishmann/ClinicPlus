<%@page import="Util.RoleURLMapper"%>
<%@page import="Util.Constants"%>
<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	User navUser = (User) session.getAttribute(Constants.USERINFO);
	if (navUser == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<nav class="navbar navbar-default  navbar-static-top" role="navigation">
	<div class="container">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="#"><img src="Images/logo.jpeg" height="50px"></a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Workspace <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<%
								Constants.ROLES[] roles = navUser.getRoles();
								Constants.ROLES currentWorkspace = (Constants.ROLES) session.getAttribute(Constants.CURRWORKSPACE);
								for (int i = 0; i < roles.length; i++) {
							%>
							<li <%=currentWorkspace == roles[i] ? "class=\"active\"" : ""%>><a
								href="<%=RoleURLMapper.getURL(roles[i])%>"><%=roles[i].toString()%></a></li>
							<%
								}
							%>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><%=navUser.getUsername()%></span><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Settings</a></li>
							<li><a href="#">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</nav>