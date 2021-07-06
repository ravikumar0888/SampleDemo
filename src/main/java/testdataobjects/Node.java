package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

import testbase.BasePage;

/**
 * POJO class for Creditor
 */
public class Node extends BasePage{
	// Creditor filter
	
	private String streetNumber;
	private String postalCode;
	private String streetName;
	private String city;
	private String CountryDropdown;


	/**
	 * Constructor for the Creditor
	 * 
	 * @param map
	 *            receives the data read from json
	 */
	public Node(LinkedTreeMap<String, ?> map) {

		
		this.streetNumber = (String) map.get("Street_number");
		this.postalCode = (String) map.get("PostalCode");
		this.streetName = (String) map.get("StreetName");
		this.city = (String) map.get("City");
		this.CountryDropdown=(String) map.get("CountryDropdown");

	}
	public String getCountryDropdown() {
		return CountryDropdown;
	}

	public void setCountryDropdown(String countryDropdown) {
		CountryDropdown = countryDropdown;
	}
	
	public String getCity() {
		return city;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

}
