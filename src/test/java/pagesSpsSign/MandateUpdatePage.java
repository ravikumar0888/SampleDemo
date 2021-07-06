package pagesSpsSign;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.google.gson.internal.LinkedTreeMap;

import testbase.BasePage;
import utilies.UserActions;

public class MandateUpdatePage extends BasePage {
	static Logger logger = LogManager.getLogger(MandateUpdatePage.class.getName());
	WebDriver driver = getDriver();

	By lblumr = By.id("umr");
	By lblIBAN = By.id("iban");
	By btnConfirm = By.id("confirm");
	UserActions action = new UserActions();

	public void verifyMandateDetailAndUpdateUMR(LinkedTreeMap<String, String> formData) {

		assertEquals(action.getText(lblumr), formData.get("UMR"));
		action.setValueInTextField(lblIBAN, formData.get("IBAN"));
		action.clickButton(btnConfirm);
	}

}
