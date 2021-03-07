/**
 * 
 */
package com.cmc.controller;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;

/**
 * @author wench
 *
 */
public class AccountController {
	
	private static AccountController self;

	/**
	 * 
	 */
	private AccountController() {
		
	}
	
	/**
	 * singleton
	 * @author Channa, Kristiana, Wenchy
	 * @return an instance of itself
	 * */
	public static AccountController getInstance() {
		if (self == null) self = new AccountController();
		return self;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param username
	 * @param password
	 * @return Account
	 * */
	public Account logon(String username, String password) {
		DBInteractions db = DBInteractions.getInstance();
		Account user = db.getUserByUserName(username);
		if (user == null) return null;
		if (user.isEnabled() && user.logon(password)) return user;
		return null;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param username
	 * @return boolean
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
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param username
	 * @return String
	 * */
	public Account viewAccount(String username) {
		return DBInteractions.getInstance().getUserByUserName(username);
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param src
	 * @param targ
	 * @param field
	 * @param value
	 * @return boolean
	 * */
	public boolean editBasicUserInfor(Account src, Account targ, ManagedField field, String value) {
		if (src.getType() != Account.AccountType.ADMIN && src != targ) return false;
		
		try {
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
		} catch (Exception ex) {
			/* If anything fails just give up and return false */
			return false;
		}
		
		return true;
	}
	//class that holds different types of managed information in AccountController
	public enum ManagedField {
		FIRSTNAME, LASTNAME, PASSWORD, RECOVERY_QUESTION, RECOVERY_ANSWER;
	}
	
}
