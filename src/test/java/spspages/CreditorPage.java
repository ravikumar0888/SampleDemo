package spspages;

import static org.testng.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.BasePage;
import testdataobjects.Creditor;
import testdataobjects.Mandates;
import utilies.GenericFunctions;
import utilies.UserActions;

public class CreditorPage extends BasePage {

	static Logger logger = LogManager.getLogger(CreditorPage.class.getName());

	UserActions action = new UserActions();
	protected WebDriver driver = getDriver();
	GenericFunctions gf = new GenericFunctions();

	By txtCREDITORNAME = By.xpath("//*[@id='creditorNameField']");
	By txtCREDITORCOUNTRYISO = By.xpath("//*[@id='creditorSciField-countryIso']");
	By txtCREDITORDIGITAL = By.xpath(".//*[@id='creditorSciField-checkDigit']");
	By txtCREDITORNATIONALID = By.xpath(".//*[@id='creditorSciField-nationalIdentifier']");
	By btnCREATE = By.name("search");
	By msgSUCESSMSG = By.xpath(".//div[@id='btbartop' and @class = 'info']/h3");

	// Display Page
	By noOFMANDATE = By.xpath(".//*[@id='divToHideContainer']/div/div/div[1]/table/tbody/tr[2]/td");
	By noOFSDD = By.xpath(".//*[@id='divToHideContainer']/div/div/div[2]/table/tbody/tr[2]/td");

	By eRROR = By.xpath("//*[@id='ferror']/ul/li");

	By code = By.xpath("//*[@id='creditorSciField-businessCode']");

	public String createCreditor(Creditor creditor) {

		action.setValueInTextField(txtCREDITORNAME, creditor.getCreditorName());
		action.setValueInTextField(txtCREDITORCOUNTRYISO, creditor.getSciIso());
		action.setValueInTextField(txtCREDITORDIGITAL, creditor.getSciCountrycode());
		String sciNumber = gf.randamGenerator();
		action.setValueInTextField(txtCREDITORNATIONALID, sciNumber);
		String countrycode = driver.findElement(code).getAttribute("value");
		action.clickLink(btnCREATE);

		// get correctect Countrycode
		String sciCode = getCorrectCreditorID();
		action.waitForElementVisible(driver, txtCREDITORDIGITAL);
		action.setValueInTextField(txtCREDITORDIGITAL, sciCode);
		action.clickLink(btnCREATE);

		String crNAME = creditor.getCreditorName() + " - " + creditor.getSciIso() + sciCode + countrycode + sciNumber;

		logger.info("Generate CR NAME is  :- " + crNAME);

		String name = creditor.getCreditorName();

		WebDriverWait wait = new WebDriverWait(driver, 60);

		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(msgSUCESSMSG));

		String actualMessage = driver.findElement(msgSUCESSMSG).getText();
		String expectedValue = "Creditor " + name + " successfully created";
		assertEquals(expectedValue, actualMessage);

		String noOFMandate = driver.findElement(noOFMANDATE).getText();
		String noSDD = driver.findElement(noOFSDD).getText();

		assertEquals("0", noOFMandate);
		assertEquals("0", noSDD);

		return crNAME;
	}

	public String getCorrectCreditorID() {
		action.waitForElementVisible(driver, eRROR);
		String error = driver.findElement(eRROR).getText();
		logger.info("The correct Creditor ID is " + error);

		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(error);
		String codeCurrent = null;
		while (m.find()) {
			logger.info(m.group(1));

			codeCurrent = m.group(1);
		}

		logger.info("The correct Value is " + codeCurrent);

		driver.findElement(txtCREDITORDIGITAL).clear();
		return codeCurrent;
	}
	By creditorName = By.id("name");
	By btnSearch = By.name("search");
	By creditorNameLabel= By.xpath("//*[@id=\'resultList\']/div[2]/table/tbody/tr/td[1]");
	By ddSCI =By.id("creditorSelect");	
	public void searchCreditor(String cr)
	{
		action.selectSearchCreditor();
		action.selectvaluesdropdown(ddSCI, cr);
		
		action.clickButton(btnSearch);

		String sciName = driver.findElement(creditorNameLabel).getText();

		boolean sciname = cr.contains(sciName);
		logger.info("SCI value is"+ sciname);

	}
	
	By btnDelete = By.xpath("//*[@id=\"resultList\"]/div[2]/table/tbody/tr/td[5]/a[3]/img");
	public void deleteCreditor(String crName)
	{
		action.selectSearchCreditor();
		action.selectvaluesdropdown(ddSCI, crName);
		
		action.clickButton(btnSearch);
		action.waitForElementClickable(driver, btnDelete);
		
		action.clickButton(btnDelete);
		logger.info("Creditor is permantly deleted" + crName);
		
	}
	
	By btnEditCreditor = By.xpath("//*[@id=\'resultList\']/div[2]/table/tbody/tr/td[5]/a[2]/img");
	By ddAddressType = By.id("creditorAdress-addressType");
	By txtSteetNumber = By.id("creditorAdress-addressNumber");
	By txtPostalCode = By.id("creditorAdress-addressPostCode");
	By txtCity = By.id("creditorAdress-addressCity");
	By txtSteetName = By.id("creditorAdress-addressStreet");
	By ddCountry = By.id("creditorAdress-addressCountry");
	By btnUpdate = By.name("search2");

	public void addingAddressOfCreditor(Creditor creditor)
	{
		action.clickButton(btnEditCreditor);
		action.selectvaluesdropdown(ddAddressType,creditor.getAddressTypeModified());
		action.setValueInTextField(txtSteetNumber, creditor.getStreetNumber());
		action.setValueInTextField(txtPostalCode, creditor.getPostalCode());
		action.setValueInTextField(txtSteetName, creditor.getStreetName());
		action.setValueInTextField(txtCity, creditor.getCity());

		action.clickButton(btnUpdate);

	}
	
	
	private By menuNewCreditor = By.xpath("//*[@id='pageMenu']/ul/ul/li[2]/a");
	public void selectNewCreditor() {

		action.clickLink(menuNewCreditor);

	}
	public void selectMenu(String menu, String subMenu) {
		By lblMenu = By.xpath(".//*[@id='pageMenu']/ul/li/a[contains(text(),'" + menu + "')]");
		By lblsubMenu = By.xpath(".//*[@id='pageMenu']/ul/ul/li/a[contains(text(),'" + subMenu + "')]");

		if (!driver.findElements(lblMenu).isEmpty()) {
			driver.findElement(lblMenu).click();
		}

		if (!driver.findElements(lblsubMenu).isEmpty()) {
			if (driver.findElement(lblsubMenu).isEnabled()) {
			
				driver.findElement(lblsubMenu).click();

				logger.info("SubMenu is Present {}", lblsubMenu);

			} else {

				logger.info("SubMenu is not found on the webpage");

			}
		} else {

			logger.info("No need to select sub menu option");
		}
	}
	
	By ddCreaditorname = By.xpath(".//*[@id='creditorSelect']");
	By imgEditCreditor = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[5]/a[2]/img");
	
	public Mandates searchCreditorAndUpdateName(Mandates mandate,String credName)
	{
		action.waitForPageToLoad();
		Select select = new Select(driver.findElement(By.xpath(".//*[@id='creditorSelect']")));
		select.selectByVisibleText(credName);
				
		action.clickLink(btnSearch);
		
		action.clickLink(imgEditCreditor);
		
		action.clearTextField(creditorName);
		action.setValueInTextField(txtCREDITORNAME,mandate.getUpdateCreditorName());
		
		action.clickLink(btnUpdate);
		
		
		return mandate;
	}
	
}