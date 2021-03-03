/**
 * 
 */
package com.cmc.controller;

import java.util.ArrayList;
import java.util.List;
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
			List<UserSchool> userSchools = new ArrayList<>();
			user = new User(firstName, lastName, username, password, rQuestion, rAnswer, enabled, userSchools);
		}
		return PsuedoDatabase.getInstance().save(user);
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
