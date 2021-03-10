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
	public void testAccountObjectMutation() {
		// Test that the account object changes properly when setters and getters are invoked.
	}

}
