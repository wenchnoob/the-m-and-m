package com.cmc.database;

import org.junit.Test;

import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DatabaseTest extends TestCase {
	
	private DBInteractions db = DBInteractions.getInstance();

	@Test
	public void testGetUser() {
		Account got = db.getUserByUserName("admin");
		Account expected = new Admin("Wenchy", "Dutreuil", "admin", "admin", "", "", true);
		Assert.assertEquals(got, expected);
	}
	
	@Test
	public void testGetNullUser() {
		Account got = db.getUserByUserName("fake");
		Assert.assertEquals(got, null);
	}
	
	@Test
	public void testSaveUser() {
		User testUser = new User("Channa", "Kalsow", "ckalsow", "Channaiskool",
				"What's your name?", "Channa", true, null);
		Assert.assertTrue(db.save(testUser));
	}
	
}
