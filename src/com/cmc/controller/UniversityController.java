/**
 * 
 */
package com.cmc.controller;

import com.cmc.model.Account;
import com.cmc.model.Address;
import com.cmc.model.University;

/**
 * @author Channa Kalsow and Kristiana Anderson
 *
 */
public class UniversityController {

	public boolean editBasicUniversityInfo(Account src,  University uni, ManagedField field, Object value) {
		if (src.getType() != Account.AccountType.ADMIN ) return false;

		try {
			switch (field) {
			case ADDRESS:
				uni.setAddress((Address)value);
				break;
			case LOCATION:
				uni.setLocation((String)value);
				break;
			case CONTROL:
				uni.setControl((String)value);
			case NUM_STUDENTS:
				uni.setNumStudents((Integer)value);
			case PERCENT_FEMALE:
				uni.setPerFemale((Float)value);
			case SAT_MATH:
				uni.setSatMath((Integer)value);
			case SAT_VERBAL:
				uni.setSatVerbal((Integer)value);
			case EXPENSES:
				uni.setExpenses((Integer)value);
			case PERCENT_FINANCIAL_AID:
				uni.setPerFinAid((Float)value);
			default:
				
			}
		} catch (Exception ex) {
			/* If anything fails just give up and return false */
			return false;
		}

		return true;
	}

	public enum ManagedField {
		ADDRESS, LOCATION, CONTROL, NUM_STUDENTS, PERCENT_FEMALE, SAT_MATH, SAT_VERBAL, EXPENSES, PERCENT_FINANCIAL_AID,
		NUM_OF_APPS, PERCENT_ADMITTED, ACADEMIC_SCALE, SOCIAL_SCALE, EMPHASES;
	}
}
