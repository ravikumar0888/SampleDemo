package utilies;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.ConfigreaderUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import testbase.BasePage;

/**
 * utility for checking Selenium hub readiness and other rest methods
 * 
 * @author A708551
 *
 */
public class RESTAssuredUtility {

	private static boolean hubstatus = false;

	static RequestSpecification restSpec;
	private static Logger logger = LogManager.getLogger(RESTAssuredUtility.class.getName());

	public static boolean isSelenumHubReady() {
		try {
			URL hubURL = new URL(ConfigreaderUtils.getConfig("environment." + BasePage.getEnvironment() + ".hubURL"));
			String host = hubURL.getHost();
			String protocol = hubURL.getProtocol();

			restSpec = new RequestSpecBuilder().setBaseUri(protocol + "://" + host).setPort(hubURL.getPort()).build();
		} catch (MalformedURLException e) {
			logger.error("malformed URL ", e);

		}

		long startTime = System.currentTimeMillis(); // fetch starting time
		while (hubstatus || (System.currentTimeMillis() - startTime) < 60000) {

			hubstatus = given().spec(restSpec).get("/wd/hub/status").then().extract().response().jsonPath().getBoolean("value.ready");
			if (hubstatus) {

				break;
			}

		}
		return hubstatus;

	}

	private RESTAssuredUtility() {
	}

}
