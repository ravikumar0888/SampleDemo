
package testdataobjects;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

public class BackOfficeInitSession {

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private String creditorId;
	private String transcationId;
	private boolean chbValidation;
	private boolean chbSkipFirstPage;
	private boolean chbSkipSummaryPage;
	private boolean chbDisplayAttachment;
	private boolean chbIsSPS;
	private boolean chbSendMndByEmail;
	private boolean chbGoogleAnalyticsConsent;

	private String UIR;
	private String ddAgreementScheme;
	private String ddCustomerType;

	private String IBAN;
	private String BIC;
	private String DebitorBBAN;
	private String DebitorCountry;

	private String SCICountry;
	private String SCICheckdigit;
	private String SCIBusinessCode;
	private String NationalIdentifer;

	private String SignerInfoLastname;
	private String SignerInfoFirstname;
	private String SingerInfoGender;
	private String SingerInfoEmail;
	private String SingerInfoAddress;
	private String PhoneNumber;

	private String btnSubmitSession;
	private String btnUseSession;

	private String Country;
	private String City;
	private String PostalCode;
	private String StreetNumber;
	private String BuildingNumber;
	private String Language;

	private String sessionParameters;

	public BackOfficeInitSession(LinkedTreeMap<String, ?> map) {
		this.creditorId = (String) map.get("creditorId");

		this.chbSkipSummaryPage = (Boolean) map.get("skipSummaryPage");
		this.chbValidation = (Boolean) map.get("validation");
		this.ddAgreementScheme = (String) map.get("agreementScheme");
		this.ddCustomerType = (String) map.get("customerType");
		this.IBAN = (String) map.get("iban");
		this.BIC = (String) map.get("bic");
		this.DebitorBBAN = (String) map.get("debtorBBAN");
		this.DebitorCountry = (String) map.get("debtorCtry");

		this.SCICountry = (String) map.get("sciCountry");
		this.SCICheckdigit = (String) map.get("sciDigit");
		this.SCIBusinessCode = (String) map.get("sciBusinessCode");
		this.NationalIdentifer = (String) map.get("sciNI");

		this.SignerInfoLastname = (String) map.get("lastName");
		this.SignerInfoFirstname = (String) map.get("firstName");
		this.SingerInfoGender = (String) map.get("gender");
		this.SingerInfoEmail = (String) map.get("email");
		this.SingerInfoAddress = (String) map.get("postalAddress");
		this.PhoneNumber = (String) map.get("phonenumber");
		this.Country = (String) map.get("country");

		this.City = (String) map.get("city");

		this.PostalCode = (String) map.get("postalCode");

		this.StreetNumber = (String) map.get("streetNumber");

		this.BuildingNumber = (String) map.get("buildingNumber");
		this.Language = (String) map.get("language");
	}

	 
	public String getBtnUseSession() {
		return btnUseSession;
	}

	public String getBuildingNumber() {
		return BuildingNumber;
	}

	public String getCity() {
		return City;
	}

	public String getCountry() {
		return Country;
	}

	public String getCreditorId() {
		return creditorId;
	}

	public String getDdAgreementScheme() {
		return ddAgreementScheme;
	}

	public String getDebitorBBAN() {
		return DebitorBBAN;
	}

	public String getDebitorCountry() {
		return DebitorCountry;
	}

	public String getIBAN() {
		return IBAN;
	}

	public String getLanguage() {
		return Language;
	}

