package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * POJO class of Mandates
 */
public class FileSupervision {
	// Creditor filter
	private String creditorEntity;
	private String selectUploadFile;

	private String fileFormat;
	private String activeLabel;
	private String activeCount;
	private String pendingLabel;
	private String pendingCount;
	private String rejectCode;

 

	/**
	 * Constructor of Mandates
	 * 
	 * @param map
	 *            receives data read from json
	 */
	public FileSupervision(LinkedTreeMap<String, ?> map) {
		this.creditorEntity = (String) map.get("CreditorEntity");

		this.selectUploadFile = (String) map.get("selectUploadFile");

		this.fileFormat = (String) map.get("fileFormat");
		this.activeLabel = (String) map.get("activeLabel");
		this.activeCount = (String) map.get("activeCount");
		this.pendingLabel = (String) map.get("pendingLabel");
		this.pendingCount = (String) map.get("pendingCount");
		this.rejectCode = (String) map.get("rejectCode");

	}

	public String getCreditorEntity() {
		return creditorEntity;
	}

	public String getddFileFormat() {
		return fileFormat;
	}

	public String getddSelectUploadFile() {
		return selectUploadFile;
	}

	/**
	 * @return the activeLabel
	 */
	public String getActiveLabel() {
		return activeLabel;
	}

	/**
	 * @return the activeCount
	 */
	public String getActiveCount() {
		return activeCount;
	}

	/**
	 * @return the pendingLabel
	 */
	public String getPendingLabel() {
		return pendingLabel;
	}

	/**
	 * @return the pendingCount
	 */
	public String getPendingCount() {
		return pendingCount;
	}

	/**
	 * @return the rejectCode
	 */
	public String getRejectCode() {
		return rejectCode;
	}

}
