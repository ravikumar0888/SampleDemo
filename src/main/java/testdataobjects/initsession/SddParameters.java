
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SddParameters {

	private Integer firstPaymentAmount;
	private PeriodicSchedule periodicSchedule;
	private List<VariableSchedule> variableSchedule = null;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public Integer getFirstPaymentAmount() {
		return firstPaymentAmount;
	}

	public PeriodicSchedule getPeriodicSchedule() {
		return periodicSchedule;
	}

	public List<VariableSchedule> getVariableSchedule() {
		return variableSchedule;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setFirstPaymentAmount(Integer firstPaymentAmount) {
		this.firstPaymentAmount = firstPaymentAmount;
	}

	public void setPeriodicSchedule(PeriodicSchedule periodicSchedule) {
		this.periodicSchedule = periodicSchedule;
	}

	public void setVariableSchedule(List<VariableSchedule> variableSchedule) {
		this.variableSchedule = variableSchedule;
	}

}
