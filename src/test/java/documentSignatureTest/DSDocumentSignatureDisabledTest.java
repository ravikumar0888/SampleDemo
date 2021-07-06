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
import pagesSpsSign.SignHomePage;
import testbase.BasePage;
import testdataobjects.InitSession;
import testdataobjects.initsession.SessionDocumentSignature;
import testdataobjects.initsession.SessionParameters;
import utilies.GenericFunctions;

public class DSDocumentSignatureDisabledTest extends BasePage {
	public enum DocPaymentType {
		MANDATE, NONE, CARD_PAYMENT, UNDEFINED
	}

	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";
	static Logger logger = LogManager.getLogger(DSDocumentSignatureDisabledTest.class.getName());

//	String backOffice = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/backoffice/";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void testDSDocSignatureDisabled() {

		CreditorPage creditor = new CreditorPage();

		Gson gson = new Gson();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();

		SignHomePage shp = new SignHomePage();
		//set creditor config
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json", "DSDocumentSignatureDisabledTest");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());

		for (DocPaymentType documentPaymentType : DocPaymentType.values()) {
			logger.info("Checking document payment type {}", documentPaymentType);
			String transId = gf.randamGenerator();
			String requestJson = FileManager.readFile("testdata/initSession.json");
			InitSession initSession = gson.fromJson(requestJson, testdataobjects.InitSession.class);
			SessionParameters sp = initSession.getSessionParameters();
			sp.setTransactionId(transId);
			SessionDocumentSignature sds = sp.getSessionDocumentSignature();
			sds.setDocumentPaymentType(documentPaymentType.toString());
			sds.setDocumentId(transId);
			sds.setDocumentName(transId);
			sp.setSessionDocumentSignature(sds);
			initSession.setSessionParameters(sp);
			String updatedInitSession = gson.toJson(initSession);
			Response response = rest.getinitSessionResponse(updatedInitSession);
			rest.verifyInitSessionError(response);
		}

	}
}
