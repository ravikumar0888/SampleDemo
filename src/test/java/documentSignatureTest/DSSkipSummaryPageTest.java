package documentSignatureTest;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import testbase.BasePage;
import testdataobjects.InitSession;
import testdataobjects.initsession.SessionDocumentSignature;
import testdataobjects.initsession.SessionParameters;
import utilies.GenericFunctions;

public class DSSkipSummaryPageTest extends BasePage {
	static Logger logger = LogManager.getLogger(DSSkipSummaryPageTest.class.getName());
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";
	//String backOffice = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/backoffice/";

	@AfterMethod
	void disableSkipSummary() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void testDSSkipSummaryPageTest() {

		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();
		Gson gson = new Gson();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json", "DSSkipSummaryPageTest");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());

		String transId = gf.randamGenerator();
		String requestJson = FileManager.readFile("testdata/initSession.json");
		InitSession initSession = gson.fromJson(requestJson, testdataobjects.InitSession.class);
		SessionParameters sp = initSession.getSessionParameters();
		sp.setTransactionId(transId);
		SessionDocumentSignature sds = sp.getSessionDocumentSignature();
		sds.setDocumentId(transId);
		sds.setDocumentName(transId);
		sp.setSessionDocumentSignature(sds);
		// Set skip summary page as true for init session
		sp.setSkipSummaryPage(true);

		initSession.setSessionParameters(sp);
		String updatedInitSession = gson.toJson(initSession);
		Response response1 = rest.getinitSessionResponse(updatedInitSession);
		String url = rest.getinitSessionUrl(response1);
		navigateToUrl(url);
		signStep1.documentSignatureStep1(true, false);
		signStep2.documentSignatureStep2(initSession);
		signStep3.validateURL("https://127.0.0.1/okURL");

	}
}
