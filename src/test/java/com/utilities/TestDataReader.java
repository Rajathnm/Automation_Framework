package com.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestDataReader {

	private Properties properties;

	private static final Logger logger = Logger.getLogger(TestDataReader.class);

	public TestDataReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src/test/resource/configAndData/devTestData.properties"));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"TestData properties file not found at : " + "configAndData/devTestData.properties");
		}
	}

	public String getPropValueUsingKey(String keyName) {
		String propValue = null;
		try {
			if (org.apache.commons.lang3.StringUtils.isEmpty(keyName)) {
				System.out.println("Key for property file lookup is empty or null:  " + keyName);
				throw new RuntimeException("Property key : " + keyName + " is empty");

			} else {
				propValue = properties.getProperty(keyName);
				if (!org.apache.commons.lang3.StringUtils.isEmpty(propValue)) {
					return propValue;
				} else {
					System.err.println(
							"Property key " + keyName + " is either not found or value is empty: " + propValue);
				}
			}
		} catch (Exception e) {
			logger.error("Error info " + e.getMessage());
		}

		return propValue;
	}

	public static void main(String a[]) {
		System.out.println(new TestDataReader().getPropValueUsingKey(""));
	}

}
