/**
 * 
 */
package com.cmc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.University;
import com.cmc.model.User;
import com.cmc.model.UserSchool;
import com.cmc.model.Admin;

/**
 * Class intended for the handling of all administrative functionalities in the system.
 * @author Channa Kalsow
 */
public class AdminFunctionalityController {


	private static AdminFunctionalityController self;

	/**
	 * Private constructor to prevent construction of objects
	 * of this class from outside of this class.
	 */
	private AdminFunctionalityController() {}
	
	/**
	 * Static method to return a reference to a singleton instance of this class.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @return AdminFunctionalityController - A singleton instance of this class.
	 * */
	public static AdminFunctionalityController getInstance() {
		if (self == null) self = new AdminFunctionalityController();
		return self;
	}
	
	/**
	 * Allows an admin to create a new user.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param src - An account object pointing to the user that is trying to add a user.
	 * @param firstName - The intended first name for the created user.
	 * @param lastName - The intended last name for the created user.
	 * @param username - The intended username for the created user.
	 * @param password - The intended password for the created user.
	 * @param rQuestion - The intended recovery question for the created user.
	 * @param rAnswer - The intended recovery answer for the created user.
	 * @param enabled - The intended status for the created user.
	 * @param type	- The intended type of the user.
	 * 
	 * @return boolean - Whether the addition of a user was successful.
	 * */
	public boolean addUser(Account src, String firstName, String lastName, String username, String password, String rQuestion, String rAnswer,
			boolean  enabled, Account.AccountType type) {
		System.out.println("Start");
		if (src == null || firstName == null || lastName == null || username == null || password == null || rQuestion == null || rAnswer == null || type == null) return false;
		System.out.println("Checkpoint 1 reached");
		if (src.getType() != Account.AccountType.ADMIN) return false;
		System.out.println("Checkpoint 2 reached");
		if (firstName.equals("") || lastName.equals("") || username.equals("") || password.equals("") || rQuestion.equals("") || rAnswer.equals("")) return false;
		Account user;
		System.out.println("Checkpoint 3 reached");
		if(type == Account.AccountType.ADMIN) {
			user = new Admin(firstName, lastName, username, password, rQuestion, rAnswer, enabled);
			System.out.println("Checkpoint 4 (if) reached");
		} else {
			Map<String, UserSchool> userSchools = new HashMap<String, UserSchool>();
			user = new User(firstName, lastName, username, password, rQuestion, rAnswer, enabled, userSchools);
			System.out.println("Checkpoint 4 (else) reached");
		}
		return DBInteractions.getInstance().save(user);
	}
	
	/**
	 * Allows an admin to change a user's type.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param src - An account object pointing to the user that is trying to modify a user's type
	 * @param targ - The target user for the modification.
	 * @param type - The desired type for the target to be changed to.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean changeUserType(Account src, Account targ, Account.AccountType type) {
		if (src.getType() != Account.AccountType.ADMIN) return false;
		if (src.equals(targ)) return false;
		DBInteractions db = DBInteractions.getInstance();
		targ.setType(type);
		return db.save(targ);
	}
	
	/**
	 * Allows an admin to change a user's status.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param src - An account object pointing to the user that is trying to modify a user's type
	 * @param targ - The target user for the modification.
	 * @param status - The desired status for the target to be changed to.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean changeStatus(Account src, Account targ, boolean status) {
		if (src == targ) return false;
		if (src.getType() != Account.AccountType.ADMIN) return false;
		targ.setEnabled(status);
		return DBInteractions.getInstance().save(targ);
	}
	
	/**
	 * Returns an iterator view of all the universities currently in the database.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @return universities list - An iterator over all the Universities.
	 * */
	public List<University> viewAllUniversities() {
		return DBInteractions.getInstance().getAllUniversities();
	}
	
	/**
	 * Returns an iterator view of all the users currently in the database.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @return accounts list - An iterator over all the Accounts.
	 * */
	public List<Account> viewAllAccounts(){
		return DBInteractions.getInstance().getAllUsers();
	}
	

}
