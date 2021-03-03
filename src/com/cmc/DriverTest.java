package com.cmc;

import java.util.ArrayList;
import java.util.List;

import com.cmc.controller.AccountController;
import com.cmc.controller.SearchController;
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
	
	public static void testViewAccount() {
		AccountController controller = AccountController.getInstance();
		String view = controller.viewAccount(controller.logon("ckalsow", "Channaiskool"));
		if (view != null) {
			System.out.println(view);
		} else {
			System.out.println("Account not Found!");
		}
	}
	
	public static void testSearchUniversities() {
		SearchController controller = new SearchController();
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
		System.out.println(controller.viewAccount(channa));
		System.out.println();
		
		// Admin editing user information
		System.out.println("Admin editing user information");
		Account admin = controller.logon("admin", "admin");
		controller.editBasicUserInfor(admin, channa, AccountController.ManagedField.RECOVERY_QUESTION, "Three digits of pie?");
		System.out.println(controller.viewAccount(channa));
	}

}
