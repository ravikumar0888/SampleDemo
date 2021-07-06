
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class PeriodicSchedule {

	private Integer defaultAmount;
	private Integer firstAmount;
	private String firstDueDt;
	private Integer lastAmount;
	private String lastDueDt;
	private Boolean lastIsFinal;
	private Integer numbreOfOccurence;
	private String period;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public Integer getDefaultAmount() {
		return defaultAmount;
	}

	public Integer getFirstAmount() {
		return firstAmount;
	}

	public String getFirstDueDt() {
		return firstDueDt;
	}

	public Integer getLastAmount() {
		return lastAmount;
	}

	public String getLastDueDt() {
		return lastDueDt;
	}

	public Boolean getLastIsFinal() {
		return lastIsFinal;
	}

	public Integer getNumbreOfOccurence() {
		return numbreOfOccurence;
	}

	public String getPeriod() {
		return period;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setDefaultAmount(Integer defaultAmount) {
		this.defaultAmount = defaultAmount;
	}

	public void setFirstAmount(Integer firstAmount) {
		this.firstAmount = firstAmount;
	}

	public void setFirstDueDt(String firstDueDt) {
		this.firstDueDt = firstDueDt;
	}

	public void setLastAmount(Integer lastAmount) {
		this.lastAmount = lastAmount;
	}

	public void setLastDueDt(String lastDueDt) {
		this.lastDueDt = lastDueDt;
	}

	public void setLastIsFinal(Boolean lastIsFinal) {
		this.lastIsFinal = lastIsFinal;
	}

	public void setNumbreOfOccurence(Integer numbreOfOccurence) {
		this.numbreOfOccurence = numbreOfOccurence;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
