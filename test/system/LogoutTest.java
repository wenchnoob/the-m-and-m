/**
 * 
 */
package system;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.controller.AccountController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;

/**
 * @author ckalsow001
 *
 */
public class LogoutTest extends TestCase{

	private AccountController controller;
	private DBInteractions db;
	private Account testUser;
	private Account disabledTestUser;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		super.setUp();
		controller = AccountController.getInstance();
		db = DBInteractions.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		disabledTestUser = new User("DisabledJoe","DisabledMathias","Disabled Who am i?","Disabled Me","DisabledJmath","DisabledPassword", false, new HashMap<String,UserSchool>());
		db.save(testUser);
		db.save(disabledTestUser);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		super.tearDown();
		db.remove(testUser);
		db.remove(disabledTestUser);
		db = null;
		testUser = null;
		disabledTestUser = null;
		
	}

	@Test
	public void testLogout() {
		
		// Successful Logins
				// Enabled user logins in using valid credentials
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

}
