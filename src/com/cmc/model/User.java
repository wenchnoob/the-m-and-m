/**
 * 
 */
package com.cmc.model;

import java.util.Map;

import com.cmc.database.DBInteractions;

public class User extends Account{
	private Map<String, UserSchool> savedSchools;
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param recoveryQuestin
	 * @param recoveryAnswer
	 * @param enabled
	 * @param savedSchools
	 * */
	public User(String firstName, String lastName, String username,
			String password, String recoveryQuestion, String recoveryAnswer,
			boolean enabled, Map<String, UserSchool> savedSchools) {
		super(firstName, lastName, enabled, recoveryQuestion,
				recoveryAnswer, username, password, AccountType.BASIC_USER);
		this.savedSchools = savedSchools;
		
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param schoolName
	 * @return boolean
	 * */
	public boolean saveSchool(String schoolName) {
		if (schoolName == null) return false;
		University university = DBInteractions.getInstance().getUniversityByName(schoolName);
		if (university == null) return false;
		UserSchool userSchool = new UserSchool(university, username);
		savedSchools.put(schoolName, userSchool);
		return true;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param schoolName
	 * @return boolean
	 * */
	public boolean unsaveSchool(String schoolName) {
		return savedSchools.remove(schoolName) != null;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @return savedSchools map
	 * */
	public Map<String, UserSchool> getSavedSchools() {
		return savedSchools;
	}
	
	
	/**
	 * sets the saved schools for this user
	 * @author wench
	 * */
	public void setSavedSchools(Map<String, UserSchool> savedSchools) {
		this.savedSchools = savedSchools;
	}
	
} 
