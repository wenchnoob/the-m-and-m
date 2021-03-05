/**
 * 
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cmc.model.Account;
import com.cmc.model.Admin;
import com.cmc.model.University;
import com.cmc.model.User;


/**
 * @author wench
 *
 */
public class DatabaseInteractionClass {
	
	private static DatabaseInteractionClass self;

	/**
	 * 
	 */
	private  DatabaseInteractionClass() {
		// TODO Auto-generated constructor stub
	}
	
	public static DatabaseInteractionClass getInstance() {
		if (self == null) self = new DatabaseInteractionClass();
		return self;
	}
	
	public Account getUserByUserName(String username) {
		// Try one of the databases
		ResultSet results;
		try {
			results = Database.getInstance(Database.DBType.WENCHY).execute("SELECT * FROM  user WHERE Username=\"" + username + "\"");
		} catch (Exception e) {
			results = Database.getInstance(Database.DBType.ALL).execute("SELECT * FROM user WHERE Username=\"" + username + "\"");
		}
		
		// Try to return a result, return null otherwise
		try {
			if (results.next()) {
				String type = results.getString("Type");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String password = results.getString("Password");
				boolean status = results.getString("Status").equals("Y") ? true : false;
				
				if (type.equals("u")) {
					// Modify the null userschools...when the load saved schools function is implemented
					return new User(firstName, lastName, username, password, "", "", status, null);
				} else {
					return new Admin(firstName, lastName, username, password, "", "", status);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		return null;
	}
	
	public List<Account> getAllUsers() {
		ResultSet results;
		try {
			results = Database.getInstance(Database.DBType.WENCHY).execute("SELECT * FROM  user ORDER BY FirstName");
		} catch (Exception e) {
			results = Database.getInstance(Database.DBType.ALL).execute("SELECT * FROM user ORDER BY FirstName");
		}
		
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			while (results.next()) {
				String type = results.getString("Type");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String username = results.getString("Username");
				String password = results.getString("Password");
				boolean status = results.getString("Status").equals("Y") ? true : false;
				
				if (type.equals("u")) {
					// Modify the null userschools...when the load saved schools function is implemented
					accounts.add(new User(firstName, lastName, username, password, "", "", status, null));
				} else {
					accounts.add(new Admin(firstName, lastName, username, password, "", "", status));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public List<University> getAllUniversities() {
		ResultSet results;
		try {
			results = Database.getInstance(Database.DBType.WENCHY).execute("SELECT * FROM  university ORDER BY School");
		} catch (Exception e) {
			results = Database.getInstance(Database.DBType.ALL).execute("SELECT * FROM  university ORDER BY School");
		}
		
		List<University> universities = new ArrayList<University>();
		
		try {
			while (results.next()) {
				String name = results.getString("School");
				String state = results.getString("State");
				String location = results.getString("Location");
				String control = results.getString("Control");
				int numOfStudents = results.getInt("NumberOfStudents");
				float perFemales = (float)results.getDouble("PercentFemales");
				float satVerbal = (float)results.getDouble("SATVerbal");
				float satMath = (float)results.getDouble("SATMath");
				float expenses = (float) results.getDouble("Expenses");
				
				universities.add(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return universities;
	}

}
