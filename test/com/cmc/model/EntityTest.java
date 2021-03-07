package com.cmc.model;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Map;

import com.cmc.model.Account;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

public class EntityTest extends TestCase {
	
	public EntityTest(String name) {
		super(name);
	}
	
	public void testAccount() {
		String firstName = "mike";
		String lastName = "bud";
		String username = "mbud";
		String password = "heyyy";
		String rQ = "";
		String rA = "";
		boolean enabled = true;
		Map<String, UserSchool> saved = null;
		Account testAccount = new User(firstName, lastName, username, password, rQ, rA, enabled, saved);
		
		Assert.assertEquals(testAccount.getFirstName(), firstName);
		Assert.assertEquals(testAccount.getUsername(), username);
	}

	
	public static Test suite() {
		TestSuite ts = new TestSuite();
		ts.addTest(new EntityTest("testAccount"));
		return ts;
	}
}
