
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class CompanyInfos {

	private String businessIdentifier;
	private String businessName;
	private String companyName;
	private Boolean corporateIndicator;
	private String signerPositionOccupied;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getBusinessIdentifier() {
		return businessIdentifier;
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Boolean getCorporateIndicator() {
		return corporateIndicator;
	}

	public String getSignerPositionOccupied() {
		return signerPositionOccupied;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setBusinessIdentifier(String businessIdentifier) {
		this.businessIdentifier = businessIdentifier;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCorporateIndicator(Boolean corporateIndicator) {
		this.corporateIndicator = corporateIndicator;
	}

	public void setSignerPositionOccupied(String signerPositionOccupied) {
		this.signerPositionOccupied = signerPositionOccupied;
	}

}
