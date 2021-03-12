package system;

import java.util.HashMap;

import org.junit.*;


import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

public class RemoveSavedSchoolTest {
	

	private DBInteractions db;
	private Account testUser;
	@Before
	public void setUp() throws Exception {
		db = DBInteractions.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		db.save(testUser);
		((User)testUser).saveSchool("BARD");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.remove(testUser);
		testUser = null;
		db = null;
	}

	@Test
	public void testRemoveSaveSchool() {
		//Adds a school to the user
		//Tries to remove a known school
		boolean removed = ((User)testUser).unsaveSchool("BARD");
		Assert.assertTrue("User removed a known school from their list", removed);
	}

}
