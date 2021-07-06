package utilies;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import testbase.BasePage;

/**
 * Basic Selenium actions
 *
 */
public class UserActions extends BasePage {

	private static Logger logger = LogManager.getLogger(UserActions.class.getName());

	private static final int EXPLICIT_WAIT_TIMEOUT = 60;
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private By menuNewCreditor = By.xpath("//*[@id='pageMenu']/ul/ul/li[2]/a");

	WebDriver driver = getDriver();

	public void clearTextField(final By by) {
		findObject(by).clear();
	}

	public void clickButton(final By by) {
		// set focus on the object first
		findObject(by).click();
		logger.info("Button: {} clicked", by);
	}

	public void clickLink(final By by) {

		waitForElementClickable(driver, by);
		findObject(by).click();

		logger.info("Link: ' {}' clicked", by);

	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();

	}

	public void clickLinkandWaitForPageToLoad(final By by) {

		waitForElementClickable(driver, by);
		findObject(by).click();
		waitForPageToLoad();
		logger.info("Link: ' {}' clicked", by);

	}

	public void clickLinkWithJS(final By by) {
		WebElement element = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		logger.info("click with JS : ' {}' clicked", by);
	}

	public void clickTextBox(final By by) {
		findObject(by).click();
		logger.info("TEXT BOX: '{}' clicked", by);
	}

