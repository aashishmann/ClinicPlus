package dto;

import java.sql.Date;

public class Patient {
	
	private int patientID;
	private String firstName;
	private String lastName;
	private String emailID;
	private String contactNumber;
	private Date dob;
	private String address; 

	public Patient(int patientID, String firstName, String lastName, String emailID, String contactNumber, Date dob,
			String address) {
		this.patientID = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.contactNumber = contactNumber;
		this.dob = dob;
		this.address = address;
	}

	public Patient() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
}
