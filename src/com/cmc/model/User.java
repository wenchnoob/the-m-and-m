/**
 * 
 */
package com.cmc.model;

/**
 * @author Channa Kalsow and Kristiana Anderson
 *
 */
import java.util.List;
import java.util.Map;

import com.cmc.PsuedoDatabase;

public class User extends Account{
	private Map<String, UserSchool> savedSchools;
	
	/**
	 * 
	 */
	public User(String firstName, String lastName, boolean enabled, String
			recoveryQuestion, String recoveryAnswer, String username,
			String password, Map<String, UserSchool> savedSchools) {
		super(firstName, lastName, enabled, recoveryQuestion,
				recoveryAnswer, username, password, AccountType.BASIC_USER);
		this.savedSchools = savedSchools;
		
	}

	public void saveSchool(String schoolName, User owner) {
		University university = PsuedoDatabase.getInstance().findUniversityByName(schoolName);
		UserSchool userSchool = new UserSchool(university, owner);
		//if (schoolName == null) "University not found";
		getSavedSchools().put(schoolName, userSchool);
	}
	public void unsaveSchool(String schoolName) {
		//TODO
	}
	
	public Map<String, UserSchool> getSavedSchools() {
		return savedSchools;
	}
	
} 
