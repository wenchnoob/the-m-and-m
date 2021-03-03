/**
 * 
 */
package com.cmc.model;

/**
 * @author Channa Kalsow
 *
 */
public abstract class Account {
	protected String firstName, lastName, recoveryQuestion,
	recoveryAnswer, username, password;
	protected boolean enabled;
	protected boolean loggedOn = false;
	
	protected AccountType type;
	
	public Account(String firstName, String lastName, boolean enabled, String
			recoveryQuestion, String recoveryAnswer, String username, String password, AccountType type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.recoveryQuestion = recoveryQuestion;
		this.recoveryAnswer = recoveryAnswer;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public boolean logon(String password) {
		if (this.password.equals(password)) loggedOn = true;
		return loggedOn;
	}
	
	public String recover(String answer) {
		//TODO
		return " ";
	}
	
	public boolean logout() {
		this.loggedOn = false;
		return loggedOn;
	}
	
	public String toString() {
		return "Name : " + lastName + ", " + firstName;
	}
	
	
	
	/**
	 * @return the type
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AccountType type) {
		this.type = type;
	}

	/**
	 * 
	 * @return loggedOn variable
	 * */
	public boolean isLoggedOn() {
		return loggedOn;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the recoveryQuestion
	 */
	public String getRecoveryQuestion() {
		return recoveryQuestion;
	}

	/**
	 * @param recoveryQuestion the recoveryQuestion to set
	 */
	public void setRecoveryQuestion(String recoveryQuestion) {
		this.recoveryQuestion = recoveryQuestion;
	}

	/**
	 * @return the recoveryAnswer
	 */
	public String getRecoveryAnswer() {
		return recoveryAnswer;
	}

	/**
	 * @param recoveryAnswer the recoveryAnswer to set
	 */
	public void setRecoveryAnswer(String recoveryAnswer) {
		this.recoveryAnswer = recoveryAnswer;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * Inner enumeration to help distinguish the user type
	 * 
	 * */
	public enum AccountType {
		BASIC_USER, ADMIN
	}
	
}
