package com.cmc;

import java.util.ArrayList;
import java.util.List;

import com.cmc.controller.AccountController;
import com.cmc.controller.AdminFunctionalityController;
import com.cmc.controller.SearchController;
import com.cmc.controller.UniversityController;
import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.University;
import com.cmc.model.User;


public class DriverTest {


	public static void main(String[] args) {
		


	}
	
	public static void testLogout() {
		// Successful Logout
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		Account admin = AccountController.getInstance().logon("admin", "admin");
		controller.ChangeStatus(admin, DBInteractions.getInstance().getUserByUserName("ckalsow"), true);
		AccountController.getInstance().logout("ckalsow");
		System.out.println("Logged out Account: ");
		System.out.println(AccountController.getInstance().viewAccount("ckalsow"));
	}

	public static void testSearchUniversities() {
		SearchController controller = SearchController.getInstance();
		System.out.println("Successful search: ");
		List<University> results = controller.searchUniversity("Uni A", "", "", "", 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				new ArrayList<String>()
				);
		System.out.println(results);
		System.out.println();

		System.out.println("Failed search: ");
		results = controller.searchUniversity("Uni B", "", "", "", 
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				new ArrayList<String>()
				);
		System.out.println(results);
	}

	public static void testEditBasicUserInfo() {
		AccountController controller = AccountController.getInstance();

		// User editing own information
		System.out.println("User editing their own information: ");
		Account channa = controller.logon("ckalsow", "Channaiskool");
		controller.editBasicUserInfo(channa, channa , AccountController.ManagedField.LASTNAME, "Anderson");
		System.out.println(controller.viewAccount("ckalsow"));
		System.out.println();

		// Admin editing user information
		System.out.println("Admin editing user information");
		Account admin = controller.logon("admin", "admin");
		controller.editBasicUserInfo(admin, channa, AccountController.ManagedField.RECOVERY_QUESTION, "Three digits of pie?");
		System.out.println(controller.viewAccount("ckalsow"));
	}

	public static void testViewUniversity() {
		System.out.println(UniversityController.getInstance().viewUniversity("Uni A"));
	}

	public static void testEditBasicUniversityInfo() {
		UniversityController controller = UniversityController.getInstance();
		controller.editBasicUniversityInfo("admin", "Uni J", UniversityController.ManagedField.NAME, "Uni B");
		System.out.println(UniversityController.getInstance().viewUniversity("Uni B"));
		System.out.println();
		System.out.println("Current list of all universities: ");
	}
	
	public static void testChangeStatus() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		Account admin = AccountController.getInstance().logon("admin", "admin");
		controller.ChangeStatus(admin, DBInteractions.getInstance().getUserByUserName("ckalsow"), false);
		System.out.println(AccountController.getInstance().viewAccount("ckalsow"));
	}
	
	public static void testAddUser() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		controller.addUser("Kristian", "Kalsow", "kkiskool" , 
				"koool", "2+2?", "4", true, Account.AccountType.ADMIN);
		System.out.println("Newly added account: ");
		System.out.println(AccountController.getInstance().viewAccount("kkiskool"));
		
	}
	
	public static void testChangeUserType() {
		AdminFunctionalityController controller = AdminFunctionalityController.getInstance();
		Account admin = AccountController.getInstance().logon("kkiskool", "koool");
		controller.ChangeStatus(admin, DBInteractions.getInstance().getUserByUserName("ckalsow"), true);
		Account user = AccountController.getInstance().logon("ckalsow", "Channaiskool");	
		controller.changeUserType(admin, user, Account.AccountType.ADMIN);
		System.out.println(AccountController.getInstance().viewAccount("ckalsow"));
	}
	
	public static void testSaveSchool() {
		User user = (User)AccountController.getInstance().logon("kanderson", "Kristianaiskool");
		System.out.println("Testing save: ");
		user.saveSchool("Uni A");
		user.saveSchool("Uni B");
		System.out.println("Saved Universities: " + user.getSavedSchools());
		
		System.out.println("Testing unsave: ");
		user.unsaveSchool("Uni A");
		System.out.println("Saved Universities: " + user.getSavedSchools());
	}

}
