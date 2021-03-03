/**
 * 
 */
package com.cmc.controller;

import java.util.*;
import com.cmc.PsuedoDatabase;
import com.cmc.model.University;

/**
 * @author Channa Kalsow and Kristiana Anderson and Wenchy
 *
 */
public class SearchController {
	
	private static SearchController self;
	
	private SearchController() {
		
	}
	
	/**
	 * singleton
	 * @author Channa, Kristiana, Wenchy
	 * @return an instance of itself
	 * */
	public static SearchController getInstance() {
		if (self == null) self = new SearchController();
		return self;
	}
	
	/**
	 * lists all accounts
	 * @author Channa, Kristiana, Wenchy
	 * @param schoolName
	 * @param state
	 * @param location
	 * @param control
	 * @param numStudents1
	 * @param numStudents2
	 * @param pFemale1
	 * @param pFemale2
	 * @param satv1
	 * @param satv2
	 * @param satm1
	 * @param satm2
	 * @param exp1
	 * @param exp2
	 * @param pFinAid1
	 * @param pFinAid2
	 * @param numApps1
	 * @param numApps2
	 * @param pAdmitted1
	 * @param pAdmitted 2
	 * @param pEnrolled1
	 * @param pEnrolled2
	 * @param academicScale1
	 * @param academicScale2
	 * @param socialScale1
	 * @param socialScale2
	 * @param qualLife1
	 * @param qualLife2
	 * @param emphases
	 * */
	public List<University> searchUniversity(String schoolName, String state, String location, String control,
			int numStudents1, int numStudents2, float pFemale1, float pFemale2, int satv1, int satv2, int satm1,int satm2, int exp1,
			int exp2,float pFinAid1, float pFinAid2, int numApps1, int numApps2, float pAdmitted1, float pAdmitted2,
			float pEnrolled1, float pEnrolled2,int academicScale1, int academicScale2, int socialScale1, int socialScale2,
			int qualLife1, int qualLife2, List<String> emphases) {
		
		// numStrudents guard
		if (numStudents1 == -1) numStudents1 = 0;
		if (numStudents2 == -1) numStudents2 = Integer.MAX_VALUE;

		// pFemale guard
		if (pFemale1 == -1) pFemale1 = 0;
		if (pFemale2 == -1) pFemale2 = Integer.MAX_VALUE;

		// satm guard
		if (satm1 == -1) satm1 = 0;
		if (satm2 == -1) satm2 = Integer.MAX_VALUE;

		// satv guard
		if (satv1 == -1) satv1 = 0;
		if (satv2 == -1) satv2 = Integer.MAX_VALUE;

		// exp guard
		if (exp1 == -1) exp1 = 0;
		if (exp2 == -1) exp2 = Integer.MAX_VALUE;

		// pFinAid guard
		if (pFinAid1 == -1) pFinAid1 = 0;
		if (pFinAid2 == -1) pFinAid2 = Integer.MAX_VALUE;

		// numApps guard
		if (numApps1 == -1) numApps1 = 0;
		if (numApps2 == -1) numApps2 = Integer.MAX_VALUE;

		// pAdmitted guard
		if (pAdmitted1 == -1) pAdmitted1 = 0;
		if (pAdmitted2 == -1) pAdmitted2 = Integer.MAX_VALUE;

		// pEnrolled guard
		if (pEnrolled1 == -1) pEnrolled1 = 0;
		if (pEnrolled2 == -1) pEnrolled2 = Integer.MAX_VALUE;

		// academicScale guard
		if (academicScale1 == -1) academicScale1 = 0;
		if (academicScale2 == -1) academicScale2 = Integer.MAX_VALUE;

		// socialScale guard
		if (socialScale1 == -1) socialScale1 = 0;
		if (socialScale2 == -1) socialScale2 = Integer.MAX_VALUE;

		// qualLife guard
		if (qualLife1 == -1) qualLife1 = 0;
		if (qualLife2 == -1) qualLife2 = Integer.MAX_VALUE;

		List<University> universities = PsuedoDatabase.getInstance().getAllUniversities();
		//by school name
		if (!schoolName.equals("")) {
			universities  = filterByName(universities, schoolName);
		}

		//by state
		if (!state.equals("")) {
			universities  = filterByState(universities, state);
		}

		//by location
		if (!location.equals("")) {
			universities  = filterByLocation(universities, location);
		}

		//by control
		if (!control.equals("")) {
			universities  = filterByControl(universities, control);
		}

		//by number of students
		universities  = filterByNumStudents(universities, numStudents1, numStudents2);

		//Percent Female
		universities  = filterByPerFemale(universities, pFemale1, pFemale2);

		//SAT Verbal
		universities  = filterBySatV(universities, satv1, satv2);

		//SAT Math
		universities  = filterBySatM(universities, satm1, satm2);

		//By Expense
		universities  = filterByExp(universities, exp1, exp2);

		//By financial aid
		universities  = filterByPerFinAid(universities, pFinAid1, pFinAid2);

		//By Number of Apps
		universities  = filterByNumApps(universities, numApps1, numApps2);

		//By percent admitted
		universities = filterByPerAdmitted(universities, pAdmitted1, pAdmitted2);

		//By percent enrolled
		universities  = filterByPerEnrolled(universities, pEnrolled1, pEnrolled2);

		//Academic Scale
		universities  = filterByAcademicScale(universities, academicScale1, academicScale2);

		//Social Scale
		universities  = filterBySocialScale(universities, socialScale1, socialScale2);

		//Quality of Life
		universities  = filterByQualLife(universities, qualLife1, qualLife2);

		//Emphases
		universities = filterByEmphases(universities, emphases);

		return universities;
	}

