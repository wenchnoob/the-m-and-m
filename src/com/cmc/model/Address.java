/**
 * 
 */
package com.cmc.model;

/**
 * @author wench
 *
 */
public class Address {

	private String street, city, state, country;
	private int postalCode;
	
	
	/**
	 * Address constructor
	 * @author Channa, Kristiana, Wenchy
	 * @param street
	 * @param city
	 * @param state
	 * @param country
	 * @param postalcode
	 * */
	public Address(String street, String city, String state, String country, int postalCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
	}
	
	/**
	 * toString method
	 * @author Channa, Kristiana, Wenchy
	 * @return String
	 * */
	public String toString() {
		return "\n\t\t" + street + "\n\t\t" + city + ", " + state + "\n\t\t" + country + ", " + postalCode;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return postalCode;
	}


	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}


}
