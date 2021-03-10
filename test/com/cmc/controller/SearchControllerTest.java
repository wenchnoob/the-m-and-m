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
	DBInteractions controller2 = DBInteractions.getInstance();
	List <University> flUnis = new ArrayList<University>();
	List <University> unisBySize = new ArrayList<University>();
	List <University> emptyList = new ArrayList<University>();
	
	@Before
	public void setUp() throws Exception {
		University uni1 = controller2.getUniversityByName("FLORIDA ACADEMIC UNIVERSITY");
		flUnis.add(uni1);
		University uni2 = controller2.getUniversityByName("FLORIDA STATE");
		flUnis.add(uni2);
		University uni3 = controller2.getUniversityByName("FLORIDA TECH");
		flUnis.add(uni3);
		
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
		
		
	}

	@After
	public void tearDown() throws Exception {
	}



	//searches for all universities in Florida
	@Test
	public void testfilterByState() {
		List <University> searchResults = controller.searchUniversity("", "FLORIDA", "", "",
				-1, -1, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		Assert.assertEquals("Ensure that search finds correct Universities", flUnis, searchResults);
		Assert.assertEquals("Ensure that search finds nothing. Fake State.", emptyList, controller.searchUniversity("", "Mars", "", "",
				-1, -1, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>()));
	}
	
	//searches for all universities with 34000-36000 students
	@Test
	public void testfilterByNumStudents() {
		List <University> searchResults = controller.searchUniversity("", "", "", "",
				34000, 36000, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>());
		Assert.assertEquals("Ensure that search finds correct Universities", unisBySize, searchResults);
		Assert.assertEquals("Ensure that search finds nothing. Fake State.", emptyList, controller.searchUniversity("", "", "", "",
				500000, 80000000, (float) -1, (float) -1, -1, -1, -1,-1, -1,
				-1,(float) -1, (float) -1, -1, -1, (float) -1, (float) -1,
				(float) -1, (float) -1,-1, -1, -1, -1,
				-1, -1, new ArrayList<String>()));
	}

}
