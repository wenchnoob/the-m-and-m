package com.cmc.controller;

import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.University;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Assert;

import junit.framework.TestCase;

public class AdminFunctionalityControllerTest extends TestCase {
	
	private AdminFunctionalityController controller;
	private DBInteractions db = DBInteractions.getInstance();
	
	private Account testUser;
	private Account testAdmin;
	private Account testAdmin2;
	
	private String usernameToBeAdded = "kkiskool";
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = AdminFunctionalityController.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin = new Admin("Wenchy", "Dutreuil", "", "", "admin", "admin", true);
		testAdmin2 = new Admin("Wenchy2", "Dutreuil2", "", "", "admin2", "admin2", true);
		db.save(testUser);
		db.save(testAdmin);
		db.save(testAdmin2);
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
	public void testAddUser() {
		// Failed add
		// User attempting to add a user
		controller.addUser(testUser, "Kristian", "Kalsow", usernameToBeAdded, 
				"koool", "2+2?", "4", true, Account.AccountType.ADMIN);
		Assert.assertSame("The user should have failed to add another user.", null, db.getUserByUserName(usernameToBeAdded)); 
		
		// Successful add
		// Admin attempting to add a user
		controller.addUser(testAdmin, "Kristian", "Kalsow", usernameToBeAdded, 
				"koool", "2+2?", "4", true, Account.AccountType.ADMIN);
		Assert.assertNotEquals("The admin should have succeeded in adding another user.", null, db.getUserByUserName(usernameToBeAdded));
	}
	
	@Test
	public void testChangeUserType() {
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
	
	@Test
	public void testChangeStatus() {
		
		// Only enable Admin's have access to this functionality; so, only those cases were tested.
		// Disabling effects
		
		// Failed status change
		// User tries to change own status while not being a an admin.
		// Name: BASIC_USER -> SELF
		boolean initialStatus = testUser.isEnabled();
		boolean statusChanged = controller.changeStatus(testUser, testUser, false);
		Assert.assertFalse("Whether the user's status has changed after the test. BASIC_USER -> SELF.", statusChanged);
		Assert.assertEquals("Ensure that the user's current status is actually the user's initial status. BASIC_USER -> SELF.", initialStatus, testUser.isEnabled());
	
		// Failed status change
		// Admin user tries to change own status.
		// Name: ADMIN -> SELF
		initialStatus = testAdmin.isEnabled();
		statusChanged = controller.changeStatus(testAdmin, testAdmin, false);
		Assert.assertFalse("Whether the user's status has changed after the test. ADMIN -> SELF.", statusChanged);
		Assert.assertEquals("Ensure that the user's current status is actually the user's initial status. ADMIN -> SELF.", initialStatus, testAdmin.isEnabled());
		
		// Successful status change
		// Admin user modifies a BasicUser's status.
		// Name: ADMIN -> BASIC_USER
		initialStatus = testUser.isEnabled();
		statusChanged = controller.changeStatus(testAdmin, testUser, false);
		Assert.assertTrue("Whether the user's status has changed after the test. ADMIN -> BASIC_USER.", statusChanged);
		Assert.assertNotSame("Ensure that the user's current status is actually the user's initial status. ADMIN -> BASIC_USER.", initialStatus, testUser.isEnabled());
		
		// Successful status change
		// Admin user modifies another Admin's status
		// Name: ADMIN -> ADMIN
		initialStatus = testAdmin2.isEnabled();
		statusChanged = controller.changeStatus(testAdmin, testAdmin2, false);
		Assert.assertTrue("Whether the user's status has changed after the test. ADMIN -> ADMIN.", statusChanged);
		Assert.assertNotSame("Ensure that the user's current status is actually the user's initial status. ADMIN -> ADMIN.", initialStatus, testAdmin2.isEnabled());
		
		// Enabling effects
		
		// Successful status change
		// Admin user modifies a BasicUser's status to enabled
		// Name: ADMIN -> BASIC_USER {enable}
		initialStatus = testUser.isEnabled(); // should be false for testing purposes
		statusChanged = controller.changeStatus(testAdmin, testUser, true);
		Assert.assertTrue("Whether the user's status has changed after the test. ADMIN -> BASIS_USER {enable}", statusChanged);
		Assert.assertFalse("Ensure that the test user was initially disabled. ADMIN -> BASIC_USER {enable}", initialStatus);
		Assert.assertTrue("Whether the user is now enabled. ADMIN -> BASIC_USER {enable}", testUser.isEnabled());
		
		// Successful status change
		// Admin user modifies another Admin's status to enabled
		// Name: ADMIN -> ADMIN {enable}
		initialStatus = testAdmin2.isEnabled(); // should be false for testing purposes
		statusChanged = controller.changeStatus(testAdmin, testAdmin2, true);
		Assert.assertTrue("Whether the user's status has changed after the test. ADMIN -> ADMIN {enable}", statusChanged);
		Assert.assertFalse("Ensure that the test user was initially disabled. ADMIN -> ADMIN {enable}", initialStatus);
		Assert.assertTrue("Whether the user is now enabled. ADMIN -> ADMIN {enable}", testAdmin2.isEnabled());

	}
	
	@Test
	public void testViewAllUniversities() {
		//Test viewing universities from the database
		List<University> universities = DBInteractions.getInstance().getAllUniversities();
		Assert.assertNotSame("Ensure that the universities being viewed are not null", null, universities);
	}
	
	@Test
	public void testViewAllAccounts() {
		//Test viewing all accounts from the database
		List<Account> accounts = DBInteractions.getInstance().getAllUsers();
		Assert.assertNotSame("Ensure that the accounts being viewed are not null", null, accounts);
	}
	

}
