package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class for Creditor
 */
public class EventReport {
	// Creditor filter
	private String email;
	private String entity;
	private String businessObject;
	


	/**
	 * Constructor for the Creditor
	 * 
	 * @param map
	 *            receives the data read from json
	 */
	public EventReport(LinkedTreeMap<String, ?> map) {

		this.email = (String) map.get("email");
		this.entity = (String) map.get("entity");
		this.businessObject = (String) map.get("businessObject");
		

	}
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getEntity() {
		return entity;
	}



	public void setEntity(String entity) {
		this.entity = entity;
	}



	public String getBusinessObject() {
		return businessObject;
	}



	public void setBusinessObject(String businessObject) {
		this.businessObject = businessObject;
	}



	
}
