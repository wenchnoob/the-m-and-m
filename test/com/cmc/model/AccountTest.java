package com.cmc.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.*;
import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	private Account testAccount;
	String firstName, lastName, username, password, rQ, rA;
	boolean enabled;

	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		firstName = "mike";
		lastName = "bud";
		username = "mbud";
		password = "heyyy";
		rQ = "";
		rA = "";
		enabled = true;
		testAccount = new Admin(firstName, lastName, username, password, rQ, rA, enabled);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		testAccount = null;
	}
	
	@Test
	public void testAccountObjectInitialization() {
		// Test that the account object was initialized properly.
		Assert.assertEquals("Test to ensure firstName was properly initialized.", testAccount.getFirstName(), firstName);
		Assert.assertEquals("Test to ensure lastName was properly initiialized.", testAccount.getLastName(), lastName);
		Assert.assertEquals("Test to ensure that the username was properly initialized.", testAccount.getUsername(), username);
		Assert.assertEquals("Test to ensure that the password was porperly iniitalized.", testAccount.getPassword(), password);
		Assert.assertEquals("Test to ensure that the recovery question was prperly initialized.", testAccount.getRecoveryQuestion(), rQ);
		Assert.assertEquals("Test to ensure that the recovery answer was properly inititialized.", testAccount.getRecoveryAnswer(), rA);
		Assert.assertEquals("Test to ensure that the status was properly initialized.", testAccount.isEnabled(), enabled);
	}
	
	@Test
	public void testGetType() {
		// Test the get Account type
		Assert.assertEquals("The returned type should be of type admin.", Account.AccountType.ADMIN, testAccount.getType());
	}
	
	@Test
	public void testSetEnabledAndIsEnabled() {
		// Sets enable getter and setters together
		testAccount.setEnabled(false);
		Assert.assertFalse("The account should be disabled.", testAccount.isEnabled());
	}
	
	@Test
	public void testUserLogon() {
		// Name: LOGOUT
		boolean loggedIn = testAccount.logout();
		Assert.assertFalse("The user should not be logged in. LOGOUT.", !loggedIn);
		
		// Name: 
		loggedIn = testAccount.isLoggedOn();
		Assert.assertFalse("The user should not be logged in. LOGOUT.", loggedIn);
		
		// Name: FAILED LOGIN
		loggedIn = testAccount.logon(password + "no");
		Assert.assertFalse("The user should not be logged in. FAILED LOGIN.", loggedIn);
		
		loggedIn = testAccount.isLoggedOn();
		Assert.assertFalse("The user should not be logged in. FAILED LOGIN.", loggedIn);
		
		// Name: SUCCESSFUL LOGIN
		loggedIn =testAccount.logon(password);
		Assert.assertTrue("The user should now be logged in. SUCCESSFUL LOGIN.", loggedIn);
		
		loggedIn = testAccount.isLoggedOn();
		Assert.assertTrue("The user should now be logged in. SUCCESSFUL LOGIN.", loggedIn);
	}
	

}
