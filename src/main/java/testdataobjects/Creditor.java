package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class for Creditor
 */
public class Creditor {
	// Creditor filter
	private String name;
	private String sciIso;
	private String sciCountryCode;
	private String sciNumber;
	private String addressType;
	private String streetNumber;
	private String postalCode;
	private String streetName;
	private String city;
	private String addressTypeModified;


	private String countryDropdown;


	/**
	 * Constructor for the Creditor
	 * 
	 * @param map
	 *            receives the data read from json
	 */
	public Creditor(LinkedTreeMap<String, ?> map) {

		this.name = (String) map.get("Name");
		this.sciIso = (String) map.get("SCI");
		this.sciCountryCode = (String) map.get("SCI_CountryCode");
		this.sciNumber = (String) map.get("SCI_Number");
		this.addressType = (String) map.get("Address_type");
		this.streetNumber = (String) map.get("Street_number");
		this.postalCode = (String) map.get("PostalCode");
		this.streetName = (String) map.get("StreetName");
		this.city = (String) map.get("City");
		this.addressTypeModified =(String) map.get("Address_typeModified");
		this.countryDropdown=(String) map.get("CountryDropdown");

	}
	public String getAddressTypeModified() {
		return addressTypeModified;
	}

	public void setAddressTypeModified(String addressTypeModified) {
		this.addressTypeModified = addressTypeModified;
	}

	public String getCountryDropdown() {
		return countryDropdown;
	}

	public void setCountryDropdown(String countryDropdown) {
		this.countryDropdown = countryDropdown;
	}
	public String getAddressType() {
		return addressType;
	}

	public String getCity() {
		return city;
	}

	public String getCreditorName() {
		return name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getSciCountrycode() {
		return sciCountryCode;
	}

	public String getSciIso() {
		return sciIso;
	}

	public String getSciNumber() {
		return sciNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

}
