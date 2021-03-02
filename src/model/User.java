/**
 * 
 */
package model;

/**
 * @author Channa Kalsow
 *
 */
import java.util.List;

public class User extends Account{
	private List<UserSchool> savedSchools;
	
	/**
	 * 
	 */
	public User(String firstName, String lastName, boolean enabled, String
			recoveryQuestion, String recoveryAnswer, String username,
			String password, List<UserSchool> savedSchools) {
		super(firstName);
		super(lastName);
		super(enabled);
		super(recoveryQuestion);
		super(recoveryAnswer);
		super(username);
		super(password);
		this.savedSchools = savedSchools;
		
	}

	public void saveSchool(UserSchool school) {
		//TODO
	}
	
	public void unsaveSchool(String schoolName) {
		//TODO
	}
	
	public List getSavedSchools() {
		return savedSchools;
	}
} 
