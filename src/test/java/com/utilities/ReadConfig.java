package com.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ReadConfig {
	private Properties properties;
	private static final Logger logger = Logger.getLogger(ReadConfig.class);

	public ReadConfig() {
		BufferedReader Configreader;
		try {
			Configreader = new BufferedReader(new FileReader("src/test/resource/configAndData/config.properties"));
			properties = new Properties();
			try {
				properties.load(Configreader);
				Configreader.close();

			} catch (IOException e) {
				logger.error("Exception occured while reading config.properties file {}", e);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Config properties file not found at : " + "configAndData/config.properties");
		}
	}

	public String getPropValueUsingKey(String keyName) {
		String propValue = null;
		try {
			if (StringUtils.isEmpty(keyName)) {
				logger.info("Key for property file lookup is empty or null:  " + keyName);
				throw new RuntimeException("Property key : " + keyName + " is empty");

			} else {
				propValue = properties.getProperty(keyName);
				if (StringUtils.isNotEmpty(propValue)) {
					return propValue;
				} else {
					logger.error("Property key " + keyName + " is either not found or value is empty: " + propValue
							+ " hence entering value provided in feature file");
				}
			}
		} catch (Exception e) {
			logger.error("Error info " + e.getMessage());
		}

		return propValue;
	}

	public static void main(String a[]) {
		System.out.println(new ReadConfig().getPropValueUsingKey(""));

	}

	public  String getReportConfigPath() {
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}

}
