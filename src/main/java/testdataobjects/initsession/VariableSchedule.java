
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class VariableSchedule {

	private Integer amount;
	private String dueDate;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public Integer getAmount() {
		return amount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
