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
import spspages.LoginPage;
import spspages.MandatePage;
import testbase.BasePage;
import utilies.GenericFunctions;

public class MandateCreationSecuredByRestWSTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCreationSecuredByRestWSTest.class.getName());

	@Description("To Create a secure mandate with REST API")
	@Test(description = "To Create a secure mandate with REST API")

	public void mandateCreationSecuredByRestWS() throws InterruptedException {
		RestEndPoints rest = new RestEndPoints();
		GenericFunctions gf = new GenericFunctions();
		String transId = gf.randamGenerator();
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		String creditor = "zTestPSE";
		String createMandateJson = JsonManager.getStringfromJson("testdata/restRequests.json",
				"MandateCreationSecuredByRestWSTest");

		JsonObject jsonObject = JsonParser.parseString(createMandateJson).getAsJsonObject();

		jsonObject.getAsJsonObject("mandateInfoParameters").getAsJsonObject("mandateReferenceParameters")
				.addProperty("umr", "UMR" + transId);
		jsonObject.getAsJsonObject("mandateInfoParameters").getAsJsonObject("mandateReferenceParameters")
				.addProperty("uir", "UIR" + transId);

		Response resp = rest.spsPostCall(jsonObject.toString(), "mandate-management/mandate");
		assertEquals(resp.statusCode(), 200, resp.asString());
		assertEquals(resp.jsonPath().getString("response.stsInf"), "OK", resp.asString());

		login.loginIntoSPS("default");
		mp.selectMandateMangement();

		mp.searchUMR("UMR" + transId, creditor);
		login.logout();

		// Response response = rest.getinitSessionResponse(updatedInitSession);
	}
}