package testbase;

import static utilies.RESTAssuredUtility.isSelenumHubReady;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import config.ConfigreaderUtils;

/**
 * Base page initialize the WebDriver and config
 * 
 * @author Ravi Murai
 *
 */
public class BasePage {

	private static Logger logger = LogManager.getLogger(BasePage.class.getName());

	protected static String environment;
	protected static String browser;
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	/**
	 * @return the environment
	 */
	public static String getEnvironment() {
		return environment;
	}
	/**
	 * @param environment
	 *            the environment to set
	 */
	public static void setEnvironment(String environment) {
		BasePage.environment = environment;
	}

	protected String url;

	protected URL hubURL;

	private void deckout(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	//	driver.navigate().to(url);
	}

	public WebDriver getDriver() {
		// Get driver from ThreadLocalMap
		return driver.get();
	}

	public void navigateToUrl(String url) {
		WebDriver driver1 = getDriver();
		driver1.navigate().to(url);
	}

	private void openBrowser(String browser) {
		logger.info("Execution browser - {}", browser);

		switch (browser) {
		case "chrome":
			if (isSelenumHubReady()) {
				logger.info("Selenium Hub status found to be ready");
				driver.set(new RemoteWebDriver(hubURL, OptionsManager.getChromeOptions()));
				logger.info("Initialized chrome browser driver in CI environment");
			} else {
				logger.error("Selenium Hub status found to be NOT ready");
			}

			break;

		case "firefox":
			if (isSelenumHubReady()) {

				driver.set(new RemoteWebDriver(hubURL, OptionsManager.getFirefoxOptions()));
				logger.info("Initialized Firefox browser driver in CI environment");
			} else {
				logger.error("Selenium Hub status found to be NOT ready");
			}
			break;

		case "chromelocal":
			System.setProperty("webdriver.chrome.driver", ConfigreaderUtils.getConfig("nodeconfig.chromeDriverPath"));
			driver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
			logger.info("Initialized chrome browser driver");

			break;
		case "firefoxlocal":
			System.setProperty("webdriver.gecko.driver", ConfigreaderUtils.getConfig("nodeconfig.geckoDriverPath"));

			driver.set(new FirefoxDriver(OptionsManager.getFirefoxOptions()));
			logger.info("Initialized firefox browser driver");

			break;
		case "ielocal":
			System.setProperty("webdriver.ie.driver", ConfigreaderUtils.getConfig("nodeconfig.IEDriverPath"));
			driver.set(new InternetExplorerDriver(OptionsManager.getIEOptions()));
			logger.info("Initialized IE browser driver");

			break;
		default:
			System.setProperty("webdriver.chrome.driver", ConfigreaderUtils.getConfig("nodeconfig.chromeDriverPath"));
			driver.set(new ChromeDriver(OptionsManager.getChromeOptions()));
			logger.info("Initialized default browser driver - chrome driver");

		}

	}

	/**
	 * tear down the WebDriver after the suite is completed
	 */
	@AfterMethod(alwaysRun = true, description = "tearing down test setup")
	public void tearDown() {
		WebDriver driver1 = getDriver();
		if (driver1 != null) {
			driver1.quit();
		}
	}

	@AfterClass
	void terminate() {
		// Remove the ThreadLocalMap element
		driver.remove();
	}

	/**
	 * reads config and setup browser for execution
	 */
	@Parameters({ "browser", "environment" })
	@BeforeMethod(alwaysRun = true, description = "Setting up the Selenium enviroment.")
	public void testSetup(String browser, String environment) {
		BasePage.environment = environment;
		BasePage.browser = browser; 
		logger.info("Execution environment - {}", environment);

		url = ConfigreaderUtils.getConfig("environment." + environment + ".URL");
		logger.info("Execution URL - {}", url);

		try {
			hubURL = new URL(ConfigreaderUtils.getConfig("environment." + environment + ".hubURL"));
			logger.info("hubURL - {}", hubURL);
		} catch (MalformedURLException e) {
			logger.error("Invalid Hub URL{}", hubURL, e);
		}
		openBrowser(browser);
		deckout(getDriver());

	}

}
