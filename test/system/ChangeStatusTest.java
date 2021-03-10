package system;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.controller.AdminFunctionalityController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;

public class ChangeStatusTest extends TestCase {
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
	public void testChangeStatus() {
		
		// Only enable Admin's have access to this functionality; so, onlyt those cases were tested.
		
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

}
