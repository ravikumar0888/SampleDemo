
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class CreditorBankAccount {

	private String bic11;
	private String iban;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getBic11() {
		return bic11;
	}

	public String getIban() {
		return iban;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setBic11(String bic11) {
		this.bic11 = bic11;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

}
