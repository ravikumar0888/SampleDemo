package pagesSpsSign;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import config.ConfigreaderUtils;
import io.qameta.allure.Step;
import testbase.BasePage;
import utilies.UserActions;

/**
 * Page class for parameters screen under support page
 */
public class SignHomePage extends BasePage {
	static Logger logger = LogManager.getLogger(SignHomePage.class.getName());

	WebDriver driver = getDriver();
	By txtSSOUserId = By.id("username");
	By txtSSOPassword = By.id("password");
	By btnSSOLogin = By.name("login");

	By txtUserId = By.id("userID");
	By txtPassword = By.id("password");
	UserActions action = new UserActions();

	@Step("For each SPS instance, click Test SPS Connection")
	public void navigateToMenu(String MenuHeading, String SubMenu) {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.partialLinkText(MenuHeading))).click().build().perform();
		action.moveToElement(driver.findElement(By.partialLinkText(SubMenu))).click().build().perform();
		this.action.waitForPageToLoad();
	}

	@Step("Launch SPSSign")
	public void signHomepageLaunch() {
		String environment = BasePage.getEnvironment();
		logger.info("SPS SIGN Execution environment - " + environment);
		String url = ConfigreaderUtils.getConfig("environment." + environment + ".signURL");
		driver.get(url);

	}
	@Step("Launch SPSSignbackOffice URL ")
	public void signOldbackOfficeURLLaunch() {
		String environment = BasePage.getEnvironment();
		logger.info("SPS SIGN Old back office environment - " + environment);
		String url = ConfigreaderUtils.getConfig("environment." + environment + ".signBackOfficeURL");
		driver.get(url);

	}
	
	@Step("Launch SPSSignbackOffice SG URL ")
	public void signOldbackOfficeURLLaunchSG() {
		String environment = BasePage.getEnvironment();
		logger.info("SPS SIGN Old back office environment - " + environment);
		String url = ConfigreaderUtils.getConfig("environment." + environment + ".signBackOfficeURLSG");
		driver.get(url);

	}
	@Step("OK URL")
	public String  OKURL() {
		String environment = BasePage.getEnvironment();
		logger.info("environment - " + environment);
		String url = ConfigreaderUtils.getConfig("environment." + environment + ".OKURL");
		
		return url;
	}

	@Step("Login to SPS SIGN angular page")
	public void loginSpsSignAngular() {

		String url = ConfigreaderUtils.getConfig("environment." + environment + ".angularbackoffice");
		String username = ConfigreaderUtils.getConfig("environment." + environment + ".SSOUser");
		String password = ConfigreaderUtils.getConfig("environment." + environment + ".SSOPassword");

		logger.info("SPS SIGN Execution environment - " + environment);
		action.navigateToUrl(url);
		driver.findElement(txtSSOUserId).sendKeys(username);
		driver.findElement(txtSSOPassword).sendKeys(password);
		action.clickButton(btnSSOLogin);
		logger.info("Logged in to angular SPS Sign page with user {}", username);

	}

}