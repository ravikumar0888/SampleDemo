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

public class MandateGeneratePDFByRestNegativeTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateGeneratePDFByRestNegativeTest.class.getName());

	@Description("Negative test for Generate mandate PDF endpont with REST API")
	@Test(description = "Negative test for Generate mandate PDF endpont with REST API")

	public void MandateGeneratePDFByRestNegative() throws InterruptedException {
		RestEndPoints rest = new RestEndPoints();

		String generateMandatePDFJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateGeneratePDFByRestTest");
		JsonObject generateMandatePDFjsonObject = JsonParser.parseString(generateMandatePDFJson).getAsJsonObject();

		Response generateMandatePDFResp = rest.spsGetCall(generateMandatePDFjsonObject.toString(),
				"mandate-management/mandate/pdf");
		assertEquals(generateMandatePDFResp.statusCode(), 400, generateMandatePDFResp.asString());
		assertEquals(generateMandatePDFResp.jsonPath().getString("response.stsCd"), "MDT001",
				generateMandatePDFResp.asString());

	}
}