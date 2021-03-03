/**
 * 
 */
package com.cmc.controller;

import java.util.List;

import com.cmc.PsuedoDatabase;
import com.cmc.model.Account;
import com.cmc.model.University;

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
	
	public List<University> viewAllUniversities() {
		List<University> universities = PsuedoDatabase.getInstance().getAllUniversities();
		return universities;
	}
	
	public List<Account> viewAllAccounts(){
		List<Account> accounts = PsuedoDatabase.getInstance().getAllUsers();
		return accounts;
	}
}
