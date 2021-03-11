package system;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cmc.controller.AccountController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;
import junit.framework.Assert;

/**
 * 
 * @author jmathias001
 *
 */


public class EditUserInfoTest extends TestCase {

	private DBInteractions db;
	private AccountController controller;
	private Account testAccount, testAdmin1;

	@Override
	@Before
	protected void setUp() throws Exception {
		controller = AccountController.getInstance();
		db = DBInteractions.getInstance();
		testAccount = new User("Joe","Mathias","Who am i?","You","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin1 = new Admin("Brady","Bobington", "Bbob","Password", "Who am i?","You",true);
		db.save(testAccount);
		db.save(testAdmin1);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		db.remove(testAccount);
		db.remove(testAdmin1);
		testAccount = null;
		testAdmin1 = null;
		db = null;
		controller = null;
	}
	
	@Test
	public void testEditBasicUserInfoMainScenario(){
		// Test an admin changing a basic user's name and succeeding;
		Assert.assertNotSame("Bobby", testAccount.getFirstName());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAccount.getFirstName());
	}
	@Test
	public void testEditBasicUserInfoAlternateScenario() {
		// Test an admin changing a basic user's name and entering an empty String leading to false being returned;
		Assert.assertNotSame("Bobby", testAccount.getFirstName());
		boolean status = controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.FIRSTNAME, "");
		Assert.assertEquals(false, status);
	}
}


