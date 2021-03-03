/**
 * 
 */
package com.cmc.controller;

import java.util.List;

import com.cmc.PsuedoDatabase;
import com.cmc.model.Account;
import com.cmc.model.Address;
import com.cmc.model.University;

/**
 * @author Channa Kalsow and Kristiana Anderson
 *
 */
public class UniversityController {
	
	private static UniversityController self;
	
	private UniversityController() {}
	
	public static UniversityController getInstance() {
		if (self == null) self = new UniversityController();
		return self;
	}
	
	public String viewUniversity(String universityName) {
		University university = PsuedoDatabase.getInstance().findUniversityByName(universityName);
		if (university == null) return "University not found";
		return "University name: " + university.getName() + "\n\t Address: " + university.getAddress().toString() + "\n\tRead More...";
	}

	public boolean editBasicUniversityInfo(String srcUsername,  String universityName, ManagedField field, Object value) {
		PsuedoDatabase db = PsuedoDatabase.getInstance();
		Account src = db.getUserbyUsername(srcUsername);
		University uni = db.findUniversityByName(universityName);
		if (src.getType() != Account.AccountType.ADMIN ) return false;

		try {
			switch (field) {
			case NAME:
				uni.setName((String) value);
				break;
			case ADDRESS:
				uni.setAddress((Address)value);
				break;
			case LOCATION:
				uni.setLocation((String)value);
				break;
			case CONTROL:
				uni.setControl((String)value);
				break;
			case NUM_STUDENTS:
				uni.setNumStudents((Integer)value);
				break;
			case PERCENT_FEMALE:
				uni.setPerFemale((Float)value);
				break;
			case SAT_MATH:
				uni.setSatMath((Integer)value);
				break;
			case SAT_VERBAL:
				uni.setSatVerbal((Integer)value);
				break;
			case EXPENSES:
				uni.setExpenses((Integer)value);
				break;
			case PERCENT_FINANCIAL_AID:
				uni.setPerFinAid((Float)value);
				break;
			case NUM_OF_APPS:
				uni.setNumOfApps((Integer)value);
				break;
			case PERCENT_ADMITTED:
				uni.setPerAdmitted((Float)value);
				break;
			case ACADEMIC_SCALE:
				uni.setAcademicScale((Integer)value);
				break;
			case SOCIAL_SCALE:
				uni.setSocialScale((Integer)value);
				break;
			case EMPHASES:
				if (value instanceof List) uni.setEmphases((List<String>)value);
				break;
			default:
				
			}
		} catch (Exception ex) {
			/* If anything fails just give up and return false */
			return false;
		}

		return true;
	}

	public enum ManagedField {
		NAME, ADDRESS, LOCATION, CONTROL, NUM_STUDENTS, PERCENT_FEMALE, SAT_MATH, SAT_VERBAL, EXPENSES, PERCENT_FINANCIAL_AID,
		NUM_OF_APPS, PERCENT_ADMITTED, ACADEMIC_SCALE, SOCIAL_SCALE, EMPHASES;
	}
}
