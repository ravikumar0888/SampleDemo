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

public class MandateCancellationByRestNegativeCase2Test extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCancellationByRestNegativeCase2Test.class.getName());

	@Description("To cancel mandate with invalid json REST API")
	@Test(description = "To cancel mandate with invalid json REST API")

	public void MandateCancellationByRestNegativeCase2() throws InterruptedException {
		RestEndPoints rest = new RestEndPoints();

		String cancelMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateCancellationByRestNegativeCase2Test");
		JsonObject canceljsonObject = JsonParser.parseString(cancelMandateJson).getAsJsonObject();

		Response cancelResp = rest.spsPutCall(canceljsonObject.toString(), "mandate-management/mandate/cancel");
		assertEquals(cancelResp.statusCode(), 400, cancelResp.asString());
		assertEquals(cancelResp.jsonPath().getString("response.stsCd"), "FI003", cancelResp.asString());

	}
}