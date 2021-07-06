package pagesSpsSign;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import spspages.RootPage;
import testbase.BasePage;
import testdataobjects.CardPayment;
import utilies.GenericFunctions;
import utilies.UserActions;

public class CardPaymentPage extends BasePage {
	static Logger logger = LogManager.getLogger(RootPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();

	By iconVISA = By.xpath("//*[@id=\'homeContent\']/div[2]/fieldset/form/div[2]/input[2]");
	By cardNumber = By.id("CARD_NUMBER");
	By cardMonth = By.name("CARD_VAL_MONTH");
	By cardYear = By.name("CARD_VAL_YEAR");
	By cardCVV =By.id("CVV_KEY");
	By submitCardDetails =By.name("PAIEMENT");

	By cancelPayment = By.xpath("/html/body/center[1]/form/input[11]");


	By cardPassword =By.name("password");
	By cardPasswordSubmit =By.name("VALID");

	By cardPaymentMessage = By.id("span_sips_payment_accepted_message");



	public CardPayment cardPaymentDataFilling(CardPayment cardpayment) 
	{action.waitForPageToLoad();
		action.clickLink(iconVISA);
		action.waitForPageToLoad();
		action.setValueInTextField(cardNumber,cardpayment.getCardNumber()); 
		action.selectvaluesdropdown(cardMonth,cardpayment.getCardMonth());

		action.selectvaluesdropdown(cardYear, cardpayment.getCardYear());

		action.setValueInTextField(cardCVV,cardpayment.getCardCVV());

		action.clickLink(submitCardDetails);

		// here we add cancel payment for cancel flow

		action.waitForPageToLoad();
		action.setValueInTextField(cardPassword,cardpayment.getCardPassword()); 

		action.clickLink(cardPasswordSubmit);


		//Your transaction has been recorded
		action.clickLink(submitCardDetails);

		return cardpayment;
	}
	By refuseMessage = By.id("span_sips_refusal_message_refuse");
	public void validateRefuse(String expectedMessage)
	{
		String actualMessage = driver.findElement(refuseMessage).getText();
		logger.info("Messages got "+ actualMessage);
		assertEquals(actualMessage, expectedMessage);
		
		
	}

}
