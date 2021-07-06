package documentSignatureTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import pagesSpsSign.SignHomePage;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import pagesSpsSign.SpsSignBackOfficePage;
import testbase.BasePage;
import testdataobjects.BackOfficeInitSession;

public class SMoneyWithoutPostalAddrTest extends BasePage {
	static Logger logger = LogManager.getLogger(SMoneyWithoutPostalAddrTest.class.getName());

	@Test(enabled = true)
	public void sMoneyWithoutPostalAddress() {

		SpsSignBackOfficePage signback = new SpsSignBackOfficePage();
		
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();
		SignHomePage shp = new SignHomePage();
	
		
		shp.signOldbackOfficeURLLaunch();

		LinkedTreeMap<String, ?> initSessionData = JsonManager.getMapfromJson("testdata/backOfficeinitSession.json", "SMoneyWithoutPostalAddrTest");
		BackOfficeInitSession backofficeSession = new BackOfficeInitSession(initSessionData);

		BackOfficeInitSession updatedBkoffInitSession = signback.SMoney_CustomerSessionCreateWithoutPostal(backofficeSession);

		signStep1.fillingPostalAddress();
		signStep2.dsStep2GetOTP(updatedBkoffInitSession, updatedBkoffInitSession.getTranscationId());
		signStep3.documentSignatureStep3();

		//	signStep3.documentSignatureStep3();

	}
}
