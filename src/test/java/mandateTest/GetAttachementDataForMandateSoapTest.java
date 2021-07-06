package mandateTest;

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

import datamanagement.FileManager;
import datamanagement.JsonManager;
import datamanagement.XMLManager;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;
import spspages.FileSupervisionPage;
import spspages.LoginPage;
import testbase.BasePage;
import testdataobjects.FileSupervision;
import utilies.GenericFunctions;
import utilies.UserActions;

public class GetAttachementDataForMandateSoapTest extends BasePage {
	static Logger logger = LogManager.getLogger(GetAttachementDataForMandateSoapTest.class.getName());
	String inputXml;
	
	
	@Test
	public void GetAttachementDataForMandateSoap() throws InterruptedException, SAXException, IOException, ParserConfigurationException, KeyStoreException, WSSecurityException, TransformerException 
	{
		RestEndPoints soap = new RestEndPoints();
		
		Document doc = XMLManager.getXmlDocument("soaprequests/GetAttachementDataForMandate.xml");

		// Replace transaction id etc.
		doc.getElementsByTagName("urn2:SCI").item(0).setTextContent("FR10ZZZ2345678");
		doc.getElementsByTagName("urn2:UMR").item(0).setTextContent("MANDATE-REFERENCE000000000000000733");

		inputXml = XMLManager.docToStr(doc);

		
		Response response = soap.soapCallSps("Mandate",inputXml);
		
		System.out.println("the response is "+response);
		assertEquals(response.statusCode(), 200, "soap requst failed");
		String data = XMLManager.getXmlElementsAsString(response.asString(),"Content");
		
		System.out.println("File is :-"+data);

	}

}
