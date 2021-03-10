package com.cmc.controller;

import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;

import junit.framework.TestCase;

public class AdminFunctionalityControllerTest extends TestCase {
	
	private AdminFunctionalityController controller;
	private DBInteractions db = DBInteractions.getInstance();
	
	private Account testUser;
	private Account testAdmin;
	private Account testAdmin2;
	
	private String usernameToBeAdded = "kkiskool";
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = AdminFunctionalityController.getInstance();
		testUser = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin = new Admin("Wenchy", "Dutreuil", "", "", "admin", "admin", true);
		testAdmin2 = new Admin("Wenchy2", "Dutreuil2", "", "", "admin2", "admin2", true);
	}
	
	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		
		db.remove(testUser);
		db.remove(testAdmin);
		db.remove(testAdmin2);
		db.remove(db.getUserByUserName(usernameToBeAdded));
		
		db = null;
		controller = null;
		testAdmin = null;
		testUser = null;
	}
	
	@Test
	public void testAddUser() {
		// Failed add
		// User attempting to add a user
		controller.addUser(testUser, "Kristian", "Kalsow", usernameToBeAdded, 
				"koool", "2+2?", "4", true, Account.AccountType.ADMIN);
		Assert.assertSame("The user should have failed to add another user.", null, db.getUserByUserName(usernameToBeAdded)); 
		
		// Successful add
		// Admin attempting to add a user
		controller.addUser(testAdmin, "Kristian", "Kalsow", usernameToBeAdded, 
				"koool", "2+2?", "4", true, Account.AccountType.ADMIN);
		Assert.assertNotEquals("The admin should have succeeded in adding another user.", null, db.getUserByUserName(usernameToBeAdded));
	}
	

}
