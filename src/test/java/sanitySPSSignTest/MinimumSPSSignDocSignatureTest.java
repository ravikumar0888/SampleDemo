package sanitySPSSignTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import datamanagement.FileManager;
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

public class MinimumSPSSignDocSignatureTest extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumSPSSignDocSignatureTest.class.getName());

	@Test(enabled = false)
	public void testSPSSignDocSignature() {
		// System.setProperty("https.protocols", "TLSv1");
		Gson gson = new Gson();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();

		String transId = gf.randamGenerator();
		String requestJson = FileManager.readFile("testdata/initSession.json");
		InitSession initSession = gson.fromJson(requestJson, testdataobjects.InitSession.class);

		SessionParameters sp = initSession.getSessionParameters();
		sp.setTransactionId(transId);
		SessionDocumentSignature sds = sp.getSessionDocumentSignature();
		sds.setDocumentId(transId);
		sds.setDocumentName(transId);
		sp.setSessionDocumentSignature(sds);
		initSession.setSessionParameters(sp);

		String updatedInitSession = gson.toJson(initSession);
		Response response = rest.getinitSessionResponse(updatedInitSession);
		String url = rest.getinitSessionUrl(response);
		navigateToUrl(url);

		signStep1.documentSignatureStep1(true, false);
		signStep2.documentSignatureStep2(initSession);
		signStep3.documentSignatureStep3();

	}
}
