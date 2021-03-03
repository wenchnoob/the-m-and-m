/**
 * 
 */
package com.cmc.model;

import java.util.List;

/**
 * @author wench
 *
 */
public class Admin extends Account {

	/**
	 * 
	 */
	public Admin (String firstName, String lastName, boolean enabled, String
			recoveryQuestion, String recoveryAnswer, String username,
			String password) {
		super(firstName, lastName, enabled, recoveryQuestion,
				recoveryAnswer, username, password, AccountType.ADMIN);
	}

}
