package documentSignatureTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import datamanagement.PdfManager;
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


public class Rest_DSGetMandateAndDocumentTest extends BasePage {
	static Logger logger = LogManager.getLogger(Rest_DSGetMandateAndDocumentTest.class.getName());
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}


	
	@Test
	public void Rest_DSGetMandateAndDocument() throws IOException {

		Gson gson = new Gson();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();
		//set creditor config
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json", "default");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());
		String transId = gf.randamGenerator();
		String paymentType = "MANDATE";
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

		logger.info("Response is " + updatedInitSession);

		Response response = rest.getinitSessionResponse(updatedInitSession);
		String url = rest.getinitSessionUrl(response);

		navigateToUrl(url);
		signStep1.documentSignatureStep1(true, true); // first parameter for DS and second Mandate
		signStep2.signSepaMandateAndDocumentSignature(initSession);
		signStep3.subscribeSepaMandateAndDocumentSingnature();

	
		
		
		
		
	//	String abc= rest.getArchivedDocument("ztestDoS",transId,transId);
		
		
		String abc= rest.getArchivedMandateAndDocument("ztestDoS",transId);
		
		//{{baseUrl}}/rest/documents/:creditorId/:transactionId/:documentId/
	
		System.out.println("the output is "+abc);
	/**/
		
}

}
