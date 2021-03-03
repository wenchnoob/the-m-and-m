/**
 * 
 */
package com.cmc.controller;

import com.cmc.PsuedoDatabase;
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
		// TODO Auto-generated constructor stub
	}
	
	public static AccountController getInstance() {
		if (self == null) self = new AccountController();
		return self;
	}
	
	public Account logon(String username, String password) {
		PsuedoDatabase db = PsuedoDatabase.getInstance();
		Account user = db.getUserbyUsername(username);
		if (user == null) return null;
		if (user.isEnabled() && user.logon(password)) return user;
		return null;
	}
	
	public String viewAccount(Account user) {
		String name = ("User Information : " + user.getFirstName() + " " + user.getLastName() + "\n" +
				"UserName : " + user.getUsername() + "\n" + "Password : " + user.getPassword() + "\n" +
				"Recovery Question: " + user.getRecoveryQuestion() + "\n" + "User Type : " + user.getAccountType());
		return name;
	}
	
	public boolean editBasicUserInfor(Account src, Account targ, ManagedField field, Object value) {
		if (src.getType() != Account.AccountType.ADMIN || src != targ) return false;
		
		try {
			switch (field) {
				case FIRSTNAME:
					targ.setFirstName((String)value);
					break;
				case LASTNAME:
					targ.setLastName((String)value);
					break;
				case PASSWORD:
					targ.setPassword((String)value);
				case RECOVERY_QUESTION:
					targ.setRecoveryQuestion((String)value);
				case RECOVERY_ANSWER:
					targ.setRecoveryAnswer((String)value);
				default:
			}
		} catch (Exception ex) { /* If anything fails just give up and return false */}
		
		return true;
	}
	
	public enum ManagedField {
		FIRSTNAME, LASTNAME, PASSWORD, RECOVERY_QUESTION, RECOVERY_ANSWER;
	}
	
}
