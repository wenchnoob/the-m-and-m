package com.cmc.database;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.cmc.model.*;
<<<<<<< HEAD
//import csb.sju.csci.*;
import dblibrary.project.csci230.UniversityDBLibrary;
=======


// import csb.sju.csci.*;
import dblibrary.project.csci230.UniversityDBLibrary;



//import csb.sju.csci.*;
// import dblibrary.project.csci230.UniversityDBLibrary;


/**
 * Class intended for the handling of all university functionalities in the system.
 * @author Wenchy Dutreuil, Kristiana Anderson, and Joseph Mathias
 */

>>>>>>> 3920e35ae7842b3a05fe730dd29dc6480730fb64

public class DBInteractions {
	
	private static DBInteractions self;
	private  UniversityDBLibrary db;
	
	/**
	 * Private constructor to prevent construction of objects
	 * of this class from outside of this class.
	 */
	private DBInteractions() {
		// Initializer for All
		// Uncomment the below line if you are in horizon view.

<<<<<<< HEAD
		 db = new UniversityDBLibrary("megatherium", "csci230");

=======
		db = new UniversityDBLibrary("megatherium", "csci230");
>>>>>>> 3920e35ae7842b3a05fe730dd29dc6480730fb64
		
		// Initializer for Wenchy (Comment out if you are not wenchy)
		//db = new UniversityDBLibrary("jdbc:mysql://localhost:3306/megatherium", "cmc", "pleasejustwork!");
	}
	
	/**
	 * Static method to return a reference to a singleton instance of this class.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @return AccountController - An singleton instance of this class.
	 * */
	public static DBInteractions getInstance() {
		if (self == null) self = new DBInteractions();
		return self;
	}
	
	/**
	 * Returns a list of all users that are in the database.
	 * 
	 * @author wench
	 * 
	 * @return List<Account> - A list containing all the accounts in the database.
	 * */
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
	
	/**
	 * Private method to load the user schools.
	 * 
	 * @author wench
	 * 
	 * @param users - The users to load the schools for.
	 * */
	private void loadUserSchools(List<Account> users) {
		String[][] usersAndSchools = db.user_getUsernamesWithSavedSchools();
		Map<String, Map<String, UserSchool>> mapping = mapify(usersAndSchools);
		
		users.forEach(user -> {
			String username = user.getUsername();
			if (mapping.containsKey(username)) ((User)user).setSavedSchools(mapping.get(username));
		});
	}
	
	/**
	 * Private method to make a map of username to a map of a school name and it's UserSchool object.
	 * 
	 * @author wench
	 * 
	 * @param userAndSchools - A two dimensional array of arrays of username and school name pairs.
	 * 
	 * @return Map<String, Map<String, UserSchool>> - A map that holds username and the corresponding saved schools.
	 * */
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
	
	/**
	 * Returns an account object that correlates with the username and the information stored in the database.
	 * 
	 * @author wench
	 * 
	 * @param username - The username to search for.
	 * 
	 * @return Account - The account object that correlates with the username. Null if the username is not in the database.
	 * */
	public Account getUserByUserName(String username) {
		for (Account user: getAllUsers()) {
			if (user.getUsername().equals(username)) return user;
		}
		return null;
	}
	
	/**
	 * Save an account to the database.
	 * 
	 * @author wench
	 * 
	 * @param toSave - The account object to be saved to the database.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean save(Account toSave) {
		if (toSave == null) return false;
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
		
		if (!toSave.isEnabled()) success = db.user_editUser(username, firstName, lastName, password, type, enabled) > 0;
		
		if (type == 'u') saveUserSchools((User)toSave);
		
		return success;
	}
	
	/**
	 * Saves the users saved Schools.
	 * 
	 * @author wench
	 * 
	 * @param user - The user whose saved schools should be saved to the database.
	 * */
	private void saveUserSchools(User user) {
		if (user.getSavedSchools() == null) return;
		user.getSavedSchools().forEach((k, v) -> db.user_saveSchool(user.getUsername(), k));
	}
	
	/**
	 * Removes an account from the database.
	 * 
	 * @author wench
	 * 
	 * @param toRemove - The account to be removed from the database.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean remove(Account toRemove) {
		if (toRemove == null) return true;
		if (toRemove.getClass() == User.class) {
			removeAllUserSchool((User) toRemove);
		}
		return db.user_deleteUser(toRemove.getUsername()) > 0;
	}
	
	/**
	 * Removes a users saved school from the database.
	 * 
	 * @author joseph and kristiana
	 * 
	 * @param user - The user whose save school should be removed.
	 * @param school - The saved School that should be removed.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * 
	 * */
	public boolean removeIndividualSchool(User user, UserSchool school) {
		if (user == null || school == null|| school.getUniversity() == null) return false;
		String username = user.getUsername();
		String removedSchool = school.getUniversity().getName();
		return db.user_removeSchool(username, removedSchool) > 0;
	}
	
