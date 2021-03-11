package com.cmc.controller;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;
import junit.framework.Assert;

/**
 * 
 * @author jmathias001
 *
 */


public class AccountControllerTest extends TestCase {


	private AccountController controller;
	private Account testAccount, testAdmin1, testAdmin2;

	@Override
	@Before
	protected void setUp() throws Exception {
		controller = AccountController.getInstance();
		testAccount = new User("Joe","Mathias","Who am i?","You","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin1 = new Admin("Brady","Bobington", "Bbob","Password", "Who am i?","You",true);
		testAdmin2 = new Admin("Zach","Kratz","ZKratz","Password", "Who am i?","You",true);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		testAccount = null;
		controller = null;

	}
	
	@Test
	public void testSomething() {
		
	}
}

