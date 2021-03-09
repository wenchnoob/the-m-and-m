package com.cmc.controller;

import java.util.HashMap;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class LoginTest extends TestCase {
	
	private AccountController controller;
	private DBInteractions db;
	private Account testUser;
	private Account disabledTestUser;
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = AccountController.getInstance();
		db = DBInteractions.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		disabledTestUser = new User("DisabledJoe","DisabledMathias","Disabled Who am i?","Disabled Me","DisabledJmath","DisabledPassword", false, new HashMap<String,UserSchool>());
		db.save(testUser);
		db.save(disabledTestUser);
	}
	
	@Override
	@Before
	protected void tearDown() throws Exception {
		super.tearDown();
		db.remove(testUser);
		db.remove(disabledTestUser);
		db = null;
		testUser = null;
		disabledTestUser = null;
	}

	@Test
	public void testLogin() {
		
		// Successful Logins
		// Enabled user logins in using valid credentials
		// VALID LOGON
		Account loggedInUser = controller.logon(testUser.getUsername(), testUser.getPassword());
		Assert.assertEquals("The User should have successfully logged on. VALID LOGON.", testUser,  loggedInUser);
		
		// Unsuccessful Logins
		// Disabled user attempts to login using valid credentials
		// DISABLED BUT VALID
		loggedInUser = controller.logon(disabledTestUser.getUsername(), disabledTestUser.getPassword());
		Assert.assertEquals("There should be no logged on user. DISABLED BUT VALID.", null, loggedInUser);
		
		// Disabled user attempts to login using invalid credentials
		// DISABLED AND INVALID
		loggedInUser = controller.logon(disabledTestUser.getUsername(), disabledTestUser.getPassword() + "wrong");
		Assert.assertEquals("There should be no logged on user. DISABLED AND VALID.", null, loggedInUser);
		
		// Enabled user attempts to login using invalid credentials
		// ENABLED BUT INVALID
		loggedInUser = controller.logon(testUser.getUsername(), testUser.getPassword() + "wrong");
		Assert.assertEquals("There should be no logged on user. ENABLED BUT INVALID.", null, loggedInUser);
		
		
	}
	
}
