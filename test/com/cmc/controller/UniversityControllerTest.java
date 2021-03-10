package com.cmc.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.University;

import junit.framework.TestCase;

public class UniversityControllerTest extends TestCase {
	
	private DBInteractions db;
	private UniversityController controller;
	private University testUniversity;
	
	@Override
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		controller = UniversityController.getInstance();
		db = DBInteractions.getInstance();
		testUniversity = new University("TEST", "A", "B", "C", 1, 1, 1, 1, 1, 1, 1, null,
				1.0f, 1.0f, 1.0f, 1.0f, 1);
		db.save(testUniversity);
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		db.remove(testUniversity);
		testUniversity = null;
		controller = null;
		db = null;
	}
	
	@Test
	public void testViewUniversity() {
		// Test viewing  a university that is actually in the database
		// Real Uni
		University got = controller.viewUniversity(testUniversity.getName());
		Assert.assertNotSame("Ensure that the university being viewed is not null. Real Uni.", null, got);
	
		// Test viewing a university that is not actually in the database
		// Fake Uni
		got = controller.viewUniversity("fake");
		Assert.assertEquals("Ensure that the university being viewed is null. Fake Uni.", null, got);
	}

	@Test
	public void testEditBasicUniversityInfo() {
		// Testing 
	}

}
