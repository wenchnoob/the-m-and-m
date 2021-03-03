/**
 * 
 */
package com.cmc.controller;

import java.util.*;
import com.cmc.PsuedoDatabase;
import com.cmc.model.University;

/**
 * @author Channa Kalsow and Kristiana Anderson
 *
 */
public class SearchController {
	public List<University> searchUniversity(String schoolName, String state, String location, String control,
			int numStudents1, int numStudents2, float pFemale1, float pFemale2, int satv1, int satv2, int satm1,int satm2, int exp1,
			int exp2,float pFinAid1, float pFinAid2, int numApps1, int numApps2, float pAdmitted1, float pAdmitted2,
			float pEnrolled1, float pEnrolled2,int academicScale1, int academicScale2, int socScale1, int socialScale2,
			int qualLife1, int qualLife2, List<String> emphases) {
		
		List<University> universities = PsuedoDatabase.getInstance().getAllUniversities();
			
			return universities;
	}
	private List<University> filterByName(List<University> universities, String schoolName){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getName().equals(schoolName)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByState(List<University> universities, String state){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getState().equals(state)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	
	private List<University> filterByLocation(List<University> universities, String location){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getLocation().equals(location)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	
	private List<University> filterByControl(List<University> universities, String control){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getControl().equals(control)){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	
	private List<University> filterByNumStudents(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getNumStudents() <= upperBound && school.getNumStudents() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByPerFemale(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerFemale() <= upperBound && school.getPerFemale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterBySatM(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSatMath() <= upperBound && school.getSatMath() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterBySatV(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSatVerbal() <= upperBound && school.getSatVerbal() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByExp(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getExpenses() <= upperBound && school.getExpenses() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByPerFinAid(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerFinAid() <= upperBound && school.getPerFinAid() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByNumApps(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getNumOfApps() <= upperBound && school.getNumOfApps() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByPerAdmitted(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerAdmitted() <= upperBound && school.getPerAdmitted() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByPerFinAid(List<University> universities, float lowerBound, float upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getPerEnrolled() <= upperBound && school.getEnrolled() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByAcademicScale(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getAcademicScale() <= upperBound && school.getAcademicScale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterBySocialScale(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getSocialScale() <= upperBound && school.getSocialScale() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByQualLife(List<University> universities, int lowerBound, int upperBound){
		List<University> fittingUniversities = new ArrayList<>();
		for (University school:universities) {
			if (school.getQualityLife() <= upperBound && school.getQualityLife() >= lowerBound){
				fittingUniversities.add(school);
			}
		}
		return fittingUniversities;
	}
	private List<University> filterByEmphases(List<University> universities, List<String> listOfEmphases){
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
