package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * 
 * POJO class for Root
 *
 * 
 */
public class Root {
	// Creditor filter
	private String rootName;
	private String rootContractinformation;
	private String rootPhoneNumber;
	private String rootStreetNumber;
	private String rootStreetName;
	private String rootPostalCode;
	private String rootCity;
	private String rootCountry;

	// Edit root

	private String rootEdit;
	private String rootBusinessCode1;
	private String rootBusinessCode2;
	private String rootIBAN;
	private String rootBIC;
	private String rootLabel;
	private String rootEmail;

	/**
	 * Constructor for Root
	 * 
	 * @param map
	 *            receives data read from json
	 */
	public Root(LinkedTreeMap<String, ?> map) {

		this.rootName = (String) map.get("RootName");
		this.rootContractinformation = (String) map.get("Root_Contractinformation");
		this.rootPhoneNumber = (String) map.get("Root_PhoneNumber");
		this.rootStreetNumber = (String) map.get("Root_Street_number");

		this.rootStreetName = (String) map.get("Root_StreetName");
		this.rootPostalCode = (String) map.get("Root_PostalCode");
		this.rootCity = (String) map.get("Root_City");
		this.rootCountry = (String) map.get("Root_Country");
		this.rootBusinessCode1 = (String) map.get("Root_BusinessCode1");
		this.rootBusinessCode2 = (String) map.get("Root_BusinessCode2");
		this.rootIBAN = (String) map.get("Root_IBAN");

		this.rootBIC = (String) map.get("Root_BIC");
		this.rootLabel = (String) map.get("Root_Label");
		this.rootEmail = (String) map.get("Root_Email");

	}

	public String getRootBIC() {
		return rootBIC;
	}

	public String getRootBusinessCode1() {
		return rootBusinessCode1;
	}

	public String getRootBusinessCode2() {
		return rootBusinessCode2;
	}

	public String getRootCity() {
		return rootCity;
	}

	public String getRootContractinformation() {
		return rootContractinformation;
	}

	public String getRootCountry() {
		return rootCountry;
	}

	public String getRootEdit() {
		return rootEdit;
	}

	public String getRootEmail() {
		return rootEmail;
	}

	public String getRootIBAN() {
		return rootIBAN;
	}

	public String getRootLabel() {
		return rootLabel;
	}

	public String getRootName() {
		return rootName;
	}

	public String getRootPhoneNumber() {
		return rootPhoneNumber;
	}

	public String getRootPostalCode() {
		return rootPostalCode;
	}

	public String getRootStreetName() {
		return rootStreetName;
	}

	public String getRootStreetNumber() {
		return rootStreetNumber;
	}

	public void setRootBIC(String rootBIC) {
		this.rootBIC = rootBIC;
	}

	public void setRootBusinessCode1(String rootBusinessCode1) {
		this.rootBusinessCode1 = rootBusinessCode1;
	}

	public void setRootBusinessCode2(String rootBusinessCode2) {
		this.rootBusinessCode2 = rootBusinessCode2;
	}

	public void setRootCity(String rootCity) {
		this.rootCity = rootCity;
	}

	public void setRootContractinformation(String rootContractinformation) {
		this.rootContractinformation = rootContractinformation;
	}

	public void setRootCountry(String rootCountry) {
		this.rootCountry = rootCountry;
	}

	public void setRootEdit(String rootEdit) {
		this.rootEdit = rootEdit;
	}

	public void setRootEmail(String rootEmail) {
		this.rootEmail = rootEmail;
	}

	public void setRootIBAN(String rootIBAN) {
		this.rootIBAN = rootIBAN;
	}

	public void setRootLabel(String rootLabel) {
		this.rootLabel = rootLabel;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public void setRootPhoneNumber(String rootPhoneNumber) {
		this.rootPhoneNumber = rootPhoneNumber;
	}

	public void setRootPostalCode(String rootPostalCode) {
		this.rootPostalCode = rootPostalCode;
	}

	public void setRootStreetName(String rootStreetName) {
		this.rootStreetName = rootStreetName;
	}

	public void setRootStreetNumber(String rootStreetNumber) {
		this.rootStreetNumber = rootStreetNumber;
	}

}
