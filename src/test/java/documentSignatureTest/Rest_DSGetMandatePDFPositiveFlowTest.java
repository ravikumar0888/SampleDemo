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
import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandatePage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import testbase.BasePage;
import testdataobjects.InitSession;
import testdataobjects.MandatesSearch;
import testdataobjects.initsession.MandateParameters;
import testdataobjects.initsession.SessionDocumentSignature;
import testdataobjects.initsession.SessionParameters;
import utilies.GenericFunctions;


public class Rest_DSGetMandatePDFPositiveFlowTest extends BasePage {
	static Logger logger = LogManager.getLogger(DSArchiveDocumentTypeNoneTest.class.getName());
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerchRest");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	@Test
	public void Rest_DSGetMandatePDFPositiveFlow() throws IOException, InterruptedException {


		RestEndPoints rest = new RestEndPoints();
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();

		CommonSectionPage cs = new CommonSectionPage();

		login.loginIntoSPS("default");
		cs.selectMandateMangement();

		ms.searchActiveMandate(searchMandate);
		String umr = ms.getUMR();
		String uir = ms.getUIR();
		// rest 
		String createMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json","GetMandatewithSCI");

		JsonObject jsonObject = JsonParser.parseString(createMandateJson).getAsJsonObject();

		jsonObject.getAsJsonObject("mandateParameters").getAsJsonObject("nciParameters")
		.addProperty("nci", "2345678");

		jsonObject.getAsJsonObject("mandateParameters").addProperty("umr",umr);
		jsonObject.getAsJsonObject("mandateParameters").addProperty("uir",uir);

		Response resp = rest.spsGetCall(jsonObject.toString(), "mandate-management/mandate/pdf");
		assertEquals(resp.statusCode(), 200, resp.asString());
		assertEquals(resp.jsonPath().getString("response.stsInf"), "The mandate has been generated into PDF", resp.asString());

	}

}
