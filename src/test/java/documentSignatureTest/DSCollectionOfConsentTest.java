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
import pagesSpsSign.CreditorPage;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import testbase.BasePage;
import testdataobjects.InitSession;
import testdataobjects.initsession.SessionDocumentSignature;
import testdataobjects.initsession.SessionParameters;
import utilies.GenericFunctions;

public class DSCollectionOfConsentTest extends BasePage {
	static Logger logger = LogManager.getLogger(DSCollectionOfConsentTest.class.getName());

	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void DSCollectionOfConsent() {

		Gson gson = new Gson();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();
		CreditorPage cp = new CreditorPage();
		//String backOffice = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/backoffice/";

		//set creditor config
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json", "DSCollectionOfConsentTest");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());

		String paymentType = "NONE";
		String transId = gf.randamGenerator();
		String requestJson = FileManager.readFile("testdata/initSession.json");
		InitSession initSession = gson.fromJson(requestJson, testdataobjects.InitSession.class);

		SessionParameters sp = initSession.getSessionParameters();
		sp.setTransactionId(transId);
		SessionDocumentSignature sds = sp.getSessionDocumentSignature();
		sds.setDocumentId(transId);
		sds.setDocumentName(transId);
		sds.setDocumentPaymentType(paymentType);

		sp.setSessionDocumentSignature(sds);
		initSession.setSessionParameters(sp);

		String updatedInitSession = gson.toJson(initSession);
		Response response = rest.getinitSessionResponse(updatedInitSession);
		String url = rest.getinitSessionUrl(response);
		navigateToUrl(url);

		signStep1.verifyCollectionOfConsent(true, false); // first parameter for DS and second Mandate
		signStep2.documentSignatureStep2(initSession);
		signStep3.documentSignatureStep3();

	}
}
