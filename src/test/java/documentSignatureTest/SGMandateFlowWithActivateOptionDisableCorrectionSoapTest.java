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
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import testbase.BasePage;
import testdataobjects.BackOfficeInitSession;
import utilies.GenericFunctions;

public class SGMandateFlowWithActivateOptionDisableCorrectionSoapTest extends BasePage {
	static Logger logger = LogManager
			.getLogger(SGMandateFlowWithActivateOptionDisableCorrectionSoapTest.class.getName());
	String initialCreditorSettings;
	String encodedCreditorId = "DA";

	@AfterMethod
	void restoreCreditorConfig() {
		RestEndPoints rest = new RestEndPoints();
		Response resp = rest.putSignCreditor(encodedCreditorId, initialCreditorSettings);
		assertEquals(resp.statusCode(), 200, resp.asString());
	}

	@Test
	public void SGMandateFlowWithActivateOptionDisableCorrection() throws KeyStoreException, WSSecurityException,
			SAXException, IOException, ParserConfigurationException, TransformerException {

		RestEndPoints soap = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();
		GenericFunctions gf = new GenericFunctions();
		String transId = gf.randamGenerator();
		String creditorID = null, inputXml;

		RestEndPoints rest = new RestEndPoints();
		initialCreditorSettings = rest.getSignCreditor(encodedCreditorId);
		String creditorSettingForTest = JsonManager.getStringfromJson("testdata/signCreditors.json",
				"SGMandateFlowWithActivateOptionDisableCorrectionSoapTest");
		Response resp = rest.putSignCreditor(encodedCreditorId, creditorSettingForTest);
		assertEquals(resp.statusCode(), 200, resp.asString());
		
		// using same test data as SGMandateFlowWithActivateOptionTrueSoapTest
		Document doc = XMLManager.getXmlDocument("soaprequests/SGMandateFlowWithActivateOptionDisableCorrectionSoapTest.xml");

		// Replace transaction id etc.
		doc.getElementsByTagName("urn1:transactionId").item(0).setTextContent(transId);
		creditorID = doc.getElementsByTagName("urn1:creditorId").item(0).getTextContent();

		Document signedDoc = XMLManager.buildSignedRequest(doc);
		inputXml = XMLManager.docToStr(signedDoc);

		Response response = soap.soapCallSpsSign(inputXml, "SPSSignature", null);
		assertEquals(response.statusCode(), 200, "soap requst failed");
		String URL = XMLManager.getXmlElementsAsString(response.asString(), "Envelope.Body.InitSessionResponse.url");

		navigateToUrl(URL);

		LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/backOfficeinitSession.json",
				"sessionParametersForSG");
		BackOfficeInitSession backofficeSession = new BackOfficeInitSession(mandate);

		signStep1.fillingIbanEmailAndPhone(backofficeSession);
		signStep2.dsStep2GetOTPFOREmail(backofficeSession, transId);
		signStep3.documentSignatureStep3();
	}
}
