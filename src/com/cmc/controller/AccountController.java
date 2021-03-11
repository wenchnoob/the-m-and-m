/**
 * 
 */
package com.cmc.controller;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;

/**
 * Class intended for the handling of all basic and shared account processes.
 * @author wench
 */
public class AccountController {

	private static AccountController self;

	/**
	 * Private constructor to prevent construction of objects
	 * of this class from outside of this class.
	 */
	private AccountController() {}

	/**
	 * Static method to return a reference to a singleton instance of this class.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @return AccountController - An singleton instance of this class.
	 * */
	public static AccountController getInstance() {
		if (self == null) self = new AccountController();
		return self;
	}

	/**
	 * Returns the account object associated with a successful login.
	 * Returns null if the login fails.
	 * Checks that the username and the password are valid matches
	 * and that the user is enabled.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param username - The username of the user to be logged in to.
	 * @param password - The password of the user to be logged in to.
	 * 
	 * @return Account - The account that is logged in. Null if login fails.
	 * */
	public Account logon(String username, String password) {
		DBInteractions db = DBInteractions.getInstance();
		Account user = db.getUserByUserName(username);
		if (user == null) return null;
		if (user.isEnabled() && user.logon(password)) {
			db.save(user);
			return user;
		}
		return null;
	}

	/**
	 * Sets a users status to logged out.
	 * If a non-existent username is passed, nothing occurs.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param username - The target user to logout of.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean logout(String username) {
		DBInteractions db = DBInteractions.getInstance();
		Account user = db.getUserByUserName(username);
		if (user == null) return false;
		if (user.isEnabled()) return user.logout();
		db.save(user);
		return false;
	}

	/**
	 * Returns an account object that represents the user name that was passed in.
	 * If the username does not exist in the database, then a null reference is returned.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param username - The username of the user to be viewed.
	 * 
	 * @return Account - The account object that will be displayed.
	 * */
	public Account viewAccount(String username) {
		return DBInteractions.getInstance().getUserByUserName(username);
	}

	/**
	 * Allows for the modification of basic user info if the src is an admin or
	 * if the src is the target. Allows the user to specifiy which field to modify
	 * and to what.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param src - The user attempting to modify another user's information.
	 * @param targ - The user being targeted for modification.
	 * @param field - The attribute that is being targeted for modification.
	 * @param value - The desired value for the targeted field to be changed to.
	 * 
	 * @return boolean - Whether the operation was successful or not.
	 * */
	public boolean editBasicUserInfo(Account src, Account targ, ManagedField field, String value) {
		if (src.getType() != Account.AccountType.ADMIN && src != targ) return false;
		if (value.length() <= 0) return false;

		DBInteractions db = DBInteractions.getInstance();
		switch (field) {
			case FIRSTNAME:
				targ.setFirstName(value);
				break;
			case LASTNAME:
				targ.setLastName(value);
				break;
			case PASSWORD:
				targ.setPassword(value);
			case RECOVERY_QUESTION:
				targ.setRecoveryQuestion(value);
			case RECOVERY_ANSWER:
				targ.setRecoveryAnswer(value);
		}
		
		db.save(targ);
		return true;
	}

	/**
	 * Enum class that holds an identifier for the different types of managed information in AccountController.
	 * */
	public enum ManagedField {
		FIRSTNAME, LASTNAME, PASSWORD, RECOVERY_QUESTION, RECOVERY_ANSWER;
	}

}
