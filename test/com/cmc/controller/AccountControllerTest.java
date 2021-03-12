package com.cmc.controller;

import java.util.HashMap;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import org.junit.*;

import junit.framework.TestCase;

/**
 * 
 * @author jmathias001
 *
 */


public class AccountControllerTest extends TestCase {


	private AccountController controller;
	private DBInteractions db;
	private Account testAccount, testAdmin1, testAdmin2;
	private Account testUser;
	private Account disabledTestUser;
	
	@Override
	@Before
	protected void setUp() throws Exception {
		controller = AccountController.getInstance();
		db = DBInteractions.getInstance();
		testAccount = new User("Joe","Mathias","Who am i?","You","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin1 = new Admin("Brady","Bobington", "Bbob","Password", "Who am i?","You",true);
		testAdmin2 = new Admin("Zach","Kratz","ZKratz","Password", "Who am i?","You",true);
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		disabledTestUser = new User("DisabledJoe","DisabledMathias","Disabled Who am i?","Disabled Me","DisabledJmath","DisabledPassword", false, new HashMap<String,UserSchool>());
		
		db.save(testAccount);
		db.save(testAdmin1);
		db.save(testAdmin2);
		db.save(testUser);
		db.save(disabledTestUser);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		db.remove(testAccount);
		db.remove(testAdmin1);
		db.remove(testAdmin2);
		db.remove(testUser);
		db.remove(disabledTestUser);
		testAccount = null;
		testAdmin1 = null;
		testAdmin2 = null;
		testUser = null;
		controller = null;
		db = null;
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
		
		// Username doesn't exist
		loggedInUser = controller.logon("unicorn", "puppetry");
		Assert.assertEquals("There should be no logged on user. FAKE ACCOUNT.", null, loggedInUser);
		
	}
	
	@Test
	public void testLogout() {
		
		// Successful Logout
		// Enabled user logout in using valid credentials
		// VALID LOGOUT
		boolean logStatus = controller.logout(testUser.getUsername());
		Assert.assertEquals("The User should have successfully logged out. VALID LOGOUT.", true,  logStatus);
				
		// Unsuccessful Logouts
		// Disabled user attempts to logout using valid credentials
		// DISABLED BUT VALID
		logStatus = controller.logout(disabledTestUser.getUsername());
		Assert.assertEquals("There should be no logged on user. DISABLED BUT VALID.", false, logStatus);
				
		//username doesn't exist
		logStatus = controller.logout("unicorn");
		Assert.assertEquals("There should be no logged off user. FAKE ACCOUNT.", false, logStatus);
	}
		
	@Test
	public void viewAccount() {
		//Test that User can view their own profile
		Account got = controller.viewAccount("luser");
		Assert.assertEquals(db.getUserByUserName("luser"), got);
		
	}

	//Tests changing the first name
	// Test a basic user attempting to change their own first name
	@Test
	public void testEditBasicUserInfoFirstName() {
		Assert.assertNotSame("Bobby", testAccount.getFirstName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAccount.getFirstName());
	}
	// Test an admin changing a basic user's name;
	@Test
	public void testAdminEditBasicUserInfoFirstName() {
		Assert.assertNotSame("Bobby", testAccount.getFirstName());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAccount.getFirstName());
	}
	// Test an admin changing another admin's first name.
	@Test 
	public void testAdminEditAdminFirstName() {
		Assert.assertNotSame("Bobby", testAdmin1.getFirstName());
		controller.editBasicUserInfo(testAdmin2, testAdmin1, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAdmin1.getFirstName());
	}
	//Tests a basic user attempting to change an admins name
	//Makes sure that it fails
	@Test
	public void testBasicUserEditsAdminFirstName() {
		Assert.assertEquals("Brady", testAdmin1.getFirstName());
		controller.editBasicUserInfo(testAccount, testAdmin1, AccountController.ManagedField.FIRSTNAME, "Bob");
		Assert.assertEquals("Brady", testAdmin1.getFirstName());
	}
	//Tests changing last name 
	//Tests basic user changing the last name
	@Test
	public void testEditBasicUserInfoLastName() {
		Assert.assertNotSame("Kratz", testAccount.getLastName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.LASTNAME, "Kratz");
		Assert.assertEquals("Kratz",testAccount.getLastName());
	}
	//Tests having an admin change a basic users last name
	@Test
	public void testAdminChangingBasicUserInfoLastName() {
		Assert.assertNotSame("Kratz", testAccount.getLastName());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.LASTNAME, "Kratz");	
		Assert.assertEquals("Kratz",testAccount.getLastName());	
	}
	//Tests having an admin change a admin last name
	@Test
	public void testAdminChangingAdminLastName() {
		Assert.assertNotSame("Robington", testAdmin1.getLastName());
		controller.editBasicUserInfo(testAdmin2, testAdmin1, AccountController.ManagedField.LASTNAME, "Robington");	
		Assert.assertEquals("Robington",testAdmin1.getLastName());	
	}
	//Tests having an basic user change a admins last name
	//Testing to make sure it fails
	@Test
	public void testBasicUserChangingAdminLastName() {
		Assert.assertEquals("Bobington", testAdmin1.getLastName());
		controller.editBasicUserInfo(testAccount, testAdmin1, AccountController.ManagedField.LASTNAME, "Kratz");	
		Assert.assertEquals("Bobington",testAdmin1.getLastName());	
	}
	//Tests changing the password
	//Tests basic user changing the password
	@Test
	public void testEditBasicUserInfoPassword() {
		Assert.assertNotSame("newPassword", testAccount.getPassword());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.PASSWORD, "newPassword");
		Assert.assertEquals("newPassword", testAccount.getPassword());
	}
	//Tests admin changing a basic users password
	@Test
	public void testAdminEditBasicUserInfoPassword() {
		Assert.assertNotSame("newPassword", testAccount.getPassword());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.PASSWORD, "newPassword");
		Assert.assertEquals("newPassword", testAccount.getPassword());
	}
	//Tests admin changing an admin password
	@Test
	public void testAdminEditAdminPassword() {
		Assert.assertNotSame("newPassword", testAdmin1.getPassword());
		controller.editBasicUserInfo(testAdmin2, testAdmin1, AccountController.ManagedField.PASSWORD, "newPassword");
		Assert.assertEquals("newPassword", testAdmin1.getPassword());
	}
	//Tests a basic user changing a admin password
	//Test should fail
	@Test
	public void testBasicUserEditAdminPassword() {
		Assert.assertEquals("Password", testAdmin1.getPassword());
		controller.editBasicUserInfo(testAccount, testAdmin1, AccountController.ManagedField.PASSWORD, "NewPassword");
		Assert.assertEquals("Password", testAdmin1.getPassword());

	}
	//Tests changing recovery question
	//Tests basic user changing the recovery question
	@Test
	public void testEditBasicUserInfoRecoveryQuestion() {
		Assert.assertNotSame("Who are you?", testAccount.getRecoveryQuestion());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.RECOVERY_QUESTION, "Who are you?");
		Assert.assertEquals("Who are you?",testAccount.getRecoveryQuestion());
	}
	//Tests admin changing basic user recovery question
	@Test
	public void testAdminEditingBasicUserRecoveryQuestion() {
		Assert.assertNotSame("Who are you?", testAccount.getRecoveryQuestion());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.RECOVERY_QUESTION, "Who are you?");
		Assert.assertEquals("Who are you?",testAccount.getRecoveryQuestion());
	}
	//Tests admin changing admin recovery question
	@Test
	public void testAdminEditingAdminRecoveryQuestion() {
		Assert.assertNotSame("Who are you?", testAdmin1.getRecoveryQuestion());
		controller.editBasicUserInfo(testAdmin2, testAdmin1, AccountController.ManagedField.RECOVERY_QUESTION, "Who are you?");
		Assert.assertEquals("Who are you?",testAdmin1.getRecoveryQuestion());
	}
	//Tests basic user changing admin recovery question
	//This test should fail 
	@Test
	public void testBasicUserEditingAdminRecoveryQuestion() {
		Assert.assertEquals("Who am i?", testAdmin1.getRecoveryQuestion());
		controller.editBasicUserInfo(testAccount, testAdmin1, AccountController.ManagedField.RECOVERY_QUESTION, "Who are you?");
		Assert.assertEquals("Who am i?",testAdmin1.getRecoveryQuestion());
	}
	//Tests changing the recovery answer
	//Basic user editing own recover answer
	@Test
	public void testEditBasicUserInfoRecoveryAnswer() {
		Assert.assertNotSame("you", testAccount.getRecoveryAnswer());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.RECOVERY_ANSWER, "you");
		Assert.assertEquals("you",testAccount.getRecoveryAnswer());
	}
	//Tests admin changing basic user recovery answer
	@Test
	public void testAdminEditBasicUserRecoveryAnswer() {
		Assert.assertNotSame("you", testAccount.getRecoveryAnswer());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.RECOVERY_ANSWER, "you");
		Assert.assertEquals("you",testAccount.getRecoveryAnswer());
	}
	//Tests admin changing admin recovery answer
	@Test
	public void testAdminEditAdminRecoveryAnswer() {
		Assert.assertNotSame("you", testAdmin1.getRecoveryAnswer());
		controller.editBasicUserInfo(testAdmin2, testAdmin1, AccountController.ManagedField.RECOVERY_ANSWER, "you");
		Assert.assertEquals("you",testAdmin1.getRecoveryAnswer());
	}
	//Tests basic user changing admin recovery question
	//Test should fail
	@Test 
	public void testBasicUserEditAdminRecoveryAnswer() {
		Assert.assertEquals("You", testAdmin1.getRecoveryAnswer());
		controller.editBasicUserInfo(testAccount, testAdmin1, AccountController.ManagedField.RECOVERY_ANSWER, "me");
		Assert.assertEquals("You", testAdmin1.getRecoveryAnswer());
	}

}

