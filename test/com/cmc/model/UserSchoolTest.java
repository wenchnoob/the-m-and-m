package com.cmc.model;

import java.util.HashMap;

import com.cmc.database.DBInteractions;

import org.junit.*;
import junit.framework.TestCase;

public class UserSchoolTest extends TestCase {
	
	private DBInteractions db = DBInteractions.getInstance();
	private Account testUser;
	
	protected void setUp() throws Exception {
		super.setUp();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		testUser = null;
	}
	
	public void testAccountObjectInitialization() {
		University uni = db.getUniversityByName("Bard");
		UserSchool school = new UserSchool(uni, testUser.getUsername());
		
		Assert.assertEquals(uni, school.getUniversity());
		Assert.assertEquals(testUser.getUsername(), school.getOwner());
	}
}
