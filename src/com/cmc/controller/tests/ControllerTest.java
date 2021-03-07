package com.cmc.controller.tests;


import com.cmc.database.DBInteractions;
import com.cmc.model.Account;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ControllerTest extends TestCase {
	
	private DBInteractions db = DBInteractions.getInstance();
	
	public ControllerTest(String name) {
		super (name);
	}
	
	public void testLogin() {
		Account wenchy = db.getUserByUserName("admin");
		wenchy.logon("admin");
		Assert.assertTrue(wenchy.isLoggedOn());
		wenchy.logout();
		Assert.assertTrue(!wenchy.isLoggedOn());
	}
	
	public void testLogout() {
		
	}
	
	public static Test suite() {
		TestSuite ts = new TestSuite();
		ts.addTest(new ControllerTest("testLogin"));
		ts.addTest(new ControllerTest("testLogout"));
		return ts;
	}

}
