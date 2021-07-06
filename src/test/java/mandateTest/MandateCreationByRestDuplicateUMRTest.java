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

public class MandateCreationByRestDuplicateUMRTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCreationByRestDuplicateUMRTest.class.getName());

	@Description("Negative test case to check duplicate UMR is not allowed")
	@Test(description = "Negative test case to check duplicate UMR is not allowed")

	public void MandateCreationByRestDuplicateUMR() throws InterruptedException {
		RestEndPoints rest = new RestEndPoints();

		String createMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateCreationByRestDuplicateUMRTest");

		JsonObject jsonObject = JsonParser.parseString(createMandateJson).getAsJsonObject();

		Response resp = rest.spsPostCall(jsonObject.toString(), "mandate-management/mandate");
		assertEquals(resp.statusCode(), 400, resp.asString());
		assertEquals(resp.jsonPath().getString("response.stsCd"), "MDT015", resp.asString());

	}
}