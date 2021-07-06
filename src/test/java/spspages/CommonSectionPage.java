package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.BasePage;
import utilies.GenericFunctions;
import utilies.UserActions;

public class CommonSectionPage extends BasePage {
	static Logger logger = LogManager.getLogger(CommonSectionPage.class.getName());

	UserActions action = new UserActions();

	protected WebDriver driver = getDriver();
	GenericFunctions gf = new GenericFunctions();
	private By menuMandateManagement = By.xpath(".//*[@id='pageMenu']/ul/li/a[contains(text(),'Mandates Management')]");

	By selectLanguage = By.xpath("//select[@id='countrySwitcher']");
	By btnLogout = By.id("tlogout");

	public boolean isSPSButtonDisabled(By Button) {
		if (driver.findElement(Button).getAttribute("class").equals("disabledAction"))
			return true;
		else
			return false;
	}
	/**
	 * Selects mandate management in SPS app
	 * 
	 * @throws InterruptedException
	 */

	public void selectMandateMangement() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(menuMandateManagement));
		action.clickLinkandWaitForPageToLoad(menuMandateManagement);
	}
	public void logout() {
		driver.findElement(btnLogout).click();
		logger.info("Clicked on logout button");
	}

	public void selectLanguage(String language) {
		Select selectLang = new Select(driver.findElement(selectLanguage));
		selectLang.selectByVisibleText(language);
		logger.info("Selected {} language from dropdown", language);
	}
}