	/**
	 * Removes all the saved schools of a user from the database.
	 * 
	 * @author joseph and krisitana
	 * 
	 * @param user - The user whose schools should be removed.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean removeAllUserSchool(User user) {
		if (user == null) return false;
		boolean success = true;
		for (UserSchool school: user.getSavedSchools().values()) {
			success &= removeIndividualSchool(user, school);
		}
		return success;
	}
	
	/**
	 * Returns a list of all the universities in the database.
	 * 
	 * @author kristiana
	 * 
	 * @return List<University> - A list of the all the universities in the database.
	 * */
	public List<University> getAllUniversities() {
		List<University> universities = new ArrayList<>();
		String[][] allUniversities = db.university_getUniversities();
		
		for (String[] universityData: allUniversities) {
			String universityName = universityData[0];
			String state = universityData[1];
			String location = universityData[2];
			String control = universityData[3];
			int numOfStudents = Integer.parseInt(universityData[4]);
			float perFemale = Float.parseFloat(universityData[5]);
			int satVerbal = (int)Double.parseDouble(universityData[6]);
			int satMath = (int)Double.parseDouble(universityData[7]);
			int expenses = (int)Float.parseFloat(universityData[8]);
			float perFinAid = Float.parseFloat(universityData[9]);
			int numOfApps = Integer.parseInt(universityData[10]);
			float perAdmitted = Float.parseFloat(universityData[11]);
			float perEnrolled = Float.parseFloat(universityData[12]);
			int academicScale = Integer.parseInt(universityData[13]);
			int socialScale = Integer.parseInt(universityData[14]);
			int qualityOfLife = Integer.parseInt(universityData[15]);
			
			List<String> emphases = new ArrayList<>();
			
			universities.add(new University(universityName, state, location, control, numOfStudents,
					satMath, satVerbal, expenses, numOfApps, academicScale, socialScale, emphases,
					perFemale, perFinAid, perAdmitted, perEnrolled, qualityOfLife));
			
		}
		
		String[][] allEmphases = db.university_getNamesWithEmphases();
		for (University school: universities) {
			for (String[] emphasis:allEmphases) {
				if (school.getName().equals(emphasis[0])){
					school.getEmphases().add(emphasis[1]);					
				}
			}
			
		}
		
		return universities;
	}
	
	/**
	 * Returns a university object that correlates with the name passed and the information stored in the database.
	 * 
	 * @author kristiana
	 * 
	 * @param name - The name to search for.
	 * 
	 * @return University - The university object that correlates with the name. Null if the name is not in the database.
	 * */
	public University getUniversityByName(String name) {
		List<University> allUniversities = getAllUniversities();
		for (University school:allUniversities) {
			if (name.equals(school.getName())) return school;
		}
		return null;
	}
	
	/**
	 * Saves a university to the database.
	 * 
	 * @author kristiana
	 * 
	 * @param toSave - The university to save.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean save(University toSave) {
		if (toSave == null) return false;
		String universityName = toSave.getName();
		String state = toSave.getState();
		String location = toSave.getLocation();
		String control = toSave.getControl();
		int numOfStudents = toSave.getNumStudents();
		float perFemale = toSave.getPerFemale();
		int satVerbal = toSave.getSatMath();
		int satMath = toSave.getSatVerbal();
		int expenses = toSave.getExpenses();
		float perFinAid = toSave.getPerFinAid();
		int numOfApps = toSave.getNumOfApps();
		float perAdmitted = toSave.getPerAdmitted();
		float perEnrolled = toSave.getPerEnrolled();
		int academicScale = toSave.getAcademicScale();
		int socialScale = toSave.getSocialScale();
		int qualityOfLife = toSave.getQualityLife();
		
		boolean success = true;
		
		if (db.university_addUniversity(universityName,state,location,control,numOfStudents,perFemale,
				satVerbal,satMath,expenses,perFinAid,numOfApps,perAdmitted,perEnrolled,
				academicScale,socialScale,qualityOfLife) <= 0) {
			
			success = db.university_editUniversity(universityName,state,location,control,numOfStudents,perFemale,
					satVerbal,satMath,expenses,perFinAid,numOfApps,perAdmitted,perEnrolled,
					academicScale,socialScale,qualityOfLife) > 0;
		}
		
		return success;
	}
	
	
	/**
	 * Removes a university from the database.
	 * 
	 * @author kristiana
	 * 
	 * @param toRemove - The university to remove.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	public boolean remove(University toRemove) {
		if (toRemove == null) return false;
		return removeEmphases(toRemove) && db.university_deleteUniversity(toRemove.getName()) > 0;
	}
	
	// TODO
	public boolean removeEmphases(University toRemove) {
		if (toRemove == null || toRemove.getEmphases() == null) return false;
		AtomicReference<Boolean> success = new AtomicReference<>(true);
		
		toRemove.getEmphases().forEach(emp -> {
			success.accumulateAndGet(removeEmphasis(toRemove.getName(), emp), (p, q) -> {
				return p & q;
			});
		});
		
		return success.get();
	}
	
	public boolean removeEmphasis(String school, String emphasis) {
		return db.university_removeUniversityEmphasis(school, emphasis) > 0;
	}
	

}
