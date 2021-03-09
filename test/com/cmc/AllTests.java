package com.cmc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cmc.database.DatabaseTest;
import com.cmc.model.AccountTest;
import com.cmc.controller.AccountControllerTest;
import com.cmc.controller.AdminFunctionalityControllerTest;
import com.cmc.controller.UniversityControllerTest;

import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({DatabaseTest.class, AccountTest.class, AccountControllerTest.class,
	AdminFunctionalityControllerTest.class, UniversityControllerTest.class})
public class AllTests extends TestSuite {

}
