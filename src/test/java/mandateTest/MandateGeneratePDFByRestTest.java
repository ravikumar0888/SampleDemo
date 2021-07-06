package mandateTest;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import datamanagement.JsonManager;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import testbase.BasePage;
import utilies.GenericFunctions;

public class MandateGeneratePDFByRestTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateGeneratePDFByRestTest.class.getName());

	@Description("To Generate PDF of a mandate with REST API")
	@Test(description = "To Generate PDF of a mandate with REST API")

	public void MandateGeneratePDFByRest() throws InterruptedException {
		RestEndPoints rest = new RestEndPoints();
		GenericFunctions gf = new GenericFunctions();
		String transId = gf.randamGenerator();
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

		String generateMandatePDFJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateGeneratePDFByRestTest");
		JsonObject generateMandatePDFjsonObject = JsonParser.parseString(generateMandatePDFJson).getAsJsonObject();

		generateMandatePDFjsonObject.getAsJsonObject("mandateParameters").addProperty("umr", "UMR" + transId);
		generateMandatePDFjsonObject.getAsJsonObject("mandateParameters").addProperty("uir", "UIR" + transId);
		Response generateMandatePDFResp = rest.spsGetCall(generateMandatePDFjsonObject.toString(),
				"mandate-management/mandate/pdf");
		assertEquals(generateMandatePDFResp.statusCode(), 200, generateMandatePDFResp.asString());
		assertEquals(generateMandatePDFResp.jsonPath().getString("response.stsInf"),
				"The mandate has been generated into PDF", generateMandatePDFResp.asString());

	}
}