
package testdataobjects;

import java.util.HashMap;
import java.util.Map;

import testdataobjects.initsession.CompanyInfos;
import testdataobjects.initsession.CreditorParameters;
import testdataobjects.initsession.MandateParameters;
import testdataobjects.initsession.SddParameters;
import testdataobjects.initsession.SessionParameters;
import testdataobjects.initsession.SignerInfos;

public class InitSession {

	private CompanyInfos companyInfos;
	private CreditorParameters creditorParameters;
	private MandateParameters mandateParameters;
	private SddParameters sddParameters;
	private SessionParameters sessionParameters;
	private SignerInfos signerInfos;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public CompanyInfos getCompanyInfos() {
		return companyInfos;
	}

	public CreditorParameters getCreditorParameters() {
		return creditorParameters;
	}

	public MandateParameters getMandateParameters() {
		return mandateParameters;
	}

	public SddParameters getSddParameters() {
		return sddParameters;
	}

	public SessionParameters getSessionParameters() {
		return sessionParameters;
	}

	public SignerInfos getSignerInfos() {
		return signerInfos;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setCompanyInfos(CompanyInfos companyInfos) {
		this.companyInfos = companyInfos;
	}

	public void setCreditorParameters(CreditorParameters creditorParameters) {
		this.creditorParameters = creditorParameters;
	}

	public void setMandateParameters(MandateParameters mandateParameters) {
		this.mandateParameters = mandateParameters;
	}

	public void setSddParameters(SddParameters sddParameters) {
		this.sddParameters = sddParameters;
	}

	public void setSessionParameters(SessionParameters sessionParameters) {
		this.sessionParameters = sessionParameters;
	}

	public void setSignerInfos(SignerInfos signerInfos) {
		this.signerInfos = signerInfos;
	}

}
