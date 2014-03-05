package Util;

public class Constants {
	/* Used by the different scopes */
	public static String IS_AUTHENTICATED = "is_auth";
	public static String IS_LOGINERROR = "is_loginerror";
	public static String CLINICID = "clinicid";
	public static String USERINFO = "userinfo";
	public static String CURRWORKSPACE = "currworkspace";

	public static enum ROLES {
		RECEPTIONIST("Receptionist"), DOCTOR("Doctor"), PHARMACIST("Pharmacist"), ADMIN("Admin");
		private String role;

		private ROLES(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return role;
		}
	};

	public static enum USERATTRIBUTES {
		USERNAME("username"), USERROLES("roles"), DEFAULTROLE("defaultRole");
		private String attribute;

		private USERATTRIBUTES(String attribute) {
			this.attribute = attribute;
		}

		@Override
		public String toString() {
			return attribute;
		}
	};

	public static enum PATIENTATTRIBUTES {
		PATIENTID("patientId"), FIRSTNAME("firstName"), LASTNAME("lastName"), CONTACTNUMBER("contactNumber"), PATIENTEMAIL(
				"patientEmail"), DOB("dob"), ADDRESS("address");
		private String attribute;

		private PATIENTATTRIBUTES(String attribute) {
			this.attribute = attribute;
		}

		@Override
		public String toString() {
			return attribute;
		}
	};
}
