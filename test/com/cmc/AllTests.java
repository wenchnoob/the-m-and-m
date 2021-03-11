package com.cmc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cmc.database.DatabaseTest;
import com.cmc.model.AccountTest;
import com.cmc.model.UniversityTest;
import com.cmc.model.UserSchoolTest;
import com.cmc.model.UserTest;
import com.cmc.controller.AccountControllerTest;
import com.cmc.controller.AdminFunctionalityControllerTest;
import com.cmc.controller.SearchControllerTest;
import com.cmc.controller.UniversityControllerTest;

import junit.framework.TestSuite;
import system.AddUniversityTest;
import system.AddUserTest;
import system.ChangeStatusTest;
import system.ChangeTypeTest;
import system.EditUserInfoTest;
import system.LoginTest;
import system.LogoutTest;
import com.cmc.model.RemoveSavedSchoolTest;
import system.SaveSchoolTest;
import system.SearchTest;
import system.ViewAllUniversitiesTest;
import system.ViewAllUsersTest;
import system.ViewUniversityTest;
import system.ViewUserTest;

@RunWith(Suite.class)
@SuiteClasses({AccountControllerTest.class, AdminFunctionalityControllerTest.class, SearchControllerTest.class, UniversityControllerTest.class,
	DatabaseTest.class, AccountTest.class, RemoveSavedSchoolTest.class, UniversityTest.class, UserSchoolTest.class, UserTest.class, AddUniversityTest.class,
	AddUserTest.class, ChangeStatusTest.class, ChangeTypeTest.class, EditUserInfoTest.class, LoginTest.class, LogoutTest.class, SaveSchoolTest.class, 
	SearchTest.class, ViewAllUniversitiesTest.class, ViewAllUsersTest.class, ViewUniversityTest.class, ViewUserTest.class, UniversityTest.class })
public class AllTests extends TestSuite {

}
