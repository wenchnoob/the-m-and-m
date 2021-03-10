/**
 * 
 */
package com.cmc.model;

/**
 *  This class is an abstract class that is supposed to express the basic functionality
 *  of any account in the system.
 *  
 * @author Channa Kalsow
 */
public abstract class Account {
	
	protected String firstName, lastName, recoveryQuestion,
	recoveryAnswer, username, password;
	protected boolean enabled;
	protected boolean loggedOn = false;
	
	protected AccountType type;
	
	/**
	 * Account constructor
	 * @author Channa, Kristiana, Wenchy
	 * @param firstName
	 * @param lastname
	 * @param enabled
	 * @param recoveryQuestion
	 * @param recoveryAnswer
	 * @param username
	 * @param password
	 * @param type
	 * */
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
	
	/**
	 * allows a user to logon
	 * @author Channa, Kristiana, Wenchy
	 * @param passwordd
	 * @return boolean
	 * */
	public boolean logon(String password) {
		if (!enabled) return false;
		if (this.password.equals(password)) loggedOn = true;
		return loggedOn;
	}

	/**
	 * allows a user to logout
	 * @author Channa, Kristiana, Wenchy
	 * @return boolean
	 * */
	public boolean logout() {
		return (this.loggedOn = false) == false;
	}
	
	/**
	 * allows a user to recover password
	 * @author Channa, Kristiana, Wenchy
	 * @param answer
	 * @return String
	 * */
	public String recover(String answer) {
		if (recoveryAnswer.equals(answer)) return password;
		return "Failed to recover";
	}

	
	/**
	 * toString method
	 * @author Channa, Kristiana, Wenchy
	 * @return String
	 * */
	public String toString() {
		return "Name : " + lastName + ", " + firstName + "\n\tUsername: " + username + "\n\tType: " + type + "\n\tStatus: " + enabled;
	}
	
	
	/**
	 * @return the type
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * @param AccountType type the type to set
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
	
	/**
	 * Equals method override to allow for testing of this class
	 * @author wench
	 * @param Object to be compared
	 * @return boolean indicating equality
	 * */
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) return false;
		
		Account other = (Account) o;
		
		boolean firstNameMatch = this.firstName.equals(other.getFirstName());
		boolean lastNameMatch = this.lastName.equals(other.getLastName());
		boolean usernameMatch = this.username.equals(other.getUsername());
		boolean passwordMatch = this.password.equals(other.getPassword());
		boolean typeMatch = this.type == other.getType();
		
		return firstNameMatch && lastNameMatch && usernameMatch && passwordMatch && typeMatch;
	}
	
}
