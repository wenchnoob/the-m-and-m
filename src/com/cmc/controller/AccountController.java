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
		PsuedoDatabase db = new PsuedoDatabase();
		Account user = db.getUserbyUsername(username);
<<<<<<< HEAD
		if (user.logon(password)) return user;
=======
		// added a diff to help git
		if (user == null) return null;
		if (user.isEnabled() && user.logon(password)) return user;
>>>>>>> f9e584ae7c5efe7c9bfdb892888271d94268a6fb
		return null;
	}
	
}
