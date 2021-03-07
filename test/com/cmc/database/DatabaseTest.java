package com.cmc.database;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DatabaseTest extends TestCase {
	
	private DBInteractions db = DBInteractions.getInstance();

	public DatabaseTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void testGetUser() {
		Account got = db.getUserByUserName("admin");
		Account expected = new Admin("Wenchy", "Dutreuil", "admin", "admin", "", "", true);
		Assert.assertEquals(got, expected);
	}
	
	public void testGetNullUser() {
		Account got = db.getUserByUserName("fake");
		Assert.assertEquals(got, null);
	}
	
	public void testSaveUser() {
		User testUser = new User("Channa", "Kalsow", "ckalsow", "Channaiskool",
				"What's your name?", "Channa", true, null);
		Assert.assertTrue(db.save(testUser));
	}
	
	public static Test suite() {
		TestSuite ts = new TestSuite();
		ts.addTest(new DatabaseTest("testGetUser"));
		ts.addTest(new DatabaseTest("testGetNullUser"));
		ts.addTest(new DatabaseTest("testSaveUser"));
		return ts;
	}
}
