package com.cmc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cmc.controller.ControllerTest;
import com.cmc.database.DatabaseTest;
import com.cmc.model.EntityTest;

import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ControllerTest.class, DatabaseTest.class, EntityTest.class})
public class AllTests extends TestSuite {

}
