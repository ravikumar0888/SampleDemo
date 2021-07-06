package pagesSpsSign;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.ConfigreaderUtils;
import datamanagement.FileManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testbase.BasePage;

public class RestEndPoints extends BasePage {
	private static String baseUriAngular = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/ws";

	//ConfigreaderUtils.getConfig("environment." + environment + ".SSOPassword");

	private static String basePathAngular = "/rest/sessions";
	private static String baseUriBackoffice = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/backoffice";
	private static String basePathBackoffice = "/api/";
	String keystorepwd = "creditorA";

	static Logger logger = LogManager.getLogger(RestEndPoints.class.getName());
	private RequestSpecification getRestAssuredSpec(String baseURI, String basePath) {

		RestAssuredConfig connectionConfig = new RestAssuredConfig();
		connectionConfig.connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponse());
		// to close idle connections as a remedy to org.apache.http.NoHttpResponseException


		return new RequestSpecBuilder().setBaseUri(baseURI).setBasePath(basePath).setConfig(connectionConfig)
				.setConfig(getRestAssuredSSLConfig())

				
			
		
				 .setProxy(BasePage.browser.contains("local")? "proxy-fr.glb.my-it-solutions.net":"proxy-prod-scl.svc.meshcore.net", BasePage.browser.contains("local")? 84:3128) // comment when run on server
				
