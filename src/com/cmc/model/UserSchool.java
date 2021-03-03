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
	private User owner;
	
	/**
	 * 
	 */
	public UserSchool(University university, User owner) {
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
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	

}
