package com.cmc.model;

import junit.framework.TestCase;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.cmc.database.DBInteractions;

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
	public void testSaveSchool() {
		// Tries to save a null school
		boolean saved = ((User)testUser).saveSchool(null);
		Assert.assertFalse("Method is call with no school to be saved.", saved);
		
		// Tries to save a school that is not in the database
		saved = ((User)testUser).saveSchool("Fuddruckers University");
		Assert.assertFalse("User tries to save an unknown school.", saved);
		
		// Tries to save a know school
		saved = ((User)testUser).saveSchool("BARD");
		Assert.assertTrue("User saved a known school from the database.", saved);
	}

}
