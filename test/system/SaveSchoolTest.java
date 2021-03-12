package system;

import junit.framework.TestCase;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

public class SaveSchoolTest extends TestCase {

	private DBInteractions db;

	private Account testUser;
	
	protected void setUp() throws Exception {
		super.setUp();
		db = DBInteractions.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		db.remove(testUser);
		db = null;
		testUser = null;
	}
	
	@Test
	public void testSaveSchoolMainScenario() {
		
		// Tries to save a known school
		boolean  saved = ((User)testUser).saveSchool("BARD");
		Assert.assertTrue("User saved a known school from the database.", saved);
	}
	public void testSaveSchoolAlternateScenario() {
		
		//Tries to save a school that is already in the database
		((User)testUser).saveSchool("FLORIDA STATE");
		boolean savedTwice = ((User)testUser).saveSchool("FLORIDA STATE");
		Assert.assertFalse("User tries to save a school that is already in their list", savedTwice);
		
		
	}

}
