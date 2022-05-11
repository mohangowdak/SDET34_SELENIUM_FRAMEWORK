package com.vtiger.practice;

import java.io.IOException;

import com.sdet34l1.genericUtility.FileUtility;
import com.sdet34l1.genericUtility.IconstantPath;

public class FetchDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
	FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
	String url = FileUtility.getDataFromPropertyFile("url");
	String timeout = FileUtility.getDataFromPropertyFile("timeout");
	String username =FileUtility.getDataFromPropertyFile("userName");
	String password = FileUtility.getDataFromPropertyFile("password");
	
	System.out.println(url);
	System.out.println(timeout);
	System.out.println(username);
	System.out.println(password);
	}
}
