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

public class MandateCancellationByRestNegativeCase1Test extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCancellationByRestNegativeCase1Test.class.getName());

	@Description("To cancel an already cancelled mandate with REST API")
	@Test(description = "To cancel an already cancelled mandate with REST API")

	public void mandateCancellationByRestNegativeCase1() throws InterruptedException {
		RestEndPoints rest = new RestEndPoints();

		String cancelMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateCancellationByRestNegativeCase1Test");
		JsonObject canceljsonObject = JsonParser.parseString(cancelMandateJson).getAsJsonObject();

		Response cancelResp = rest.spsPutCall(canceljsonObject.toString(), "mandate-management/mandate/cancel");
		assertEquals(cancelResp.statusCode(), 400, cancelResp.asString());
		assertEquals(cancelResp.jsonPath().getString("response.stsCd"), "MDT079", cancelResp.asString());

	}
}