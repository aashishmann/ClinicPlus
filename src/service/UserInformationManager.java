package service;

import java.util.HashMap;
import java.util.Map;

import Util.Constants;

import dto.User;

public class UserInformationManager {

	Map<String, Map> userInfo = new HashMap<String, Map>();

	public UserInformationManager() {
		Map<Object, Object> user = new HashMap<Object, Object>();
		user.put(Constants.USERATTRIBUTES.USERNAME, "Mithun Raman");
		user.put(Constants.USERATTRIBUTES.USERROLES, new Constants.ROLES[] {
				Constants.ROLES.DOCTOR, Constants.ROLES.RECEPTIONIST });
		user.put(Constants.USERATTRIBUTES.DEFAULTROLE, Constants.ROLES.RECEPTIONIST);
		userInfo.put("mithunr@gmail.com", user);

		user = new HashMap<Object, Object>();
		user.put(Constants.USERATTRIBUTES.USERNAME, "Ojas Raman");
		user.put(Constants.USERATTRIBUTES.USERROLES, new Constants.ROLES[] {
				Constants.ROLES.DOCTOR, Constants.ROLES.ADMIN });
		user.put(Constants.USERATTRIBUTES.DEFAULTROLE, Constants.ROLES.ADMIN);
		userInfo.put("ojasr@gmail.com", user);
	}

	public User getUserInformation(String email) {
		Map<Object,Object> userMap = getDummyData(email);
		/*
		 * TODO Call Hibernate function
		 */
		if (userMap != null) {
			User user = new User();
			user.setUsername(userMap.get(Constants.USERATTRIBUTES.USERNAME)
					.toString());
			user.setRoles((Constants.ROLES[]) userMap
					.get(Constants.USERATTRIBUTES.USERROLES));
			user.setDefaultRole((Constants.ROLES) userMap
					.get(Constants.USERATTRIBUTES.DEFAULTROLE));
			return user;
		}
		return null;
	}

	private Map<Object, Object> getDummyData(String email) {
		Map<Object, Object> map = userInfo.get(email);
		return map;
	}
}
