package com.cmc.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DatabaseTest extends TestCase {
	
	private DBInteractions db;
	private Account testUser;
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		db = DBInteractions.getInstance();
		testUser = new User("Channa", "Kalsow", "ckalsow", "Channaiskool",
				"What's your name?", "Channa", true, null);
	}
	
	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		db = null;
		testUser = null;
	}

	@Test
	public void testGetUser() {
		Account got = db.getUserByUserName(testUser.getUsername());
		Assert.assertEquals("Tests that when we request the test user by username, we get a object that is \"equal\" to the testUser object. ",testUser, got);
	}
	
	@Test
	public void testGetNullUser() {
		Account got = db.getUserByUserName("fake");
		Assert.assertEquals("Tests that requesting a username that is not in the database returns a null user object", null, got);
	}
	
	@Test
	public void testSaveUser() {
		Assert.assertTrue("Asserts that the database can save a valid user object.", db.save(testUser));
	}
	
}
