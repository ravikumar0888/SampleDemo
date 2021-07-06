package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Step;
import testbase.BasePage;
import testdataobjects.FileSupervision;
import utilies.GenericFunctions;
import utilies.UserActions;

public class FileSupervisionPage extends BasePage {
	static Logger logger = LogManager.getLogger(FileSupervisionPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();

	WebDriver driver = getDriver();

	By ddCreditorEntity = By.xpath(".//*[@id='organizationSelect']");
	By ddSelectUploadFile = By.xpath(".//*[@id='selectUploadType']");
	By ddFileFormat = By.xpath(".//*[@id='selectFileFormat']");
	By btnValdiate = By.xpath(".//*[@id='submitUploadFile']");
	By fileUploaded = By.id("fileUploaded");
	By menuSearchForFile = By.xpath("//*[@id='pageMenu']/ul/ul/li[2]/a");
	By searchButton = By.xpath(".//*[@id='searchButtons']/input[1]");
	By btnReset = By.name("reset");
	By fileStatus = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[3]");
	By fileDetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[9]/a[1]/img");
	By uploadedSucessmessage = By.xpath("//*[@id='btbartop']/h3");

	By activeStatus = By.xpath(".//*[@id='fileOperationsIntegrated_id']/div[2]/table/tbody/tr[1]/td[1]");

	By activeCount = By.xpath(".//*[@id='fileOperationsIntegrated_id']/div[2]/table/tbody/tr[1]/td[2]");
	By pendingStatus = By.xpath(".//*[@id='fileOperationsIntegrated_id']/div[2]/table/tbody/tr[2]/td[1]");

	By pendingCount = By.xpath(".//*[@id='fileOperationsIntegrated_id']/div[2]/table/tbody/tr[2]/td[2]");
	By rejectCode = By.xpath(".//*[@id='fileOperationsRejected_id']/div[2]/table/tbody/tr/td[3]");

	By searchFileStatus = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[3]");
	By searchByFilename = By.xpath(".//*[@id='fileName']");

	public void mandateIsLoading() throws InterruptedException {
		do {

			action.clickLink(searchButton);

			action.waitForPageToLoad();

		} while (!driver.findElement(By.xpath("//*[@id='resultList']/div[2]/table/tbody/tr/td[9]/a[1]/img")).isDisplayed());

		Thread.sleep(10000);
		// driver.findElement(searchFileStatus).getText().contains("Partially
		// processed"));

	}

	@Step("User searches uploaded file and Validate the data")
	public void searchFileAndValidate(String filename, FileSupervision fs) throws InterruptedException {

		action.saveScreenshot();
		action.clickLinkandWaitForPageToLoad(menuSearchForFile);
		action.clickLinkandWaitForPageToLoad(btnReset);
		action.setValueInTextField(searchByFilename, filename);

		waitTillFileIsLoading(); //Wait until certain condition

		action.clickLinkandWaitForPageToLoad(fileDetails);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement activestatus = wait.until(ExpectedConditions.visibilityOfElementLocated(activeStatus));

		if (fs.getActiveCount() != null) {
			String active = driver.findElement(activeStatus).getText().trim();
			Assert.assertEquals(active, fs.getActiveLabel());

			String count = driver.findElement(activeCount).getText().trim();
			Assert.assertEquals(count, fs.getActiveCount());
		}
		if (fs.getPendingCount() != null) {
			String pending = driver.findElement(pendingStatus).getText().trim();
			Assert.assertEquals(pending, "Pending");

			String pcount = driver.findElement(pendingCount).getText().trim();
			Assert.assertEquals(pcount, "1");
		}
		if (fs.getRejectCode() != null) {

			String reject = driver.findElement(rejectCode).getText().trim();
			Assert.assertEquals(reject, "MDT011");
		}
	}

	@Step("User upload csv file for mandate creation")
	public String uploadCSVForMandateCreation(FileSupervision filesupervision, String filepath) throws InterruptedException {

		action.selectvaluesdropdownWithValue(ddCreditorEntity, filesupervision.getCreditorEntity()); // RootRaviM1
		Thread.sleep(3000);
		action.selectvaluesdropdownWithValue(ddSelectUploadFile, filesupervision.getddSelectUploadFile());
		action.selectvaluesdropdownWithValue(ddFileFormat, filesupervision.getddFileFormat());

		WebElement uploadElement = driver.findElement(By.id("fileUploaded"));
		// for remotely uploading files
		((RemoteWebElement) uploadElement).setFileDetector(new LocalFileDetector());

		// enter the file path onto the file-selection input field
		uploadElement.sendKeys(filepath);
		action.saveScreenshot();
		action.clickLink(btnValdiate);

		String filename = driver.findElement(uploadedSucessmessage).getText().trim();

		int indexRename = filename.indexOf("renamed ") + 8;
		int endindex = filename.indexOf(" The file");

		String name = filename.substring(indexRename, endindex);
		logger.info("File name is " + name);

		return name;
	}

	public void waitTillFileIsLoading() {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				action.clickLink(searchButton);

				return driver.findElement(fileDetails).isDisplayed();
			}
		};
		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(expectation);

		} catch (Exception error) {
			Assert.fail("Assertion fail {}", error);

		}
	}

}
