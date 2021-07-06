package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class for CreateSDD
 */
public class SDD {
	// Creditor filter
	private String amount;
	private String dueDate;
	private String endToEndId;
	private String updateAmt;
	private String credEntity;
	private String sci;

	// updated at display sdd page
	private String entity;
	private String status;
	private String settlementMode;
	private String sequenceType;
	private String initiatorTransactionReference;
	private String initiatorCollectionReference;
	private String iban;
	private String valuedate;

	private String creationtype;
	private String umr;
	private String uir;

	/**
	 * Constructor for the class
	 * 
	 * @param map
	 *            data read form json file is recieved in this parameter
	 */
	public SDD(LinkedTreeMap<String, ?> map) {
		this.amount = (String) map.get("Amount");
		this.endToEndId = (String) map.get("EndtoEndid");
		this.updateAmt = (String) map.get("UpdateAmt");
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @return the creationtype
	 */
	public String getCreationtype() {
		return creationtype;
	}

	/**
	 * @return the credEntity
	 */
	public String getCredEntity() {
		return credEntity;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @return the endToEndId
	 */
	public String getEndToEndId() {
		return endToEndId;
	}

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * @return the initiatorCollectionReference
	 */
	public String getInitiatorCollectionReference() {
		return initiatorCollectionReference;
	}

	/**
	 * @return the initiatorTransactionReference
	 */
	public String getInitiatorTransactionReference() {
		return initiatorTransactionReference;
	}

	/**
	 * @return the sci
	 */
	public String getSci() {
		return sci;
	}

	/**
	 * @return the sequenceType
	 */
	public String getSequenceType() {
		return sequenceType;
	}

	/**
	 * @return the settlementMode
	 */
	public String getSettlementMode() {
		return settlementMode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the uir
	 */
	public String getUir() {
		return uir;
	}

	/**
	 * @return the umr
	 */
	public String getUmr() {
		return umr;
	}

	/**
	 * @return the updateAmt
	 */
	public String getUpdateAmt() {
		return updateAmt;
	}

	/**
	 * @return the valuedate
	 */
	public String getValuedate() {
		return valuedate;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @param creationtype
	 *            the creationtype to set
	 */
	public void setCreationtype(String creationtype) {
		this.creationtype = creationtype;
	}

	/**
	 * @param credEntity
	 *            the credEntity to set
	 */
	public void setCredEntity(String credEntity) {
		this.credEntity = credEntity;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @param endToEndId
	 *            the endToEndId to set
	 */
	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * @param iban
	 *            the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * @param initiatorCollectionReference
	 *            the initiatorCollectionReference to set
	 */
	public void setInitiatorCollectionReference(String initiatorCollectionReference) {
		this.initiatorCollectionReference = initiatorCollectionReference;
	}

	/**
	 * @param initiatorTransactionReference
	 *            the initiatorTransactionReference to set
	 */
	public void setInitiatorTransactionReference(String initiatorTransactionReference) {
		this.initiatorTransactionReference = initiatorTransactionReference;
	}

	/**
	 * @param sci
	 *            the sci to set
	 */
	public void setSci(String sci) {
		this.sci = sci;
	}

	/**
	 * @param sequenceType
	 *            the sequenceType to set
	 */
	public void setSequenceType(String sequenceType) {
		this.sequenceType = sequenceType;
	}

	/**
	 * @param settlementMode
	 *            the settlementMode to set
	 */
	public void setSettlementMode(String settlementMode) {
		this.settlementMode = settlementMode;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param uir
	 *            the uir to set
	 */
	public void setUir(String uir) {
		this.uir = uir;
	}

	/**
	 * @param umr
	 *            the umr to set
	 */
	public void setUmr(String umr) {
		this.umr = umr;
	}

	/**
	 * @param updateAmt
	 *            the updateAmt to set
	 */
	public void setUpdateAmt(String updateAmt) {
		this.updateAmt = updateAmt;
	}

	/**
	 * @param valuedate
	 *            the valuedate to set
	 */
	public void setValuedate(String valuedate) {
		this.valuedate = valuedate;
	}
}
