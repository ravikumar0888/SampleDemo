package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class for CreateSDD
 */
public class SCT {
	// Creditor filter
	private String sci;
	

	private String businessCode;
	private String endToEndId;
	private String iban;
	private String bic;
	private String amount;
	private String initiatorTransactionReference;
	


	private String updateAmount;
	


	public void setInitiatorTransactionReference(String initiatorTransactionReference) {
		this.initiatorTransactionReference = initiatorTransactionReference;
	}

	/**
	 * Constructor for the class
	 * 
	 * @param map
	 *            data read form json file is recieved in this parameter
	 */
	public SCT(LinkedTreeMap<String, ?> map) {
		this.sci = (String) map.get("SCI");
		this.amount = (String) map.get("Amount");
		this.businessCode = (String) map.get("Businesscode");
		this.iban = (String) map.get("IBAN");
		this.bic = (String) map.get("BIC");
		this.updateAmount = (String) map.get("UpdateAmt");
		
	}
	public String getUpdateAmount() {
		return updateAmount;
	}

	public void setUpdateAmount(String updateAmount) {
		this.updateAmount = updateAmount;
	}



	public String getSci() {
		return sci;
	}




	public void setSci(String sci) {
		this.sci = sci;
	}




	public String getBusinessCode() {
		return businessCode;
	}




	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}




	public String getEndToEndId() {
		return endToEndId;
	}


	public String getInitiatorTransactionReference() {
		return initiatorTransactionReference;
	}


	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}




	public String getIban() {
		return iban;
	}




	public void setIban(String iban) {
		this.iban = iban;
	}




	public String getBic() {
		return bic;
	}




	public void setBic(String bic) {
		this.bic = bic;
	}




	public String getAmount() {
		return amount;
	}




	public void setAmount(String amount) {
		this.amount = amount;
	}



	
	
}
