package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import Util.Constants;
import Util.Constants.PATIENTATTRIBUTES;

import dto.Patient;

public class PatientInformationManager {

	List<Patient> patients;

	public PatientInformationManager() {
		patients = new ArrayList<Patient>();
		patients.add(new Patient(1, "ab", "zxcxc", "ab@gmail.com", "9911223344", new Date(System.currentTimeMillis()),
				"sadasd asdAd dsd"));
		patients.add(new Patient(2, "abcd", "erqxcxc", "abcd@gmail.com", "9911556677", new Date(System
				.currentTimeMillis()), "sadasd asdAd dsd"));
		patients.add(new Patient(3, "cdefg", "axzcxc", "cdef@gmail.com", "9911112233", new Date(System
				.currentTimeMillis()), "sadasd asdAd dsd"));
		patients.add(new Patient(4, "popos", "zxsadaxc", "garzik@gmail.com", "9911998877", new Date(System
				.currentTimeMillis()), "sadasd asdAd dsd"));
		patients.add(new Patient(5, "pokos", "zsdaacxc", "ps@gmail.com", "9911225577", new Date(System
				.currentTimeMillis()), "sadasd asdAd dsd"));
		patients.add(new Patient(6, "mithun", "zasdc", "mithun@gmail.com", "9900221144", new Date(System
				.currentTimeMillis()), "sadasd asdAd dsd"));
		for(int i=0;i<30;i++) {
			patients.add(new Patient(i+7, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()+"@gmail.com", "9900221144", new Date(System
				.currentTimeMillis()), UUID.randomUUID().toString()));
		}
	}

	public List<Patient> searchPatient(PATIENTATTRIBUTES[] attr, Map<Constants.PATIENTATTRIBUTES, Object> patientInfo) {
		/*
		 * TODO call hibernate function
		 */
		List<Patient> patientsRetrieved = dummySearchPatient(attr, patientInfo);
		if (patientsRetrieved == null || patientsRetrieved.isEmpty()) {
			return null;
		}
		return patientsRetrieved;
	}

	private List<Patient> dummySearchPatient(PATIENTATTRIBUTES[] attr, Map<PATIENTATTRIBUTES, Object> patientInfo) {
		List<Patient> patientsRetrieved = new ArrayList<Patient>();
		Random r = new Random();
		for (int i = 0; i < patients.size(); i++) {
			if (r.nextInt(10) % 2 == 0) {
				patientsRetrieved.add(patients.get(i));
			}
		}
		return patientsRetrieved;
	}

	public Patient getPatientInformation(int patientId) {
		/*
		 * TODO call hibernate function
		 */
		return dummyGetPatient(patientId);
	}

	private Patient dummyGetPatient(int patientId) {
		for (Patient patient : patients) {
			if (patient.getPatientID() == patientId) {
				return patient;
			}
		}
		return null;
	}
}
