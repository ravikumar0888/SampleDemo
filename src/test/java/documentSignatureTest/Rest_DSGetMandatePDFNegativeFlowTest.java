package documentSignatureTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import spspages.LoginPage;
import spspages.MandatePage;
import testbase.BasePage;
import testdataobjects.InitSession;
import testdataobjects.initsession.MandateParameters;
import testdataobjects.initsession.SessionDocumentSignature;
import testdataobjects.initsession.SessionParameters;
import utilies.GenericFunctions;


public class Rest_DSGetMandatePDFNegativeFlowTest extends BasePage {
	static Logger logger = LogManager.getLogger(DSArchiveDocumentTypeNoneTest.class.getName());
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	/*@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}
*/


	@Test
	public void Rest_DSGetMandatePDFNegativeFlow() throws IOException {

	Gson gson = new Gson();
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints rest = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();

	
		// rest 
		String creditor = "ztestDoS";
		String createMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"GetMandatewithSCI");

		JsonObject jsonObject = JsonParser.parseString(createMandateJson).getAsJsonObject();

		jsonObject.getAsJsonObject("mandateParameters").getAsJsonObject("nciParameters")
		.addProperty("nci", "2345678");




		jsonObject.getAsJsonObject("mandateParameters").addProperty("umr","Abc");
		jsonObject.getAsJsonObject("mandateParameters").addProperty("uir","PQR");

		Response resp1 = rest.spsGetCall(jsonObject.toString(), "mandate-management/mandate/pdf");
		assertEquals(resp1.statusCode(), 400, resp1.asString());
			assertEquals(resp1.jsonPath().getString("response.stsInf"), "The mandate was not found.", resp1.asString());


	/*	RestEndPoints rest = new RestEndPoints();
		GenericFunctions gf = new GenericFunctions();
		String transId = gf.randamGenerator();
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		String creditor = "zTestPSE";
		String createMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateCreationByRestTest");

		JsonObject jsonObject = JsonParser.parseString(createMandateJson).getAsJsonObject();

		jsonObject.getAsJsonObject("mandateInfoParameters").getAsJsonObject("mandateReferenceParameters")
				.addProperty("umr", "UMR" + transId);
		jsonObject.getAsJsonObject("mandateInfoParameters").getAsJsonObject("mandateReferenceParameters")
				.addProperty("uir", "UIR" + transId);

		Response resp = rest.spsPostCall(jsonObject.toString(), "mandate-management/mandate");
		assertEquals(resp.statusCode(), 200, resp.asString());
		assertEquals(resp.jsonPath().getString("response.stsInf"), "OK", resp.asString());
*/
		
		// create mandate and get docu
		
		
	/*	
		String createMandateJson1 = JsonManager.getStringfromJson("testdata/restRequests.json",
				"GetMandatewithSCI");

		JsonObject jsonObject1 = JsonParser.parseString(createMandateJson1).getAsJsonObject();

		jsonObject1.getAsJsonObject("mandateParameters").getAsJsonObject("nciParameters")
		.addProperty("nci","2345678");




		jsonObject1.getAsJsonObject("mandateParameters").addProperty("umr","UMR151209");
		jsonObject1.getAsJsonObject("mandateParameters").addProperty("uir", "UIR151209");

		Response resp1 = rest.spsGetCall(jsonObject1.toString(), "mandate-management/mandate/pdf");
		assertEquals(resp1.statusCode(), 200, resp1.asString());
		//	assertEquals(resp.jsonPath().getString("response.stsInf"), "The mandate has been generated into PDF", resp.asString());
		


*/


	}

}
