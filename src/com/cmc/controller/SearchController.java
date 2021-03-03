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
		if (numStudents1 != -1) {
			if (numStudents2 != -1) {
				universities  = filterByNumStudents(universities, numStudents1, numStudents2);

			}
			else{
				numStudents2 = Integer.MAX_VALUE;
				universities  = filterByNumStudents(universities, numStudents1, numStudents2);

			}
		}
		else {
			numStudents1 = 0;
			if (numStudents2 != -1) {
				universities  = filterByNumStudents(universities, numStudents1, numStudents2);

			}
			else{
				numStudents2 = Integer.MAX_VALUE;
				universities  = filterByNumStudents(universities, numStudents1, numStudents2);

			}
		}
		//Percent Female
		if (pFemale1 != -1) {
			if (pFemale2 != -1) {
				universities  = filterByPerFemale(universities, pFemale1, pFemale2);

			}
			else{
				pFemale2 = Float.MAX_VALUE;
				universities = filterByPerFemale(universities, pFemale1, pFemale2);

			}
		}
		else {
			pFemale1 = 0;
			if (pFemale2 != -1) {
				universities  = filterByPerFemale(universities, pFemale1, pFemale2);

			}
			else{
				pFemale2 = Float.MAX_VALUE;
				universities  = filterByPerFemale(universities, pFemale1, pFemale2);

			}
		}
		//SAT Verbal
		if (satv1 != -1) {
			if (satv2 != -1) {
				universities  = filterBySatV(universities, satv1, satv2);

			}
			else{
				satv2 = Integer.MAX_VALUE;
				universities  = filterBySatV(universities, satv1, satv2);

			}
		}
		else {
			satv1 = 0;
			if (satv2 != -1) {
				universities  = filterBySatV(universities, satv1, satv2);

			}
			else{
				satv2 = Integer.MAX_VALUE;
				universities  = filterBySatV(universities, satv1, satv2);

			}
		}
		//SAT Math
		if (satm1 != -1) {
			if (satm2 != -1) {
				universities  = filterBySatM(universities, satm1, satm2);

			}
			else{
				satm2 = Integer.MAX_VALUE;
				universities  = filterBySatM(universities, satm1, satm2);

			}
		}
		else {
			satm1 = 0;
			if (satm2 != -1) {
				universities  = filterBySatM(universities, satm1, satm2);

			}
			else{
				satm2 = Integer.MAX_VALUE;
				universities  = filterBySatM(universities, satm1, satm2);

			}
		}
		//By Expense
		if (exp1 != -1) {
			if (exp2 != -1) {
				universities  = filterByExp(universities, exp1, exp2);

			}
			else{
				exp2 = Integer.MAX_VALUE;
				universities  = filterByExp(universities, exp1, exp2);

			}
		}
		else {
			exp1 = 0;
			if (exp2 != -1) {
				universities  = filterByExp(universities, exp1, exp2);

			}
			else{
				exp2 = Integer.MAX_VALUE;
				universities  = filterByExp(universities, exp1, exp2);
			}
		}
		//By financial aid
		if (pFinAid1 != -1) {
			if (pFinAid2 != -1) {
				universities  = filterByPerFinAid(universities, pFinAid1, pFinAid2);

			}
			else{
				pFinAid2 = Float.MAX_VALUE;
				universities = filterByPerFinAid(universities, pFinAid1, pFinAid2);

			}
		}
		else {
			pFinAid1 = 0;
			if (pFinAid2 != -1) {
				universities  = filterByPerFinAid(universities, pFinAid1, pFinAid2);

			}
			else{
				pFinAid2 = Float.MAX_VALUE;
				universities  = filterByPerFinAid(universities, pFinAid1, pFinAid2);

			}
		}
		
		//By Number of Apps
		if (numApps1 != -1) {
			if (numApps2 != -1) {
				universities  = filterByNumApps(universities, numApps1, numApps2);

			}
			else{
				numApps2 = Integer.MAX_VALUE;
				universities  = filterByNumApps(universities, numApps1, numApps2);

			}
		}
		else {
			numApps1 = 0;
			if (numApps2 != -1) {
				universities  = filterByNumApps(universities, numApps1, numApps2);

			}
			else{
				numApps2 = Integer.MAX_VALUE;
				universities  = filterByNumApps(universities, numApps1, numApps2);
			}
		}
		
		//By percent admitted
		if (pAdmitted1 != -1) {
			if (pAdmitted2 != -1) {
				universities  = filterByPerAdmitted(universities, pAdmitted1, pAdmitted2);

			}
			else{
				pAdmitted2 = Float.MAX_VALUE;
				universities = filterByPerAdmitted(universities, pAdmitted1, pAdmitted2);

			}
		}
		else {
			pAdmitted1 = 0;
			if (pAdmitted2 != -1) {
				universities  = filterByPerAdmitted(universities, pAdmitted1, pAdmitted2);

			}
			else{
				pAdmitted2 = Float.MAX_VALUE;
				universities  = filterByPerAdmitted(universities, pAdmitted1, pAdmitted2);

			}
		}
		
		//By percent enrolled
		if (pEnrolled1 != -1) {
			if (pEnrolled2 != -1) {
				universities  = filterByPerEnrolled(universities, pEnrolled1, pEnrolled2);

			}
			else{
				pEnrolled2 = Float.MAX_VALUE;
				universities = filterByPerEnrolled(universities, pEnrolled1, pEnrolled2);

			}
		}
		else {
			pEnrolled1 = 0;
			if (pEnrolled2 != -1) {
				universities  = filterByPerEnrolled(universities, pEnrolled1, pEnrolled2);

			}
			else{
				pEnrolled2 = Float.MAX_VALUE;
				universities  = filterByPerEnrolled(universities, pEnrolled1, pEnrolled2);

			}
		}
		//Academic Scale
		if (academicScale1 != -1) {
			if (academicScale2 != -1) {
				universities  = filterByAcademicScale(universities, academicScale1, academicScale2);

			}
			else{
				academicScale2 = Integer.MAX_VALUE;
				universities  = filterByAcademicScale(universities, academicScale1, academicScale2);

			}
		}
		else {
			academicScale1 = 0;
			if (academicScale2 != -1) {
				universities  = filterByAcademicScale(universities, academicScale1, academicScale2);

			}
			else{
				academicScale2 = Integer.MAX_VALUE;
				universities  = filterByAcademicScale(universities, academicScale1, academicScale2);
			}
		}
		//Social Scale
		if (socialScale1 != -1) {
			if (socialScale2 != -1) {
				universities  = filterBySocialScale(universities, socialScale1, socialScale2);

			}
			else{
				socialScale2 = Integer.MAX_VALUE;
				universities  = filterBySocialScale(universities, socialScale1, socialScale2);

			}
		}
		else {
			socialScale1 = 0;
			if (socialScale2 != -1) {
				universities  = filterBySocialScale(universities, socialScale1, socialScale2);

			}
			else{
				socialScale2 = Integer.MAX_VALUE;
				universities  = filterBySocialScale(universities, socialScale1, socialScale2);
			}
		}
		//Quality of Life
		if (qualLife1 != -1) {
			if (qualLife2 != -1) {
				universities  = filterByQualLife(universities, qualLife1, qualLife2);

			}
			else{
				qualLife2 = Integer.MAX_VALUE;
				universities  = filterByQualLife(universities, qualLife1, qualLife2);

			}
		}
		else {
			qualLife1 = 0;
			if (qualLife2 != -1) {
				universities  = filterByQualLife(universities, qualLife1, qualLife2);

			}
			else{
				qualLife2 = Integer.MAX_VALUE;
				universities  = filterByQualLife(universities, qualLife1, qualLife2);
			}
		}
		//Emphases
		universities = filterByEmphases(universities, emphases);
		
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
