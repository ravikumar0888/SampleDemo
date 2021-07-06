package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import config.ConfigreaderUtils;
import io.qameta.allure.Step;
import testbase.BasePage;
import utilies.UserActions;

public class LoginPage extends BasePage {
	static Logger logger = LogManager.getLogger(LoginPage.class.getName());
	WebDriver driver = getDriver();
	UserActions action = new UserActions();

	/*
	 * public LoginPage() { this.driver = driver; }
	 */

	By txtUSERNAME = By.xpath(".//*[@id='wrapper']/fieldset/form/p[1]/input");

	// login

	By txtPASSWORD = By.xpath(".//*[@id='wrapper']/fieldset/form/p[2]/input");
	By btnLOGIN = By.id("bthp");

	public String getPageTitle() {
		return driver.getTitle();

	}

	@Step("User Logs in the application with user {user}.")
	public void loginIntoSPS(String user) {
		String url = ConfigreaderUtils.getConfig("environment." + BasePage.environment + ".URL");

		action.navigateToUrl(url);
		pageIsLoading();
		String username = ConfigreaderUtils.getConfig("environment." + BasePage.environment + "." + user + "_username");
		String password = ConfigreaderUtils.getConfig("environment." + BasePage.environment + "." + user + "_password");
		action.setValueInTextField(txtUSERNAME, username);
		action.setValueInTextField(txtPASSWORD, password);
		action.saveScreenshot();
		logger.info("User {} logged sucessfully....", user);
		action.clickLink(btnLOGIN);
	}

	public void pageIsLoading() {
		while (driver.getTitle().contains("État HTTP 404 – Not Found")) {
			driver.navigate().refresh();
		}

		
	}
	By btnlogout = By.xpath(".//*[@id='tlogout']");
	public void logout()
	{
		
		action.clickLink(btnlogout);
	}
}
