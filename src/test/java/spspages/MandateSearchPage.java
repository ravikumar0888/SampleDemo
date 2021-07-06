package spspages;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.BasePage;
import testdataobjects.MandatesSearch;
import utilies.GenericFunctions;
import utilies.UserActions;

public class MandateSearchPage extends BasePage {
	static Logger logger = LogManager.getLogger(MandateSearchPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();

	// Mandate Serch
	By ddCreditorEntity = By.xpath(".//*[@id='organizationSelect']");
	By txtUMR = By.id("umr");
	By searchMandateDetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[11]/a[1]/img");
	By btnCreateSDD = By.xpath("//div[@class='buttonBar']//a[contains(text(),'Create SDD')]");
	By ddStatus = By.xpath(".//*[@id='statusSelect']");
	By btnSearch = By.name("search");

	By btnSearchMandate = By.xpath("//*[@id='btbartop']/div[1]/a[contains(text(),'Search mandate')]");

	By editMandateicon = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[11]/a[1]/img");

	By btnAssociatePaymentSchedule = By.xpath("//a[contains(text(),'Associated Payment Schedules')]");

	By newPayementSchedule = By.xpath("//a[contains(text(),'New Payment Schedule')]");

	By tabMandateData = By.xpath(".//*[@id='mandateInfoTrigger']");

	By tabPaymentScheduledata = By.xpath(".//*[@id='divToHide']/span[contains(text(),'Payment Schedule Data')]");
	By tabPaymentPlan = By
			.xpath(".//*[@id='divToHide']/span[contains(text(),'Notification period for the payment plan')]");
	By txtRemittedInfo = By.xpath(".//*[@id='remittanceInfo']");

	By ddPaymentType = By.xpath(".//*[@id='type']");
	By ddFirstDuedate = By.xpath(".//*[@id='firstDueDateField']");

	By txtFirstAmt = By.xpath(".//*[@id='firstAmount']");

	By txtNoOfPayment = By.xpath(".//*[@id='nbOccurences']");
	By txtDefaultAmount = By.xpath(".//*[@id='defaultAmount']");

	By ddPeriod = By.xpath(".//*[@id='period']");
	By chkLastSDD = By.xpath(".//*[@id='lastPeriodicSddFinal']");
	By btnSubmit = By.xpath(".//*[@id='paymentScheduleForm']/div[6]/input[1]");
	By statusFirstPayment = By.xpath(".//*[@id='listSdd']/div[2]/table/tbody/tr[1]/td[4]");
	By statusFinalPayment = By.xpath(".//*[@id='listSdd']/div[2]/table/tbody/tr[2]/td[4]");

	By btnDeleteSchedule = By.xpath("//a[contains(text(),'Delete Payment Schedule')]");
	By btnValidate = By.xpath(".//*[@id='deleteForm']/div[2]/input[1]");

	public void btnSDDCreate() {
		action.clickLink(btnCreateSDD);
	}

	public void deletePaymentScheule() {
		action.clickLink(btnDeleteSchedule);
		action.clickLink(btnValidate);
	}

	public void issuedSDDSearch() {
		action.clickLink(searchMandateDetails);

	}

	public void mandateSearchWithActiveStatus(MandatesSearch mandate) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(ddCreditorEntity));

		action.selectvaluesdropdown(ddCreditorEntity, mandate.getCreditorEntity());
		action.selectvaluesdropdown(ddStatus, mandate.getStatus());
		action.clickLink(btnSearch);
		WebDriverWait waitbtn = new WebDriverWait(driver, 60);
		WebElement btn = waitbtn.until(ExpectedConditions.elementToBeClickable(searchMandateDetails));
		btn.click();

		action.waitForPageToLoad();
		// action.saveScreenshot();

		action.clickLinkWithJS(btnCreateSDD);

