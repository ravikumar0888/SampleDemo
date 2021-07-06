package testdataobjects.initsession;

public class SessionDocumentSignature {

	private String cardAmount;
	private String cardMerchantId;
	private String cardTransactionId;
	private String documentFile;
	private String documentId;
	private String documentName;
	private String documentPaymentType;
	private String uir;
	private String umr;


	public String getUir() {
		return uir;
	}

	public void setUir(String uir) {
		this.uir = uir;
	}

	public String getUmr() {
		return umr;
	}

	public void setUmr(String umr) {
		this.umr = umr;
	}





	public String getCardAmount() {
		return cardAmount;
	}

	public String getCardMerchantId() {
		return cardMerchantId;
	}

	public String getCardTransactionId() {
		return cardTransactionId;
	}

	public String getDocumentFile() {
		return documentFile;
	}

	public String getDocumentId() {
		return documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public String getDocumentPaymentType() {
		return documentPaymentType;
	}

	public void setCardAmount(String cardAmount) {
		this.cardAmount = cardAmount;
	}

	public void setCardMerchantId(String cardMerchantId) {
		this.cardMerchantId = cardMerchantId;
	}

	public void setCardTransactionId(String cardTransactionId) {
		this.cardTransactionId = cardTransactionId;
	}

	public void setDocumentFile(String documentFile) {
		this.documentFile = documentFile;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public void setDocumentPaymentType(String documentPaymentType) {
		this.documentPaymentType = documentPaymentType;
	}

}
