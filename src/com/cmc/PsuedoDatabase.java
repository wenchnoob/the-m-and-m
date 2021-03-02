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
	
	private List<Account> users;
	private List<University> universities;

	/**
	 * 
	 */
	public PsuedoDatabase() {
		// TODO Auto-generated constructor stub
	}
	
	private void initUsers() {
		
	}
	
	private void initUniversities() {
		
	}

	
	public boolean getUsers() {
		return true;
	}
	public Account getUserbyUsername(String username) {
		for (Account acc: users) {
			if (acc.getName)
		}
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
		return users.add(toAdd);
	}
	public boolean save(University toAdd) {
		return universities.add(toAdd);
	}
	
	}