		/*
		 * WebDriverWait wait1 = new WebDriverWait(driver, 100); WebElement show1 =
		 * wait1.until(ExpectedConditions.elementToBeClickable(btnCreateSDD));
		 * show1.click();
		 */
		// action.clickLink(btnCreateSDD);

	}

	public void searchActiveMandateandPaymentSchedule(MandatesSearch mandate) {
		action.selectvaluesdropdown(ddCreditorEntity, mandate.getCreditorEntity());
		action.selectvaluesdropdown(ddStatus, mandate.getStatus());
		action.clickLink(searchMandateDetails);
		action.saveScreenshot();

		action.clickLink(btnAssociatePaymentSchedule);

		WebDriverWait wait = new WebDriverWait(driver, 90);

		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(newPayementSchedule));

		action.clickLink(newPayementSchedule);

		String actualMessage = driver.findElement(tabMandateData).getText();
		assertEquals(actualMessage, "Mandate Data");

		String actualMessage1 = driver.findElement(tabPaymentScheduledata).getText();
		assertEquals(actualMessage1, "Payment Schedule Data");

		String actualMessage2 = driver.findElement(tabPaymentPlan).getText();
		assertEquals(actualMessage2, "Notification period for the payment plan");

		action.setValueInTextField(txtRemittedInfo, "testing");
		action.selectvaluesdropdown(ddPaymentType, mandate.getPaymenttype());

		String date = gf.currentDate();
		action.setValueInTextField(ddFirstDuedate, date);
		driver.findElement(ddFirstDuedate).sendKeys(Keys.ARROW_RIGHT);
		driver.findElement(ddFirstDuedate).sendKeys(Keys.TAB);

		action.setValueInTextField(txtFirstAmt, "1");
		action.setValueInTextField(txtNoOfPayment, "2");
		action.setValueInTextField(txtDefaultAmount, "1");

		action.selectvaluesdropdown(ddPeriod, "Daily");
		action.clickLink(chkLastSDD);
		action.clickLink(btnSubmit);

		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement show1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(statusFirstPayment));
		String statusfirst = driver.findElement(statusFirstPayment).getText();
		assertEquals(statusfirst, "Recurrent");

		String statusFinal = driver.findElement(statusFinalPayment).getText();
		assertEquals(statusFinal, "Final");
	}

	public void searchMandate(MandatesSearch mandate) {
		action.selectvaluesdropdown(ddCreditorEntity, mandate.getCreditorEntity());

		action.clickLink(searchMandateDetails);

		action.clickLink(btnCreateSDD);
	}

	public void searchMandate(String Creditor, String umr) {
		action.selectvaluesdropdown(ddCreditorEntity, Creditor);
		action.setValueInTextField(txtUMR, umr);
		action.clickButton(btnSearch);
		action.clickLink(searchMandateDetails);

		action.clickLink(btnCreateSDD);
	}

	public void searchMandateBtn() {
		action.clickLink(btnSearchMandate);
	}

	public void searchActiveMandate(MandatesSearch mandate) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(ddCreditorEntity));

		action.selectvaluesdropdown(ddCreditorEntity, mandate.getCreditorEntity());
		action.selectvaluesdropdown(ddStatus, mandate.getStatus());
		action.clickLink(btnSearch);
		WebDriverWait waitbtn = new WebDriverWait(driver, 60);
		WebElement btn = waitbtn.until(ExpectedConditions.elementToBeClickable(searchMandateDetails));
		btn.click();

		action.waitForPageToLoad();
	}

	By txtumr = By.xpath("//*[@id=\"mandateInfoContainer\"]/div/div/div[1]/dl/dd[1]");

	public String getUMR() {
		String umr = action.getText(txtumr);

		return umr;
	}

	By txtuir = By.xpath("//*[@id=\"mandateInfoContainer\"]/div/div/div[2]/dl/dd[1]");

	public String getUIR() {
		String uir = action.getText(txtuir);

		return uir;
	}
}