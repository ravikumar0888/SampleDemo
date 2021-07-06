
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class OptionalMandateFieldsType {

	private String contractDescription;
	private String contractId;
	private String ultimateDebtorId;
	private String ultimateDebtorName;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getContractDescription() {
		return contractDescription;
	}

	public String getContractId() {
		return contractId;
	}

	public String getUltimateDebtorId() {
		return ultimateDebtorId;
	}

	public String getUltimateDebtorName() {
		return ultimateDebtorName;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public void setUltimateDebtorId(String ultimateDebtorId) {
		this.ultimateDebtorId = ultimateDebtorId;
	}

	public void setUltimateDebtorName(String ultimateDebtorName) {
		this.ultimateDebtorName = ultimateDebtorName;
	}

}