	public String getNationalIdentifer() {
		return NationalIdentifer;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public String getSCIBusinessCode() {
		return SCIBusinessCode;
	}

	public String getSCICheckdigit() {
		return SCICheckdigit;
	}

	public String getSCICountry() {
		return SCICountry;
	}

	public String getSignerInfoFirstname() {
		return SignerInfoFirstname;
	}

	public String getSignerInfoLastname() {
		return SignerInfoLastname;
	}

	public String getSingerInfoAddress() {
		return SingerInfoAddress;
	}

	public String getSingerInfoEmail() {
		return SingerInfoEmail;
	}

	public String getSingerInfoGender() {
		return SingerInfoGender;
	}

	public String getStreetNumber() {
		return StreetNumber;
	}

	 
	public String getUIR() {
		return UIR;
	}

	public boolean isChbSkipSummaryPage() {
		return chbSkipSummaryPage;
	}

	public void setBtnSubitSession(String btnSubitSession) {
		this.btnSubmitSession = btnSubitSession;
	}

	public void setBtnUseSession(String btnUseSession) {
		this.btnUseSession = btnUseSession;
	}

	public void setBuildingNumber(String buildingNumber) {
		BuildingNumber = buildingNumber;
	}

	public void setChbSkipSummaryPage(boolean chbSkipSummaryPage) {
		this.chbSkipSummaryPage = chbSkipSummaryPage;
	}

	public void setCity(String city) {
		City = city;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}

	public void setDdAgreementScheme(String ddAgreementScheme) {
		this.ddAgreementScheme = ddAgreementScheme;
	}

	public void setDebitorBBAN(String debitorBBAN) {
		DebitorBBAN = debitorBBAN;
	}

	public void setDebitorCountry(String debitorCountry) {
		DebitorCountry = debitorCountry;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public void setNationalIdentifer(String nationalIdentifer) {
		NationalIdentifer = nationalIdentifer;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public void setSCIBusinessCode(String sCIBusinessCode) {
		SCIBusinessCode = sCIBusinessCode;
	}

	public void setSCICheckdigit(String sCICheckdigit) {
		SCICheckdigit = sCICheckdigit;
	}

	public void setSCICountry(String sCICountry) {
		SCICountry = sCICountry;
	}

	public void setSignerInfoFirstname(String signerInfoFirstname) {
		SignerInfoFirstname = signerInfoFirstname;
	}

	public void setSignerInfoLastname(String signerInfoLastname) {
		SignerInfoLastname = signerInfoLastname;
	}

	public void setSingerInfoAddress(String singerInfoAddress) {
		SingerInfoAddress = singerInfoAddress;
	}

	public void setSingerInfoEmail(String singerInfoEmail) {
		SingerInfoEmail = singerInfoEmail;
	}

	public void setSingerInfoGender(String singerInfoGender) {
		SingerInfoGender = singerInfoGender;
	}

	public void setStreetNumber(String streetNumber) {
		StreetNumber = streetNumber;
	}

	 

	public void setUIR(String uIR) {
		UIR = uIR;
	}

	/**
	 * @return the additionalProperties
	 */
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	/**
	 * @return the transcationid
	 */
	public String getTranscationId() {
		return transcationId;
	}

	/**
	 * @return the chbValidation
	 */
	public boolean isChbValidation() {
		return chbValidation;
	}

	/**
	 * @return the chbSkipFirstPage
	 */
	public boolean isChbSkipFirstPage() {
		return chbSkipFirstPage;
	}

	/**
	 * @return the chbDisplayAttachment
	 */
	public boolean isChbDisplayAttachment() {
		return chbDisplayAttachment;
	}

	/**
	 * @return the chbIsSPS
	 */
	public boolean isChbIsSPS() {
		return chbIsSPS;
	}

	/**
	 * @return the chbSendMndByEmail
	 */
	public boolean isChbSendMndByEmail() {
		return chbSendMndByEmail;
	}

	/**
	 * @return the chbGoogleAnalyticsConsent
	 */
	public boolean isChbGoogleAnalyticsConsent() {
		return chbGoogleAnalyticsConsent;
	}

	/**
	 * @return the ddCustomerType
	 */
	public String getDdCustomerType() {
		return ddCustomerType;
	}

	/**
	 * @return the bIC
	 */
	public String getBIC() {
		return BIC;
	}

	/**
	 * @return the btnSubmitSession
	 */
	public String getBtnSubmitSession() {
		return btnSubmitSession;
	}

	/**
	 * @return the sessionParameters
	 */
	public String getSessionParameters() {
		return sessionParameters;
	}

	/**
	 * @param additionalProperties the additionalProperties to set
	 */
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	/**
	 * @param transcationid the transcationid to set
	 */
	public void setTranscationid(String transcationid) {
		this.transcationId = transcationid;
	}

	/**
	 * @param chbValidation the chbValidation to set
	 */
	public void setChbValidation(boolean chbValidation) {
		this.chbValidation = chbValidation;
	}

	/**
	 * @param chbSkipFirstPage the chbSkipFirstPage to set
	 */
	public void setChbSkipFirstPage(boolean chbSkipFirstPage) {
		this.chbSkipFirstPage = chbSkipFirstPage;
	}

	/**
	 * @param chbDisplayAttachment the chbDisplayAttachment to set
	 */
	public void setChbDisplayAttachment(boolean chbDisplayAttachment) {
		this.chbDisplayAttachment = chbDisplayAttachment;
	}

	/**
	 * @param chbIsSPS the chbIsSPS to set
	 */
	public void setChbIsSPS(boolean chbIsSPS) {
		this.chbIsSPS = chbIsSPS;
	}

	/**
	 * @param chbSendMndByEmail the chbSendMndByEmail to set
	 */
	public void setChbSendMndByEmail(boolean chbSendMndByEmail) {
		this.chbSendMndByEmail = chbSendMndByEmail;
	}

	/**
	 * @param chbGoogleAnalyticsConsent the chbGoogleAnalyticsConsent to set
	 */
	public void setChbGoogleAnalyticsConsent(boolean chbGoogleAnalyticsConsent) {
		this.chbGoogleAnalyticsConsent = chbGoogleAnalyticsConsent;
	}

	/**
	 * @param ddCustomerType the ddCustomerType to set
	 */
	public void setDdCustomerType(String ddCustomerType) {
		this.ddCustomerType = ddCustomerType;
	}

	/**
	 * @param bIC the bIC to set
	 */
	public void setBIC(String bIC) {
		BIC = bIC;
	}

	/**
	 * @param btnSubmitSession the btnSubmitSession to set
	 */
	public void setBtnSubmitSession(String btnSubmitSession) {
		this.btnSubmitSession = btnSubmitSession;
	}

	/**
	 * @param sessionParameters the sessionParameters to set
	 */
	public void setSessionParameters(String sessionParameters) {
		this.sessionParameters = sessionParameters;
	}

}
