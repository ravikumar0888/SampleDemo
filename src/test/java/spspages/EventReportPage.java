package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import testbase.BasePage;
import testdataobjects.EventReport;
import testdataobjects.SCT;
import testdataobjects.SDD;
import utilies.GenericFunctions;
import utilies.UserActions;

public class EventReportPage extends BasePage 
{
	static Logger logger = LogManager.getLogger(EventReportPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();
	
	By txtEventReportName = By.xpath(".//*[@id='eventReportName']");
	By chkEmail =By.xpath(".//*[@id='sendingMailCheckbox']");
	By txtEmail = By.xpath(".//*[@id='emailAddress']");
	By chkFileEmpty = By.xpath(".//*[@id='sendingEmptyFileCheckbox']");
	
	
	By rdSingleEntity = By.xpath(".//*[@id='scopeAlone']");
	By ddSingleEntityValue = By.xpath(".//*[@id='scopeOrgaAlone']");
	
	
	
	By chkEventtypeCreation = By.id("eventCreationCheckbox");
	By chkEventtypeModification = By.id("eventModifCheckbox");
	By chkEventtypeStatusChange =By.id("eventStatusCheckbox");
	
	By chkEventtypeCreationSCT = By.id("eventCreationCheckbox_0");
	By chkEventtypeModificationSCT = By.id("eventModifCheckbox_0");
	By chkEventtypeStatusChangeSCT =By.id("eventStatusCheckbox_0");
	
	By multiTxtSDDStatus = By.id("eventSddStatus");
	By multitxtSCTStatus = By.id("eventSctStatus");
	// Select oSelect = new Select(driver.findElement(By.id(Element_ID)));
	//oSelect.selectByValue(value)
	
	By btnCreate = By.name("create");
	By ddBusinessObjedct= By.id("eventReportBusinessObject");
	public EventReport createEventReport(EventReport er,String businessObject )
	{
		action.getCurrentDateTime();
		String eventreport = "EventReport" + action.getCurrentDateTime();
		
		action.waitForPageToLoad();
		
		action.setValueInTextField(txtEventReportName, eventreport);
		
		Select oSelect = new Select(driver.findElement(ddBusinessObjedct));
		oSelect.selectByValue(businessObject);
		
		action.clickLink(chkEmail);
		action.setValueInTextField(txtEmail, er.getEmail());
		action.clickLink(chkFileEmpty);
	
		action.clickLink(rdSingleEntity);
		action.selectvaluesdropdown(ddSingleEntityValue, er.getEntity());
			
	
		if(businessObject =="SCT")
		{
		
		action.clickLink(chkEventtypeModificationSCT);
		action.clickLink(chkEventtypeCreationSCT);
		action.clickLink(chkEventtypeStatusChangeSCT);
		Select oSelect1 = new Select(driver.findElement(multitxtSCTStatus));
		oSelect1.selectByValue("CREATED");
		}
		else 
		{
			action.clickLink(chkEventtypeModification);
			action.clickLink(chkEventtypeCreation);
			action.clickLink(chkEventtypeStatusChange);
			Select oSelect1 = new Select(driver.findElement(multiTxtSDDStatus));
			oSelect1.selectByValue("CREATED");
		}
		
		
		
		
		action.clickLink(btnCreate);
		
		return er;
	}
	By ddlang = By.id("countrySwitcher");
	public void selectLanguage()
	{
		action.selectvaluesdropdown(ddlang,"English");
	}
	public void selectMenu(String menu, String subMenu) {
		By lblMenu = By.xpath(".//*[@id='pageMenu']/ul/li/a[contains(text(),'" + menu + "')]");
		By lblsubMenu = By.xpath(".//*[@id='pageMenu']/ul/ul/li/a[contains(text(),'" + subMenu + "')]");

		if (!driver.findElements(lblMenu).isEmpty()) {
			driver.findElement(lblMenu).click();
		}

		if (!driver.findElements(lblsubMenu).isEmpty()) {
			if (driver.findElement(lblsubMenu).isEnabled()) {
			
				driver.findElement(lblsubMenu).click();

				logger.info("SubMenu is Present {}", lblsubMenu);

			} else {

				logger.info("SubMenu is not found on the webpage");

			}
		} else {

			logger.info("No need to select sub menu option");
		}
	}
	
	By btnSearch = By.name("search");
	By btnGenerateReportExportIcon = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[4]/a[3]/img");
	By btnGenerateReport = By.id("generateSinceLast");
	public void eventReportGeneration()
	{
		action.waitForPageToLoad();
		action.waitForElementClickable(driver, btnSearch);
		action.clickLink(btnSearch);
		action.clickLink(btnGenerateReportExportIcon);
		action.clickLink(btnGenerateReport);
	
	}

	By iconTODownload = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[7]/a/img");
	By txtEventreportname = By.id("eventReportName");
	public void downloadGenerateFile(String value) throws InterruptedException
	{
		action.setValueInTextField(txtEventreportname, value);
		action.clickLink(btnSearch);
		action.clickLink(iconTODownload);
		
	Thread.sleep(100000); // application take time to generate the file
	}
	
	public void validatesdd(SDD sdd,String xmldata)
	{
		String endtoend =sdd.getEndToEndId();
		xmldata.contains(endtoend);
		
		String transId = sdd.getInitiatorTransactionReference();
		xmldata.contains(transId);
		
	}
	
	public void validatesct(SCT sct,String xmldata)
	{
		String endtoend =sct.getEndToEndId();
		xmldata.contains(endtoend);
		
		String transId = sct.getInitiatorTransactionReference();
		xmldata.contains(transId);
		
	}
	
	public void validateBlanksdd(String xmldata)
	{
		String blankSddData ="<Document xmlns=\'urn:awl:emd2:tech:xsd:emdd.file.eventsreports.001\'>\r\n" + 
				"</Document>";
		xmldata.contains(blankSddData);
	
	}
	By editEventReportBtn = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[4]/a[1]/img");
	
	By ddModelSDD = By.id("eventReportSddModel");
	
	
	By ddModelSCT = By.xpath("//*[@id='eventReportSctModel']");
	
	By btnUpdate = By.xpath(".//*[@id='updateButtons']/input");

	public void changeModelFormatSDD(String format) throws InterruptedException
	{
		action.clickLink(btnSearch);
		action.clickLink(editEventReportBtn);
		action.waitForPageToLoad();
		
		Select oSelect = new Select(driver.findElement(ddModelSDD));
		oSelect.selectByValue(format);
		action.clickLink(btnUpdate);
	}
	public void changeModelFormatSCT(String format) throws InterruptedException
	{
		action.clickLink(btnSearch);
		action.clickLink(editEventReportBtn);
		action.waitForPageToLoad();
		
		Select oSelect = new Select(driver.findElement(ddModelSCT));
		oSelect.selectByValue(format);
		action.clickLink(btnUpdate);
	}
}
