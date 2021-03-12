package com.cmc.model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class UniversityTest extends TestCase {
	
	private University testUniversity;
	ArrayList<String> emphases = new ArrayList<String>();
	

	protected void setUp() throws Exception {
		super.setUp();
		emphases.add("String");
		testUniversity = new University("SCHOOL", "STATE", "LOCATION", "CONTROL", 1, 1, 1, 1, 1, 1, 
				1, emphases, 1.0f, 1.0f, 1.0f, 1.0f, 1);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		emphases = null;
		testUniversity = null;
	}
	
	@Test
	public void testUniversityObjectInitilization() {
		Assert.assertEquals("tests to make sure name is initialized", testUniversity.getName(), "SCHOOL");
		Assert.assertEquals("tests to make sure state is initialized", testUniversity.getState(), "STATE");
		Assert.assertEquals("tests to make sure location is initialized",testUniversity.getLocation(), "LOCATION");
		Assert.assertEquals("tests to make sure control is initialized", testUniversity.getControl(), "CONTROL");
		Assert.assertEquals("tests to make sure numStudents is initialized", testUniversity.getNumStudents(), 1);
		Assert.assertEquals("tests to make sure satMath is initialized", testUniversity.getSatMath(), 1);
		Assert.assertEquals("tests to make sure satVerbal is initialized", testUniversity.getSatVerbal(), 1);
		Assert.assertEquals("tests to make sure expenses is initialized", testUniversity.getExpenses(), 1);
		Assert.assertEquals("tests to make sure numOfApps is initialized", testUniversity.getNumOfApps(), 1);
		Assert.assertEquals("tests to make sure academicScale is initialized", testUniversity.getAcademicScale(), 1);
		Assert.assertEquals("tests to make sure is socialScale initialized", testUniversity.getSocialScale(), 1);
		Assert.assertEquals("tests to make sure is emphases initialized", testUniversity.getEmphases(), emphases);
		Assert.assertTrue("tests to make sure is perFemale initialized", Double.valueOf(testUniversity.getPerFemale()) == 1.0);
		Assert.assertTrue("tests to make sure is perAdmitted initialized", Double.valueOf(testUniversity.getPerAdmitted()) == 1.0);
		Assert.assertTrue("tests to make sure is perFinAid initialized", Double.valueOf(testUniversity.getPerFinAid()) == 1.0);
		Assert.assertTrue("tests to make sure is perEnrolled initialized", Double.valueOf(testUniversity.getPerEnrolled()) == 1.0);
		Assert.assertEquals("tests to make sure is qualityLife initialized", testUniversity.getQualityLife(), 1);
		
	}

}
