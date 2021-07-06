package documentSignatureTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.security.KeyStoreException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.WSSecurityException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import datamanagement.XMLManager;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import pagesSpsSign.SpsSignBackOfficePage;
import testbase.BasePage;
import utilies.GenericFunctions;

public class MandateUpdateBackofficeDisabledSoapTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateUpdateBackofficeDisabledSoapTest.class.getName());

	LinkedTreeMap<String, String> formData = JsonManager.getMapfromJson("testdata/testdata.json",
			"MandateUpdateBackofficeTest");
	String initialCreditorSettings;
	final String encodedCreditorId = "ALg";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void mandateUpdateAllowedTest() throws SAXException, IOException, ParserConfigurationException,
			KeyStoreException, WSSecurityException, TransformerException {
		GenericFunctions gf = new GenericFunctions();
		RestEndPoints soap = new RestEndPoints();
		SpsSignBackOfficePage sbo = new SpsSignBackOfficePage();
		String transId = gf.randamGenerator();
		String creditorID = null, inputXml;

// set creditor config
		initialCreditorSettings = soap.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json",
				"MandateUpdateBackofficeDisabledTest");
		Response resp = soap.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());

		Document doc = XMLManager.getXmlDocument("soaprequests/MandateUpdateBackofficeSoapTest.xml");

// Replace transaction id etc.
		doc.getElementsByTagName("urn1:transactionId").item(0).setTextContent(transId);
		creditorID = doc.getElementsByTagName("urn1:creditorId").item(0).getTextContent();

		Document signedDoc = XMLManager.buildSignedRequest(doc);
		inputXml = XMLManager.docToStr(signedDoc);

		Response response = soap.soapCallSpsSign(inputXml, "SPSSignature", "sps.awl.net/initSessionForMandateUpdate");
		assertEquals(response.statusCode(), 200, "soap requst failed");
		assertEquals(XMLManager.getXmlElementsAsString(response.asString(), "Envelope.Body.InitSessionForMandateUpdateResponse.StsCd"),"SIGN_ERR_001");

	}

}
