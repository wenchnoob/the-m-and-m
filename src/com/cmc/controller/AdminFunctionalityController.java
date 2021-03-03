/**
 * 
 */
package com.cmc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmc.PsuedoDatabase;
import com.cmc.model.Account;
import com.cmc.model.University;
import com.cmc.model.User;
import com.cmc.model.UserSchool;
import com.cmc.model.Admin;

/**
 * @author ckalsow001
 *
 */
public class AdminFunctionalityController {


	private static AdminFunctionalityController db;

	private AdminFunctionalityController() {
	}

	public static AdminFunctionalityController getInstance() {
		if (db == null) db = new AdminFunctionalityController();
		return db;
	}
	
	public boolean addUser(String firstName, String lastName, String username, String password, String rQuestion, String rAnswer, boolean  enabled, Account.AccountType type) {
		Account user;
		if(type == Account.AccountType.ADMIN) {
			user = new Admin(firstName, lastName, username, password, rQuestion, rAnswer, enabled);
		} else {
			Map<String, UserSchool> userSchools = new HashMap<String, UserSchool>();
			user = new User(firstName, lastName, username, password, rQuestion, rAnswer, enabled, userSchools);
		}
		return PsuedoDatabase.getInstance().save(user);
	}
	
	public boolean changeUserType(Account src, Account targ, Account.AccountType type) {
		if (src.getType() != Account.AccountType.ADMIN) return false;
		
		PsuedoDatabase db = PsuedoDatabase.getInstance();
		db.remove(targ);
		
		Account user;
		if (type == Account.AccountType.ADMIN) {
			user = new Admin(targ.getFirstName(), targ.getLastName(), targ.getUsername(),
					targ.getPassword(), targ.getRecoveryQuestion(), targ.getRecoveryAnswer(),
					targ.isEnabled());
		} else {
			user = new User(targ.getFirstName(), targ.getLastName(), targ.getUsername(),
					targ.getPassword(), targ.getRecoveryQuestion(), targ.getRecoveryAnswer(),
					targ.isEnabled(), new HashMap<String, UserSchool>());
		}

		if (targ.isEnabled()) user.logon(user.getPassword());
		
		if (db.save(user)) {
			return true;
		} else {
			db.save(targ);
			return false;
		}
	}
	
	
	public boolean changeType() {
		return true;
	}

	public List<University> viewAllUniversities() {
		List<University> universities = PsuedoDatabase.getInstance().getAllUniversities();
		return universities;
	}

	public List<Account> viewAllAccounts(){
		List<Account> accounts = PsuedoDatabase.getInstance().getAllUsers();
		return accounts;
	}

	public boolean ChangeStatus(Account src, Account targ, boolean status) {
		if (src.getType() != Account.AccountType.ADMIN) return false;
		
		targ.setEnabled(status);
		
		return true;
	}

}