	/**
	 * filters all universities out of list that do not match name
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param schoolName
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByName(List<University> universities, String schoolName){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getName().equals(schoolName)) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match state
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param state
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByState(List<University> universities, String state){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getAddress().getState().equalsIgnoreCase(state)) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match location
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param location
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByLocation(List<University> universities, String location){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getLocation().equalsIgnoreCase(location)) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match control
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param control
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByControl(List<University> universities, String control){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getControl().equalsIgnoreCase(control)) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match numStudents
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param upperBound
	 * @param lowerBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByNumStudents(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getNumStudents() <= upperBound && school.getNumStudents() >= lowerBound) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match perFemale
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param upperBound
	 * @param lowerBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByPerFemale(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getPerFemale() <= upperBound && school.getPerFemale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match satm
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param upperBound
	 * @param lowerBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterBySatM(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school : universities) {
			if (school.getSatMath() <= upperBound && school.getSatMath() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match satv
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param upperBound
	 * @param lowerBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterBySatV(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getSatVerbal() <= upperBound && school.getSatVerbal() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match expenses
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByExp(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getExpenses() <= upperBound && school.getExpenses() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match perFinAid
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByPerFinAid(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getPerFinAid() <= upperBound && school.getPerFinAid() >= lowerBound) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match numApps
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByNumApps(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getNumOfApps() <= upperBound && school.getNumOfApps() >= lowerBound) {
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match perAdmitted
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByPerAdmitted(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerAdmitted() <= upperBound && school.getPerAdmitted() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match perEnrolled
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByPerEnrolled(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerEnrolled() <= upperBound && school.getPerEnrolled() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match academiScale
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByAcademicScale(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school: universities) {
			if (school.getAcademicScale() <= upperBound && school.getAcademicScale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match socialScale
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterBySocialScale(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSocialScale() <= upperBound && school.getSocialScale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match qualLife
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param lowerBound
	 * @param upperBound
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByQualLife(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getQualityLife() <= upperBound && school.getQualityLife() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	/**
	 * filters all universities out of list that do not match emphases
	 * @author Channa, Kristiana, Wenchy
	 * @param universities
	 * @param listOfEmphases
	 * @return list of universities that pass filter
	 * */
	private static List<University> filterByEmphases(List<University> universities, List<String> listOfEmphases){
		if (listOfEmphases == null || listOfEmphases.size() == 0)
		{
			return universities;
		}
		List<University> fittingUniversities = new ArrayList<>();
		for (University school : universities) {
			for (String major : listOfEmphases) {
				if (fittingUniversities.contains(school)) continue;
				if(school.getEmphases().contains(major)){
					fittingUniversities.add(school);
				}
			}
		}
		return fittingUniversities;
	}

}
