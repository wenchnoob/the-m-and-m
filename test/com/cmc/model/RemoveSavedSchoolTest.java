package com.cmc.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;

public class RemoveSavedSchoolTest {
	

	private DBInteractions db;
	private Account testUser;
	
	
	@Before
	public void setUp() throws Exception {
		db = DBInteractions.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		db.save(testUser);
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
		boolean saved = ((User)testUser).saveSchool("BARD");
		Assert.assertTrue("User saved a known school from the database.", saved);
		boolean removed = ((User)testUser).unsaveSchool("BARD");
		Assert.assertTrue("User removed a known school from their list", removed);
	}

}
