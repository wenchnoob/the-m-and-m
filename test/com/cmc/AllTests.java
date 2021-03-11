package com.cmc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cmc.database.DatabaseTest;
import com.cmc.model.AccountTest;
import com.cmc.controller.AccountControllerTest;
import com.cmc.controller.AdminFunctionalityControllerTest;
import com.cmc.controller.SearchControllerTest;
import com.cmc.controller.UniversityControllerTest;

import junit.framework.TestSuite;
import system.ChangeStatusTest;
import system.ChangeTypeTest;
import system.EditUserInfoTest;
import system.LoginTest;
import system.LogoutTest;
import system.SaveSchoolTest;
import system.SearchTest;

@RunWith(Suite.class)
@SuiteClasses({DatabaseTest.class, AccountTest.class, AccountControllerTest.class,
	AdminFunctionalityControllerTest.class, UniversityControllerTest.class, LoginTest.class,
	SearchControllerTest.class,ChangeStatusTest.class,ChangeTypeTest.class,EditUserInfoTest.class,
	SearchTest.class, SaveSchoolTest.class, LogoutTest.class})
public class AllTests extends TestSuite {

}
