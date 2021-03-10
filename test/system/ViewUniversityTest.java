/**
 * 
 */
package system;

import static org.junit.Assert.*;

import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.controller.UniversityController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.University;

import junit.framework.TestCase;

public class ViewUniversityTest extends TestCase {
	private UniversityController controller;
	private DBInteractions db;
	private Account testUser;
	private University testUniversity;
	private String universityName;
	

	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = UniversityController.getInstance();
		testUniversity = new University("TEST", "A", "B", "C", 1, 1, 1, 1, 1, 1, 1, null, 1.0f, 1.0f, 1.0f, 1.0f, 1);
		universityName = "BARD";
	}

	
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	@Test
	public void testViewUniversity() {
		// Test viewing  a university that is actually in the database
		// Real Uni
		University got = controller.viewUniversity(universityName);
		Assert.assertNotSame("Ensure that the university being viewed is not null. Real Uni.", null, got);
	
		// Test viewing a university that is not actually in the database
		// Fake Uni
		got = controller.viewUniversity("fake");
		Assert.assertEquals("Ensure that the university being viewed is null. Fake Uni.", null, got);
	}

}