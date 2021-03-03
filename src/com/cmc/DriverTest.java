package com.cmc;

import com.cmc.controller.AccountController;
import com.cmc.model.Account;

public class DriverTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testTesting();
		
	}
	
	public static void testTesting() {
		assert true;
		System.out.println("The test of the testing passed. Yay!!!");
	}
	
	public static void testLogin() {
		Account loggedIn = AccountController.getInstance().logon("a", "b");
		if (loggedIn != null) {
			System.out.println(loggedIn);
		} else {
			System.out.println("Invalid Crdentials Logon Failed!");
		}
	}
	

}
