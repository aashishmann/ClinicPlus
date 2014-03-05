package Util;

public class RoleURLMapper {
	public static String getURL(Constants.ROLES role) {
		switch (role) {
		case ADMIN:
			return "admin.jsp";
		case RECEPTIONIST:
			return "receptionist.jsp";
		case DOCTOR:
			return "doctor.jsp";
		case PHARMACIST:
			return "pharmacist.jsp";
		default:
			return "Login.jsp";
		}
	}
}
