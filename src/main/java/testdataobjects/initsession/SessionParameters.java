
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class SessionParameters {

	private String agreementScheme;
	private Boolean displayAttachments;
	private Boolean documentSignature;
	private Boolean googleAnalyticsConsent;
	private String language;
	private Boolean sendMndByEmail;
	private SessionDocumentSignature sessionDocumentSignature;
	private Boolean skipFirstPage;
	private Boolean skipSummaryPage;
	private Boolean sps;
	private String transactionId;
	private String urlCancel;
	private String urlKO;
	private String urlOK;
	private Boolean validation;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getAgreementScheme() {
		return agreementScheme;
	}

	public Boolean getDisplayAttachments() {
		return displayAttachments;
	}

	public Boolean getDocumentSignature() {
		return documentSignature;
	}

	public Boolean getGoogleAnalyticsConsent() {
		return googleAnalyticsConsent;
	}

	public String getLanguage() {
		return language;
	}

	public Boolean getSendMndByEmail() {
		return sendMndByEmail;
	}

	public SessionDocumentSignature getSessionDocumentSignature() {
		return sessionDocumentSignature;
	}

	public Boolean getSkipFirstPage() {
		return skipFirstPage;
	}

	public Boolean getSkipSummaryPage() {
		return skipSummaryPage;
	}

	public Boolean getSps() {
		return sps;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getUrlCancel() {
		return urlCancel;
	}

	public String getUrlKO() {
		return urlKO;
	}

	public String getUrlOK() {
		return urlOK;
	}

	public Boolean getValidation() {
		return validation;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setAgreementScheme(String agreementScheme) {
		this.agreementScheme = agreementScheme;
	}

	public void setDisplayAttachments(Boolean displayAttachments) {
		this.displayAttachments = displayAttachments;
	}

	public void setDocumentSignature(Boolean documentSignature) {
		this.documentSignature = documentSignature;
	}

	public void setGoogleAnalyticsConsent(Boolean googleAnalyticsConsent) {
		this.googleAnalyticsConsent = googleAnalyticsConsent;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setSendMndByEmail(Boolean sendMndByEmail) {
		this.sendMndByEmail = sendMndByEmail;
	}

	public void setSessionDocumentSignature(SessionDocumentSignature sessionDocumentSignature) {
		this.sessionDocumentSignature = sessionDocumentSignature;
	}

	public void setSkipFirstPage(Boolean skipFirstPage) {
		this.skipFirstPage = skipFirstPage;
	}

	public void setSkipSummaryPage(Boolean skipSummaryPage) {
		this.skipSummaryPage = skipSummaryPage;
	}

	public void setSps(Boolean sps) {
		this.sps = sps;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setUrlCancel(String urlCancel) {
		this.urlCancel = urlCancel;
	}

	public void setUrlKO(String urlKO) {
		this.urlKO = urlKO;
	}

	public void setUrlOK(String urlOK) {
		this.urlOK = urlOK;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

}
