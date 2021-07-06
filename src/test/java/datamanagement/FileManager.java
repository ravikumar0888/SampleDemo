package datamanagement;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.awaitility.Awaitility;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import utilies.GenericFunctions;

/**
 * Methods related to file Management
 *
 * @author A708551
 *
 */

public class FileManager {
	private static Logger logger = LogManager.getLogger(FileManager.class.getName());
	GenericFunctions gf = new GenericFunctions();
	/**
	 * Read a file and return content as string
	 *
	 * @param path
	 *            classpath of the string
	 * @return content of the read file
	 */
	public static String readFile(String path) {

		String content = null;
		try {

			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			content = CharStreams.toString(new InputStreamReader(in, Charsets.UTF_8));
		} catch (IOException e) {

			logger.error("Unable to read file " + e);
		}
		return content;

	}

	public static InputStream readFileToInputStream(String path) {

		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

	}

	/**
	 * Writes string content to a temporary file and returns absolute path of file
	 * 
	 * @param Content
	 *            Content of the file
	 * @param fileName
	 *            name of file without extension
	 * @param FileExtension
	 *            extension of the file
	 * @return Absolute path of temp file
	 */
	public static String writetempfile(String Content, String fileName, String FileExtension) {
		Path absPath = null;
		try {
			if (!FileExtension.contains(".")) {
				FileExtension = ".".concat(FileExtension);
			}
			final Path path = Files.createTempFile(fileName, FileExtension);

			// Writing data here
			byte[] buf = Content.getBytes(StandardCharsets.UTF_8);
			absPath = Files.write(path, buf);
			absPath = absPath.toAbsolutePath();
			// Delete file on exit
			// path.toFile().deleteOnExit();

		} catch (IOException e) {
			logger.error("Unable to read file " + e);
		}
		return absPath.toString();
	}

	public void waitForFileToExist(Path path) {
		Awaitility.await().atMost(1, TimeUnit.MINUTES).until(() -> {
			return path.toFile().exists();
		});
	}

	// Event report code 
	// Ravi Murai 
	public static String readXMLtoString(String path) throws IOException
	{
		// our XML file for this example
		File xmlFile = new File(path);

		// Let's get XML file as String using BufferedReader
		// FileReader uses platform's default character encoding
		// if you need to specify a different encoding, use InputStreamReader
		Reader fileReader = new FileReader(xmlFile);
		BufferedReader bufReader = new BufferedReader(fileReader);

		StringBuilder sb = new StringBuilder();
		String line = bufReader.readLine();
		while( line != null){
			sb.append(line).append("\n");
			line = bufReader.readLine();
		}
		String xml2String = sb.toString();
		System.out.println("XML to String using BufferedReader : ");
		System.out.println(xml2String);

		bufReader.close();
		return xml2String;
	}
	private static final int BUFFER_SIZE = 4096;
	public static void unzip(String zipFilePath, String destDirectory) throws IOException {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, filePath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	public static File getLastModified(String directoryFilePath)
	{
		File directory = new File(directoryFilePath);
		File[] files = directory.listFiles(File::isFile);
		long lastModifiedTime = Long.MIN_VALUE;
		File chosenFile = null;

		if (files != null)
		{
			for (File file : files)
			{
				if (file.lastModified() > lastModifiedTime)
				{
					chosenFile = file;
					lastModifiedTime = file.lastModified();
				}
			}
		}

		return chosenFile;
	}
	
	public String getLatestXmlDownload(String extensionOfFile) throws IOException
	{

		String path = PdfManager.getDownloadsDirectoryPath();
				//"C:\\Users\\a687416\\Downloads";
		
			
		File lastfile = getLastModified(path);
		
		
		// rename file name 
		String filenameNew =  gf.randamGenerator();
		File file2 = new File(path+"/"+filenameNew+"."+"zip"); // added hard code bez everytime its zip file

		if (lastfile.renameTo(file2)) {
			logger.info("File renamed successfully");
		} else {
			logger.info("Failed to rename file");
		}
		
		logger.info("lastfile + "+file2);
		String zipfliepath =file2.toString();
		
		

		unzip(zipfliepath,path);
		
		// operation with xml or csv 
		File unzipxmlFile = getLastModified(path);
		logger.info("unzipped xml file is "+ unzipxmlFile);
		
		// rename that xml file 
		File filexml = new File(path+"/"+filenameNew+"."+extensionOfFile);
		if (unzipxmlFile.renameTo(filexml)) {
			logger.info("File renamed successfully");
			logger.info("xml file is "+ filexml);
		} else {
			logger.info("Failed to rename file");
		}
		
		String abc = filexml.toString();

		String xmlfile = abc.substring(0, zipfliepath.length() - 4)+"." + extensionOfFile ;

		
		
		logger.info("xml file path"+ xmlfile);

		String xmldata =readXMLtoString(xmlfile);
		logger.info("xmldata: -- "+ xmldata);
		
	
		return xmldata;
	}
	
	
	public String getLatestXmltoDelete(String extensionOfFile) throws IOException
	{

		String path = PdfManager.getDownloadsDirectoryPath();
		
		File lastfile = getLastModified(path);
		
		logger.info("lastfile + "+lastfile);
		String zipfliepath =lastfile.toString();

		
		
	
		return zipfliepath;
	}
	
	
	public void deleteDownloadedFile(String Path)
	{
		File file = new File(Path); 
        
        if(file.delete()) 
        { 
        	logger.info("File deleted successfully"); 
        } 
        else
        { 
        	logger.info("Failed to delete the file"); 
        } 
	}
	
}
