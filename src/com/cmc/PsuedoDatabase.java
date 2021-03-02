/**
 * 
 */
package com.cmc;

import java.util.List;

import model.Account;
import model.University;
import model.User;

/**
 * @author wench
 *
 */
public class PsuedoDatabase {

	/**
	 * 
	 */
	public PsuedoDatabase() {
		// TODO Auto-generated constructor stub
	}

	
	public boolean getUsers() {
		return true;
	}
	public Account getUserbyUsername(String username) {
		return account; 
	}
	public University findUniversityByName(String name) {
		return university;
	}
	public List<Account> getAllUsers() {
		return users;
	}
	public List<University> getAllUniversities() {
		return universities;
	}
	public boolean save(User toAdd) {
		return true;
	}
	public boolean save(University toAdd) {
		return true;
	}
	
	}