			/*	
			 * //	 .setProxy("proxy-fr.glb.my-it-solutions.net", 84)
					.setProxy("proxy-prod-scl.svc.meshcore.net", 3128) // Comment when run on local
*/				.build();

	}

	private static String otpbaseUriAngular = "https://ssl-sps-sign-service.vm-rct.stg.as8677.net/";



	private static String otpbasePathAngular = "/testws/test/agreement";

	public String getOTPForDS(String creditorId, String transactionId) {

		Response response = given().spec(getRestAssuredSpec(otpbaseUriAngular, otpbasePathAngular)).header("Accept-Encoding", "gzip,deflate").contentType(ContentType.JSON).when().get("/" + creditorId + "/" + transactionId ).then().log().all().extract().response();

		assertEquals(response.statusCode(), 200, response.jsonPath().getString("code"));
		logger.info("Session status is: {}", response.asString());
		logger.info("OTP is " + response.jsonPath().getString("otpCode") );
		return response.jsonPath().getString("otpCode");
	}



	public String deleteSessionAndVerify(String creditorId, String transactionId) {

		Response response = given().spec(getRestAssuredSpec(baseUriAngular,basePathAngular)).header("Accept-Encoding", "gzip,deflate").contentType(ContentType.JSON).when().delete("/" + creditorId + "/" + transactionId).then().log().all().extract().response();

		assertEquals(response.statusCode(), 200, response.jsonPath().getString("code"));
		assertEquals(response.jsonPath().getString("state"), "Cancelled");

		logger.info("Delete request response in string is: {}", response.asString());

		return "";
	}
	// Get mandate
	public String getArchivedMandateAndDocument(String creditorId, String transactionId) {
		String archiveWSBasePath = "/rest/documents";
		Response response = given().spec(getRestAssuredSpec(baseUriAngular,archiveWSBasePath)).header("Accept-Encoding", "gzip,deflate").contentType(ContentType.JSON).when().get("/" + creditorId + "/" + transactionId + "/").then().log().all().extract().response();
		logger.info("Session status is: {}", response.asString());
		assertEquals(response.statusCode(), 200);
		return response.jsonPath().getString("file");
	}

	public String getArchivedDocument(String creditorId, String transactionId, String documentId) {
		String archiveWSBasePath = "/rest/documents";
		Response response = given().spec(getRestAssuredSpec(baseUriAngular,archiveWSBasePath)).header("Accept-Encoding", "gzip,deflate").contentType(ContentType.JSON).when().get("/" + creditorId + "/" + transactionId + "/" + documentId + "/").then().log().all().extract().response();
		logger.info("Session status is: {}", response.asString());
		assertEquals(response.statusCode(), 200);
		return response.jsonPath().getString("document");
	}


	public Response getinitSessionResponse(String requestJson) {

		return given().spec(getRestAssuredSpec(baseUriAngular, basePathAngular)).header("Accept-Encoding", "gzip,deflate").contentType(ContentType.JSON).when().body(requestJson).post("/init-session").then().log().all().extract().response();
	}

	public String getinitSessionUrl(Response response) {

		assertEquals(response.statusCode(), 200, response.jsonPath().getString("code"));
		String url = response.jsonPath().getString("url");
		logger.info(response.asString());

		return url;
	}

	public RestAssuredConfig getRestAssuredSSLConfig() {

		System.setProperty("javax.net.debug", "all");
		System.setProperty("deployment.security.SSLv2Hello", "false");
		System.setProperty("deployment.security.SSLv3", "false");
		System.setProperty("deployment.security.TLSv1.1", "false");
		System.setProperty("deployment.security.TLSv1", "false");
		System.setProperty("deployment.security.TLSv1.2", "true");
		System.setProperty("https.protocols", "TLSv1.2");
		System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");

		return RestAssuredConfig.config().sslConfig(new SSLConfig().with().trustStoreType("JKS").trustStore("certs/cacerts.jks", "changeit").and().keystoreType("PKCS12").keyStore("certs/auth.p12", keystorepwd).and().allowAllHostnames());

	}

	public String getSessionStatus(String creditorId, String transactionId) {

		Response response = given().spec(getRestAssuredSpec(baseUriAngular, basePathAngular)).header("Accept-Encoding", "gzip,deflate").contentType(ContentType.JSON).when().get("/" + creditorId + "/" + transactionId + "/status").then().log().all().extract().response();

		assertEquals(response.statusCode(), 200, response.jsonPath().getString("code"));
		logger.info("Session status is: {}", response.asString());

		return response.jsonPath().getString("state");
	}

	public void verifyInitSessionError(Response response) {

		assertEquals(response.statusCode(), 400);
		assertEquals(response.jsonPath().getString("code"), "SIGN_ERR_022");
		logger.info(response.asString());

	}

	public String getSignCreditor(String signCreditorIdEncoded) {

		Response response = given().spec(getRestAssuredSpec(baseUriBackoffice, basePathBackoffice)).auth()
				.basic("spsSignAdmin", "spsSignAdmin").header("Accept-Encoding", "gzip,deflate")
				.contentType(ContentType.JSON).when().get("/creditors/{encodedId}", signCreditorIdEncoded).then().log()
				.all().extract().response();

		return response.body().asString();
	}

	public Response putSignCreditor(String signCreditorIdEncoded, String CreditorJson) {

		return given().spec(getRestAssuredSpec(baseUriBackoffice, basePathBackoffice)).auth()
				.basic("spsSignAdmin", "spsSignAdmin").header("Accept-Encoding", "gzip, deflate, br")
				.header("Cache-Control", "no-cache").header("Content-Type", "text/plain").body(CreditorJson)
				.contentType("application/json;charset=UTF-8").log().everything().when()
				.put("/creditors/{encodedId}", signCreditorIdEncoded).then().log().all().extract().response();

	}

	public Response soapCallSps(String endpoint, String xmlfile) throws FileNotFoundException, IOException {
		//String soapSpsBaseURI = "https://ssl-product-creditor-web.vm-rct.stg.as8677.net/";

		String soapSpsBaseURI = ConfigreaderUtils.getConfig("environment." + environment + ".soapBaseURL");
		String soapSpsBasePath = "ws/services/";

		return given().spec(getRestAssuredSpec(soapSpsBaseURI, soapSpsBasePath)).auth().basic("atos", "atosatos")
				.header("Accept-Encoding", "gzip, deflate, br").header("Cache-Control", "no-cache")
				.header("Content-Type", "text/xml").body(xmlfile).contentType("application/json;charset=UTF-8").log()
				.everything().when().put(endpoint).then().log().all().extract().response();

	}

	public String enrichRequestwithParameters(String request, Properties properties) {
		Set<String> keys = properties.stringPropertyNames();
		for (String key : keys) {
			String sb = "${" + key + "}";
			String request2 = request;
			if (request2.contains(sb)) {
				request = request2.replaceAll(Pattern.quote(sb), properties.getProperty(key));
			}
		}
		return request;

	}

	public Response soapCallSpsSign(String requestXmlFile, String endPoint, String SoapAction) throws FileNotFoundException, IOException {
		//	String soapSpsBaseURI = "https://ssl-sps-rct-sign-service.aw.atos.net/";soapcall
		String soapSpsBaseURI = ConfigreaderUtils.getConfig("environment." + environment + ".soapcallURL");
		String soapSpsBasePath = endPoint.isEmpty() ? "ws/services/SPSSignature" : ("ws/services/" + endPoint);
		String defaultSoapAction = "sps.awl.net/initSession";
		return given().spec(getRestAssuredSpec(soapSpsBaseURI, soapSpsBasePath))
				.header("Accept-Encoding", "gzip, deflate, br").header("Cache-Control", "no-cache")
				.header("Content-Type", "text/xml").header("SOAPAction", SoapAction== null?defaultSoapAction:SoapAction).body(requestXmlFile).contentType("application/json;charset=UTF-8")
				.log().everything().when().post().then().log().all().extract().response();

	}

	public Response spsPostCall(String requestJson, String endPoint) {
		//	String restSpsBaseURI = "https://ssl-product-creditor-web.vm-rct.stg.as8677.net/";

		String restSpsBaseURI=ConfigreaderUtils.getConfig("environment." + environment + ".restBaseURL");
		String restSpsBasePath = endPoint.isEmpty() ? "ws/services/rest/" : ("ws/services/rest/" + endPoint);

		return given().spec(getRestAssuredSpec(restSpsBaseURI, restSpsBasePath)).auth().basic("atos", "atosatos")
				.header("Accept-Encoding", "gzip, deflate, br").header("Cache-Control", "no-cache")
				.header("Content-Type", "text/plain").body(requestJson).contentType("application/json;charset=UTF-8")
				.log().everything().when().post().then().log().all().extract().response();

	}

	public Response spsPutCall(String requestJson, String endPoint) {
		//	String restSpsBaseURI = "https://ssl-product-creditor-web.vm-rct.stg.as8677.net/";

		String restSpsBaseURI=ConfigreaderUtils.getConfig("environment." + environment + ".restBaseURL");
		String restSpsBasePath = endPoint.isEmpty() ? "ws/services/rest/" : ("ws/services/rest/" + endPoint);

		return given().spec(getRestAssuredSpec(restSpsBaseURI, restSpsBasePath)).auth().basic("atos", "atosatos")
				.header("Accept-Encoding", "gzip, deflate, br").header("Cache-Control", "no-cache")
				.header("Content-Type", "text/plain").body(requestJson).contentType("application/json;charset=UTF-8")
				.log().everything().when().put().then().log().all().extract().response();

	}

	public Response spsGetCall(String requestJson, String endPoint) {
		//	String restSpsBaseURI = "https://ssl-product-creditor-web.vm-rct.stg.as8677.net/";
		String restSpsBaseURI=ConfigreaderUtils.getConfig("environment." + environment + ".restBaseURL");
		String restSpsBasePath = endPoint.isEmpty() ? "ws/services/rest/" : ("ws/services/rest/" + endPoint);

		String username =ConfigreaderUtils.getConfig("environment." + environment + ".soapUsername");
		String password = ConfigreaderUtils.getConfig("environment." + environment + ".soapPassword");


		return given().spec(getRestAssuredSpec(restSpsBaseURI, restSpsBasePath)).auth().basic(username, password)
				.header("Accept-Encoding", "gzip, deflate, br").header("Cache-Control", "no-cache")
				.header("Content-Type", "text/plain").body(requestJson).contentType("application/json;charset=UTF-8")
				.log().everything().when().get().then().log().all().extract().response();

	}

}
