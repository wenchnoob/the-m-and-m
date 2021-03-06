package com.cmc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cmc.controller.AccountController;
import com.cmc.controller.AdminFunctionalityController;
import com.cmc.controller.SearchController;
import com.cmc.controller.UniversityController;
import com.cmc.database.DBInteractions;
import com.cmc.database.UniversityDBLibrary;
import com.cmc.database.tests.DatabaseTest;
import com.cmc.model.Account;
import com.cmc.model.University;
import com.cmc.model.User;

public class DriverTest {


	public static void main(String[] args) {
		
		DatabaseTest.suite().run(null);
		
		// 1
		System.out.println("Testing login: ");
		testLogin();
		System.out.println();

		// 2
		System.out.println("Testing view account: ");
		testViewAccount();
		System.out.println();

		// 3
		System.out.println("Testing search Universities: ");
		testSearchUniversities();
		System.out.println();

		// 4
		System.out.println("Testing editing basic user information: ");
		testEditBasicUserInfo();
		System.out.println();

		// 5
		System.out.println("Testing view all universities: ");
		testViewAllUniversities();
		System.out.println();

		// 6
		System.out.println("Testing view all accounts: ");
		testViewAllAccounts();
		System.out.println();

		// 7
		System.out.println("Testing view University: ");
		testViewUniversity();
		System.out.println();

		// 8
		System.out.println("Testing editing basic university info: ");
		testEditBasicUniversityInfo();
		System.out.println();
		
		// 9
		System.out.println("Testing activate/deactivate user: ");
		testChangeStatus();
		System.out.println();

		// 10
		System.out.println("Testing Logout: ");
		testLogout();
		System.out.println();
		
		// 11
		System.out.println("Testing adding new User: ");
		testAddUser();
		System.out.println();
		
		// 12
		System.out.println("Testing changer user type: ");
		testChangeUserType();
		System.out.println();
		
		// 13
		System.out.println("Testing saving schools: ");
		testSaveSchool();
		System.out.println();
		
		
		
		// Newest test
		System.out.println("Testing getting all university information!: ");
		UniversityDBLibrary lib = new UniversityDBLibrary("jdbc:mysql://localhost:3306/megatherium", "cmc", "pleasejustwork!");
		Arrays.stream(lib.university_getUniversities()).forEach(arr -> System.out.println(Arrays.toString(arr)));
		
		System.out.println("Users: ");
		Arrays.stream(lib.user_getUsers()).forEach(arr -> System.out.println(Arrays.toString(arr)));
		
		// Test of saving current dummy users
		PsuedoDatabase.getInstance().getAllUsers().forEach(user -> DBInteractions.getInstance().save(user));
		
		// Test of removing a user
		DBInteractions.getInstance().remove(PsuedoDatabase.getInstance().getUserByUsername("ckalsow"));
		
	}

	public static void testLogin() {
		// Successful Login
		System.out.println("Testing a successful login: ");
		Account loggedIn = AccountController.getInstance().logon("ckalsow", "Channaiskool");
		if (loggedIn != null) {
			System.out.println(loggedIn);
		} else {
			System.out.println("Invalid Credentials Logon Failed!");
		}

		// Failed login
		System.out.println("Testing a failed login: ");
		Account loggedIn2 = AccountController.getInstance().logon("a", "bingo");
		if (loggedIn2 != null) {
			System.out.println(loggedIn2);
		} else {
			System.out.println("Invalid Credentials Logon Failed!");
		}
	}
	
	public static void testLogout() {
		// Successful Logout
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		Account admin = AccountController.getInstance().logon("admin", "admin");
		controller.ChangeStatus(admin, PsuedoDatabase.getInstance().getUserByUsername("ckalsow"), true);
		AccountController.getInstance().logout("ckalsow");
		System.out.println("Logged out Account: ");
		System.out.println(AccountController.getInstance().viewAccount("ckalsow"));
	}

