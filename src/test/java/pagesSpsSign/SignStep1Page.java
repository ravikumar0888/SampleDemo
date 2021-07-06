package pagesSpsSign;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import testbase.BasePage;
import testdataobjects.BackOfficeInitSession;
import utilies.UserActions;

public class SignStep1Page extends BasePage {
	static Logger logger = LogManager.getLogger(SignStep1Page.class.getName());
	WebDriver driver = getDriver();

	By txtName = By.name("userName");
	By txtStNum = By.name("userAddressStNum");
	By txtPhone = By.xpath("(//input[@id='userPhoneNumberEditableField'])|(//input[@id='userPhoneNumberField'])");
	By txtStName = By.name("userAddressStName");
	By txtPostCode = By.name("userAddressPostCode");
	By txtCity = By.name("userAddressCity");
	
	
	By txtIBAN = By.xpath("//*[@name='Iban' or @name='bankIban' or @name='iban'] ");				//By.name("iban");     //('bankIban')|
	By txtIBANBANK = By.name("bankIban");
	By selPhoneCountry = By
			.xpath("(//select[@id='userPhoneCountryCodeEditableField'])|(//select[@id='userPhoneCountryCodeField'])");
	By selAddrCountry = By.name("userAddressCountry");
	By btnConfirm = By.name("confirm");
	By chkPartnerConsent = By.name("partnersConsentChecked");
	By chkCreditorConsent = By.name("creditorConsentChecked");
	By txtemailid = By.id("userMailRequired");
	By ddCountryCode = By.name("userPhoneCountryCodeField");

	By labelName = By.xpath(
			"//label[contains(text(),'Name')]/ancestor::div[@class = 'one-third column']/following-sibling::div[@class = 'two-thirds column']/label");
	By labelEmail = By.xpath(
			"//label[contains(text(),'Email')]/ancestor::div[@class = 'one-third column']/following-sibling::div[@class = 'two-thirds column']/label");
	By labelPhone = By.xpath(
			"//label[contains(text(),'Phone')]/ancestor::div[@class = 'one-third column']/following-sibling::div[@class = 'two-thirds column']/label");
	By labelAdressLine1 = By.xpath(
			"//label[contains(text(),'Address')]/ancestor::div[@class = 'one-third column']/following-sibling::div[@class = 'two-thirds column']/label");
	By labelAddressLine2 = By.xpath(
			"(//label[@for = 'userAddress'])[2]/ancestor::div[@class = 'one-third column']/following-sibling::div[@class = 'two-thirds column']/label");

	By labelCollectionOFConsent = By.xpath("//*[@id=\"collectionOfConsent\"]/fieldset[1]/div/label[1]");
	By labelCollectionOFPartner = By.xpath("//*[@id=\"collectionOfConsent\"]/fieldset[1]/div/label[2]");

	By rdbtnSepa = By.id("paymentTypeSEPA");

	// WebDriver driver = getDriver();
	UserActions action = new UserActions();

	public void confirmStep1(boolean signdocument, boolean mandate) {
		action.clickLink(btnConfirm);

	}

	public void documentSignatureStep1(boolean signdocument, boolean mandate) {

		action.setValueInTextField(txtName, "Prakhar");
		action.setValueInTextField(txtStNum, "123");
		action.setValueInTextField(txtPhone, "+33612345678");
		action.setValueInTextField(txtStName, "Strasse1");
		action.setValueInTextField(txtPostCode, "456789");
		action.setValueInTextField(txtCity, "Lyon");
		action.selectvaluesdropdown(selPhoneCountry, "France : (+33)"); // editable
		action.selectvaluesdropdown(selAddrCountry, "France");
		// action.clickButton(chkCreditorConsent);
		action.clickButton(chkPartnerConsent);

		if (mandate) {
			action.setValueInTextField(txtIBAN, "FR7630003000330002005741495");
		}

		action.clickButton(btnConfirm);

	}

	By cardPayment = By.xpath("//*[@id=\'bicIbanForm\']/div[6]/div[2]/label");

	public void documentSignatureStep1Undefined() {

		action.setValueInTextField(txtName, "Prakhar");
		action.setValueInTextField(txtStNum, "123");
		action.setValueInTextField(txtPhone, "+33612345678");
		action.setValueInTextField(txtStName, "Strasse1");
		action.setValueInTextField(txtPostCode, "456789");
		action.setValueInTextField(txtCity, "Lyon");
		action.selectvaluesdropdown(selPhoneCountry, "France : (+33)"); // editable
		action.selectvaluesdropdown(selAddrCountry, "France");
		// action.clickButton(chkCreditorConsent);
		action.clickButton(chkPartnerConsent);

		action.clickLink(cardPayment);

		action.clickButton(btnConfirm);

	}

