/**
 * 
 */
package com.cmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cmc.model.Account;
import com.cmc.model.Address;
import com.cmc.model.Admin;
import com.cmc.model.University;
import com.cmc.model.User;
import com.cmc.model.UserSchool;


/**
 * @author wench
 *
 */
public class PsuedoDatabase {
	private static PsuedoDatabase db;
	
	private List<Account> users;
	private List<University> universities;

	/**
	 * 
	 */
	private PsuedoDatabase() {
		initUsers();
		initUniversities();
	}
	
	public static PsuedoDatabase getInstance() {
		if (db == null) db = new PsuedoDatabase();
		return db;
	}
	
	private void initUsers() {
		Map<String, UserSchool> map1 = new HashMap<String, UserSchool>();
		Map<String, UserSchool> map2 = new HashMap<String, UserSchool>();
		User testUser = new User("Channa", "Kalsow", "ckalsow", "Channaiskool",
				"What's your name?", "Channa", true,  map1);
		User testUser2 = new User("Kristiana", "Anderson", "kanderson", "Kristianaiskool",
				"What's Heather's cat's name?",
				"Ned", true, map2);
		Admin admin = new Admin("Wenchy", "Dutreuil", "admin", "admin",
				"What?", "Do you want?", true);		
		
		users = new ArrayList<>();
		users.add(testUser2);
		users.add(testUser);
		users.add(admin);
	}
	
	private void initUniversities() {
		List<String> emphases = new ArrayList<String>();
		emphases.add("Space Studies");
		emphases.add("Puppetry");
		emphases.add("Galaxy Business");
		emphases.add("Mars Gardening");
		University studentsRUs = new University("Uni A", new Address("Street", "City", "State", "Country", 12345)
				, "Mars", "private", 
				6, 200, 200, 1000000, 0, 1, 5, emphases,100.0f, 1.2f, 1.2f, 100.0f,5);
		
		University studentsRUs2 = new University("Uni C", new Address("Street", "City", "State", "Country", 12345)
				, "Mars", "private", 
				6, 200, 200, 1000000, 0, 1, 5, emphases,100.0f, 1.2f, 1.2f, 100.0f,5);
		
		University studentsRUs3 = new University("Uni J", new Address("Street", "City", "State", "Country", 12345)
				, "Mars", "private", 
				6, 200, 200, 1000000, 0, 1, 5, emphases,100.0f, 1.2f, 1.2f, 100.0f,5);
		
		
		
		universities = new ArrayList<>();
		universities.add(studentsRUs);
		universities.add(studentsRUs2);
		universities.add(studentsRUs3);
	}
	
	public Account getUserByUsername(String username) {
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
	
	
	public boolean save(Account toAdd) {
		return users.add(toAdd);
	}
	
	
	public boolean save(University toAdd) {
		return universities.add(toAdd);
	}
	
}

