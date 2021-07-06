package soapApiTest;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import datamanagement.FileManager;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import pagesSpsSign.RestEndPoints;

public class ArchiveMandateSoapTest {
	static Logger logger = LogManager.getLogger(ArchiveMandateSoapTest.class.getName());
	RestEndPoints rep = new RestEndPoints();
	 

	@Test

	public void createMandateOK() throws FileNotFoundException, IOException   {
		
		String xmlfile = FileManager.readFile("soaprequests/ArchiveMandate_CreateMandateOK.xml");
		
		Response response = rep.soapCallSps("Mandate",xmlfile);
 
		assertEquals(response.statusCode(), 200, "soap requst failed");
		XmlPath xmlpath = new XmlPath(response.asString());
		assertEquals(xmlpath.getString("Envelope.Body.SearchMandateResponse.Rspns.StsCd"),"GNL000");
	}

}


