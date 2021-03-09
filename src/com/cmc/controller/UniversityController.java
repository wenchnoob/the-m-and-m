/**
 * 
 */
package com.cmc.controller;

import java.util.List;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.University;

/**
 * @author Channa Kalsow and Kristiana Anderson
 *
 */
public class UniversityController {
	
	private static UniversityController self;
	
	private UniversityController() {}
	
	/**
	 * singleton
	 * @author Channa, Kristiana, Wenchy
	 * @return an instance of itself
	 * */
	public static UniversityController getInstance() {
		if (self == null) self = new UniversityController();
		return self;
	}
	
	/**
	 * allows a user to view a university
	 * @author Channa, Kristiana, Wenchy
	 * @param universityName
	 * @return String
	 * */
	public University viewUniversity(String universityName) {
		return  DBInteractions.getInstance().getUniversityByName(universityName);
	}

	/**
	 * allows admin to edit a university
	 * @author Channa, Kristiana, Wenchy
	 * @param srcUsername
	 * @param universityName
	 * @param field
	 * @param value
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public boolean editBasicUniversityInfo(String srcUsername,  String universityName, ManagedField field, Object value) {
		DBInteractions db = DBInteractions.getInstance();
		Account src = db.getUserByUserName(srcUsername);
		University uni = db.getUniversityByName(universityName);
		if (src.getType() != Account.AccountType.ADMIN ) return false;

		try {
			switch (field) {
			case NAME:
				uni.setName((String) value);
				break;
			case STATE:
				uni.setState((String)value);
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
		
		db.save(uni);
		return true;
	}
	
	//class that holds different types of managed information in UniversityController
	public enum ManagedField {
		NAME, STATE, COUNTRY, POSTALCODE,  LOCATION, CONTROL, NUM_STUDENTS, PERCENT_FEMALE, SAT_MATH, SAT_VERBAL, EXPENSES, PERCENT_FINANCIAL_AID,
		NUM_OF_APPS, PERCENT_ADMITTED, ACADEMIC_SCALE, SOCIAL_SCALE, EMPHASES;
	}
}
