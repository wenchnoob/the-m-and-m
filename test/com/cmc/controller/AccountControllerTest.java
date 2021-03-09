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
	
	private Account testAccount;
	private AccountController controller;

	@Before
	public void setup() throws Exception {
		testAccount = new User("Joe","Mathias","Who am i?","Me","Jmath","Password",true,new HashMap<String,UserSchool>());
		controller = AccountController.getInstance();
	}
	
	@After
	public void teardown() throws Exception {
		testAccount = null;
		controller = null;
	}
	
	//Tests changing the first name
	@Test
	public void testEditBasicUserInfoFirstName() {
		Assert.assertNotSame("Bob", testAccount.getFirstName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.FIRSTNAME, "Bob");
		Assert.assertEquals("Bob", testAccount.getFirstName());
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
		Assert.assertEquals("newPassword", testAccount.getPassword());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.PASSWORD, "newPassword");
		Assert.assertEquals("newPassword",testAccount.getPassword());
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

