/**
 * 
 */
package com.cmc.controller;

import java.util.List;

import com.cmc.database.DBInteractions;
import com.cmc.model.Account;
import com.cmc.model.University;

/**
 * Class intended for the handling of all university functionalities in the system.
 * @author Channa Kalsow and Kristiana Anderson
 */
public class UniversityController {

	private static UniversityController self;

	/**
	 * Private constructor to prevent construction of objects
	 * of this class from outside of this class.
	 */
	private UniversityController() {}

	/**
	 * Static method to return a reference to a singleton instance of this class.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @return AccountController - An singleton instance of this class.
	 * */
	public static UniversityController getInstance() {
		if (self == null) self = new UniversityController();
		return self;
	}

	/**
	 * Allows an admin to add a university
	 * 
	 *@author Joseph
	 *
	 * @return boolean whether the university was added 
	 */
	public boolean addUniversity(Account src, String name, String state, String location, String control,
			int numStudents, int satMath, int satVerbal, int expenses,
			int numOfApps, int academicScale, int socialScale, List<String> emphases, float perFemale,
			float perFinAid, float perAdmitted, float perEnrolled, int qualityLife) {

		if (src.getType() != Account.AccountType.ADMIN) return false;
		if (DBInteractions.getInstance().getUniversityByName(name)!= null) return false;
			University newUniversity = new University(name, state, location, control, numStudents, satMath, satVerbal, expenses,
					numOfApps, academicScale, socialScale, emphases, perFemale, perFinAid, perAdmitted, perEnrolled, qualityLife);
		return DBInteractions.getInstance().save(newUniversity);
	}


	/**
	 * Returns a university object that represents the university name that was passed in.
	 * If the university name does not exist in the database, then a null reference is returned.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param universityName - The name of the university to be viewed.
	 * 
	 * @return University - The university object that will be displayed.
	 * */
	public University viewUniversity(String universityName) {
		return  DBInteractions.getInstance().getUniversityByName(universityName);
	}

	/**
	 * Allows admin's to modify a targeted field in a university object.
	 * 
	 * @author Channa, Kristiana, Wenchy
	 * 
	 * @param srcUsername - The username of the object trying to modify the university information.
	 * @param universityName - The name of the university that is to be modified.
	 * @param field - The attribute that is being targeted for modification.
	 * @param value - The desired value for the targeted field to be changed to.
	 * 
	 * @return boolean - Whether the operation was successful.
	 * */
	@SuppressWarnings("unchecked")
	public boolean editBasicUniversityInfo(String srcUsername,  String universityName, ManagedField field, Object value) {
		DBInteractions db = DBInteractions.getInstance();
		Account src = db.getUserByUserName(srcUsername);
		University uni = db.getUniversityByName(universityName);
		if (src.getType() != Account.AccountType.ADMIN ) return false;
		if (uni == null) return false;

		try {
			switch (field) {
			case NAME:
				db.remove(uni);
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
		} catch (ClassCastException ex) {
			/* If anything fails just give up and return false */
			return false;
		}

		db.save(uni);
		return true;
	}

	/**
	 * Enum class that holds an identifier for the different types of managed information in UniversityController.
	 * */
	public enum ManagedField {
		NAME, STATE, COUNTRY, POSTALCODE,  LOCATION, CONTROL, NUM_STUDENTS, PERCENT_FEMALE, SAT_MATH, SAT_VERBAL, EXPENSES, PERCENT_FINANCIAL_AID,
		NUM_OF_APPS, PERCENT_ADMITTED, ACADEMIC_SCALE, SOCIAL_SCALE, EMPHASES;
	}
}
