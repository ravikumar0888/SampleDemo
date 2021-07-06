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

public class MandateCancellationByRestTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCancellationByRestTest.class.getName());

	@Description("To cancel a mandate with REST API")
	@Test(description = "To cancel a mandate with REST API")

	public void MandateCancellationByRest() throws InterruptedException {
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

		String cancelMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateCancellationByRestTest");
		JsonObject canceljsonObject = JsonParser.parseString(cancelMandateJson).getAsJsonObject();

		canceljsonObject.getAsJsonObject("mandateParameters").addProperty("umr", "UMR" + transId);
		canceljsonObject.getAsJsonObject("mandateParameters").addProperty("uir", "UIR" + transId);
		Response cancelResp = rest.spsPutCall(canceljsonObject.toString(), "mandate-management/mandate/cancel");
		assertEquals(cancelResp.statusCode(), 200, cancelResp.asString());
		assertEquals(cancelResp.jsonPath().getString("response.stsInf"), "OK", cancelResp.asString());

	}
}