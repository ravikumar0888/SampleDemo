package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class of Mandates
 */
public class Mandates {
	// Creditor filter
	private String creditorEntity;
	private String sci;
	private String businessCode;

	// Creditor Data
	private String creditorName;
	private String creditorAccount;

	// Debtor Data
	private String thirdPartyFastSearch;
	private String debtorName;
	private String email;
	private String phoneNumber;
	private String debtorIdentifier;
	private boolean useofBBAN;
	private String debtorIBAN;
	private String debtorBIC;
	private String addressType;
	private String streetNumber;
	private String postalCode;
	private String streetName;
	private String city;
	private String country;
	private String sequenceType;
	private String updateIBAN;

	

	// Mandate Data
	private String mandateType;
	private boolean automaticUMRGeneration;
	private String umrTemplate;
	private String scheme;

	private String contractInformation;
	private String uir;
	private String umr;


	private String signatureDate;
	private String signatureCity;
	private String contractDescription;
	private String communicationLanguage;

	// Notification service
	private String notificationModeForDebtor;

	// Additional Information
	private String ultimateDebtorName;
	private String ultimateDebtorIdentifier;
	private String ultimateCreditorName;
	private String ultimateCreditorIdentifier;

	// Attachment list
	private String physicalArchivingKey;
	private String description;
	private String filepath;
	private String invlaidUMR;
	private String invaliddebtorName;
	private String invalidEmail;
	private String invalidphoneNumber;
	private String invalidpostalCode;
	private String invaliddebtorIBAN;
	private String newdebtorName;
	private String newdebtorIBAN;
	private String invalidUMR;
	private String newpostalCode;
	private String newcity;
	private String newstreetName;
	private String mode;


	private String phykey;
	private String phonenumberEr;

	private String updateCreditorName;


	/**
	 * Constructor of Mandates
	 * 
	 * @param map
	 *            receives data read from json
	 */
	public Mandates(LinkedTreeMap<String, ?> map) {
		this.creditorEntity = (String) map.get("CreditorEntity");
		this.sci = (String) map.get("SCI");
		this.businessCode = (String) map.get("BusinessCode");
		this.postalCode = (String) map.get("postalCode");
		this.debtorIBAN = (String) map.get("IBAN");
		this.city = (String) map.get("city");
		this.debtorName = (String) map.get("debtorName");
		this.streetName = (String) map.get("streetName");
		this.country = (String) map.get("country");
		this.invlaidUMR = (String) map.get("invlaidUMR");
		this.invaliddebtorName = (String) map.get("invaliddebtorName");
		this.invalidEmail = (String) map.get("invalidEmail");
		this.invalidphoneNumber = (String) map.get("invalidphoneNumber");

		this.invaliddebtorIBAN = (String) map.get("invaliddebtorIBAN");
		this.invalidpostalCode = (String) map.get("invalidpostalCode");
		this.invalidUMR = (String) map.get("invalidUMR");

		this.newdebtorName = (String) map.get("newdebtorName");
		this.newdebtorIBAN = (String) map.get("newdebtorIBAN");
		this.newpostalCode = (String) map.get("newpostalCode");

		this.newcity = (String) map.get("newcity");
		this.newstreetName = (String) map.get("newstreetName");
		this.mode = (String) map.get("mode");
		this.email = (String) map.get("email");
		this.phykey = (String) map.get("phykey");
		this.phonenumberEr = (String) map.get("phonenumberEr");
		this.addressType =(String) map.get("addressType");
		
		this.sequenceType= (String) map.get("sequenceType");
		this.updateCreditorName= (String) map.get("updateCreditorName");	
		this.updateIBAN =(String) map.get("updateIBAN");
		
		this.uir  =(String) map.get("Uir");
		this.umr  =(String) map.get("Umr");
	}
	
	public String getUpdateCreditorName() {
		return updateCreditorName;
	}
	public String getUpdateIBAN() {
		return updateIBAN;
	}

	public void setUpdateIBAN(String updateIBAN) {
		this.updateIBAN = updateIBAN;
	}


	public void setUpdateCreditorName(String updateCreditorName) {
		this.updateCreditorName = updateCreditorName;
	}
	
	public String getUmr() {
		return umr;
	}

	public void setUmr(String umr) {
		this.umr = umr;
	}
	
