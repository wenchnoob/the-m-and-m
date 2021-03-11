package com.cmc.controller;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.University;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;

public class UniversityControllerTest extends TestCase {
	
	private DBInteractions db;
	private UniversityController controller;
	private University testUniversity;
	private Account testAccount;
	private Account testAdmin;
	
	@Override
	@Before
	protected void setUp() throws Exception {
		controller = UniversityController.getInstance();
		db = DBInteractions.getInstance();
		testUniversity = new University("TEST", "A", "B", "C", 1, 1, 1, 1, 1, 1, 1, null,
				1.0f, 1.0f, 1.0f, 1.0f, 1);
		testAdmin = new Admin("Brady","Bobington", "Bbob","Password", "Who am i?","You",true);
		testAccount = new User("Joe","Mathias","Who am i?","You","Jmath","Password",true,new HashMap<String,UserSchool>());
		db.save(testAccount);
		db.save(testUniversity);
		db.save(testAdmin);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		db.remove(testUniversity);
		db.remove(testAccount);
		db.remove(testAdmin);
		testAccount = null;
		testAdmin = null;
		testUniversity = null;
		controller = null;
		db = null;
	}
	
	@Test
	public void testViewUniversity() {
		// Test viewing  a university that is actually in the database
		// Real Uni
		University got = controller.viewUniversity("BARD");
		Assert.assertNotSame("Ensure that the university being viewed is not null. Real Uni.", null, got);
	
		// Test viewing a university that is not actually in the database
		// Fake Uni
		got = controller.viewUniversity("fake");
		Assert.assertEquals("Ensure that the university being viewed is null. Fake Uni.", null, got);
	}

	@Test
	public void testEditBasicUniversityInfo() {
		
		// Failed test
		// User trying to edit a universities information and failing
		// Name: USER -> FAILED
		String initialName = testUniversity.getName();
		controller.editBasicUniversityInfo(testAccount.getUsername(), testUniversity.getName(), 
				UniversityController.ManagedField.NAME, "Apple Uni");
		Assert.assertEquals("The name of the university should not change.", initialName, testUniversity.getName());
		
		// Admin trying to edit the information with an incorrect object value
		boolean success = controller.editBasicUniversityInfo(testAccount.getUsername(), testUniversity.getName(), 
				UniversityController.ManagedField.NUM_STUDENTS, "100");
		Assert.assertFalse("The admin should fail to modify the information.", success);
		
		// Successful test
		// Testing editing the name of a university
		// Name: ADMIN -> SUCCESS
		Assert.assertNotEquals("The university being test should not have the name Apple Uni. ADMIN -> SUCCESS", "Apple Uni", testUniversity.getName());
		success = controller.editBasicUniversityInfo(testAdmin.getUsername(), testUniversity.getName(), 
				UniversityController.ManagedField.NAME, "Apple Uni");
		Assert.assertTrue("The admin should succeed in modifying the information.", success);
		testUniversity = db.getUniversityByName("Apple Uni");
		Assert.assertEquals("The university being tested should now have the name Apple Uni. ADMIN -> SUCCESS", "Apple Uni", testUniversity.getName());
	}

}
