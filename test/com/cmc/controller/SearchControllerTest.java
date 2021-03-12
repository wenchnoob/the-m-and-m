/**
 * UNIT TESTING
*/

package com.cmc.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cmc.database.DBInteractions;

import com.cmc.model.University;

public class SearchControllerTest {
	SearchController controller = SearchController.getInstance();
	com.cmc.database.DBInteractions controller2 = DBInteractions.getInstance();
	List <University> flUnis = new ArrayList<University>();
	List <University> unisBySize = new ArrayList<University>();
	List <University> uniBySizeandState = new ArrayList<University>();
	List<University> emptyList = new ArrayList<University>();
	List <University> smallUni = new ArrayList<University>();
	List <University> largeUni = new ArrayList<University>();
	
	private University testUniversity2;
	private University testUniversity5;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		University uni1 = controller2.getUniversityByName("FLORIDA ACADEMIC UNIVERSITY");
		flUnis.add(uni1);
		University uni2 = controller2.getUniversityByName("FLORIDA STATE");
		flUnis.add(uni2);
		University uni3 = controller2.getUniversityByName("FLORIDA TECH");
		flUnis.add(uni3);

		University uni = controller2.getUniversityByName("FLORIDA STATE");
		uniBySizeandState.add(uni);

		University uni1a = controller2.getUniversityByName("AUBURN");
		unisBySize.add(uni1a);
		University uni2a = controller2.getUniversityByName("FLORIDA STATE");
		unisBySize.add(uni2a);
		University uni3a = controller2.getUniversityByName("NOTRE DAME");
		unisBySize.add(uni3a);
		University uni4 = controller2.getUniversityByName("OREGON STATE");
		unisBySize.add(uni4);
		University uni5 = controller2.getUniversityByName("QUEENS");
		unisBySize.add(uni5);
		University uni6 = controller2.getUniversityByName("SUNY ALBANY");
		unisBySize.add(uni6);
		University uni7 = controller2.getUniversityByName("TEMPLE");
		unisBySize.add(uni7);
		University uni8 = controller2.getUniversityByName("UNIVERSITY OF ALABAMA");
		unisBySize.add(uni8);
		University uni9 = controller2.getUniversityByName("UNIVERSITY OF PENNSYLVANIA");
		unisBySize.add(uni9);
		University uni10 = controller2.getUniversityByName("UNIVERSITY OF TOLEDO");
		unisBySize.add(uni10);
		
		University testUniversity5 = new University("LOWATTENDS", "", "", "", 1000, 1, 1, 1, 1, 1, 1, null,
				1.0f, 1.0f, 1.0f, 1.0f, 1);
		controller2.save(testUniversity5);
		smallUni.add(testUniversity5);
		//smallUni.add(controller2.getUniversityByName("SCHOOL"));
		smallUni.add(controller2.getUniversityByName("TEST"));
		
		University testUniversity2 = new University("HIGHATTENDS", "", "", "", 100000, 1, 1, 1, 1, 1, 1, null,
				1.0f, 1.0f, 1.0f, 1.0f, 1);
		controller2.save(testUniversity2);
		largeUni.add(testUniversity2);
	}

	/**
	 * @throws java.lang.Exception
	 * not adding anything to the database, so we don't need to tear down
	 */
	@After
	public void tearDown() throws Exception {
		controller2.remove(testUniversity5);
		controller2.remove(testUniversity2);
	}
	//Below are extra tests removed during progress meeting but are still useful
	//searches all schools in florida
	@Test
	public void testMainSearchScenario() {
		List <University> searchResults = controller.searchUniversity("", "FLORIDA", "", "",
				-1, -1, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		
		for (int I = 0; I < searchResults.size(); I++) {

			//Assert.assertEquals("Ensure that search finds correct Universities", flUnis, searchResults);
			Assert.assertEquals(flUnis.get(I), searchResults.get(I));
			
		}

	}

	//searches all schools with a 34000-36000 students
	@Test
	public void testMainSearchScenario2() {
		List <University> searchResults = controller.searchUniversity("", "", "", "",
				34000, 36000, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		for (int I = 0; I < searchResults.size(); I++) {

			//Assert.assertEquals("Ensure that search finds correct Universities", unisBySize, searchResults);
			Assert.assertEquals(unisBySize.get(I), searchResults.get(I));
			
		}

	}

	//searches all schools in Florida with 34000-36000 students
	@Test
	public void testMainSearchScenario3() {
		List <University> searchResults = controller.searchUniversity("", "FLORIDA", "", "",
				34000, 36000, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		for (int I = 0; I < searchResults.size(); I++) {

			//Assert.assertEquals("Ensure that search finds correct Universities", uniBySizeandState, searchResults);
			Assert.assertEquals(uniBySizeandState.get(I), searchResults.get(I));
			
		}
		

	}
	
	//searches all schools with less that 1500 students
	@Test
	public void testMainSearchScenario4() {
		List <University> searchResults = controller.searchUniversity("", "", "", "",
				-1, 1500, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		//System.out.println(smallUni);
		//System.out.print(searchResults);
		for (int I = 0; I < searchResults.size(); I++) {
			//Assert.assertEquals("Ensure that search finds correct Universities", uniBySizeandState, searchResults);
			Assert.assertEquals(smallUni.get(I), searchResults.get(I));
			
		}
		

	}
	
	//searches all schools with more than 50000 students
	@Test
	public void testMainSearchScenario5() {
		List <University> searchResults = controller.searchUniversity("", "", "", "",
				50000, -1, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		for (int I = 0; I < searchResults.size(); I++) {

			//Assert.assertEquals("Ensure that search finds correct Universities", uniBySizeandState, searchResults);
			Assert.assertEquals(largeUni.get(I), searchResults.get(I));
			
		}
		

	}

	//searches all schools on Mars and returns empty list
	@Test
	public void testAlternateSearchScenario() {
		
		Assert.assertEquals("Ensure that search finds nothing. Fake State.", emptyList, controller.searchUniversity("", "Mars", "", "",
				-1, -1, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>()));
	}

	//searches all schools with a ridiculous number of students and returns empty list
	@Test
	public void testAlternateSearchScenario2() {

		Assert.assertEquals("Ensure that search finds nothing. Fake numStudents.", emptyList, controller.searchUniversity("", "", "", "",
				500000, 80000000, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>()));
	}

	//searches for schools in Alaska and returns an empty list
	@Test
	public void testAlternateScenario3() {

		Assert.assertEquals("Ensure that search finds nothing. Fake numStudents.", emptyList, controller.searchUniversity("", "ALASKA", "", "",
				34000, 36000, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>()));
	}
	
}
