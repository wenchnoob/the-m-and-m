package com.cmc.controller;

import java.util.HashMap;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;
import org.junit.*;

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
		
		//username doesn't exist
		loggedInUser = controller.logon("unicorn", "puppetry");
		Assert.assertEquals("There should be no logged on user. FAKE ACCOUNT.", null, loggedInUser);
		
	}
	
	@Test
	public void testLogout() {
		fail("Not yet implemented.");
	}
	
	@Test
	public void viewAccount() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEditBasicUserInfo() {
		fail("Not yet implemented");
	}
}

