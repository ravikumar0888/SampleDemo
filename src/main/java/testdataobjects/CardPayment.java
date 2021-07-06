package testdataobjects;

import com.google.gson.internal.LinkedTreeMap;

/**
 * 
 * POJO class for Root
 *
 * 
 */
public class CardPayment {
	// Creditor filter
	private String cardMonth;
	private String cardYear;
	private String cardCVV;
	private String cardPassword;
	
	private String cardNumber;
	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCardMonth() {
		return cardMonth;
	}


	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}


	public String getCardYear() {
		return cardYear;
	}


	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}


	public String getCardCVV() {
		return cardCVV;
	}


	public void setCardCVV(String cardCVV) {
		this.cardCVV = cardCVV;
	}


	public String getCardPassword() {
		return cardPassword;
	}


	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}



	

	public CardPayment(LinkedTreeMap<String, ?> map) {

		this.cardNumber = (String) map.get("cardNumber");
		this.cardMonth = (String) map.get("cardMonth");
		this.cardYear = (String) map.get("cardYear");
		this.cardCVV = (String) map.get("cardCVV");

		this.cardPassword = (String) map.get("cardPassword");
		

	}

	
}
