/**
 * 
 */
package com.cmc.controller;

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
 * @author Channa Kalsow
 *
 */
public class AdminFunctionalityController {


	private static AdminFunctionalityController db;

	private AdminFunctionalityController() {
	}
	
	/**
	 * singleton
	 * @author Channa, Kristiana, Wenchy
	 * @return an instance of itself
	 * */
	public static AdminFunctionalityController getInstance() {
		if (db == null) db = new AdminFunctionalityController();
		return db;
	}
	
	/**
	 * allows an admin to create a new user
	 * @author Channa, Kristiana, Wenchy
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param rQuestion
	 * @param rAnswer
	 * @param enabled
	 * @param type
	 * @return boolean
	 * */
	public boolean addUser(String firstName, String lastName, String username, String password, String rQuestion, String rAnswer,
			boolean  enabled, Account.AccountType type) {
		Account user;
		if(type == Account.AccountType.ADMIN) {
			user = new Admin(firstName, lastName, username, password, rQuestion, rAnswer, enabled);
		} else {
			Map<String, UserSchool> userSchools = new HashMap<String, UserSchool>();
			user = new User(firstName, lastName, username, password, rQuestion, rAnswer, enabled, userSchools);
		}
		return PsuedoDatabase.getInstance().save(user);
	}
	
	/**
	 * allows an admin to change a user's type
	 * @author Channa, Kristiana, Wenchy
	 * @param src
	 * @param targ
	 * @param type
	 * @return boolean
	 * */
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
	
	/**
	 * lists all universities in database
	 * @author Channa, Kristiana, Wenchy
	 * @return universities list
	 * */
	public List<University> viewAllUniversities() {
		List<University> universities = PsuedoDatabase.getInstance().getAllUniversities();
		return universities;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @return accounts list
	 * */
	public List<Account> viewAllAccounts(){
		List<Account> accounts = PsuedoDatabase.getInstance().getAllUsers();
		return accounts;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param src
	 * @param targ
	 * @param status
	 * @return boolean
	 * */
	public boolean ChangeStatus(Account src, Account targ, boolean status) {
		if (src.getType() != Account.AccountType.ADMIN) return false;
		
		targ.setEnabled(status);
		
		return true;
	}

}