	public void resumeMandateStep1(boolean signdocument, boolean mandate) {

		action.setValueInTextField(txtName, "Prakhar");
		action.setValueInTextField(txtStNum, "123");
		action.setValueInTextField(txtPhone, "+33612345678");
		action.setValueInTextField(txtStName, "Strasse1");
		action.setValueInTextField(txtPostCode, "456789");
		action.setValueInTextField(txtCity, "Lyon");
		action.selectvaluesdropdown(selPhoneCountry, "France : (+33)"); // editable
		action.selectvaluesdropdown(selAddrCountry, "France");
		action.clickButton(chkCreditorConsent);
		action.clickButton(chkPartnerConsent);

		action.clickButton(btnConfirm);

	}

	public void resumeMandateStep1Undefined(boolean signdocument, boolean mandate) {

		action.setValueInTextField(txtName, "Prakhar");
		action.setValueInTextField(txtStNum, "123");
		action.setValueInTextField(txtPhone, "+33612345678");
		action.setValueInTextField(txtStName, "Strasse1");
		action.setValueInTextField(txtPostCode, "456789");
		action.setValueInTextField(txtCity, "Lyon");
		action.selectvaluesdropdown(selPhoneCountry, "France : (+33)"); // editable
		action.selectvaluesdropdown(selAddrCountry, "France");
		action.clickButton(chkCreditorConsent);
		action.clickButton(chkPartnerConsent);
		// action.clickLink(rdbtnSepa);

		action.clickButton(btnConfirm);

	}

	public void fillingPostalAddress() {
		action.setValueInTextField(txtStNum, "123");
		action.setValueInTextField(txtStName, "Strasse1");
		action.setValueInTextField(txtPostCode, "456789");
		action.setValueInTextField(txtCity, "Lyon");
		action.selectvaluesdropdown(selAddrCountry, "France");

		// action.setValueInTextField(txtIBAN, backofficeSession.getIBAN());
		action.clickLink(btnConfirm);
	}

	public void fillingIbanEmailAndPhoneOnlyForActive(BackOfficeInitSession backofficeSession) {
		
	//	action.waitForElementClickable(driver, txtIBAN);
	//	action.setValueInTextField(txtIBAN, backofficeSession.getIBAN());
		
		action.selectvaluesdropdown(selPhoneCountry, "France : (+33)"); // not editable
		action.setValueInTextField(txtPhone, backofficeSession.getPhoneNumber());
	//	action.setValueInTextField(txtemailid, backofficeSession.getSingerInfoEmail());

		
		action.clickLink(btnConfirm);
	}
	public void fillingIbanEmailAndPhone(BackOfficeInitSession backofficeSession) {
		
		//	action.waitForElementClickable(driver, txtIBAN);
			action.setValueInTextField(txtIBAN, backofficeSession.getIBAN());
			
			action.selectvaluesdropdown(selPhoneCountry, "France : (+33)"); // not editable
			action.setValueInTextField(txtPhone, backofficeSession.getPhoneNumber());
			action.setValueInTextField(txtemailid, backofficeSession.getSingerInfoEmail());

			
			action.clickLink(btnConfirm);
		}

	public void verifyCollectionOfConsent(boolean signdocument, boolean mandate) {
		action.setValueInTextField(txtName, "Prakhar");
		action.setValueInTextField(txtStNum, "123");
		action.setValueInTextField(txtPhone, "+33612345678");
		action.setValueInTextField(txtStName, "Strasse1");
		action.setValueInTextField(txtPostCode, "456789");
		action.setValueInTextField(txtCity, "Lyon");
		action.selectvaluesdropdown(selPhoneCountry, "France : (+33)");
		action.selectvaluesdropdown(selAddrCountry, "France");
		action.clickButton(chkCreditorConsent);
		action.clickButton(chkPartnerConsent);

		String collectionOfConsent = driver.findElement(labelCollectionOFConsent).getText();
		assertEquals(collectionOfConsent,
				"I agree to receive commercial information by e-mail and SMS on product and service offers from ENGIE Home Services");

		String collectionOfPartner = driver.findElement(labelCollectionOFPartner).getText();
		assertEquals(collectionOfPartner,
				"I agree to receive commercial information by e-mail and SMS on the offers of products and services of the companies of the ENGIE Group and its partners.");
		action.clickButton(btnConfirm);

	}
}
