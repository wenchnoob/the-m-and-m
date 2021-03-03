/**
 * 
 */
package com.cmc.controller;

import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> 41cd86e917d7eba814ca1b353a565795b3d5f61f
import com.cmc.PsuedoDatabase;
import com.cmc.model.Account;
import com.cmc.model.University;

/**
 * @author ckalsow001
 *
 */
public class AdminFunctionalityController {
<<<<<<< HEAD
	
	private static AdminFunctionalityController self;
	
	private AdminFunctionalityController() {
		
	}

	public List<University> ViewAllUniversities(){
		List<University> uv = PsuedoDatabase.getInstance().getAllUniversities();
		return uv;
	}

	public List<Account> ViewAllUsers(){
		List<Account> usr = PsuedoDatabase.getInstance().getAllUsers();
		return usr;
	}

	public static AdminFunctionalityController getInstance() {
		if (self == null) self = new AdminFunctionalityController();
		return self;
=======
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
>>>>>>> 41cd86e917d7eba814ca1b353a565795b3d5f61f
	}
}
