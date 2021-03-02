/**
 * 
 */
package com.cmc.controller;

import java.util.List;

/**
 * @author Channa Kalsow and Kristiana Anderson
 *
 */
public class SearchController {
	public List<String> searchUniversity(String editState, String location, String control,
			String numStudents, String pFemale, String satv, String satm, String exp,
			String pFinAid, String numApps, String pAdmitted, String pEnrolled,
			String academicScale, String socScale, String qualLife, String emphases) {
		List<String> parameters = new List<String>
		parameters.add(editState);
		parameters.add(location);
		parameters.add(control);
		parameters.add(numStudents);
		parameters.add(pFemale);
		parameters.add(satv);
		parameters.add(satm);
		parameters.add(exp);
		parameters.add(pFinAid);
		parameters.add(numApps);
		parameters.add(pAdmitted);
		parameters.add(pEnrolled);
		parameters.add(academicScale);
		parameters.add(socScale);
		parameters.add(qualLife);
		parameters.add(emphases);
		
		List<University> universities = new List<University>;
		
		for (university : database) {

			for (String parameter : parameters) {
				if (parameter.equalsIgnoreCase(universityData) == true) {
					universities.add(university);
				}
			}
			return universities;
		}
	}

}
