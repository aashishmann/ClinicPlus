package service;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
	Map<String, String> userMap = new HashMap<String, String>();

	public AuthenticationService() {
		userMap.put("mithunr@gmail.com", "pass");
		userMap.put("ojasr@gmail.com", "pass");
	}

	public boolean AuthenticateUser(String clinicID, String email,
			String password) {
		/*
		 * TODO
		 * Call hibernate function
		 */
		if (clinicID == null || email == null || password == null) {
			return false;
		}
		if (userMap.get(email) == null || !userMap.get(email).equals(password)) {
			return false;
		}
		return true;
	}
}
