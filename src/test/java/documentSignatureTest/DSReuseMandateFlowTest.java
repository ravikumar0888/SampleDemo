package documentSignatureTest;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.restassured.response.Response;
import pagesSpsSign.CreditorPage;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import testbase.BasePage;
import testdataobjects.InitSession;
import testdataobjects.Mandates;
import testdataobjects.initsession.MandateParameters;
import testdataobjects.initsession.SessionDocumentSignature;
import testdataobjects.initsession.SessionParameters;
import utilies.GenericFunctions;

public class DSReuseMandateFlowTest extends BasePage {
	static Logger logger = LogManager.getLogger(DSReuseMandateFlowTest.class.getName());
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void DSReuseMandateFlow() {

		Gson gson = new Gson();
		CreditorPage cp = new CreditorPage();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();

		// check back office setting
		/*
		 * String zTestDosURL = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/backoffice/creditor/display/ALg"; navigateToUrl(zTestDosURL); cp.checkReuseMandateCheckBox();
		 */
		LinkedTreeMap<String, ?> testdata = JsonManager.getMapfromJson("testdata/testdata.json", "DSReuseMandateFlowTest");
		Mandates mandatedata = new Mandates(testdata);
		//set creditor config
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json", "DSReuseMandateFlowTest");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());

		String transId = gf.randamGenerator();
		logger.info("ID is " + transId);
		String requestJson = FileManager.readFile("testdata/initSession.json");
		InitSession initSession = gson.fromJson(requestJson, testdataobjects.InitSession.class);

		SessionParameters sp = initSession.getSessionParameters(); // get data from json
		sp.setTransactionId(transId);
		sp.setDocumentSignature(true);

		SessionDocumentSignature sds = sp.getSessionDocumentSignature();
		sds.setDocumentId(transId);
		sds.setDocumentName(transId);
		sds.setDocumentPaymentType("MANDATE");//  UNDEFINED 

		//

		sp.setSessionDocumentSignature(sds);

		initSession.setSessionParameters(sp);
		MandateParameters mp = initSession.getMandateParameters();

		mp.setUir(mandatedata.getUIR());
		logger.info("UIR is "+mandatedata.getUIR());
		mp.setUmr(mandatedata.getUmr());
		logger.info("UMR is "+mandatedata.getUmr());
		initSession.setMandateParameters(mp);

		initSession.setMandateParameters(mp);

		String updatedInitSession = gson.toJson(initSession);

		Response response = rest.getinitSessionResponse(updatedInitSession);
		String url = rest.getinitSessionUrl(response);

		navigateToUrl(url);
		signStep1.resumeMandateStep1(false, true); // first parameter for DS and second Mandate
		signStep2.documentSignatureStep2ForReuse(initSession);
		signStep3.documentSignatureStep3();

	}

	@AfterMethod
	public void removeconfig() {

		/*
		 * navigateToUrl(zTestDosURL); cp.checkReuseMandateCheckBox();
		 */
	}
}
