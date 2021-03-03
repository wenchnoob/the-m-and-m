/**
 * 
 */
package com.cmc.model;

/**
 * @author wench
 *
 */
public class Admin extends Account {

	/**
	 * Admin constructor
	 * @author Channa, Kristiana, Wenchy
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param recoveryQuestion
	 * @param recoveryAnswer
	 * @param enabled
	 * */
	public Admin (String firstName, String lastName, String username,
			String password, String recoveryQuestion, 
			String recoveryAnswer, boolean enabled) {
		super(firstName, lastName, enabled, recoveryQuestion,
				recoveryAnswer, username, password, AccountType.ADMIN);
	}

}
