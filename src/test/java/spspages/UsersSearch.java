package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testbase.BasePage;
import utilies.GenericFunctions;
import utilies.UserActions;

public class UsersSearch extends BasePage {
	static Logger logger = LogManager.getLogger(UsersSearch.class.getName());
	protected WebDriver driver = getDriver();

	UserActions action = new UserActions();
	CommonSectionPage CommonPage = new CommonSectionPage();
	LoginPage login = new LoginPage();
	GenericFunctions gf = new GenericFunctions();

	By txtLogin = By.id("login");
	By txtLastname = By.id("lastname");
	By selectStatus = By.id("state");
	By selectGroup = By.id("group");
	By selectMandator = By.id("mandator");
	By btnSearch = By.name("search");
	By btnSubmit = By.name("submit");
	By btnEditGroupList = By.xpath("//a[.=\"Edit the groups list\"]");

	public void addGroupListToProfile(String CheckboxLabel) {
		WebElement theCheckBox = driver.findElement(By.xpath("//p[contains(text(),\"" + CheckboxLabel + "\")]/input"));
		if (!theCheckBox.isSelected()) {
			theCheckBox.click();
			logger.info("checked the checkbox with label {}", CheckboxLabel);
		}
		driver.findElement(btnSubmit);
	}

	public void clickEditForUser(String login) {
		driver.findElement(By.xpath("//td[./span[.=\"" + login + "\"] and @class='login']/following-sibling::td[@class='action']/a[2]")).click();
		logger.info("clicked on edit user button for userid {}", login);
	}

	public void clickEditGroupList() {
		driver.findElement(btnEditGroupList).click();
		logger.info("Clicked on edit group list button");

	}

	public void editGrouplistFromUser(String addORremove, String user, String grouplistName) {
		login.loginIntoSPS("admin");
		CommonPage.selectLanguage("English");

		action.selectMenu("User management", "User Search");

		userSearch(user);
		clickEditForUser(user);
		clickEditGroupList();
		if (addORremove.equalsIgnoreCase("add")) {
			addGroupListToProfile(grouplistName);

		} else if (addORremove.equalsIgnoreCase("remove")) {
			removeGroupListFromProfile(grouplistName);

		}
		CommonPage.logout();
	}

	public void removeGroupListFromProfile(String CheckboxLabel) {
		WebElement theCheckBox = driver.findElement(By.xpath("//p[contains(text(),\"" + CheckboxLabel + "\")]/input"));
		if (theCheckBox.isSelected()) {
			theCheckBox.click();
			logger.info("checked the checkbox with label {}", CheckboxLabel);
		}
		driver.findElement(btnSubmit);
	}

	public void userSearch(String Login) {

		driver.findElement(txtLogin).sendKeys(Login);
		logger.info("Entered Login ID  as {}", Login);

		driver.findElement(btnSearch).click();
		logger.info("Clicked on search button");

	}

	public void userSearch(String lastName, String login, String status, String group, String mandator) {
		driver.findElement(txtLastname).sendKeys(lastName);
		logger.info("Entered Last name as {}", lastName);

		driver.findElement(txtLogin).sendKeys(login);
		logger.info("Entered Login ID  as {}", login);

		Select selState = new Select(driver.findElement(selectStatus));
		selState.selectByVisibleText(status);
		logger.info("Selected status  {}", status);

		Select selGroup = new Select(driver.findElement(selectGroup));
		selGroup.selectByVisibleText(group);
		logger.info("Selected status  {}", group);

		Select selMandator = new Select(driver.findElement(selectMandator));
		selMandator.selectByVisibleText(mandator);
		logger.info("Selected status  {}", mandator);

		driver.findElement(btnSearch).click();
		logger.info("Clicked on search button");

	}
	
	By chkboxElectronicMandate = By.xpath("/html/body/div[10]/div[2]/div/div[4]/div/div[1]/form/p[8]");
			//("//*[@id=\"maincol\"][contains(text(),'Electronic validation of mandates: Access to the mandates creation by the signature module')] ");

public void validateChkBoxElectronicMandate()
{
	WebElement theCheckBox  = driver.findElement(chkboxElectronicMandate);
	
	if (theCheckBox.isSelected()) {
		logger.info("checkbox value is "+ theCheckBox.isSelected());
	}else
	{
		action.clickLink(chkboxElectronicMandate);
		action.clickLink(btnSubmit);
	}
	//boolean isChecked = driver.findElement(chkboxElectronicMandate).isSelected();
	/*
	logger.info("checkbox value is "+ isChecked);
	
	if(isChecked == false)
	{
		action.clickLink(chkboxElectronicMandate);
		action.clickLink(btnSubmit);
	}*/

} 
By ddlang = By.id("countrySwitcher");
public void selectLanguage()
{
	action.selectvaluesdropdown(ddlang,"English");
}

}
