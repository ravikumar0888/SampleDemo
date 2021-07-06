package sanitySPSSignTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import pagesSpsSign.ParametersSupportPage;
import pagesSpsSign.SignHomePage;
import testbase.BasePage;

public class MinimumSPSSignConnectionTest extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumSPSSignConnectionTest.class.getName());

	@Test
	public void testSPSConnections() {
		SignHomePage shp = new SignHomePage();
		ParametersSupportPage psp = new ParametersSupportPage();
		shp.signHomepageLaunch();
		shp.navigateToMenu("Support page", "Parameters");
		psp.checkConnectionsWithSPS();
	}

}
