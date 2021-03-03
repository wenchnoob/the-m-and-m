package com.cmc;

import java.util.List;

import com.cmc.controller.AccountController;
import com.cmc.controller.AdminFunctionalityController;
import com.cmc.model.Account;
import com.cmc.model.University;

public class DriverTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testTesting();
		testLogin();
		testViewAllUniversities();
		testViewAllUsers();
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
			System.out.println("Invalid Crdentials Logon Failed!");
		}
		
		// Failed login
		Account loggedIn2 = AccountController.getInstance().logon("a", "bingo");
		if (loggedIn2 != null) {
			System.out.println(loggedIn2);
		} else {
			System.out.println("Invalid Crdentials Logon Failed!");
		}
	}
	
	public static void testViewAllUniversities() {
		List<University> uniList = AdminFunctionalityController.getInstance().ViewAllUniversities();
		System.out.println(uniList);
	}
	
	public static void testViewAllUsers() {
		List<Account> userList = AdminFunctionalityController.getInstance().ViewAllUsers();
		System.out.println(userList);
	}
	

}
