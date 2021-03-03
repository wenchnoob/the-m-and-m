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
		String name = ("User Information : \n\tName: " + user.getFirstName() + " " + user.getLastName() + "\n\t" +
				"UserName : " + user.getUsername() + "\n\t" + "Password : " + user.getPassword() + "\n\t" +
				"Recovery Question: " + user.getRecoveryQuestion() + "\n\t" + "User Type : " + user.getType()) + "\n\t" + "Enabled: " + user.isEnabled()
				+ "\n\t" + "LoggedOn: " + user.isLoggedOn();
		return name;
	}
	
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
		} catch (Exception ex) { /* If anything fails just give up and return false */}
		
		return true;
	}
	
	public enum ManagedField {
		FIRSTNAME, LASTNAME, PASSWORD, RECOVERY_QUESTION, RECOVERY_ANSWER;
	}
	
}
