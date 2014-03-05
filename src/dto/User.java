package dto;

import Util.Constants;

public class User {
	private String username;
	private Constants.ROLES[] roles;
	private Constants.ROLES defaultRole;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Constants.ROLES[] getRoles() {
		return roles;
	}

	public void setRoles(Constants.ROLES[] roles) {
		this.roles = roles;
	}

	public Constants.ROLES getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(Constants.ROLES defaultRole) {
		this.defaultRole = defaultRole;
	}
}
