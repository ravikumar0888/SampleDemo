package spspages;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

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
import testdataobjects.SDD;
import utilies.GenericFunctions;
import utilies.UserActions;

/**
 * Page class for Creation of SDD screen
 */
public class SDDPage extends BasePage {
	static Logger logger = LogManager.getLogger(SDDPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();
	/**
	 * constructor with Webdriver as parameter
	 * 
	 * @param driver
	 *            instance of webdriver
	 */

	By txtAMT = By.xpath("//*[@id='amount']");
	By txtDATE = By.xpath("//*[@id='dueDateField']");
	By txtENDTOEND = By.xpath("//*[@id='endToEndId']");
	By btnCREATESDD = By.xpath(".//*[@id='sddCreateForm']/div[3]/input[1]");
	By lblSTATUSSDD = By.xpath("//*[@id='sddDisplayContainer']/div/div/div[1]/dl[1]/dd[4]");
	By btnCreateSdd = By.xpath(".//*[@id='btbartop']/div[1]/a[contains(text(),'Create SDD')]");
	By initRef = By.xpath(".//*[@id='initiatorTxRef']");
	By sentNotification = By.xpath(".//*[@id='generatePreNotification']");
	By updateSDD = By.xpath(".//*[@id='sddEditForm']/div[3]/input[1]");
	By errorMsgFordate = By.xpath(".//*[@id='ferror']/ul/li");

	By entity = By.xpath("//dt[.=\"Entity :\"]/following-sibling::dd[1]");

	By status = By.xpath("//div[@id='sddDisplayContainer']//dt[.=\"Status :\"]/following-sibling::dd[1]");

	By settlementMode = By.xpath("//dt[.=\"Settlement Mode :\"]/following-sibling::dd[1]");
	By sequenceType = By.xpath("//dt[.=\"Sequence type :\"]/following-sibling::dd[1]");
	By initiatorTransactionReference = By.xpath("//dt[.=\"Initiator Transaction Reference :\"]/following-sibling::dd[1]");
	By initiatorCollectionReference = By.xpath("//dt[.=\"Initiator Collection Reference :\"]/following-sibling::dd[1]");
	By iban = By.xpath("//dt[.=\"Debtor IBAN :\"]/following-sibling::dd[1]");
	By valuedate = By.xpath("//dt[.=\"Value date :\"]/following-sibling::dd[1]");
	By duedate = By.xpath("//dt[.=\"Due date :\"]/following-sibling::dd[1]");
	By Creationtype = By.xpath("//dt[.=\"Creation type :\"]/following-sibling::dd[1]");
	By UMR = By.xpath("//dt[.=\"UMR :\"]/following-sibling::dd[1]");
	By UIR = By.xpath("//dt[.=\"UIR :\"]/following-sibling::dd[1]");
	By editIcon = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[10]/a[2]/img");
	By updateStatus = By.xpath(".//*[@id='btbartop']/h3");
By errorMessage = By.xpath("//div[@id='ferror']/ul/li");
	By sCI = By.xpath(".//*[@id='mandateInfoContainer']/div/div/div[1]/dl/dd[1]");

	By btneditSDD = By.xpath("//*[@id='btbartop']/div/a[contains(text(),'Edit SDD')]");
	By sDDDetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[10]/a[1]/img");
	By sddDetailIcon = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[10]/a[1]/img");
	By updateStatusBtn = By.xpath(".//*[@id='btbartop']/div/a[contains(text(),'Update status')]");

	By btnStatus = By.xpath(".//*[@id='sddFuncStatusSelect']");

	By cancelSDDBtn = By.xpath(".//*[@id='btbartop']/div/a[contains(text(),'Cancel SDD')]");

	By yesButtonRD = By.xpath(".//*[@id='cancelYes']");

	By noButtonRD = By.xpath(".//*[@id='cancelNo']");
	By txtComment = By.xpath(".//*[@id='cancelComment']");
	By btnSubmit = By.name("submit");

	By sddStatus = By.xpath(".//*[@id='sddDisplayContainer']/div/div/div[1]/dl[1]/dd[4]");

	By lifecycleStatus = By.xpath(".//*[@id='divToHideContainer']/div/table/tbody/tr[1]/td[contains(text(),'Cancelled')]");

	public void clickOnSDDDetails()
	{
		action.clickLink(sDDDetails);
	}
	
	public void cancelSDDwithCheckNO() {
		action.clickLink(sDDDetails);
		action.clickLink(cancelSDDBtn);
		action.clickLink(noButtonRD);
		action.setValueInTextField(txtComment, "Select No");
		action.clickLink(btnSubmit);

		String actualMessage = driver.findElement(sddStatus).getText();
		String expectedValue = "Created";
		assertEquals(actualMessage, expectedValue);
	}
	public void cancelSDDwithComments() {
		// action.clickLink(sDDDetails);
		action.clickLink(cancelSDDBtn);

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(yesButtonRD));
		action.clickLink(yesButtonRD);
		action.setValueInTextField(txtComment, "Please cancel");
		action.clickLink(btnSubmit);
		String actualMessage = driver.findElement(lifecycleStatus).getText();
		String expectedValue = "Cancelled";
		assertEquals(actualMessage, expectedValue);
	}
	public void clickOnCreateSDD() {

		action.clickLink(btnCreateSdd);

	}
	By chkLastSDD = By.xpath(".//*[@id='isLastSdd']");
	@Step("User create the SDD.")
	public SDD createSDD(SDD sdd) throws InterruptedException {

		String date = gf.currentDate();
		// String date = gf.currentdateWithAddMoredays(7);
		action.setValueInTextField(txtDATE, date);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
	//	driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		
		driver.findElement(txtDATE).sendKeys(Keys.TAB);

		driver.findElement(txtAMT).clear();
		action.setValueInTextField(txtAMT, sdd.getAmount());

		String endId = gf.randamGenerator();
		action.setValueInTextField(txtENDTOEND, endId);
		
		sdd.setEndToEndId(endId);

		String initiatorTxRef = gf.randamGenerator();
		action.setValueInTextField(initRef, initiatorTxRef);

		sdd.setInitiatorTransactionReference(initiatorTxRef);
		
		action.clickLink(sentNotification);
		
		//action.clickLink(chkLastSDD);

		action.waitForElementClickable(driver, btnCREATESDD);
		action.saveScreenshot();
		action.clickLink(btnCREATESDD);
		String expectedValue = "Created";
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(lblSTATUSSDD,"Created"));

		
		String actualMessage = driver.findElement(lblSTATUSSDD).getText();
		
