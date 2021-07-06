package pagesSpsSign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import datamanagement.PdfManager;
import testbase.BasePage;
import utilies.UserActions;

public class SignStep3Page extends BasePage {
	static Logger logger = LogManager.getLogger(SignStep3Page.class.getName());
	UserActions action = new UserActions();
	PdfManager pdfManager = new PdfManager();
	SignHomePage sh = new SignHomePage();

	// Step 2
	By btnDownloadDocument = By.id("dlpdf");
	By btnContinueMySubscription = By.name("buttonReturnCreditorSite");
	By btndownloadMandate = By.xpath("//div[@id='welcomeMandate']/following-sibling::div/a[@id='dlpdf']");
	By btnDownloadDocumentSignature = By.xpath("//div[@id='welcomeDocument']/following-sibling::div/a[@id='dlpdf']");

	public void documentSignatureStep3() {
		action.clickButton(btnDownloadDocument);
		action.waitForPageToLoad();
		verifyPDFSignature();
		action.waitForPageToLoad();
		action.clickButton(btnContinueMySubscription);
	}

	public void subscribeSepaMandateAndDocumentSingnature() {
		action.clickButton(btnDownloadDocumentSignature);
		verifyPDFSignature();

		action.clickButton(btndownloadMandate);
		verifyPDFSignature();
		action.clickButton(btnContinueMySubscription);
		action.waitForPageURL(getDriver(),sh.OKURL());
		action.validateURL(sh.OKURL());

	}

	public void verifyPDFSignature() {
		try {
			File downloadedFile = pdfManager.getLatestFilefromDir(PdfManager.getDownloadsDirectoryPath());
			assertTrue(pdfManager.isPDFSigned(downloadedFile), "Document is not signed!: ");
		} catch (URISyntaxException | IOException e) {

			e.printStackTrace();
		}
	}
	public void validateURL(String expectedURL) {
	
		action.waitForPageToLoad();
		String actualURL = action.getCurrentURL();
		logger.info("Current page title is {}", action.getTittle());
		assertEquals(actualURL, expectedURL);
	}
}
