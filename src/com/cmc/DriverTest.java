package com.cmc;

import java.util.ArrayList;
import java.util.List;

import com.cmc.controller.AccountController;
import com.cmc.controller.SearchController;
import com.cmc.model.Account;
import com.cmc.model.University;

public class DriverTest {


	public static void main(String[] args) {
		testTesting();
		testLogin();
		testViewAccount();
		testSearchUniversities();
	}
	
	public static void testTesting() {
		assert true;
		System.out.println("The test of the testing passed. Yay!!!");
	}
	
	public static void testLogin() {
		// Successful Login
		Account loggedIn = AccountController.getInstance().logon("ckalsow", "Channaiskool");
		if (loggedIn != null) {
			System.out.println(loggedIn);
		} else {
			System.out.println("Invalid Credentials Logon Failed!");
		}
		
		// Failed login
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
		List<University> results = controller.searchUniversity("Uni B", "", "", "", 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				new ArrayList<String>()
				);
		System.out.println(results);
	}
	

}
