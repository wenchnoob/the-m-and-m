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
		// added a diff to help git
		if (user == null) return null;
		if (user.isEnabled() && user.logon(password)) return user;
		return null;
	}

}
