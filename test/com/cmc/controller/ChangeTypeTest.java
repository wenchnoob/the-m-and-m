package com.cmc.controller;


import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;

public class ChangeTypeTest extends TestCase {
	private AdminFunctionalityController controller;
	private DBInteractions db;
	
	private Account testUser;
	private Account testAdmin;
	private Account testAdmin2;
	
	private String usernameToBeAdded = "kkiskool";
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		db = DBInteractions.getInstance();
		controller = AdminFunctionalityController.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin = new Admin("Wenchy", "Dutreuil", "", "", "admin", "admin", true);
		testAdmin2 = new Admin("Wenchy2", "Dutreuil2", "", "", "admin2", "admin2", true);
	}
	
	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		
		db.remove(testUser);
		db.remove(testAdmin);
		db.remove(testAdmin2);
		db.remove(db.getUserByUserName(usernameToBeAdded));
		
		db = null;
		controller = null;
		testAdmin = null;
		testUser = null;
	}

	@Test
	public void testChangeType() {
		// Failed type change
		
		// User tries to modify his own type
		// Name: USER -> SELF
		Account.AccountType initialType = testUser.getType();
		boolean typeChanged = controller.changeUserType(testUser, testUser, Account.AccountType.ADMIN);
		Assert.assertFalse("Whether the user's type has changed. USER -> SELF", typeChanged);
		Assert.assertEquals("Test to see that the user's type has not change after the operation. USER -> SELF", initialType, testUser.getType());
		
		// Admin tries to modify his own type
		// Name: ADMIN -> SELF
		initialType = testAdmin.getType();
		typeChanged = controller.changeUserType(testAdmin, testAdmin, Account.AccountType.BASIC_USER);
		Assert.assertFalse("Whether the user's type has changed. ADMIN -> SELF", typeChanged);
		Assert.assertEquals("Test to see that the user's type has not change after the operation. ADMIN -> SELF", initialType, testAdmin.getType());
		
		// Admin modifies another user's type
		// Name: ADMIN -> OTHER
		initialType = testUser.getType();
		typeChanged = controller.changeUserType(testAdmin, testUser, Account.AccountType.ADMIN);
		Assert.assertTrue("Whether the user's type has changed. ADMIN -> SELF", typeChanged);
		Assert.assertNotSame("Test to see that the user's type has not change after the operation. ADMIN -> SELF", initialType, testUser.getType());
		
		initialType = testAdmin.getType();
		typeChanged = controller.changeUserType(testAdmin2, testAdmin, Account.AccountType.BASIC_USER);
		Assert.assertTrue("Whether the user's type has changed. ADMIN -> SELF", typeChanged);
		Assert.assertNotSame("Test to see that the user's type has not change after the operation. ADMIN -> SELF", initialType, testAdmin.getType());
	}

}