	public static void testViewAccount() {
		AccountController controller = AccountController.getInstance();
		String view = controller.viewAccount("ckalsow");
		if (view != null) {
			System.out.println(view);
		} else {
			System.out.println("Account not Found!");
		}
	}

	public static void testSearchUniversities() {
		SearchController controller = SearchController.getInstance();
		System.out.println("Successful search: ");
		List<University> results = controller.searchUniversity("Uni A", "", "", "", 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				new ArrayList<String>()
				);
		System.out.println(results);
		System.out.println();

		System.out.println("Failed search: ");
		results = controller.searchUniversity("Uni B", "", "", "", 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				new ArrayList<String>()
				);
		System.out.println(results);
	}

	public static void testEditBasicUserInfo() {
		AccountController controller = AccountController.getInstance();

		// User editing own information
		System.out.println("User editing their own information: ");
		Account channa = controller.logon("ckalsow", "Channaiskool");
		controller.editBasicUserInfor(channa, channa , AccountController.ManagedField.LASTNAME, "Anderson");
		System.out.println(controller.viewAccount("ckalsow"));
		System.out.println();

		// Admin editing user information
		System.out.println("Admin editing user information");
		Account admin = controller.logon("admin", "admin");
		controller.editBasicUserInfor(admin, channa, AccountController.ManagedField.RECOVERY_QUESTION, "Three digits of pie?");
		System.out.println(controller.viewAccount("ckalsow"));
	}

	public static void testViewAllUniversities() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		List<University> allUniversities = controller.viewAllUniversities();
		System.out.println(allUniversities);
	}

	public static void testViewAllAccounts() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		List<Account> allAccounts = controller.viewAllAccounts();
		System.out.println(allAccounts);
	}

	public static void testViewUniversity() {
		System.out.println(UniversityController.getInstance().viewUniversity("Uni A"));
	}

	public static void testEditBasicUniversityInfo() {
		UniversityController controller = UniversityController.getInstance();
		controller.editBasicUniversityInfo("admin", "Uni J", UniversityController.ManagedField.NAME, "Uni B");
		System.out.println(UniversityController.getInstance().viewUniversity("Uni B"));
		System.out.println();
		System.out.println("Current list of all universities: ");
		testViewAllUniversities();
	}
	
	public static void testChangeStatus() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		Account admin = AccountController.getInstance().logon("admin", "admin");
		controller.ChangeStatus(admin, PsuedoDatabase.getInstance().getUserByUsername("ckalsow"), false);
		System.out.println(AccountController.getInstance().viewAccount("ckalsow"));
	}
	
	public static void testAddUser() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		controller.addUser("Kristian", "Kalsow", "kkiskool" , 
				"koool", "2+2?", "4", true, Account.AccountType.ADMIN);
		System.out.println("Newly added account: ");
		System.out.println(AccountController.getInstance().viewAccount("kkiskool"));
		
	}
	
	public static void testChangeUserType() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		Account admin = AccountController.getInstance().logon("kkiskool", "koool");
		controller.ChangeStatus(admin, PsuedoDatabase.getInstance().getUserByUsername("ckalsow"), true);
		Account user = AccountController.getInstance().logon("ckalsow", "Channaiskool");	
		controller.changeUserType(admin, user, Account.AccountType.ADMIN);
		System.out.println(AccountController.getInstance().viewAccount("ckalsow"));
	}
	
	public static void testSaveSchool() {
		User user = (User)AccountController.getInstance().logon("kanderson", "Kristianaiskool");
		System.out.println("Testing save: ");
		user.saveSchool("Uni A");
		user.saveSchool("Uni B");
		System.out.println("Saved Universities: " + user.getSavedSchools());
		
		System.out.println("Testing unsave: ");
		user.unsaveSchool("Uni A");
		System.out.println("Saved Universities: " + user.getSavedSchools());
	}

}