	public String getPhonenumberEr() {
		return phonenumberEr;
	}
	public void setPhonenumberEr(String phonenumberEr) {
		this.phonenumberEr = phonenumberEr;
	}
	public String getAddressType() {
		return addressType;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public String getCity() {
		return city;
	}

	public String getCommunicationLanguage() {
		return communicationLanguage;
	}

	public String getContractDescription() {
		return contractDescription;
	}

	public String getContractInformation() {
		return contractInformation;
	}

	public String getCountry() {
		return country;
	}

	public String getCreditorAccount() {
		return creditorAccount;
	}

	public String getCreditorEntity() {
		return creditorEntity;
	}

	public String getCreditorName() {
		return creditorName;
	}

	public String getDebtorBIC() {
		return debtorBIC;
	}

	public String getDebtorIBAN() {
		return debtorIBAN;
	}

	public String getDebtorIdentifier() {
		return debtorIdentifier;
	}

	public String getDebtorName() {
		return debtorName;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getInvalidDebtorIBAN() {
		return invaliddebtorIBAN;
	}

	public String getInvalidDebtorName() {
		return invaliddebtorName;
	}

	public String getInvalidemail() {
		return invalidEmail;
	}

	public String getInvalidPhoneNumber() {
		return invalidphoneNumber;
	}

	public String getInvalidPostalCode() {
		return invalidpostalCode;
	}

	public String getinvalidUMR() {
		return invalidUMR;
	}

	public String getinvlaidUMR() {
		return invlaidUMR;
	}

	public String getMandateType() {
		return mandateType;
	}

	public String getModeOfDebitor() {
		return mode;
	}

	public String getNewCity() {
		return newcity;
	}

	public String getNewDebtorIBAN() {
		return newdebtorIBAN;
	}

	public String getNewDebtorName() {
		return newdebtorName;
	}

	public String getNewPostalCode() {
		return newpostalCode;
	}

	public String getNewStreetName() {
		return newstreetName;
	}

	public String getNotificationModeForDebtor() {
		return notificationModeForDebtor;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPhyKey() {
		return phykey;
	}

	public String getPhysicalArchivingKey() {
		return physicalArchivingKey;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getScheme() {
		return scheme;
	}

	public String getSCI() {
		return sci;
	}

	public String getSequenceType() {
		return sequenceType;
	}

	public String getSignatureCity() {
		return signatureCity;
	}

	public String getSignatureDate() {
		return signatureDate;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getThirdPartyFastSearch() {
		return thirdPartyFastSearch;
	}

	public String getUIR() {
		return uir;
	}

	public String getUltimateCreditorIdentifier() {
		return ultimateCreditorIdentifier;
	}

	public String getUltimateCreditorName() {
		return ultimateCreditorName;
	}

	public String getUltimateDebtorIdentifier() {
		return ultimateDebtorIdentifier;
	}

	public String getUltimateDebtorName() {
		return ultimateDebtorName;
	}

	public String getUMRtemplate() {
		return umrTemplate;
	}

	public boolean isAutomaticUMRGeneration() {
		return automaticUMRGeneration;
	}

	public boolean isUseofBBAN() {
		return useofBBAN;
	}

	public void setAddressType(String addressTypeValue) {
		addressType = addressTypeValue;
	}

	public void setAutomaticUMRGeneration(boolean automaticUMRGenerationValue) {
		automaticUMRGeneration = automaticUMRGenerationValue;
	}

	public void setBusinessCode(String businessCodeValue) {
		businessCode = businessCodeValue;
	}

	public void setCity(String cityValue) {
		city = cityValue;
	}

	public void setCommunicationLanguage(String communicationLanguageValue) {
		communicationLanguage = communicationLanguageValue;
	}

	public void setContractDescription(String contractDescriptionValue) {
		contractDescription = contractDescriptionValue;
	}

	public void setContractInformation(String contractInformationValue) {
		contractInformation = contractInformationValue;
	}

	public void setCountry(String countryValue) {
		country = countryValue;
	}

	public void setCreditorAccount(String creditorAccountValue) {
		creditorAccount = creditorAccountValue;
	}

	public void setCreditorEntity(String creditorEntityValue) {
		creditorEntity = creditorEntityValue;
	}

	public void setCreditorName(String creditorNameValue) {
		creditorName = creditorNameValue;
	}

	public void setDebtorBIC(String debtorBICValue) {
		debtorBIC = debtorBICValue;
	}

	public void setDebtorIBAN(String debtorIBANValue) {
		debtorIBAN = debtorIBANValue;
	}

	public void setDebtorIdentifier(String debtorIdentifierValue) {
		debtorIdentifier = debtorIdentifierValue;
	}

	public void setDebtorName(String debtorNameValue) {
		debtorName = debtorNameValue;
	}

	public void setDescription(String descriptionValue) {
		description = descriptionValue;
	}

	public void setEmail(String emailValue) {
		email = emailValue;
	}

	public void setFilepath(String filepathValue) {
		filepath = filepathValue;
	}

	public void setMandateType(String mandateTypeValue) {
		mandateType = mandateTypeValue;
	}

	public void setNotificationModeForDebtor(String notificationModeForDebtorValue) {
		notificationModeForDebtor = notificationModeForDebtorValue;
	}

	public void setPhoneNumber(String phoneNumberValue) {
		phoneNumber = phoneNumberValue;
	}

	public void setPhysicalArchivingKey(String physicalArchivingKeyValue) {
		physicalArchivingKey = physicalArchivingKeyValue;
	}

	public void setPostalCode(String postalCodeValue) {
		postalCode = postalCodeValue;
	}

	public void setScheme(String schemeValue) {
		scheme = schemeValue;
	}

	public void setSCI(String sCI) {
		sci = sCI;
	}

	public void setSequenceType(String sequenceTypeValue) {
		sequenceType = sequenceTypeValue;
	}

	public void setSignatureCity(String signatureCityValue) {
		signatureCity = signatureCityValue;
	}

	public void setSignatureDate(String signatureDateValue) {
		signatureDate = signatureDateValue;
	}

	public void setStreetName(String streetNameValue) {
		streetName = streetNameValue;
	}

	public void setStreetNumber(String streetNumberValue) {
		streetNumber = streetNumberValue;
	}

	public void setThirdPartyFastSearch(String thirdPartyFastSearchValue) {
		thirdPartyFastSearch = thirdPartyFastSearchValue;
	}

	public void setUIR(String uIR) {
		uir = uIR;
	}

	public void setUltimateCreditorIdentifier(String ultimateCreditorIdentifierValue) {
		ultimateCreditorIdentifier = ultimateCreditorIdentifierValue;
	}

	public void setUltimateCreditorName(String ultimateCreditorNameValue) {
		ultimateCreditorName = ultimateCreditorNameValue;
	}

	public void setUltimateDebtorIdentifier(String ultimateDebtorIdentifierValue) {
		ultimateDebtorIdentifier = ultimateDebtorIdentifierValue;
	}

	public void setUltimateDebtorName(String ultimateDebtorNameValue) {
		ultimateDebtorName = ultimateDebtorNameValue;
	}

	public void setUMRtemplate(String uMRtemplateValue) {
		umrTemplate = uMRtemplateValue;
	}

	public void setUseofBBAN(boolean useofBBANValue) {
		useofBBAN = useofBBANValue;
	}

	@Override
	public String toString() {
		return ("CreditorEntity " + creditorEntity + " SCI " + sci + " BusinessCode " + businessCode + " CreditorName " + creditorName + " CreditorAccount " + creditorAccount + " ThirdPartyFastSearch " + thirdPartyFastSearch + " DebtorName " + debtorName + " Email " + email + " PhoneNumber " + phoneNumber + " DebtorIdentifier " + debtorIdentifier + " UseofBBAN " + useofBBAN + " DebtorIBAN " + debtorIBAN + " DebtorBIC " + debtorBIC + " AddressType " + addressType + " StreetNumber " + streetNumber
				+ " PostalCode " + postalCode + " StreetName " + streetName + " City " + city + " Country " + country + " MandateType " + mandateType + " AutomaticUMRGeneration " + automaticUMRGeneration + " UMRtemplate " + umrTemplate + " Scheme " + scheme + " SequenceType " + sequenceType + " ContractInformation " + contractInformation + " UIR " + uir + " SignatureDate " + signatureDate + " SignatureCity " + signatureCity + " ContractDescription " + contractDescription
				+ " CommunicationLanguage " + communicationLanguage + " NotificationModeForDebtor " + notificationModeForDebtor + " UltimateDebtorName " + ultimateDebtorName + " UltimateDebtorIdentifier " + ultimateDebtorIdentifier + " UltimateCreditorName " + ultimateCreditorName + " UltimateCreditorIdentifier " + ultimateCreditorIdentifier + " PhysicalArchivingKey " + physicalArchivingKey + " Description " + description + " Filepath " + filepath);
	}

}
