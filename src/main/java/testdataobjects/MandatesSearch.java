package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class for MandatesSearch
 */
public class MandatesSearch {
	// Creditor filter
	private String creditorEntity;
	private String status;
	private String payment;

	/**
	 * Constructor of MandatesSearch
	 * 
	 * @param map
	 *            receives data read from json
	 */
	public MandatesSearch(LinkedTreeMap<String, ?> map) {
		this.creditorEntity = (String) map.get("CreditorEntity");
		this.status = (String) map.get("status");
		this.payment = (String) map.get("payment");
	}

	public String getCreditorEntity() {
		return creditorEntity;
	}

	public String getPaymenttype() {
		return payment;
	}

	public String getStatus() {
		return status;
	}

}
