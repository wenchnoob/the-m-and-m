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
	
	public Account getUserbyUsername(String username) {
		for (Account acc: users) {
			if (acc.getUsername().equals(username)) return acc;
		}
		return null; 
	}
	
	public University findUniversityByName(String name) {
		for (University uni: universities) {
			if (uni.getName().equals(name)) return uni;
		}
		return null;
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

