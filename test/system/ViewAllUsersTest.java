package system;

import java.util.List;

import org.junit.*;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;

import junit.framework.TestCase;

public class ViewAllUsersTest extends TestCase {

	private DBInteractions db;
	
	protected void setUp() throws Exception {
		super.setUp();
		db = DBInteractions.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		db = null;
	}

	
	@Test
	public void testViewAllUsers() {
		// Test case, as long as the database is not empty
		// this use case should never fail.
		List<Account> users = db.getAllUsers();
		Assert.assertFalse("If there is at least one user in the databsse the returned list should never be empty." , users.size() <= 0);
	}
}
