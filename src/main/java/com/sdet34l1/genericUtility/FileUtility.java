package com.sdet34l1.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains only Property file Specific common methods
 * @author MOHAN GOWDA
 *
 */
public class FileUtility {
	static Properties property;
	/**
	 * This method is used to open the property File
	 * @throws IOException
	 */
	public static void openPropertyFile(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		property=new Properties();
		property.load(fis);
	}
	
	/**
	 * This method is used to fetch the data from the Property File
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getDataFromPropertyFile(String key) {
		String value = property.getProperty(key);
		return value;
	}
	
	

	
}
