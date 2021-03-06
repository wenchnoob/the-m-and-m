package database;

import java.util.*;
import com.cmc.model.*;

public class DBInteractions {
	
	private static DBInteractions self;
	private  UniversityDBLibrary db;
	
	private DBInteractions() {
		// Initializer for All
		db = new UniversityDBLibrary("jdbc:mysql://devsrv.cs.csbsju.edu/megatherium", "megatherium", "csci230");
		// Initializer for Wenchy
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
			String status = userData[5];
			
			if (type.equals("u")) {
				allUsers.add(new User(firstName, lastName, username, password, "", "", 
						status.equals("Y")? true : false, null));
			} else {
				allUsers.add(new Admin(firstName, lastName, username, password, "", "", 
						status.equals("Y")? true : false));
			}
		}
		
		loadUserSchools(allUsers);
		return allUsers;
	}
	
	// Need the load of university data to be implemented before implementing this functionality
	private void loadUserSchools(List<Account> users) {
		String[][] usersAndSchools = db.user_getUsernamesWithSavedSchools();
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
		
		if (db.user_addUser(firstName, lastName, username, password, type) == -1) {
			db.user_editUser(username, firstName, lastName, password, type, enabled);
		}
		
		if (type == 'a') saveUserSchools((User)toSave);
		
		return true;
	}
	
	private void saveUserSchools(User user) {
		String userName = user.getUsername();
		user.getSavedSchools().forEach((k, v) -> db.user_saveSchool(userName, k));
	}
	
	public boolean remove(Account toRemove) {
		return db.user_deleteUser(toRemove.getUsername()) > 0;
	}
	
	// TODO
	public List<University> getAllUniversities() {
		return null;
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
