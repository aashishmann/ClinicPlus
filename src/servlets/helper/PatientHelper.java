package servlets.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import service.PatientInformationManager;
import Util.Constants;
import Util.JSONUtils;
import dto.Patient;

public class PatientHelper {
	
	public JSONObject getPatientInformation(String patientId) {
		PatientInformationManager pim = new PatientInformationManager();
		JSONObject obj = new JSONObject();
		int intPatientId;
		try {
			intPatientId = Integer.parseInt(patientId);
			Patient patient = pim.getPatientInformation(intPatientId);
			if (patient == null) {
				obj.put("Result", "Info");
				obj.put("Reason",
						"Ooopss!! Could not find the patient. Try entering a different patient id or create a new patient.");
			} else {
				JSONUtils jsonUtils = new JSONUtils();
				obj.put("Result", "Success");
				obj.put("patientInfo", jsonUtils.jsonifyObject(patient));
			}
		} catch (Exception ex) {
			obj.put("Result", "Fail");
			obj.put("Reason", "Error: Please check patient id again.");
		}
		return obj;
	}

	public JSONObject SearchPatient(JSONObject jsonObject) {
		PatientInformationManager pim = new PatientInformationManager();
		JSONObject obj = new JSONObject();
		Class<?> clz = Constants.PATIENTATTRIBUTES.class;
		Object[] consts = clz.getEnumConstants();
		Map<Constants.PATIENTATTRIBUTES, Object> patientInfo = new HashMap<Constants.PATIENTATTRIBUTES, Object>();
		for (Object field : consts) {
			String value = (String) jsonObject.get(field.toString());
			if (value != null && value.trim() != "") {
				patientInfo.put((Constants.PATIENTATTRIBUTES) field, value);
			}
		}
		Constants.PATIENTATTRIBUTES[] attr = new Constants.PATIENTATTRIBUTES[] { Constants.PATIENTATTRIBUTES.PATIENTID,
				Constants.PATIENTATTRIBUTES.FIRSTNAME, Constants.PATIENTATTRIBUTES.LASTNAME,
				Constants.PATIENTATTRIBUTES.PATIENTEMAIL, Constants.PATIENTATTRIBUTES.CONTACTNUMBER };
		List<Patient> patientsResults = pim.searchPatient(attr, patientInfo);
		JSONUtils jsonUtils = new JSONUtils();
		if (patientsResults == null || patientsResults.isEmpty()) {
			obj.put("List", "Empty");
			return obj;
		} else {
			obj = jsonUtils.jsonifyList(patientsResults);
		}
		return obj;
	}
}
