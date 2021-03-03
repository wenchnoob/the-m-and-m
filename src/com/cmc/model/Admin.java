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
	 * 
	 */
	public Admin (String firstName, String lastName, String username,
			String password, String recoveryQuestion, 
			String recoveryAnswer, boolean enabled) {
		super(firstName, lastName, enabled, recoveryQuestion,
				recoveryAnswer, username, password, AccountType.ADMIN);
	}

}
