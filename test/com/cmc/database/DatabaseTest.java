package com.cmc.database;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cmc.model.Account;
import com.cmc.model.University;
import com.cmc.model.User;

import org.junit.*;
import junit.framework.TestCase;

public class DatabaseTest extends TestCase {
	
	private DBInteractions db;
	private Account testUser;
	private University testUniversity;
	ArrayList<String> emphases = new ArrayList<String>();
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		db = DBInteractions.getInstance();
		testUser = new User("Channa", "Kalsow", "ckalsow", "Channaiskool",
				"What's your name?", "Channa", true, null);
		testUniversity = new University("SCHOOL", "STATE", "LOCATION", "CONTROL", 1, 1, 1, 1, 1, 1, 
				1, emphases, 1.0f, 1.0f, 1.0f, 1.0f, 1);
	}
	
	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		db.remove(testUser);
		db.remove(testUniversity);
		db = null;
		testUser = null;
	}

	// Black Box Test
	@Test
	public void testGetUser() {
		Account got = db.getUserByUserName(testUser.getUsername());
		Assert.assertEquals("Tests that when we request the test user by username, we get a object that is \"equal\" to the testUser object. ",testUser, got);
	}
	
	// Black Box Test
	@Test
	public void testGetNullUser() {
		Account got = db.getUserByUserName("fake");
		Assert.assertEquals("Tests that requesting a username that is not in the database returns a null user object", null, got);
	}
	
	// Black Box Test
	@Test
	public void testSaveUser() {
		Assert.assertTrue("Asserts that the database can save a valid user object.", db.save(testUser));
	}
	// Black Box Test
	@Test
	public void testGetUniversityByName() {
		University realUniversity = db.getUniversityByName("BARD");
		Assert.assertEquals("Asserts that the university can be called by name", "BARD", realUniversity.toString());
	}
	
	@Test
	public void testSaveUniversity() {
		Assert.assertTrue("Asserts that the database can save a valid user object.", db.save(testUniversity));
	}
	
}
