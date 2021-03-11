/**
 * 
 */
package com.cmc.model;

/**
 * @author wench
 *
 */
public class UserSchool {

	private University university;
	private String owner;
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param university
	 * @param owne
	 * */
	public UserSchool(University university, String owner) {
		this.university = university;
		this.owner = owner;
	}

	/**
	 * @return the university
	 */
	public University getUniversity() {
		return university;
	}

	/**
	 * @param the university to set
	 */
	public void setUniversity(University university) {
		this.university = university;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	//toString method
	public String toString() {
		return university.toString() + "   " + owner.toString();
	}
	

}
