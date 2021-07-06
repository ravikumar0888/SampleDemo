package datamanagement;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.components.crypto.Merlin;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecSignature;
import org.apache.xml.security.signature.XMLSignature;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import config.ConfigreaderUtils;
import io.restassured.path.xml.XmlPath;
import testbase.BasePage;

/**
 * Methods related to XML File Management
 *
 * @author A708551
 *
 */
public class XMLManager extends BasePage {
	private static Logger logger = LogManager.getLogger(XMLManager.class.getName());
	static String keystorepwd = ConfigreaderUtils.getConfig("environment." + environment + ".keystorepwd");
			//"creditorA";

/*	public static Document getXmlDocument(String pathToXml)
			throws SAXException, IOException, ParserConfigurationException {
		// Read the xml of the test case
		ClassLoader classLoader = XMLManager.class.getClassLoader();
		String testDataFilePath = new File(classLoader.getResource(pathToXml).getFile()).getAbsolutePath();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		// Update the xml file with transaction id etc.
		DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
		return documentBuilder.parse(testDataFilePath);
	} 
	*/
	public static Document getXmlDocument(String pathToXml)
			throws SAXException, IOException, ParserConfigurationException {
		
		// Read the xml of the test case
	/*	ClassLoader classLoader = XMLManager.class.getClassLoader();
		File testDataFilePath = new File(classLoader.getResource(pathToXml).getFile());		//.getAbsolutePath();
	*/
		InputStream xmlfile = FileManager.readFileToInputStream(pathToXml); 
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		// Update the xml file with transaction id etc.
		DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
		return documentBuilder.parse(xmlfile);
	}
	
	

	/**
	 * @return signed document with security header
	 */
	public static Document buildSignedRequest(Document document) throws WSSecurityException, KeyStoreException {
		KeyStore keyStore = initKeyStore("certs/auth.p12");

		// Create and insert security headers
		WSSecHeader secHeader = new WSSecHeader();
		secHeader.insertSecurityHeader(document);

		// Create the object to compute the signature
		WSSecSignature sign = new WSSecSignature();
		String keyAlias = getPrivateKeyAlias(keyStore);

		sign.setDigestAlgo(DigestMethod.SHA1);
		sign.setSignatureAlgorithm(XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);
		sign.setUserInfo(keyAlias, keystorepwd);
		sign.setUseSingleCertificate(false);

		// Load the crypto
		sign.setKeyIdentifierType(WSConstants.X509_KEY_IDENTIFIER);
		Merlin merlin = new Merlin();
		merlin.setKeyStore(keyStore);

		X509Certificate certificate = (X509Certificate) keyStore.getCertificate(keyAlias);
		logger.info("Going to sign ORG request with Private Key {}", certificate.getSubjectDN().toString());

		// Compute the signature and return document
		return sign.build(document, merlin, secHeader);

	}

	/**
	 * @return the first alias for private key
	 */
	private static String getPrivateKeyAlias(KeyStore keyStore) throws KeyStoreException {

		String alias = null;
		for (Enumeration<String> e = keyStore.aliases(); e.hasMoreElements();) {
			System.out.println("Alias: " + e);
			String entry = e.nextElement();
			if (keyStore.isKeyEntry(entry)) {
				alias = entry;
				break;
			}
		}

		return alias;
	}

	private static KeyStore initKeyStore(String pathToKeyStore) {
		KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(FileManager.readFileToInputStream(pathToKeyStore), keystorepwd.toCharArray());
		} catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
			e.printStackTrace();
		}
		return keyStore;
	}

	public static String docToStr(Document doc) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();

		StreamResult result = new StreamResult(writer);
		transformer.transform(domSource, result);

		return writer.toString();

	}

	public static String getXmlElementsAsString(String xmlString, String xmlPath) {
		XmlPath xmlpath = new XmlPath(xmlString);
		return StringEscapeUtils.unescapeXml(xmlpath.getString(xmlPath));

	}

}
