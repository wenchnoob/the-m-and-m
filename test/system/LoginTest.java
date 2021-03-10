package system;

import java.util.HashMap;

import com.cmc.controller.AccountController;
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
	public void testLoginMain() {
		// Successful Logins
		// Enabled user logins in using valid credentials
		// VALID LOGON
		Account loggedInUser = controller.logon(testUser.getUsername(), testUser.getPassword());
		// System Test, Main Scenario: successful login
		Assert.assertEquals("The User should have successfully logged on. VALID LOGON.", testUser,  loggedInUser);

	}
	
	@Test
	public void testLoginAlternate1() {
		// System Test, Alternate Scenario #1: wrong password
		// Enabled user attempts to login using invalid credentials
		// ENABLED BUT INVALID
		Account loggedInUser = controller.logon(testUser.getUsername(), testUser.getPassword() + "wrong");
		Assert.assertEquals("There should be no logged on user. ENABLED BUT INVALID.", null, loggedInUser);

	}
	
	@Test
	public void testLoginAlternate2() {
		// System Test, Alternate Scenario #2: disabled user
		// Unsuccessful Logins
		// Disabled user attempts to login using valid credentials
		// DISABLED BUT VALID
		Account loggedInUser = controller.logon(disabledTestUser.getUsername(), disabledTestUser.getPassword());
		Assert.assertEquals("There should be no logged on user. DISABLED BUT VALID.", null, loggedInUser);
	}
	
}
