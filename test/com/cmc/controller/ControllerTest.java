package com.cmc.controller;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {
	
	private DBInteractions db = DBInteractions.getInstance();
	
	@Before
	public void SetUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testLogin() {
		Account wenchy = db.getUserByUserName("admin");
		wenchy.logon("admin");
		wenchy.logout();
	}
	
	@Test
	public void testLogout() {
		
	}

}
