package documentSignatureTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.WSSecurityException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import datamanagement.XMLManager;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import testbase.BasePage;
import utilies.GenericFunctions;

public class GetSessionSoapSPSSignTest extends BasePage {
	static Logger logger = LogManager.getLogger(GetSessionSoapSPSSignTest.class.getName());

	@Test(enabled = true)
	public void sMoneyWithPostalAddress() throws ParserConfigurationException, SAXException, IOException,
			TransformerException, URISyntaxException, KeyStoreException, WSSecurityException {

		RestEndPoints soap = new RestEndPoints();

		GenericFunctions gf = new GenericFunctions();
		String transId = gf.randamGenerator();
		String creditorID = null, inputXml;

		Document doc = XMLManager.getXmlDocument("soaprequests/SMoneyWithPostalAddrSoapTest.xml");

		// Replace transaction id etc.
		doc.getElementsByTagName("urn1:transactionId").item(0).setTextContent(transId);
		creditorID = doc.getElementsByTagName("urn1:creditorId").item(0).getTextContent();

		Document signedDoc = XMLManager.buildSignedRequest(doc);
		inputXml = XMLManager.docToStr(signedDoc);
		logger.info(inputXml);

		Response response = soap.soapCallSpsSign(inputXml, "SPSSignature", null);
		logger.info(response.asString());
		assertEquals(response.statusCode(), 200, "soap requst failed");
		
		Document getSessionDoc = XMLManager.getXmlDocument("soaprequests/GetSessionSoapSPSSignTest.xml");

		// Replace transaction id etc.
		getSessionDoc.getElementsByTagName("urn1:transactionId").item(0).setTextContent(transId);
		getSessionDoc.getElementsByTagName("urn1:creditorId").item(0).setTextContent(creditorID);

		Document signedGetSesionDoc = XMLManager.buildSignedRequest(getSessionDoc);
		String inputXml1 = XMLManager.docToStr(signedGetSesionDoc);
		Response getSessionResponse = soap.soapCallSpsSign(inputXml1, "SPSSignatureUtils", "sps.awl.net/getSessionStatus");
		logger.info(getSessionResponse.asString());

		assertEquals(getSessionResponse.statusCode(), 200, "soap requst failed");
		assertTrue(getSessionResponse.asString().contains("Initialized"));

		
	}
}
