package com.cmc.controller;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cmc.model.Account;
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
	private Account testAccount;
	
	@Override
	@Before
	protected void setUp() throws Exception {
		controller = AccountController.getInstance();
		testAccount = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
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
		// Tests Changing ther first name of a user
		// Name: BASIC_USER -> BASIC_USER
		Assert.assertNotSame("Bob", testAccount.getFirstName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.FIRSTNAME, "Bob");
		Assert.assertEquals("Bob", testAccount.getFirstName());
		
		// Test a basic user attempting to change their own first name
		
		// Test an admin changin a basic user's name;
		
		// Teat an admin changing another admin's name.
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

