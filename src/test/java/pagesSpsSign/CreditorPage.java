package pagesSpsSign;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigreaderUtils;
import io.qameta.allure.Step;
import testbase.BasePage;
import utilies.UserActions;

public class CreditorPage extends BasePage {
	static Logger logger = LogManager.getLogger(CreditorPage.class.getName());
	WebDriver driver = getDriver();
	UserActions action = new UserActions();

	By menuCreditor = By.xpath(".//*[@id='creditorDropDown']");
	By menuShow = By.xpath("html/body/app-root/nav/ul/li[2]/div/a[1]");
	By txtsignatureId = By.xpath(".//*[@id='creditorIdentifier']");
	By linkShow = By.linkText("Show");
	By editCreditorConfigration = By.xpath(".//*[@id='form']/div[1]/button");
	By chbConsentOfCreditor = By.xpath(".//*[@id='collectionOfConsentForCreditor']");
	By chbConsentOfPartner = By.xpath(".//*[@id='collectionOfConsentForPartner']");
	By btnSubmit = By.id("submit");
	By btnEditCreditorConfigration = By.xpath("//div[@class='row']/button[text()='Edit Creditor Configuration']");
	By chkdocumentSignature = By.id("documentSignatureAllowed");
	By chkSkipSummaryPage = By.id("allowSkipSummaryPage");
	By chkMandateUpdateAllowed = By.id("mandateUpdateAllowed");

	@Step("Launch SPSSign")
	public void angularSPSSignHomepageLaunch() {
		String environment = BasePage.getEnvironment();
		logger.info("Angular SPS SIGN Execution environment - {}", environment);
		String url = ConfigreaderUtils.getConfig("environment." + environment + ".angularbackoffice");
		driver.get(url);

	}
	By reuseCheckbox =By.id("reuseExistingMandate");
	public void checkReuseMandateCheckBox()
	{
		action.waitForPageToLoad();
		boolean chkreuse= driver.findElement(reuseCheckbox).isSelected();
		if (chkreuse == false)
		{
			action.clickLink(editCreditorConfigration);
			action.clickLink(reuseCheckbox);
			action.clickLink(btnSubmit);
		}
		else 
		{
			logger.info("reuse mandate already selected");
		}
		
	}

	public void disableDocumentSignature() {
		action.clickLink(btnEditCreditorConfigration);
		action.selectCheckboxIfChecked(chkdocumentSignature);
		action.clickButton(btnSubmit);
	}

	public void disableMandateUpdateAllowed() {
		action.clickLink(btnEditCreditorConfigration);
		action.selectCheckboxIfChecked(chkMandateUpdateAllowed);
		action.clickButton(btnSubmit);
	}

	public void disableSkipSummaryPage() {
		action.clickLink(btnEditCreditorConfigration);
		action.selectCheckboxIfChecked(chkSkipSummaryPage);
		action.clickButton(btnSubmit);
	}

	public void enableDocumentSignature() {
		action.clickLink(btnEditCreditorConfigration);
		action.selectCheckboxIfUnchecked(chkdocumentSignature);

		action.clickButton(btnSubmit);
	}

	public void enableMandateUpdateAllowed() {
		action.clickLink(btnEditCreditorConfigration);
		action.selectCheckboxIfUnchecked(chkMandateUpdateAllowed);
		action.clickButton(btnSubmit);
	}

	public void enableSkipSummaryPage() {
		action.clickLink(btnEditCreditorConfigration);
		action.selectCheckboxIfUnchecked(chkSkipSummaryPage);
		action.clickButton(btnSubmit);
	}

	public void searchCreditorId() {

		action.clickLink(menuCreditor);
		action.clickLink(menuShow);

		action.setValueInTextField(txtsignatureId, "ztestDoS");

		WebDriverWait wait = new WebDriverWait(driver, 100);

		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Show")));
		action.clickLink(linkShow);

		action.clickLink(editCreditorConfigration);

		if (!driver.findElement(chbConsentOfCreditor).isSelected()) {
			driver.findElement(chbConsentOfCreditor).click();
		}
		if (!driver.findElement(chbConsentOfPartner).isSelected()) {
			driver.findElement(chbConsentOfPartner).click();
		}
		action.clickButton(btnSubmit);

	}

}
