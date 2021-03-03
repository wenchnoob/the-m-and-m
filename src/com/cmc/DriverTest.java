package com.cmc;

import java.util.ArrayList;
import java.util.List;

import com.cmc.controller.AccountController;
import com.cmc.controller.AdminFunctionalityController;
import com.cmc.controller.SearchController;
import com.cmc.controller.UniversityController;
import com.cmc.model.Account;
import com.cmc.model.University;

public class DriverTest {


	public static void main(String[] args) {
		System.out.println("Testing login: ");
		testLogin();

		System.out.println();

		System.out.println("Testing view account: ");
		testViewAccount();
		System.out.println();

		System.out.println("Testing search Universities: ");
		testSearchUniversities();
		System.out.println();

		System.out.println("Testing editing basic user information: ");
		testEditBasicUserInfo();
		System.out.println();

		System.out.println("Testing view all universities: ");
		testViewAllUniversities();
		System.out.println();

		System.out.println("Testing view all accounts: ");
		testViewAllAccounts();
		System.out.println();

		System.out.println("Testing view University: ");
		testViewUniversity();
		System.out.println();

		System.out.println("Testing editing basic university info: ");
		testEditBasicUniversityInfo();
		System.out.println();
		
		System.out.println("Testing activate/deactivate user: ");
		testChangeStatus();

		System.out.println("Testing Logout: ");
		testLogout();
		System.out.println();
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
	
	public static void testSaveSchool() {
		
	}

}
