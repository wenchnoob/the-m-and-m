/**
 * 
 */
package com.cmc.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kanderson003
 *
 */
public class UserTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	}
	public void testSavetoUserSchools() {
		// Tries to save a null school
		boolean saved = ((User)testUser).saveSchool(null);
		Assert.assertFalse("Method is call with no school to be saved.", saved);
		
		// Tries to save a school that is not in the database
		saved = ((User)testUser).saveSchool("Fuddruckers University");
		Assert.assertFalse("User tries to save an unknown school.", saved);
	
		// Tries to save a know school	
		saved = ((User)testUser).saveSchool("BARD");
		Assert.assertTrue("User saved a known school from the database.", saved);
	}
}
