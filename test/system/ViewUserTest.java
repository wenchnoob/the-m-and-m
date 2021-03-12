/**
 * 
 */
package system;

import org.junit.*;


import com.cmc.controller.AccountController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;


/**
 * @author kanderson003
 *
 */
public class ViewUserTest {
	private AccountController controller;
	private DBInteractions db;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = AccountController.getInstance();
		db = DBInteractions.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db = null;
		controller = null;
	}

	@Test
	public void testViewUserMainScenario() {
		//Test that User can view their own profile
		Account got = controller.viewAccount("luser");
		Assert.assertEquals(db.getUserByUserName("luser"), got);
	}

}
