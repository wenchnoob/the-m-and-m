/**
 * 
 */
package com.cmc.model;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import com.cmc.database.DBInteractions;

/**
 * @author zkratz001
 *
 */
public class ViewUniversityTest {
	
	private DBInteractions db = DBInteractions.getInstance();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {}

	@Test
	public void testViewUniversity() {
		// Should return a university object.
		Assert.assertNotSame(null, db.getUniversityByName("BARD"));

		// Should return null.
		Assert.assertSame(null, db.getUniversityByName("pi"));
	}

}
