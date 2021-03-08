package com.cmc.database;

import java.util.*;
import java.util.stream.Collectors;

import com.cmc.model.*;
import csb.sju.csci.*;

public class DBInteractions {
	
	private static DBInteractions self;
	private  UniversityDBLibrary db;
	
	private DBInteractions() {
		// Initializer for All
		// Uncomment the below line if you are in horizon view.
		// db = new UniversityDBLibrary("megatherium", "csci230");
		
		// Initializer for Wenchy (Comment out if you are not wenchy)
		db = new UniversityDBLibrary("jdbc:mysql://localhost:3306/megatherium", "cmc", "pleasejustwork!");
	}
	
	public static DBInteractions getInstance() {
		if (self == null) self = new DBInteractions();
		return self;
	}
	
	public List<Account> getAllUsers() {
		String[][] allUsersData = db.user_getUsers();
		List<Account> allUsers = new ArrayList<Account>();
		
		for (String[] userData: allUsersData) {
			String firstName = userData[0];
			String lastName = userData[1];
			String username = userData[2];
			String password = userData[3];
			String type = userData[4];
			boolean status = userData[5].equals("Y")? true : false;
			
			if (type.equals("u")) {
				allUsers.add(new User(firstName, lastName, username, password, "", "", 
						status, null));
			} else {
				allUsers.add(new Admin(firstName, lastName, username, password, "", "", 
						status));
			}
		}
		
		loadUserSchools(allUsers);
		return allUsers;
	}
	
	private void loadUserSchools(List<Account> users) {
		String[][] usersAndSchools = db.user_getUsernamesWithSavedSchools();
		Map<String, Map<String, UserSchool>> mapping = mapify(usersAndSchools);
		
		users.forEach(user -> {
			String username = user.getUsername();
			if (mapping.containsKey(username)) ((User)user).setSavedSchools(mapping.get(username));
		});
	}
	
	private Map<String, Map<String, UserSchool>> mapify(String[][] userAndSchools) {
		Map<String, Map<String, UserSchool>> userToSchools = new HashMap<String,  Map<String, UserSchool>>();
		Map<String, University> universities = getAllUniversities()
				.stream()
				.collect(Collectors.toConcurrentMap(uni -> uni.getName(), uni -> uni));
		
		if (userAndSchools == null) return userToSchools;
		
		for (int i = 0; i < userAndSchools.length; i++) {
			String user = userAndSchools[i][0];
			String universityName = userAndSchools[i][1];
			UserSchool school = new UserSchool(universities.get(universityName), user);
			
			if (userToSchools.containsKey(user)) {
				userToSchools.get(user).put(universityName, school);
			} else {
				userToSchools.put(user, new HashMap<String, UserSchool>() {
					private static final long serialVersionUID = 1L;
					{
						put(universityName, school);
					}	
				});
			}
		}
		
		return userToSchools;
	}
	
	public Account getUserByUserName(String username) {
		for (Account user: getAllUsers()) {
			if (user.getUsername().equals(username)) return user;
		}
		return null;
	}
	
	public boolean save(Account toSave) {
		String firstName = toSave.getFirstName();
		String lastName = toSave.getLastName();
		String username = toSave.getUsername();
		String password = toSave.getPassword();
		char type = toSave.getClass() == User.class ? 'u': 'a';
		char enabled = toSave.isEnabled() ? 'Y' : 'N';
		
		boolean success = true;
		
		if (db.user_addUser(firstName, lastName, username, password, type) <= 0) {
			success = db.user_editUser(username, firstName, lastName, password, type, enabled) > 0;
		}
		
		if (type == 'u') saveUserSchools((User)toSave);
		
		return success;
	}
	
	private void saveUserSchools(User user) {
		if (user.getSavedSchools() == null) return;
		user.getSavedSchools().forEach((k, v) -> db.user_saveSchool(user.getUsername(), k));
	}
	
	public boolean remove(Account toRemove) {
		return db.user_deleteUser(toRemove.getUsername()) > 0;
	}
	
	// TODO
	public List<University> getAllUniversities() {
		List<University> universities = new ArrayList<>();
		loadEmphases(universities);
		return universities;
	}
	
	// TODO
	private void loadEmphases(List<University> universities) {
		
	}
	
	// TODO
	public University getUniversityByName(String name) {
		return null;
	}
	
	// TODO
	public boolean save(University toSave) {
		return true;
	}
	
	// TODO
	public boolean remove(University toRemove) {
		return true;
	}
	

}
