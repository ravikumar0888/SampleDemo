package pagesSpsSign;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;
import testbase.BasePage;
import utilies.UserActions;

/**
 * Page class for parameters screen under support page
 */
public class ParametersSupportPage extends BasePage {
	static Logger logger = LogManager.getLogger(ParametersSupportPage.class.getName());

	WebDriver driver = getDriver();
	@FindBy(id = "creditorInstanceAlias")
	WebElement drdSPSInstance;
	By btnTestConnection = By.id("confirm");
	By lblResultMessage = By.xpath("//div[@id='confirmButtonScsConnection']/preceding-sibling::span");
	By lblErrorMessage = By.xpath("//div[@class='k-error-messages']/ul/li");

	@Step("For each SPS instance, click Test SPS Connection")
	public void checkConnectionsWithSPS() {
		SoftAssert softAssert = new SoftAssert();
		SignHomePage shp = new SignHomePage();
		UserActions action = new UserActions();
		PageFactory.initElements(driver, this);
		Select spsInstance = new Select(drdSPSInstance);
		List<WebElement> options = spsInstance.getOptions();
		List<String> optionsText = new ArrayList<String>();

		for (WebElement webElement : options) {
			optionsText.add(webElement.getText());
		}

		for (String instanceName : optionsText) {
			shp.navigateToMenu("Support page", "Parameters");
			//since page reload each time here we are making new instance each time.
			Select spsInstance1 = new Select(driver.findElement(By.id("creditorInstanceAlias")));
			spsInstance1.selectByValue(instanceName);
			logger.info("{} SPS Instance selected", instanceName);
			action.waitForPageToLoad();
			driver.findElement(btnTestConnection).click();
			if (!driver.findElements(lblErrorMessage).isEmpty()) {

				softAssert.assertFalse(driver.findElement(lblErrorMessage).isDisplayed(), driver.findElement(lblErrorMessage).getText());
			}
			if (!driver.findElements(lblResultMessage).isEmpty()) {
				softAssert.assertEquals(driver.findElement(lblResultMessage).getText(), "Conection was established", "Failed to connect with instance " + instanceName);

			}
		}
		softAssert.assertAll();
	}

}