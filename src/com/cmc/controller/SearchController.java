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

		return universities;
	}
	private static List<University> filterByName(List<University> universities, String schoolName){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getName().equals(schoolName)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByState(List<University> universities, String state){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getAddress().getState().equals(state)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	private static List<University> filterByLocation(List<University> universities, String location){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getLocation().equals(location)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	private static List<University> filterByControl(List<University> universities, String control){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getControl().equals(control)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}

	private static List<University> filterByNumStudents(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getNumStudents() <= upperBound && school.getNumStudents() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByPerFemale(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerFemale() <= upperBound && school.getPerFemale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterBySatM(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSatMath() <= upperBound && school.getSatMath() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterBySatV(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSatVerbal() <= upperBound && school.getSatVerbal() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByExp(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getExpenses() <= upperBound && school.getExpenses() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByPerFinAid(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerFinAid() <= upperBound && school.getPerFinAid() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByNumApps(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getNumOfApps() <= upperBound && school.getNumOfApps() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByPerAdmitted(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerAdmitted() <= upperBound && school.getPerAdmitted() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByPerEnrolled(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerEnrolled() <= upperBound && school.getPerEnrolled() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByAcademicScale(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getAcademicScale() <= upperBound && school.getAcademicScale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterBySocialScale(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSocialScale() <= upperBound && school.getSocialScale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByQualLife(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getQualityLife() <= upperBound && school.getQualityLife() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private static List<University> filterByEmphases(List<University> universities, List<String> listOfEmphases){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			for (String major: listOfEmphases) {
				if (fittingUniversities.contains(school)) {
					continue;
				}
				if(school.getEmphases().contains(major)){
					fittingUniversities.add(school);
				}
			}
		}
		return fittingUniversities;
	}

}
