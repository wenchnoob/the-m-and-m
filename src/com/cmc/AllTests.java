package com.cmc;

import com.cmc.controller.tests.ControllerTest;
import com.cmc.database.tests.DatabaseTest;
import com.cmc.model.tests.EntityTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public AllTests(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite allTest = new TestSuite();
		
		allTest.addTest(DatabaseTest.suite());
		allTest.addTest(EntityTest.suite());
		allTest.addTest(ControllerTest.suite());
		
		
		return allTest;
	}
}
