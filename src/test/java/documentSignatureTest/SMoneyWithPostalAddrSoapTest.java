package documentSignatureTest;

import static org.testng.Assert.assertEquals;

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
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import testbase.BasePage;
import utilies.GenericFunctions;

public class SMoneyWithPostalAddrSoapTest extends BasePage {
	static Logger logger = LogManager.getLogger(SMoneyWithPostalAddrSoapTest.class.getName());

	@Test(enabled = true)
	public void SMoneyWithPostalAddrSoap() throws ParserConfigurationException, SAXException, IOException,
			TransformerException, URISyntaxException, KeyStoreException, WSSecurityException {

		RestEndPoints soap = new RestEndPoints();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();
		GenericFunctions gf = new GenericFunctions();
		String transId = gf.randamGenerator();
		String creditorID = null, inputXml;

		Document doc = XMLManager.getXmlDocument("soaprequests/SMoneyWithPostalAddrSoapTest.xml");

		// Replace transaction id etc.
		doc.getElementsByTagName("urn1:transactionId").item(0).setTextContent(transId);
		creditorID = doc.getElementsByTagName("urn1:creditorId").item(0).getTextContent();

		Document signedDoc = XMLManager.buildSignedRequest(doc);
		inputXml = XMLManager.docToStr(signedDoc);

		Response response = soap.soapCallSpsSign(inputXml, "SPSSignature", null);
		assertEquals(response.statusCode(), 200, "soap requst failed");
		String URL = XMLManager.getXmlElementsAsString(response.asString(), "Envelope.Body.InitSessionResponse.url");
		
		navigateToUrl(URL);

		signStep1.confirmStep1(false, true);
		signStep2.step2FillOtp("SMS", creditorID, transId);
		signStep3.documentSignatureStep3();

		// signStep3.documentSignatureStep3();

	}
}
