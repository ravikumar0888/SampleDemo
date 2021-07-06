
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class DebtorBankAccount {

	private String bban;
	private String bic11;
	private String ctry;
	private String iban;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getBban() {
		return bban;
	}

	public String getBic11() {
		return bic11;
	}

	public String getCtry() {
		return ctry;
	}

	public String getIban() {
		return iban;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setBban(String bban) {
		this.bban = bban;
	}

	public void setBic11(String bic11) {
		this.bic11 = bic11;
	}

	public void setCtry(String ctry) {
		this.ctry = ctry;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

}
