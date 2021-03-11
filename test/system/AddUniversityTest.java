package system;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.controller.AdminFunctionalityController;
import com.cmc.controller.UniversityController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;

import junit.framework.TestCase;

public class AddUniversityTest extends TestCase {
	
	ArrayList<String> emphases = new ArrayList<String>();
	private DBInteractions db = DBInteractions.getInstance();
	private UniversityController controller;
	private Account testAdmin;

	@Before
	public void setUp() throws Exception {
		controller = UniversityController.getInstance();
		testAdmin = new Admin("Wenchy", "Dutreuil", "", "", "admin", "admin", true);
		emphases.add("String");
		
	}

	@After
	public void tearDown() throws Exception {
		db.remove(testAdmin);
		
		db = null;
		testAdmin = null;
		emphases = null;
	}

	@Test
	public void testAddUniversity() {
		//Successful admin adding of university
		controller.addUniversity(testAdmin, "SCHOOL", "STATE", "LOCATION", "CONTROL", 1, 1, 1, 1, 1, 1, 
				1, emphases, 1.0f, 1.0f, 1.0f, 1.0f, 1);
		Assert.assertNotSame("This test makes sure the school is added",null, db.getUniversityByName("SCHOOL"));
		
		//failed admin attempting to add a school thats already in the database
		boolean success = controller.addUniversity(testAdmin, "BARD", "SCHOOL", "LOCATION", "CONTROL", 1, 1, 1, 1, 1, 1, 
				1, emphases, 1.0f, 1.0f, 1.0f, 1.0f, 1);
		Assert.assertFalse("This test makes sure that it fails because its already in the database",success);
		
	}

}