	public WebElement findElement(final By by) {
		final int pollingFrequency = 500;
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(pollingFrequency));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

		} catch (Exception e) {
			logger.error("Failed to locate the element ", e);

			return null;
		}

	}

	/**
	 * Finds Object on page with wait condition to make method robust
	 * 
	 * @param by
	 *            Locator to be searched
	 * @return Webelement from the page
	 */
	public WebElement findObject(final By by) {
		final int pollingFrequency = 500;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofMillis(pollingFrequency)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.presenceOfElementLocated(by));

		return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

	}

	public String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);

	}

	public String getCurrentURL() {

		String currentURL = driver.getCurrentUrl();
		logger.info("The browser is on URL: {} ", currentURL);
		return currentURL;
	}

	public String getTittle() {

		String currentTittle = driver.getTitle();
		logger.info("The browser is on URL: {} ", currentTittle);
		return currentTittle;
	}

	public String getText(final By by) {
		// set focus on the object first
		String text = driver.findElement(by).getText();
		logger.info("Text found at webelement {} ", text);
		return text;
	}

	public String getAttribute(final By by, String attribute) {
		String text = findElement(by).getAttribute(attribute);

		logger.info("Attribute value found at webelement {} ", text);
		return text;
	}

	public String getValueFromDropDownField(final By by) {
		String value = findObject(by).getAttribute("value");
		logger.info("Text Field: {}. Value {} found", by, value);
		return value;
	}

	public String getValueFromTextField(final By by) {
		clickLink(by);
		String value = findObject(by).getText();
		String value1 = findObject(by).getAttribute("title");
		logger.info("Text Field: '{}'. Value '{}' found", by, value);
		logger.info("Text Field: '{}'. Value '{}' found", by, value1);
		return value;
	}

	public void pressEnterKeyInField(final By by) {
		findObject(by).sendKeys(Keys.ENTER);
	}

	/**
	 * Method to attach screenshot of the browser
	 * 
	 * @return byte array for allure reporting framework
	 */
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	}

	public void saveScreenshot1(String nameOfScreen) {

		ByteArrayInputStream is = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		Allure.addAttachment(nameOfScreen, is);

	}

	public void sddstatuschecck(WebDriver driver, final By locator, final String text) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);

		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					driver.findElement(By.name("search")).click();

					String elementText = driver.findElement(locator).getText();
					return elementText.contains(text);
				} catch (StaleElementReferenceException e) {
					logger.error("Stale element encountered ", e);
					return false;

				}
			}

			@Override
			public String toString() {
				return String.format("text ('%s') to be present in element found by %s", text, locator);
			}
		});
	}

	public void selectCheckboxIfChecked(By checkbox) {

		if (driver.findElement(checkbox).isSelected() == true) {
			clickLink(checkbox);
		} else {
			logger.info("check box already unchecked. {}", checkbox);
		}
	}

	public void selectCheckboxIfUnchecked(By checkbox) {

		if (driver.findElement(checkbox).isSelected() == false) {
			clickLink(checkbox);
		} else {
			logger.info("check box already selected. {}", checkbox);
		}
	}

	public void selectUncheckAndCheck(By checkbox) {

		if (driver.findElement(checkbox).isSelected() == true) {
			clickLink(checkbox);
			waitForElementPrescence(checkbox);
			clickLink(checkbox);
		} else {
			clickLink(checkbox);
			logger.info("{} Check box was unchecked. It is now checked.", checkbox);
		}
	}
	/**
	 * Method to select Menu in SPS App
	 * 
	 * 
	 * @param menu
	 *            Name of menu
	 * @param subMenu
	 *            Name of submenu
	 */
	@Step("User selects menu with value {Submenu} ")
	public void selectMenu(String menu, String subMenu) {
		By lblMenu = By.xpath(".//*[@id='pageMenu']/ul/li/a[contains(text(),'" + menu + "')]");
		By lblsubMenu = By.xpath(".//*[@id='pageMenu']/ul/ul/li/a[contains(text(),'" + subMenu + "')]");

		if (!driver.findElements(lblMenu).isEmpty()) {
			driver.findElement(lblMenu).click();
		}

		if (!driver.findElements(lblsubMenu).isEmpty()) {
			if (driver.findElement(lblsubMenu).isEnabled()) {
				saveScreenshot();
				driver.findElement(lblsubMenu).click();
				waitForPageToLoad();
				logger.info("SubMenu is Present {}", lblsubMenu);

			} else {

				logger.info("SubMenu is not found on the webpage");

			}
		} else {

			logger.info("No need to select sub menu option");
		}
	}

	/**
	 * Selects creditor in SPS app
	 */

	By creditorSearch = By.xpath("//*[@id=\'pageMenu\']/ul/ul/li[1]/a");

	public void selectSearchCreditor() {
		clickLink(creditorSearch);
	}
	
	By menuOrag = By.xpath("//*[@id=\'pageMenu\']/ul/li[7]/a");
	public void selectOragnization()
	{
		clickLink(menuOrag);
	}
	
	By menuUserSearch = By.xpath("//*[@id=\"pageMenu\"]/ul/ul/li[1]/span");
	public void menuUserSearch() {
		clickLink(menuUserSearch);
	}
	
	/**
	 * Select Value from multiple dropdown
	 * 
	 * @param by
	 *            locator
	 * @param value
	 *            to be selected
	 */
	public void selectvaluefromMultipleDropdown(final By by, String value) {
		Select oSelect = new Select(driver.findElement(by));
		List<WebElement> oSize = oSelect.getOptions();
		int iListSize = oSize.size();

		for (int i = 0; i < iListSize; i++) {
			// Storing the value of the option
			String sValue = oSelect.getOptions().get(i).getText();

			if (sValue.equals(value)) {
				oSelect.selectByVisibleText(value);
			}

		}
	}

	/**
	 * Select Values from dropdown
	 * 
	 * @param by
	 *            locator
	 * @param value
	 *            to be selected
	 */
	public void selectvaluesdropdown(final By by, String value) {

		new Select(driver.findElement(by)).selectByVisibleText(value);

	}
	public void selectByValue(final By by, String value) {

		new Select(driver.findElement(by)).selectByValue(value);

	}
	/**
	 * Select dropdown with a given value
	 * 
	 * @param by
	 *            locator of the select webelement
	 * @param value
	 *            to be selected
	 */
	public void selectvaluesdropdownWithValue(final By by, String value) {
		List<WebElement> options = new Select(driver.findElement(by)).getOptions();

		WebElement dropdown = driver.findElement(by);
		dropdown.click();

		for (WebElement option : options) {
			String optTxt = option.getText();

			if (optTxt.contains(value)) {
				option.click();
				break;
			}
		}

	}

	public void setValueInTextField(final By by, String value) {

		if (value != null) {
			findObject(by).click();
			findObject(by).clear();
			findObject(by).sendKeys(value);
			logger.info("Text Field: '{}'. Value '{}' entered", by, value);
		} else {

			logger.info("String value to be set in text field {} is empty", by);
		}
	}

	/**
	 * Sleep script for the specified length (generally not an ideal solution)
	 * 
	 * @param length
	 *            time in millisecond
	 */
	public void sleep(long length) {
		try {
			Thread.sleep(length);
		} catch (InterruptedException e) {
			logger.error("Sleep Interrupted", e);
		}
	}

	public void switchToFrame(String frame) {
		driver.switchTo().frame(frame);
	}

	public void validateURL(String expectedURL) {
		String actualURL = getCurrentURL();
		logger.info("Current page title is {}", driver.getTitle());
		assertEquals(actualURL, expectedURL);
	}

	/**
	 * Waits for a given element to be clickable
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            By to locate element to wait for
	 */
	public void waitForElementClickable(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Waits for given element to be clickable
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param e
	 *            element to wait for
	 */
	public void waitForElementClickable(WebDriver driver, WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}

	/**
	 * Waits for a given element to not be visible
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            By of the element to wait for
	 */
	public void waitForElementNotVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * Wait for element to be present. Fails test case if element not present
	 * 
	 * @param elementLocator
	 *            Locator
	 * @return webelement
	 */
	public WebElement waitForElementPrescence(By elementLocator) {
		WebElement webElement = null;
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		try {
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));

		} catch (WebDriverException e) {
			logger.error("Failed to locate the element ", e);
		}

		if (webElement == null) {
			assertFalse(true, "WebElement not found within " + EXPLICIT_WAIT_TIMEOUT + " seconds. Failing test!" + " of element: " + elementLocator + "\nCurrent page: " + driver.getCurrentUrl());
		}
		return webElement;
	}

	/**
	 * Waits for a given element to be selected
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            By of the element to wait for
	 */
	public void waitForElementSelected(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	/**
	 * Waits for a given element to be selected
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param e
	 *            element to wait for
	 */
	public void waitForElementSelected(WebDriver driver, WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeSelected(e));
	}

	/**
	 * Waits for a given element to be visible
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param locator
	 *            By of the element to wait for
	 */
	public void waitForElementVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Waits for a given element to be visible
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param e
	 *            element to wait for
	 */
	public void waitForElementVisible(WebDriver driver, WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public Boolean waitForElementGoStale(WebDriver driver, WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		return wait.until(ExpectedConditions.stalenessOf(e));
	}

	/**
	 * Waits for the page to have a given title
	 * 
	 * This method is an attempt to address a problem where Chrome/IE drivers are trying to check the page title on page load before the title has changed to that of the new page.
	 * 
	 * @param driver
	 *            WebDriver instance
	 * @param title
	 *            title the page should have
	 * @return returns boolean value - false if not present
	 */
	public boolean waitForPageTitle(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		return wait.until(ExpectedConditions.titleContains(title));
	}

	public boolean waitForPageURL(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT);
		return wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitForPageToLoad() {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {

			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(expectation);
			logger.info("Page loaded sucessfully ......");
		} catch (Exception error) {
			Assert.fail("Timeout waiting for Page Load Request to complete. {}", error);

		}
	}
}
