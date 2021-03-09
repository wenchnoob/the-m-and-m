/**
 * 
 */
package com.cmc.model;

import java.util.List;

/**
 * @author wench
 *
 */
public class University {
	
	private String name;
	// private Address address;
	private String state, location, control;
	private int numStudents, satMath, satVerbal, expenses, numOfApps, academicScale, socialScale, qualityLife;
	private List<String> emphases;
	private float perFemale, perFinAid, perAdmitted, perEnrolled;
	

	/**
	 * University constructor
	 * @author Channa, Kristiana, Wenchy
	 * @param name
	 * @param address
	 * @param location
	 * @param control
	 * @param numStudents
	 * @param satMath
	 * @param satVerbal
	 * @param expenses
	 * @param numOfApps
	 * @param academicScale
	 * @param socialScale
	 * @param emphases
	 * @param perFemale
	 * @param perFinAid
	 * @param perAdmitted
	 * @param perEnrolled
	 * @param qualityLife
	 * */
	public University(String name, String state, String location, String control,
			int numStudents, int satMath, int satVerbal, int expenses,
			int numOfApps, int academicScale, int socialScale, List<String> emphases, float perFemale,
			float perFinAid, float perAdmitted, float perEnrolled, int qualityLife) {
		
		this.name = name;
		// this.address = address;
		this.state = state;
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
		this.perFemale = perFemale;
		this.perAdmitted = perAdmitted;
		this.perFinAid = perFinAid;
		this.perEnrolled = perEnrolled;
		this.qualityLife = qualityLife;
		
	}
	
	/**
	 * toString method
	 * @author Channa, Kristiana, Wenchy
	 * @returns String
	 * */
	public String toString() {
		
		return name;

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
	public String getState() {
		return state;
	}


	/**
	 * @param address the address to set
	 */
	public void setState(String state) {
		this.state = state;
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


	/**
	 * @return the qualityLife
	 */
	public int getQualityLife() {
		return qualityLife;
	}


	/**
	 * @param qualityLife the qualityLife to set
	 */
	public void setQualityLife(int qualityLife) {
		this.qualityLife = qualityLife;
	}


	/**
	 * @return the perFemale
	 */
	public float getPerFemale() {
		return perFemale;
	}


	/**
	 * @param perFemale the perFemale to set
	 */
	public void setPerFemale(float perFemale) {
		this.perFemale = perFemale;
	}


	/**
	 * @return the perFinAid
	 */
	public float getPerFinAid() {
		return perFinAid;
	}


	/**
	 * @param perFinAid the perFinAid to set
	 */
	public void setPerFinAid(float perFinAid) {
		this.perFinAid = perFinAid;
	}


	/**
	 * @return the perAdmitted
	 */
	public float getPerAdmitted() {
		return perAdmitted;
	}


	/**
	 * @param perAdmitted the perAdmitted to set
	 */
	public void setPerAdmitted(float perAdmitted) {
		this.perAdmitted = perAdmitted;
	}


	/**
	 * @return the perEnrolled
	 */
	public float getPerEnrolled() {
		return perEnrolled;
	}


	/**
	 * @param perEnrolled the perEnrolled to set
	 */
	public void setPerEnrolled(float perEnrolled) {
		this.perEnrolled = perEnrolled;
	}
	
	

}
