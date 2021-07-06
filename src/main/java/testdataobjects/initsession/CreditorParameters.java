
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class CreditorParameters {

	private CreditorBankAccount creditorBankAccount;
	private String creditorId;
	private String sci;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public CreditorBankAccount getCreditorBankAccount() {
		return creditorBankAccount;
	}

	public String getCreditorId() {
		return creditorId;
	}

	public String getSci() {
		return sci;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setCreditorBankAccount(CreditorBankAccount creditorBankAccount) {
		this.creditorBankAccount = creditorBankAccount;
	}

	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}

	public void setSci(String sci) {
		this.sci = sci;
	}

}
