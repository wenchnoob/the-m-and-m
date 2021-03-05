package database;

//library needed to connect to, retrieve data from, and modify data in the database
import java.sql.*;
import java.io.*;
import java.util.*;

/**
* Class UniversityDBLibrary allows data operations on the MySQL database used in 
* your CSCI230 class project. 
* 
* @author Imad M Rahal
* @version 1.9 Last modified on January 09, 2019
*/
public class UniversityDBLibrary {

// the name of the MySQL database to use 
private String database;
// a valid MySQL username needed to access the specified MySQL database
private String username;
// a valid MySQL password needed to access the specified MySQL database
private String password;

private DBType type;

public enum DBType {
	WENCHY, ALL;
}

/**
 * Constructor takes a username and password as parameters and sets the 
 * values for all three instance fields in order to enable access to 
 * the specified MySQL database. Please provide your team username and 
 * password in order to create an instance of this class. Assumes username 
 * and database name are the same.
 * 
 * @param username String representing a valid username needed to access the 
 * specified MySQL database. Assumes username and database name are the same.
 * @param password String representing a valid password needed to access the 
 * specified MySQL database
 * @throws IllegalStateException if JDBC drivers can't be loaded properly
 */
public UniversityDBLibrary(String username, String password){
  this.database = username;
  this.username = username;
  this.password = password;
  try{
    Class.forName("com.mysql.jdbc.Driver");
  }
  catch (ClassNotFoundException cnfe) {
    throw new IllegalStateException("failed to load JDBC drivers");
  }
}

/**
 * Constructor takes a database name, username and password as parameters 
 * and sets the values for all three instance fields in order to enable access to 
 * the specified MySQL database. Username and database name may differ.
 * 
 * @param database String representing the name of the MySQL database to use 
 * @param username String representing a valid username needed to access the 
 * specified MySQL database
 * @param password String representing a valid password needed to access the 
 * specified MySQL database
 * @throws IllegalStateException if JDBC drivers can't be loaded properly
 */
public UniversityDBLibrary(String database, String username, String password, DBType type){
  this.database = database;
  this.username = username;
  this.password = password;
  this.type = type;
  try{
    Class.forName("com.mysql.jdbc.Driver");
  }
  catch (ClassNotFoundException cnfe) {
    throw new IllegalStateException("failed to load JDBC drivers");
  }
}

/**
 * A private helper method used by other methods in this class to connect 
 * to the database. 
 * This method SHOULD NOT be used outside this class.
 * 
 * @return a Connection object required by methods of this class to connect 
 * to the database. A null is returned in case a database error occurs
 */
private Connection openDBConnection(){
    Connection conn;
    try {
		if (type == DBType.WENCHY) {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} else {
			Class.forName("com.mysql.jdbc.Driver");
		}
		conn = DriverManager.getConnection(database, username, password);
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		return null;
	}
    return conn;  
}

/**
 * A private helper method used by other methods in this class to convert 
 * data in parameter ResultSet rs into a 2-D array of Strings. 
 * This method SHOULD NOT be used outside this class.
 * 
 * @param rs a ResultSet object containing the data returned from the database
 * @return a 2-D array of Strings containing the same data in the parameter 
 * object. Note that even though some/all data in parameter ResultSet rs 
 * might not be of type String, for convenience, everything (including number data) 
 * is converted into type String in order to be stored and returned in a single 2D 
 * array. All methods in this class that return data to your application use this 
 * method; thus, it is left up to you, the programmer, to cast non-String data correctly 
 * (using Java methods like Integer.parseInt(String s) and Double.pareDouble(String s)). 
 * A null is returned if the ResultSet parameter is empty or if a database error is 
 * encountered.
 */
private String[][] wrapper(ResultSet rs) {
  try{
    ResultSetMetaData rsmd = rs.getMetaData();
    int cols = rsmd.getColumnCount();
    int rows = 1;
    while (rs.next()) {
      rows++;
    }
    String[][] result = new String[rows][cols];
    rs.beforeFirst();
    int currRow = 0;
    while (rs.next()) {
      for (int currCol = 0; currCol < cols; currCol++) {
        result[currRow][currCol] = rs.getString(currCol + 1);
      }
      currRow++;
    }
    return result;
  }
  catch(SQLException se){
	se.printStackTrace();
    return null;
  }
}

/**
 * A private helper method used by other methods in this class to display 
 * data returned from the database for display purposes.
 * This method SHOULD NOT be used outside this class.
 * 
 * @param table a 2-D array of Strings containing data from the database
 */
private void display(String[][] table, PrintWriter pw) {
  if(table!=null){
    for (int row = 0; row < table.length; row++) {         
      for (int col = 0; col < table[0].length; col++) {
        pw.print(table[row][col] + "     ");
      }
      pw.println();
    }     
  }
  else{
    pw.println("Nothing to display");
  }
}

/**
 * A private helper method used by other methods in this class to retrieve 
 * data from the database by passing a properly formatted SELECT SQL query 
 * String.
 * This method SHOULD NOT be used outside this class.
 * 
 * @param queryString the input SELECT SQL query String to be used to 
 * retrieve data from the database
 * @return a 2-D array of Strings containing the data returned from database 
 * in response to the query. A null is returned if there are no matches in 
 * the database or if a database error is encountered
 */
private String[][] issueDBURead(String queryString){
  String[][] result;
  Statement stmt = null;
  Connection conn = null;
  ResultSet rs = null;
  
  try{
    conn = openDBConnection();
    // Changed the way the result set was created.
    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    rs = stmt.executeQuery(queryString);
    if (!rs.next()) {
    	System.out.println("No result");
      return null;
    } 
    else {
      result = wrapper(rs);
      return result;
    }
  }
  catch(SQLException se){
    return null;
  }
  finally{
    try{
      if (rs != null) rs.close ();
      if (stmt != null) stmt.close ();
      if (conn != null) conn.close ();
    }
    catch (SQLException se){
      return null;
    }       
  }
}

/**
 * A private helper method used by other methods in this class to modify 
 * data in the database by passing a properly formatted INSERT, UPDATE, or 
 * DELETE SQL query String.
 * This method SHOULD NOT be used outside this class.
 * 
 * @param queryString the input INSERT, UPDATE, or DELETE SQL query String 
 * to be used to modify data in the database
 * @return an integer indicating the number of database records affected by
 * the update operation or -1 if a database error is encountered
 */
private int issueDBUpdate(String queryString) {
  Statement stmt = null;
  Connection conn = null;
  try{    
    conn = openDBConnection();
    stmt = conn.createStatement();
    int result = stmt.executeUpdate(queryString);
    return result;
  }
  catch(SQLException se){
    return -1;
  }
  finally{
    try{
      if (stmt != null) stmt.close ();
      if (conn != null) conn.close ();
    }
    catch (SQLException se){
      return -1;
    }       
  } 
}

/**
 * When called, this method returns a 2-D array of Strings containing all 
 * user records in the database. Every array row contains a user record which, in 
 * turn, is made up of the following fields (in the shown order): FirstName String, 
 * LastName String, Username String (must be unique among users), Password String, 
 * Type char (can be either 'a' for administrators or 'u' for regular users), 
 * Status char (can be either 'Y' for active accounts or 'N' inactive ones). 
 * Records in the array are sorted by usernames in ascending order.
 * 
 * For example, the following statement: 
 *        String[][] result = getUsers() 
 * will store all users in array result. The username and password fields for 
 * the first user are located in result[0][2] and result[0][3], respectively.
 * 
 * @return a 2-D array of Strings containing all users in the database. A 
 * null is returned if there are no users in the database or if a database error 
 * is encountered
 */
public String[][] user_getUsers(){
  return issueDBURead("Select * from User Order by username");
}

/**
 * When called, this method creates a new user record using the information 
 * provided in the parameters. The user will be active by default. Callers 
 * of this method must ensure that the specified username is unique among all 
 * users, otherwise, as SQLException may be thrown
 * 
 * @param firstName a String containing the first name for the new user
 * @param lastName a String containing the last name for the new user
 * @param username a String containing the username for the new user which 
 * must be unique among all users
 * @param password a String containing the password for the new user
 * @param type should be either 'a' for administrators or 'u' for regular 
 * users
 * @return an integer indicating the number of database records affected by
 * the update operation or -1 if a database error is encountered
 */
public int user_addUser(String firstName, String lastName, String username, String password, 
                        char type) {
  String query = "Insert Into User Values('" + firstName + "','"
    + lastName + "','"
    + username + "','"
    + password + "','"
    + type + "','"
    + "Y')";
  return issueDBUpdate(query);
}

/**
 * When called, this method updates the record of the user, whose username is 
 * specified as a parameter, using the information provided in the parameters. 
 * The user fields that can be modified are: first name, last name, password, 
 * type, and status. Nothing happens if an invalid username is specified.
 * 
 * @param username a String containing the username of the user whose 
 * account is being modified 
 * @param firstName a String containing the new first name for the user
 * @param lastName a String containing the new last name for the user
 * @param password a String containing the new password for the user
 * @param type a char which should be either 'a' for administrators or 'u' 
 * for regular users       
 * @param status a char which should be either 'Y' for active accounts or 
 * 'N' for inactive accounts
 * @return an integer indicating the number of database records affected by
 * the update operation or -1 if a database error is encountered
 */
public int user_editUser(String username, String firstName, String lastName, 
                         String password, char type, char status) {
  String query = "Update User set FirstName = '" + firstName + "',"
    + " LastName = '" + lastName + "',"
    + " Password = '" + password + "',"
    + " Status = '" + status + "',"
    + " Type = '" + type + "'"
    + " where Username='" + username + "'";
  return issueDBUpdate(query);
}

/**
 * When called, this method deletes from the database the record of 
 * the user whose username is specified as a parameter. 
 * 
 * PLEASE NOTE THAT USERS CAN NOT BE DELETED IF THEY HAVE SAVED SCHOOLs;
 * SAVED SCHOOLS MUST BE REMOVED FIRST.
 * 
 * @param username a String containing the username of the user whose 
 * record is being deleted 
 * @return an integer indicating the number of database records affected by
 * the delete operation or -1 if a database error is encountered. 
 */
public int user_deleteUser(String username) {
  String query = "Delete From User where Username='" + username + "'";
  return issueDBUpdate(query);
}

/**
 * When called, this method returns a 2-D array of Strings containing all 
 * usernames along with their saved schools and timestamp of when they were 
 * added. Every array row contains a triplet (username, school, timestamp). Users 
 * with multiple saved schools will have their usernames repeat multiple times 
 * in the array each time with a different school. Users with no saved schools 
 * will not be included in the output array. Records in the array are sorted 
 * first by username and then by school in ascending order.
 * 
 * For example, the following statement: 
 *     String[][] result = getUsernamesWithSavedSchools() will store 
 * the first (username,school,timestamp) triplet in result[0][0], and 
 * result[0][1], and result[0][2] respectively.
 * 
 * @return a 2-D array of Strings containing all (username,school,timestamp) 
 * triplets in the database. A null is returned if no users have saved schools in 
 * the database or if a database error is encountered
 */
public String[][] user_getUsernamesWithSavedSchools(){
  return issueDBURead("Select * from MySavedSchools Order by User, School");
}

/**
 * When called, this method saves the specified school to the user's list 
 * of saved schools with the current system date and time as timestamp 
 * and returns true. No changes occur and a false is returned if 
 * an invalid user is specified or if the specified school is already in user's list 
 * of saved schools, or if a database error is encountered
 * 
 * @param user a String representing the username of the user for whom this school
 * is being added
 * @param school a String containing the school to be saved to the user's 
 * list of schools
 * @return an integer indicating the number of schools inserted due to this update 
 * operation or -1 if a database error is encountered
 */
public int user_saveSchool(String user, String school)  {
  String query = "insert into MySavedSchools values('" + user + "','" + school + "', NULL)";
  return issueDBUpdate(query);
}
/**
 * When called, this method removes the specified school from the user's 
 * list of saved schools. No changes occur if an invalid user is specified 
 * or if the specified school is not already in user's list of saved schools.
 * 
 * @param user a String representing the username of the user from whom this school
 * is being removed
 * @param school a String containing the school to be removed from the user's list 
 * of saved schools
 * @return an integer indicating the number of schools removed due to this update 
 * operation or -1 if a database error is encountered
 */
public int user_removeSchool(String user, String school){
  String query = "delete from MySavedSchools where user='" + user + "' and School='" + school + "'";
  return issueDBUpdate(query);
}
/**
 * When called, this method returns a 2-D array of Strings containing all 
 * universities in the database. Every array row contains a university 
 * record which, in turn, is made up of the following fields (in the shown 
 * order): School String (must be unique among universities), State String, 
 * Location String, Control String, NumberOfStudents int, PercentFemales 
 * double (between 0 and 100), SATVerbal double (up to 800), SATMath double 
 * (up to 800), Expenses double, PercentFinancialAid double (between 0 and 100), 
 * NumberOfApplicants int, PercentAdmitted double (between 0 and 100), 
 * PercentEnrolled double (between 0 and 100), AcademicsScale int (between 
 * 1 and 5 where is best), SocialScale int (between 1 and 5 where is best), 
 * QualityOfLifeScale int (between 1 and 5 where is best). Records in the 
 * array are sorted by School name in ascending order.
 * 
 * For example, the following Java statement 
 *      String[][] result = getUniversities() 
 * will store all universities in array result. The State and SATVerbal 
 * fields for the first university are located in result[0][1] and 
 * result[0][6], respectively.
 * 
 * Please note that some universities have missing field information which 
 * have been indicated by the String "-1" for String fields and the number 
 * -1 for number fields.
 * 
 * @return a 2-D array of Strings containing all universities in the 
 * database. A null is returned if there are no universities in the 
 * database or if a database error is encountered
 */    
public String[][] university_getUniversities() {
  return issueDBURead("Select * from University Order by School");
}

/**
 * When called, this method returns a 2-D array of Strings containing all 
 * possible university emphases that occur in the database. Every array row 
 * contains a single field storing an existing emphasis. Duplicate emphases 
 * are eliminated. Records in the array are sorted by emphasis in ascending 
 * order. A null is returned if no universities have emphases in the database 
 * or if a database error is encountered
 * 
 * For example, the following statement: 
 *     String[][] result = getEmphases()
 * will store the first emphasis at result[0][0], second emphasis at location
 * result[1][0], etc..
 * 
 * @return a 2-D  array of Strings containing all possible university 
 * emphases that occur in the database. A null is returned if no 
 * universities have emphases in the database or if a database error 
 * is encountered
 */
public String[][] university_getEmphases(){
  return issueDBURead("Select distinct Area from Emphasis Order by Area");
}

/**
 * When called, this method returns a 2-D array of Strings containing all 
 * university names along with their emphases in the database. Every array 
 * row contains a pair (university name, emphasis) both of which are 
 * Strings. Universities with multiple emphases will have their names repeat 
 * multiple times in the array each time with a different emphasis. 
 * Universities with no emphases will not be included in the output array. 
 * Records in the array are sorted first by school name and then by emphasis 
 * in ascending order. A null is returned if no universities have 
 * emphases in the database or if a database error is encountered
 * 
 * For example, the following statement: 
 *     String[][] result = getUniversityNamesWithEmphases() will store 
 * the first school name in result[0][0], its first emphasis in result[0][1], 
 * respectively. The second emphasis for this school, if it exists, will be in 
 * result[1][1].
 * 
 * @return a 2-D array of Strings containing all (university name, emphasis)
 * pairs in the database. A null is returned if no universities have 
 * emphases in the database or if a database error is encountered
 */
public String[][] university_getNamesWithEmphases() {
  return issueDBURead("Select * from Emphasis Order by School, Area");
}

/**
 * When called, this method creates a new university record using the 
 * information provided in the parameters. Callers of this method must 
 * ensure that the school name is unique among all universities, otherwise, 
 * no changes occur and a -1 in returned.
 * 
 * @param school a String containing the name for the new university which 
 * must be unique among all universities
 * @param state a String containing the state in which the new university is 
 * located. Specify "-1" if the value of this field is unknown. Specify 
 * "FOREIGN" if the university is located outside the US
 * @param location a String specifying the type of area in which the new 
 * university is located. Possible values are "SUBURBAN", "URBAN" and 
 * "SMALL-CITY".  Specify "-1" if the value of this field is unknown
 * @param control a String specifying who controls the new university. 
 * Possible values are "PRIVATE", "STATE" and "CITY". Specify "-1" if the 
 * value of this field is unknown
 * @param numberOfStudents an integer representing the number of students 
 * currently enrolled in the new university
 * @param percentFemales a double out of 100 representing the percentage of 
 * females in the student population
 * @param SATVerbal a double out of 800 representing the average SAT Verbal 
 * exam score for all students currently enrolled in the new university
 * @param SATMath a double out of 800 representing the average SAT Math 
 * exam score for all students currently enrolled in the new university
 * @param expenses a double representing the annual tuition for the new 
 * university
 * @param percentFinancialAid a double out of 100 representing the 
 * percentage of students receiving any form sort of financial assistance 
 * from the new university
 * @param numberOfApplicants an integer representing the number of students 
 * who typically apply annually to the new university
 * @param percentAdmitted a double out of 100 representing the percentage 
 * of applicants who are admitted annually to the new university
 * @param percentEnrolled a double out of 100 representing the percentage 
 * of admitted students who enroll in the new university
 * @param academicsScale an int between 1 and 5 (with 5 being best) 
 * indicating the quality of the academics at the new university 
 * @param socialScale an int between 1 and 5 (with 5 being best) 
 * indicating the quality of the social life at the new university 
 * @param qualityOfLifeScale an int between 1 and 5 (with 5 being best) 
 * indicating the overall quality of life at the new university 
 * @return an integer indicating the number of university records inserted or 
 * -1 if school name is NOT unique or if a database error is encountered
 */
public int university_addUniversity(String school, String state, String location, String control, 
                                    int numberOfStudents, double percentFemales, double SATVerbal, 
                                    double SATMath, double expenses, double percentFinancialAid, 
                                    int numberOfApplicants, double percentAdmitted, double percentEnrolled, 
                                    int academicsScale, int socialScale, int qualityOfLifeScale) {
  String query = "Insert Into University Values('" + school + "','"
    + state + "','"
    + location + "','"
    + control + "',"
    + numberOfStudents + ","
    + percentFemales + ","
    + SATVerbal + ","
    + SATMath + ","
    + expenses + ","
    + percentFinancialAid + ","
    + numberOfApplicants + ","
    + percentAdmitted + ","
    + percentEnrolled + ","
    + academicsScale + ","
    + socialScale + ","
    + qualityOfLifeScale + ")";
  return issueDBUpdate(query);
}

/**
 * When called, this method updates the university record for the university 
 * whose name is specified as a parameter, using the information provided in 
 * the parameters. This method can update all university fields except for 
 * school name and emphases. No changes occur if an invalid university name 
 * is specified or if a database error is encountered
 * 
 * @param school a String containing the name for the university being 
 * updated
 * @param state a String containing the updated state in which the 
 * university being updated is located. Specify "-1" if the value of this 
 * field is unknown.
 * @param location a String specifying the updated type of area in which the 
 * university being updated is located. Possible values are "SUBURBAN", 
 * "URBAN" and "SMALL-CITY".  Specify "-1" if the value of this field is 
 * unknown
 * @param control a String specifying who controls the university  being 
 * updated. Possible values are "PRIVATE", "STATE" and "CITY". Specify "-1" 
 * if the value of this field is unknown
 * @param numberOfStudents an integer representing the updated number of 
 * students currently enrolled in the university being updated
 * @param percentFemales a double out of 100 representing the updated 
 * percentage of females in the student population
 * @param SATVerbal a double out of 800 representing the updated average SAT
 * Verbal exam score for all students currently enrolled in the university 
 * being updated
 * @param SATMath a double out of 800 representing the updated average SAT
 * Math exam score for all students currently enrolled in the university 
 * being updated
 * @param expenses a double representing the updated annual tuition for the 
 * university being updated
 * @param percentFinancialAid a double out of 100 representing the updated
 * percentage of students receiving any form sort of financial assistance 
 * from the university being updated
 * @param numberOfApplicants an integer representing the number of students 
 * who typically apply annually to the new university
 * @param percentAdmitted a double out of 100 representing the updated 
 * percentage of applicants who are admitted annually to the university 
 * being updated
 * @param percentEnrolled a double out of 100 representing the updated 
 * percentage of admitted students who enroll in the university being 
 * updated
 * @param academicsScale an int between 1 and 5 (with 5 being best) 
 * indicating the updated quality of the academics at the university being 
 * updated
 * @param socialScale an int between 1 and 5 (with 5 being best) 
 * indicating the updated quality of the social life at the university being 
 * updated
 * @param qualityOfLifeScale an int between 1 and 5 (with 5 being best) 
 * indicating the updated overall quality of life at the university being 
 * updated
 * @return an integer indicating the number of university records affected by
 * the update operation or -1 if a database error is encountered
 */
public int university_editUniversity(String school, String state, String location, String control, 
                                     int numberOfStudents, double percentFemales, double SATVerbal, 
                                     double SATMath, double expenses, double percentFinancialAid, 
                                     int numberOfApplicants, double percentAdmitted, double percentEnrolled, 
                                     int academicsScale, int socialScale, int qualityOfLifeScale) {
  String query = "Update University set   "
    + "State               ='" + state + "'"
    + ",Location            ='" + location + "'"
    + ",Control             ='" + control + "'"
    + ",NumberOfStudents    = " + numberOfStudents
    + ",PercentFemales      = " + percentFemales
    + ",SATVerbal           = " + SATVerbal
    + ",SATMath             = " + SATMath
    + ",Expenses            = " + expenses
    + ",PercentFinancialAid = " + percentFinancialAid
    + ",NumberOfApplicants  = " + numberOfApplicants
    + ",PercentAdmitted     = " + percentAdmitted
    + ",PercentEnrolled     = " + percentEnrolled
    + ",AcademicsScale      = " + academicsScale
    + ",SocialScale         = " + socialScale
    + ",QualityOfLifeScale  = " + qualityOfLifeScale
    + " Where School = '" + school + "'";
  return issueDBUpdate(query);
}

/**
 * When called, this method adds a new emphasis area for the specified 
 * school. No changes occur if an invalid school name is specified, if 
 * the specified emphasis already exists for the specified school, or 
 * if a database error is encountered
 * 
 * @param school a String containing the name of the school for which the 
 * new emphasis area is being added
 * @param emphasis a String containing the new emphasis to be added to the 
 * specified school
 * @return an integer indicating the number of database records inserted by
 * this operation or -1 if an invalid school name is specified, if the specified 
 * emphasis already exists for the specified school, or if a database error is 
 * encountered
 */
public int university_addUniversityEmphasis(String school, String emphasis){
  String query = "Insert Into Emphasis Values('" + school + "','" + emphasis + "')";
  return issueDBUpdate(query);
}

/**
 * When called, this method deletes the given emphasis area for the 
 * specified school from the database. No changes occur if an invalid 
 * school name is specified, if the specified emphasis does not exist
 * for the specified school, or if a database error is encountered
 * 
 * @param school a String containing the name of the school for which the 
 * emphasis areas are being removed
 * @param emphasis a String containing the emphasis to be removed to the 
 * specified school
 * @return an integer indicating the number of database records removed by
 * this operation or -1 if an invalid school name is specified, if the specified 
 * emphasis doesn't exist for the specified school, or if a database error is 
 * encountered
 */    
public int university_removeUniversityEmphasis(String school, String emphasis){
  String query = "Delete From Emphasis where School='" + school + "' and Area='" + emphasis + "';";
  return issueDBUpdate(query);
}   

/**
 * When called, this method deletes from the database the record of 
 * the university whose name is specified as a parameter.
 * 
 * PLEASE NOTE THAT UNIVERSITIES CAN NOT BE DELETED IF THEY HAVE BEEN SAVED 
 * TO A USER'S LIST OF SCHOOLS;THE LATTER SHOULD BE REMOVED FIRST.SIMILARLY, 
 * A UNIVERISTY CAN NOT BE DELETED IF IT HAS EXISTING AREAS OF EMPHASIS; AREAS 
 * OF EMPHASIS MUST BE REMOVED FIRST.
 * 
 * @param school a String containing the name for the university being 
 * deleted
 * @return an integer indicating the number of database records affected by
 * the delete operation or -1 if a database error is encountered. 
 */
public int university_deleteUniversity(String school){
  String query = "Delete From University Where School = '" + school + "'";
  return issueDBUpdate(query);
}  
}
