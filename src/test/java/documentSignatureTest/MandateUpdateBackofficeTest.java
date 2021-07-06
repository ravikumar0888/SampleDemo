package documentSignatureTest;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import io.restassured.response.Response;
import pagesSpsSign.CreditorPage;
import pagesSpsSign.MandateUpdatePage;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SignHomePage;
import pagesSpsSign.SpsSignBackOfficePage;
import testbase.BasePage;
import utilies.GenericFunctions;

public class MandateUpdateBackofficeTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateUpdateBackofficeTest.class.getName());

	LinkedTreeMap<String, String> formData = JsonManager.getMapfromJson("testdata/testdata.json", "MandateUpdateBackofficeTest");
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void mandateUpdateAllowedTest() {
		SignHomePage shp = new SignHomePage();
		CreditorPage cp = new CreditorPage();
		GenericFunctions gf = new GenericFunctions();
		SpsSignBackOfficePage sbo = new SpsSignBackOfficePage();
		MandateUpdatePage mup = new MandateUpdatePage();
		RestEndPoints rest = new RestEndPoints();
		//set creditor config
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json", "default");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());

		shp.signHomepageLaunch();
		shp.navigateToMenu("Tests", "Web Service demo");
		sbo.navigateToMandateUpdatePage();
		String transId = gf.randamGenerator();
		formData.replace("TransactionId", transId);
		sbo.fillInitSessionFormMandateUpdate(formData);
		String url = sbo.getUrlMandateUpdate();
		navigateToUrl(url);
		mup.verifyMandateDetailAndUpdateUMR(formData);

	}

}
