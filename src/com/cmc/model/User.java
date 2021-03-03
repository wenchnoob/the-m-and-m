/**
 * 
 */
package com.cmc.model;

import java.util.Map;

import com.cmc.PsuedoDatabase;

public class User extends Account{
	private Map<String, UserSchool> savedSchools;
	
	/**
	 * 
	 */

	public User(String firstName, String lastName, String username,
			String password, String
			recoveryQuestion, String recoveryAnswer, boolean enabled, Map<String, UserSchool> savedSchools) {
		super(firstName, lastName, enabled, recoveryQuestion,
				recoveryAnswer, username, password, AccountType.BASIC_USER);
		this.savedSchools = savedSchools;
		
	}

	public boolean saveSchool(String schoolName) {
		if (schoolName == null) return false;
		University university = PsuedoDatabase.getInstance().findUniversityByName(schoolName);
		UserSchool userSchool = new UserSchool(university, this);
		if (university == null) return false;
		getSavedSchools().put(schoolName, userSchool);
		return true;
	}
	
	public boolean unsaveSchool(String schoolName) {
		return savedSchools.remove(schoolName) != null;
	}
	
	public Map<String, UserSchool> getSavedSchools() {
		return savedSchools;
	}
	
} 