		assertEquals(actualMessage, expectedValue);

		return sdd;

	}
	public SDD enrichSddObject(SDD sdd) {

		sdd.setEntity(driver.findElement(entity).getText());
		sdd.setStatus(driver.findElement(status).getText());
		sdd.setSettlementMode(driver.findElement(settlementMode).getText());
		sdd.setSequenceType(driver.findElement(sequenceType).getText());
		sdd.setInitiatorTransactionReference(driver.findElement(initiatorTransactionReference).getText());
		sdd.setInitiatorCollectionReference(driver.findElement(initiatorCollectionReference).getText());
		sdd.setIban(driver.findElement(iban).getText());
		sdd.setValuedate(driver.findElement(valuedate).getText());
		sdd.setDueDate(driver.findElement(duedate).getText());
		sdd.setCreationtype(driver.findElement(Creationtype).getText());
		sdd.setUmr(driver.findElement(UMR).getText());
		sdd.setUir(driver.findElement(UIR).getText());

		return sdd;
	}
	public void updateEventSdd(SDD sdd)
	{
		action.clickLink(editIcon);
		action.waitForElementPrescence(txtAMT);

		driver.findElement(txtAMT).clear();
		action.setValueInTextField(txtAMT, sdd.getUpdateAmt());
		
		
		action.waitForElementClickable(driver, updateSDD);
		action.saveScreenshot();
		action.clickLink(updateSDD);
	}
	
	public void modifySDD(SDD sdd) throws ParseException {

		action.clickLink(editIcon);

		action.waitForElementPrescence(txtAMT);

		driver.findElement(txtAMT).clear();
		action.setValueInTextField(txtAMT, sdd.getUpdateAmt());

		action.clearTextField(txtENDTOEND);
		String endId = gf.randamGenerator();
		action.setValueInTextField(txtENDTOEND, endId);

		action.clearTextField(txtDATE);
		String currdate = gf.currentDate();
	//	String nextday = gf.getNextDate(currdate);
		driver.findElement(txtDATE).sendKeys(currdate);

		action.waitForElementClickable(driver, updateSDD);
		action.saveScreenshot();
		action.clickLinkandWaitForPageToLoad(updateSDD);
		
		String dateMessage = driver.findElement(errorMessage).getText();
		dateMessage.contains("Due date is not valid for the settlement mode CORE, the next possible due date");

		action.clearTextField(txtDATE);

		String date = gf.currentDate();
		action.setValueInTextField(txtDATE, date);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(txtDATE).sendKeys(Keys.ARROW_RIGHT);

		driver.findElement(txtDATE).sendKeys(Keys.TAB);

		action.waitForElementClickable(driver, updateSDD);
		action.saveScreenshot();
		action.clickLink(updateSDD);

		/*
		 * String actualMessage = driver.findElement(updateStatus).getText(); String expectedValue = "SDD modification succesfull"; assertEquals(actualMessage, expectedValue);
		 */
	}

	public SDD searchSDD(SDD sdd) {
		action.clickLink(sDDDetails);

		sdd.setSci(driver.findElement(sCI).getText());

		action.setValueInTextField(btnCreateSdd, sdd.getSci());

		return sdd;
	}

	public void verifySDDStatusAfterAcknoledgeByBankStatus() {
		action.clickLink(sddDetailIcon);
		action.clickLink(updateStatusBtn);

		int count = 0;
		String[] exp = { "Refunded", "Rejected", "Returned" };

		Select dropdown = new Select(driver.findElement(btnStatus));

		List<WebElement> options = dropdown.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (we.getText().equals(exp[i])) {
					count++;
				}
			}

		}

	}

	public void verifySDDStatusAfterIssueStatus() {
		action.clickLink(sddDetailIcon);
		action.clickLink(updateStatusBtn);

		int count = 0;
		String[] exp = { "Acknowledged by the bank", "Refunded", "Rejected", "Returned" };

		Select dropdown = new Select(driver.findElement(btnStatus));

		List<WebElement> options = dropdown.getOptions();
		for (WebElement we : options) {
			for (int i = 0; i < exp.length; i++) {
				if (we.getText().equals(exp[i])) {
					count++;
				}
			}

		}

	}

}