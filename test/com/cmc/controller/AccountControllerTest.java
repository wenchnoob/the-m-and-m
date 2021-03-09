package com.cmc.controller;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cmc.model.Account;
import com.cmc.model.User;
import com.cmc.model.UserSchool;

import junit.framework.TestCase;

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
	
	}
	//Tests changing the first name
	@Test
	public void editBasicUserInfoFirstNameTest() {
		try {
			setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertNotEquals("Bob", testAccount.getFirstName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.FIRSTNAME, "Bob");
		Assertions.assertEquals("Bob", testAccount.getFirstName());
	}
	//Tests changing the last name
	@Test
	public void editBasicUserInfoLastNameTest() {
		try {
			setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertNotEquals("Kratz", testAccount.getLastName());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.LASTNAME, "Kratz");
		Assertions.assertEquals("Kratz",testAccount.getLastName());
	}
	//Tests changing the password
	@Test
	public void editBasicUserInfoPasswordTest() {
		try {
			setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertNotEquals("newPassword", testAccount.getPassword());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.PASSWORD, "newPassword");
		Assertions.assertEquals("newPassword",testAccount.getPassword());
	}
	//Tests changing the Recovery Question
	@Test
	public void editBasicUserInfoRecoveryQuestionTest() {
		try {
			setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertNotEquals("Who are you?", testAccount.getRecoveryQuestion());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.RECOVERY_QUESTION, "Who are you?");
		Assertions.assertEquals("Who are you?",testAccount.getRecoveryQuestion());
	}
	//Tests changing the Recovery Answer
	@Test
	public void editBasicUserInfoRecoveryAnswerTest() {
		try {
			setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertNotEquals("you", testAccount.getRecoveryAnswer());
		controller.editBasicUserInfo(testAccount, testAccount, AccountController.ManagedField.RECOVERY_ANSWER, "you");
		Assertions.assertEquals("you",testAccount.getRecoveryAnswer());
	}
}

