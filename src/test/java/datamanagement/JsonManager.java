/*
 * Copyright (c) Worldline 2019 - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited
﻿ * Proprietary and confidential᠎        
 */
package datamanagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

/**
 * JsonManager manages the json file related tasks
 * 
 * @author A708551
 */
public class JsonManager {
	private static GsonBuilder builder = new GsonBuilder();
	private static Gson gson = builder.setPrettyPrinting().create();

	private static Logger logger = LogManager.getLogger(JsonManager.class.getName());

	static void generateJsonTemplate(Object object, String ClassName) {

		try {
			File file = new File(JsonManager.class.getResource("/testdata/" + ClassName + ".json").toURI());
			FileWriter writer = new FileWriter(file);

			gson.toJson(object, writer);

			writer.flush();
			writer.close();

		} catch (JsonIOException e) {
			logger.error("failed to write json object" + e);
		} catch (IOException e) {
			logger.error("failed to write json to file" + e);
		} catch (URISyntaxException e) {
			logger.error("failed to read json URL" + e);
		}

	}

	// Receives file path, object name and returns Map of the data
	/**
	 * returns map after reading the json data
	 * 
	 * @param filePath
	 *            path of json in jar or project
	 * @param testDataName
	 *            test data header which needs to be read
	 * @return map with values from json
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LinkedTreeMap<String, String> getMapfromJson(String filePath, String testDataName) {
		Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath), StandardCharsets.UTF_8);

		Type type = new TypeToken<Map<String, ?>>() {
		}.getType();

		Map<String, ?> fromJson = gson.fromJson(reader, type);

		return (LinkedTreeMap) fromJson.get(testDataName);

	}
	
	// Receives file path, object name and returns String
		/**
		 * returns map after reading the json data
	 
		 */
 		public static String getStringfromJson(String filePath, String testDataName) {
			Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath), StandardCharsets.UTF_8);

			/*
			 * Type type = new TypeToken<Map<String, ?>>() { }.getType();
			 */

			JsonObject fromJson = gson.fromJson(reader, JsonObject.class);
 			
			return  fromJson.get(testDataName).toString();

		}
}
