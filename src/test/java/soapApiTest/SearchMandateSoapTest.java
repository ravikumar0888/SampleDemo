package soapApiTest;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import testbase.BasePage;

public class SearchMandateSoapTest extends BasePage {
	static Logger logger = LogManager.getLogger(SearchMandateSoapTest.class.getName());

	public SearchMandateSoapTest() {
		super();
	}

	@Test

	public void soapdemo() throws InterruptedException, FileNotFoundException, IOException {
		RestEndPoints rep = new RestEndPoints();

		Response response = rep.soapCallSps("Mandate", "soaprequests/searchMandate.xml");
 
		assertEquals(response.statusCode(), 200, "soap requst failed");
		XmlPath xmlpath = new XmlPath(response.asString());
		assertEquals(xmlpath.getString("Envelope.Body.SearchMandateResponse.Rspns.StsCd"),"GNL000");
	}

}

//Todo file upload and data validation with PDf 