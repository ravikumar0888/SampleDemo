package testbase;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;

import config.ConfigreaderUtils;

public class OptionsManager extends BasePage {

	private static Logger logger = LogManager.getLogger(OptionsManager.class.getName());

//	private static final String PROD_PROXY_PORT = "proxy-prod-scl.svc.meshcore.net:3128";
	private static final String PROD_PROXY_PORT= ConfigreaderUtils.getConfig("environment." + environment + ".proxy");
	// Get Chrome Options
	public static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		
		if(!BasePage.browser.contains("local"))
		{
		options.setProxy(getProxy()); // comment for local run
		}
		// Path abc = Paths.get("./target/Downloads" +
		// File.separator).toAbsolutePath().normalize();
		// downloadFilepath = abc.toString();
		// logger.info("Download file path set in optionsmanager class {}" ,
		// downloadFilepath);

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "/home/seluser/Downloads");
		// //we prefer to download in chrome default directory hence commented this
		// line.
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("download.directory_upgrade", true);
		options.setExperimentalOption("prefs", chromePrefs);
		return options;
	}

	// Get Firefox Options
	public static FirefoxOptions getFirefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		// Accept Untrusted Certificates
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);

		// Use Proxy Settings
		options.setProxy(getProxy());


		//Directly download PDF

		/*
		 * The value of browser.download.folderList can be set to either 0, 1, or 2. When set to 0, 
		 * Firefox will save all files downloaded via the browser on the user's desktop. When set to 1, these downloads are stored in the Downloads folder. When set to 2, the location specified for the most recent download is utilized again.
		 */
		//profile.setPreference("browser.download.folderList", 1); 
		//profile.setPreference("browser.download.dir", "C:\\Windows\\temp");
		//profile.setPreference("browser.download.useDownloadDir", true);

		//profile.setPreference("pdfjs.enabledCache.state", false);
		profile.setPreference("browser.helperApps.neverAsk.openFile", "application/pdf");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		profile.setPreference("pdfjs.disabled", true); // disable the built-in PDF viewer
		// Set Firefox profile to capabilities
		options.setCapability(FirefoxDriver.PROFILE, profile);
		return options;
	}

	// Get IE Options
	public static InternetExplorerOptions getIEOptions() {

		return new InternetExplorerOptions();

	}

	public static Proxy getProxy() {

		// Create proxy class object
		Proxy p = new Proxy();
		return p.setHttpProxy(PROD_PROXY_PORT).setFtpProxy(PROD_PROXY_PORT).setSslProxy(PROD_PROXY_PORT);
	}

	// static String downloadFilepath = null;
	private OptionsManager() {
	}
}
