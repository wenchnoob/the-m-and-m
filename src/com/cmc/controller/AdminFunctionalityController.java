/**
 * 
 */
package com.cmc.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import com.cmc.database.DBInteractions;
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
	public boolean addUser(Account src, String firstName, String lastName, String username, String password, String rQuestion, String rAnswer,
			boolean  enabled, Account.AccountType type) {
		if (src.getType() != Account.AccountType.ADMIN) return false;
		Account user;
		if(type == Account.AccountType.ADMIN) {
			user = new Admin(firstName, lastName, username, password, rQuestion, rAnswer, enabled);
		} else {
			Map<String, UserSchool> userSchools = new HashMap<String, UserSchool>();
			user = new User(firstName, lastName, username, password, rQuestion, rAnswer, enabled, userSchools);
		}
		return DBInteractions.getInstance().save(user);
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
		if (src == targ) return false;
		DBInteractions db = DBInteractions.getInstance();
		targ.setType(type);
		return db.save(targ);
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param src
	 * @param targ
	 * @param status
	 * @return boolean
	 * */
	public boolean changeStatus(Account src, Account targ, boolean status) {
		if (src == targ) return false;
		if (src.getType() != Account.AccountType.ADMIN) return false;
		targ.setEnabled(status);
		return DBInteractions.getInstance().save(targ);
	}
	
	/**
	 * lists all universities in database
	 * @author Channa, Kristiana, Wenchy
	 * @return universities list
	 * */
	public Iterator<University> viewAllUniversities() {
		return DBInteractions.getInstance().getAllUniversities().iterator();
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @return accounts list
	 * */
	public Iterator<Account> viewAllAccounts(){
		return DBInteractions.getInstance().getAllUsers().iterator();
	}
	

}
