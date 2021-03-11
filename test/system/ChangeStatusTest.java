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
	public void testChangeStatusMainScenario() {
		// Only enable Admin's have access to this functionality; so, only those cases were tested.
		// Disabling effects
		// Successful status change
		// Admin user modifies a BasicUser's status.
		// Name: ADMIN -> BASIC_USER
		boolean initialStatus = testUser.isEnabled();
		boolean statusChanged = controller.changeStatus(testAdmin, testUser, false);
		Assert.assertTrue("Whether the user's status has changed after the test. ADMIN -> BASIC_USER.", statusChanged);
		Assert.assertNotSame("Ensure that the user's current status is actually the user's initial status. ADMIN -> BASIC_USER.", initialStatus, testUser.isEnabled());
	}
	
}
