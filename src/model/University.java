/**
 * 
 */
package model;

import java.util.List;

/**
 * @author wench
 *
 */
public class University {
	
	private String name;
	private Address address;
	private String location, control;
	private int numStudents, satMath, satVerbal, expenses, numOfApps, academicScale, socialScale;
	private List<String> emphases;
	

	/**
	 * 
	 */
	public University(String name, Address address, String location, String control, int numStudents, int satMath, int satVerbal, int expenses,
			int numOfApps, int academicScale, int socialScale, List<String> emphases) {
		this.name = name;
		this.address = address;
		this.location = location;
		this.control = control;
		this.numStudents = numStudents;
		this.satMath = satMath;
		this.satVerbal = satVerbal;
		this.expenses = expenses;
		this.numOfApps = numOfApps;
		this.academicScale = academicScale;
		this.socialScale = socialScale;
		this.emphases = emphases;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	/**
	 * @return the control
	 */
	public String getControl() {
		return control;
	}


	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}


	/**
	 * @return the numStudents
	 */
	public int getNumStudents() {
		return numStudents;
	}


	/**
	 * @param numStudents the numStudents to set
	 */
	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}


	/**
	 * @return the satMath
	 */
	public int getSatMath() {
		return satMath;
	}


	/**
	 * @param satMath the satMath to set
	 */
	public void setSatMath(int satMath) {
		this.satMath = satMath;
	}


	/**
	 * @return the satVerbal
	 */
	public int getSatVerbal() {
		return satVerbal;
	}


	/**
	 * @param satVerbal the satVerbal to set
	 */
	public void setSatVerbal(int satVerbal) {
		this.satVerbal = satVerbal;
	}


	/**
	 * @return the expenses
	 */
	public int getExpenses() {
		return expenses;
	}


	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(int expenses) {
		this.expenses = expenses;
	}


	/**
	 * @return the numOfApps
	 */
	public int getNumOfApps() {
		return numOfApps;
	}


	/**
	 * @param numOfApps the numOfApps to set
	 */
	public void setNumOfApps(int numOfApps) {
		this.numOfApps = numOfApps;
	}


	/**
	 * @return the academicScale
	 */
	public int getAcademicScale() {
		return academicScale;
	}


	/**
	 * @param academicScale the academicScale to set
	 */
	public void setAcademicScale(int academicScale) {
		this.academicScale = academicScale;
	}


	/**
	 * @return the socialScale
	 */
	public int getSocialScale() {
		return socialScale;
	}


	/**
	 * @param socialScale the socialScale to set
	 */
	public void setSocialScale(int socialScale) {
		this.socialScale = socialScale;
	}


	/**
	 * @return the emphases
	 */
	public List<String> getEmphases() {
		return emphases;
	}


	/**
	 * @param emphases the emphases to set
	 */
	public void setEmphases(List<String> emphases) {
		this.emphases = emphases;
	}
	
	

}
