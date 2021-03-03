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
	}
}
