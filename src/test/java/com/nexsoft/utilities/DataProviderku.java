package com.nexsoft.utilities;

import org.testng.annotations.DataProvider;

import com.github.hemanthsridhar.CSVUtils;
import com.github.hemanthsridhar.lib.ExtUtils;

public class DataProviderku {
	
	@DataProvider(parallel = false)
	public Object[][] getUserBiodata() throws Exception {
		ExtUtils ext = new CSVUtils(getPathOfTheFile("MOCK_DATA.csv"), true);
		return ext.parseData();
	}

	/**
	 * Get file from src/test/resources
	 */
	String getPathOfTheFile(String fileName) throws Exception {
		String path;
		try {
			path = getClass().getClassLoader().getResource(fileName).getPath();
		} catch (NullPointerException e) {
			throw new Exception("External TestNG dataprovider file not found");
		}
		return path;
	}
}
