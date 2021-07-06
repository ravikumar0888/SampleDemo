package datamanagement;

import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.text.PDFTextStripper;

/*
 * @author A687416 Function to validate the data from pdf file.
 *
 */
public class PdfManager {
	private static Logger logger = LogManager.getLogger(PdfManager.class.getName());

	String base64EncodedPDFtoString(String base64String) throws IOException {
		InputStream is = new ByteArrayInputStream(base64String.getBytes());
		InputStream decoded = java.util.Base64.getDecoder().wrap(is);

		PDDocument doc = PDDocument.load(decoded);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String decodedString = pdfStripper.getText(doc);
		doc.close();
		return decodedString;
	}

	public boolean compareBase64EncodedPDFText(String signedPDF, String actualPDF) throws IOException {
		String decodedSignedpdf = base64EncodedPDFtoString(signedPDF);
		logger.info("decoded signed PDF is {}", decodedSignedpdf);
		String decodedactualpdf = base64EncodedPDFtoString(actualPDF);
		logger.info("decoded actual PDF is {}", decodedactualpdf);
		return decodedSignedpdf.equals(decodedactualpdf);

	}

	public static String getDownloadsDirectoryPath() {
		return System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
	}

	public File getFileNameAndRename(String name) throws URISyntaxException {

		logger.info("The downloaded path is {}", getDownloadsDirectoryPath());

		File getLatestFile = getLatestFilefromDir(getDownloadsDirectoryPath());
		logger.info("The latest downloaded file is {}", getLatestFile);

		File backupFile = new File(getDownloadsDirectoryPath() + File.separator + name + ".pdf");

		logger.info("The file will be renamed as {}", backupFile);
		if (backupFile.exists()) {
			backupFile.delete();
		}
		if (!getLatestFile.renameTo(backupFile)) {
			logger.error("failed to rename the file.");
			return getLatestFile;
		}
		return backupFile;
	}

	public File getLatestFilefromDir(String downloadFilepath) throws URISyntaxException {
		File dir = new File(downloadFilepath);
		File[] files = dir.listFiles();

		if (files == null || files.length == 0) {
			logger.error("Number of files in directory = {} no files found in download directory {}", files.length, downloadFilepath);
			return null;
		}
		for (File file : files) {
			logger.info("Files in the downloaded directory are {}", file);
		}
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	public boolean isPDFSigned(File signedFile) throws IOException {
		PDDocument document = PDDocument.load(signedFile);
		List<PDSignature> signatureDictionaries = document.getSignatureDictionaries();
		return !signatureDictionaries.isEmpty();

	}

	public void verifyPDFContent(File file, String reqTextInPDF) throws IOException {

		PDDocument doc = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(doc);
		doc.close();
		logger.info(text);
		assertTrue(text.contains(reqTextInPDF));

	}
}
