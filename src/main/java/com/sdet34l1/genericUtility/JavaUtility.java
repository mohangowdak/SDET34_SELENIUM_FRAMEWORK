package com.sdet34l1.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;

/**
 * This class contains only java specific reusable methods
 * @author MOHAN GOWDA
 *
 */
public class JavaUtility {

	/**
	 * This method is used to Convert String value to long datatype
	 * @param value
	 * @return
	 */
	public  long stringToLong(String value) {
		return Long.parseLong(value);
	}
	
	/**
	 * This method is used to get the random Number
	 * @param limit
	 * @return
	 */
	public  int getRandomNumber(int limit) {
		Random ran=new Random();
		return ran.nextInt(limit);
	}
	
	/**
	 * This method is used to print the message
	 * @param message
	 */
	public void printStatement(String message) {
		System.out.println(message);
	}
	
	/**
	 * This method is used to do the assertion
	 * @param actualResult
	 * @param expectedResult
	 * @param testCaseName
	 */
	public void assertionThroughIfCondition(String actualResult, String expectedResult, String testCaseName) {
		if(actualResult.equalsIgnoreCase(expectedResult))
		{
			System.out.println(testCaseName+" created Successfully");
			System.out.println("TC Pass");
		}
	}
	
	/**
	 * This method is used to wait untill element clickable
	 * @param element
	 * @param polingTime
	 * @param duration
	 * @throws InterruptedException
	 */
	public void customWait(WebElement element, long polingTime, int duration) throws InterruptedException {
		int count=0;
		while(count<=duration) {
			try {
			element.click();
			break;
			}
			catch(Exception e) {
				Thread.sleep(polingTime);
				count++;
			}
		}
	}
	
	/**
	 * This method is used to fetch 
	 * @return
	 */
	public  String getDateTimeInFormat() {
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss").format(new Date());
		
	}
}
