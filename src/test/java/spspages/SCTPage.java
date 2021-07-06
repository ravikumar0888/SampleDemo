package spspages;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import testbase.BasePage;
import testdataobjects.SCT;
import utilies.GenericFunctions;
import utilies.UserActions;

/**
 * Page class for Creation of SDD screen
 */
public class SCTPage extends BasePage {
	static Logger logger = LogManager.getLogger(SCTPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();
	/**
	 * constructor with Webdriver as parameter
	 * 
	 * @param driver
	 *            instance of webdriver
	 */

	By txtAMT = By.xpath(".//*[@id='amount']");
	By ddSCI = By.xpath(".//*[@id='creditorSelect']");
	By ddbusinesscode = By.xpath(".//*[@id='busiCodeSelectBlank']");
	
	By udpateEndtoendid = By.xpath(".//*[@id='endToEndId']");
	
	By txtEndtoEndId = By.xpath(".//*[@id='endToEndIdSct']");
	By txtDueDate = By.xpath(".//*[@id='dueDateField']");
	By txtIban = By.xpath(".//*[@id='beneficiaryIbanField']");
	By txtBic = By.xpath(".//*[@id='beneficiaryBicField']");
	
	By btncreate = By.xpath(".//*[@id='sctCreateForm']/div[4]/input[1]");
	By txtinitRef = By.id("initiatorTxRef");   
	
	By updateInitRef = By.xpath(".//*[@id='remittanceInfo']");
	By lblCreate = By.id("btbartop");
	
	@Step("User create the SDD.")
	public SCT createSCT(SCT sct) throws InterruptedException {
		
		driver.findElement(txtAMT).click();
		driver.findElement(txtAMT).clear();
		action.setValueInTextField(txtAMT,sct.getAmount());
		
		//	action.selectvaluesdropdown(ddbusinesscode, sct.getBusinessCode());
		
	
		
		String endId = gf.randamGenerator();
		action.setValueInTextField(txtEndtoEndId, endId);
		
		sct.setEndToEndId(endId);
		
		action.setValueInTextField(txtIban, sct.getIban());
	//	action.setValueInTextField(txtBic, sct.getBic());
		
		

		String date = gf.currentDate();
		action.setValueInTextField(txtDueDate, date);
		driver.findElement(txtDueDate).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDueDate).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDueDate).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDueDate).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDueDate).sendKeys(Keys.TAB);

		
		String initiatorTxRef = gf.randamGenerator();
		action.setValueInTextField(txtinitRef, initiatorTxRef);

		sct.setInitiatorTransactionReference(initiatorTxRef);
		
		action.waitForPageToLoad();
		Thread.sleep(3000);
		action.selectvaluesdropdown(ddSCI, sct.getSci());
		Thread.sleep(3000);
		Select select = new Select(driver.findElement(ddbusinesscode));
		select.selectByVisibleText(sct.getBusinessCode());
		
		
		action.waitForElementClickable(driver, btncreate);
		action.saveScreenshot();
		action.clickLink(btncreate);
		String expectedValue = "SCT(s) creation successful";
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(lblCreate,"SCT(s) creation successful"));

		
		String actualMessage = driver.findElement(lblCreate).getText();
		
		assertEquals(actualMessage, expectedValue);

		return sct;

	}
	
	By editIcon = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[9]/a[1]/img");
	By updateSCT =By.xpath(".//*[@id='sctEditForm']/div[3]/input[1]");
	By btnedit = By.xpath("html/body/div[7]/div[2]/div/div[2]/div[1]/a");
	public SCT updateSCT(SCT sct)
	{
		action.clickLink(editIcon);
		action.clickLink(btnedit);
		action.waitForElementPrescence(txtAMT);

		driver.findElement(txtAMT).clear();
		action.setValueInTextField(txtAMT, sct.getUpdateAmount());
		sct.setEndToEndId(sct.getUpdateAmount());
		
		action.clearTextField(udpateEndtoendid);
		String endid = gf.randamGenerator();
		action.setValueInTextField(udpateEndtoendid, endid);
		
		action.clearTextField(updateInitRef);
		String initiatorTxRef = gf.randamGenerator();
		action.setValueInTextField(updateInitRef, initiatorTxRef);

		sct.setInitiatorTransactionReference(initiatorTxRef);
		
		action.waitForElementClickable(driver, updateSCT);
		action.saveScreenshot();
		action.clickLink(updateSCT);
		
		return sct;
	}
	
	By btncancelSCT = By.xpath(".//*[@id='btbartop']/div[3]/a");
	By yesButtonRD = By.xpath(".//*[@id='cancelYes']");
	By txtComment = By.xpath(".//*[@id='cancelComment']");
	By lifecycleStatus = By.xpath(".//*[@id='divToHideContainer']/div/table/tbody/tr[1]/td[contains(text(),'Cancelled')]");
	By imgdetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[9]/a/img");
	By btnSubmit = By.name("submit");
	public void cancelSCTwithComments() {
		action.clickLink(imgdetails);
		action.clickLink(btncancelSCT);

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(yesButtonRD));
		action.clickLink(yesButtonRD);
		action.setValueInTextField(txtComment, "Please cancel");
		action.clickLink(btnSubmit);
		String actualMessage = driver.findElement(lifecycleStatus).getText();
		String expectedValue = "Cancelled";
		assertEquals(actualMessage, expectedValue);
	}

}