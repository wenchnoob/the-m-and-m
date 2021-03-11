package system;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cmc.database.DBInteractions;
import com.cmc.model.University;

import junit.framework.TestCase;

public class ViewAllUniversitiesTest extends TestCase {

private DBInteractions db;
	
	protected void setUp() throws Exception {
		super.setUp();
		db = DBInteractions.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		db = null;
	}

	
	@Test
	public void testViewAllUniversities() {
		// Test case, as long as the database is not empty
		// this use case should never fail.
		List<University> unis = db.getAllUniversities();
		Assert.assertFalse("If there is at least one user in the databsse the returned list should never be empty." , unis.size() <= 0);
	}

}
