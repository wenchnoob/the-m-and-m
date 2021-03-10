package com.cmc.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
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
		Assert.assertEquals(testAccount.getUsername(), username);
		Assert.assertEquals(testAccount.getPassword(), password);
		Assert.assertEquals(testAccount.getRecoveryQuestion(), rQ);
		Assert.assertEquals(testAccount.getRecoveryAnswer(), rA);
		Assert.assertEquals(testAccount.isEnabled(), enabled);
	}
	
	@Test
	public void testAccountObjectMutation() {
		// Test that the account object changes properly when setters and getters are invoked.
		
	}

}
