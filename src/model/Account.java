/**
 * 
 */
package model;

/**
 * @author Channa Kalsow
 *
 */
public abstract class Account {
	protected String firstName, lastName, recoveryQuestion,
	recoveryAnswer, username, password;
	protected boolean enabled;
	
	public Account(String firstName, String lastName, boolean enabled, String
			recoveryQuestion, String recoveryAnswer, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.recoveryQuestion = recoveryQuestion;
		this.recoveryAnswer = recoveryAnswer;
		this.username = username;
		this.password = password;
	}
	
	public boolean logon(String password) {
		//TODO
		return true;
	}
	public String recover(String answer) {
		//TODO
		return " ";
	}
	public boolean logout() {
		//TODO
		return true;
	}
}
