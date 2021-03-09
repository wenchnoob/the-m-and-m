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
		testAccount = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		testAdmin1 = new Admin("Brady","Bobington", "Who am i?","You","Bbob","Password",true);
		testAdmin2 = new Admin("Zach","Kratz","Who are you?","I am me","ZKratz","Password",true);
	}
	
	@Override
	@After
	protected void tearDown() throws Exception {
		testAccount = null;
		controller = null;
	}
	
	//Tests changing the first name
	@Test
	public void testEditBasicUserInfoFirstName() {
		// Test a basic user attempting to change their own first name
		// Name: BASIC_USER -> BASIC_USER
		Assert.assertNotSame("Bobby", testAccount.getFirstName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAccount.getFirstName());
	}
		// Test a basic user attempting to change their own first name
		// Name: ADMIN -> BASIC_USER
	@Test
	public void testAdminEditBasicUserInfoFirstName() {
		// Test an admin changing a basic user's name;
		Assert.assertNotSame("Bobby", testAccount.getFirstName());
		controller.editBasicUserInfo(testAdmin1, testAccount, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAccount.getFirstName());
	}
	// Test an admin changing another admin's first name.
	@Test 
	public void testAdminEditAdminFirstName() {
		Assert.assertNotSame("Bobby", testAdmin1.getFirstName());
		controller.editBasicUserInfo(testAdmin2, testAdmin1, AccountController.ManagedField.FIRSTNAME, "Bobby");
		Assert.assertEquals("Bobby", testAdmin1.getFirstName());
	}
	//Tests a basic user attempting to change an admins name
	//Makes sure that it fails
	@Test
	public void testBasicUserEditsAdminFirstName() {
		Assert.assertEquals("Brady", testAdmin1.getFirstName());
		controller.editBasicUserInfo(testAccount, testAdmin1, AccountController.ManagedField.FIRSTNAME, "Bob");
		Assert.assertEquals("Brady", testAdmin1.getFirstName());
	}
	
	//Tests changing the last name
	@Test
	public void testEditBasicUserInfoLastName() {
		Assert.assertNotSame("Kratz", testAccount.getLastName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.LASTNAME, "Kratz");
		Assert.assertEquals("Kratz",testAccount.getLastName());
	}
	
	//Tests changing the password
	@Test
	public void testEditBasicUserInfoPassword() {
		Assert.assertNotSame("newPassword", testAccount.getPassword());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.PASSWORD, "newPassword");
		Assert.assertEquals("newPassword", testAccount.getPassword());
	}
	
	//Tests changing the Recovery Question
	@Test
	public void testEditBasicUserInfoRecoveryQuestion() {
		Assert.assertNotSame("Who are you?", testAccount.getRecoveryQuestion());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.RECOVERY_QUESTION, "Who are you?");
		Assert.assertEquals("Who are you?",testAccount.getRecoveryQuestion());
	}
	
	//Tests changing the Recovery Answer
	@Test
	public void testEditBasicUserInfoRecoveryAnswer() {
		Assert.assertNotSame("you", testAccount.getRecoveryAnswer());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.RECOVERY_ANSWER, "you");
		Assert.assertEquals("you",testAccount.getRecoveryAnswer());
	}
	
}

