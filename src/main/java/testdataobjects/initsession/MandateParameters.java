
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class MandateParameters {

	private String mandateSeqType;
	private String mandateType;
	private OptionalMandateFieldsType optionalMandateFieldsType;
	private String uir;
	private String umr;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getMandateSeqType() {
		return mandateSeqType;
	}

	public String getMandateType() {
		return mandateType;
	}

	public OptionalMandateFieldsType getOptionalMandateFieldsType() {
		return optionalMandateFieldsType;
	}

	public String getUir() {
		return uir;
	}

	public String getUmr() {
		return umr;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setMandateSeqType(String mandateSeqType) {
		this.mandateSeqType = mandateSeqType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	public void setOptionalMandateFieldsType(OptionalMandateFieldsType optionalMandateFieldsType) {
		this.optionalMandateFieldsType = optionalMandateFieldsType;
	}

	public void setUir(String uir) {
		this.uir = uir;
	}

	public void setUmr(String umr) {
		this.umr = umr;
	}

}
