/**
 * 
 */
package com.cmc;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.University;
import model.User;
import model.UserSchool;


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
		List<UserSchool> list1 = new ArrayList<UserSchool>();
		List<UserSchool> list2 = new ArrayList<UserSchool>();
		User testUser = new User("Channa", "Kalsow", true, "What's your name?",
				"Channa", "ckalsow", "Channaiskool", list1);
		User testUser2 = new User("Kristiana", "Anderson", true, "What's Heather's cat's name?",
				"Ned", "kanderson", "Kristianaiskool", list2);
		users = new ArrayList<>();
		users.add(testUser2);
		users.add(testUser);
	}
	
	private void initUniversities() {
		List<String> emphases = new List<String>();
		emphases.add("Space Studies");
		emphases.add("Puppetry");
		emphases.add("Galaxy Business");
		emphases.add("Mars Gardening");
		University studentsRUs = new University("123 ABC Street", "Mars", "private", 
				6, 100.0f, 200, 200, 1000000, 0, 500, 1.2f, 1, 5, emphases);
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

