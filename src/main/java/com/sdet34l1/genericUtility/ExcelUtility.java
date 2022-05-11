package com.sdet34l1.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to maintain all excel specific common methods
 * @author MOHAN GOWDA
 *
 */
public class ExcelUtility {
 static	Workbook wb; 
	public static void openExcel(String filePath) throws EncryptedDocumentException, IOException {
		FileInputStream fisExcel=new FileInputStream(filePath);
		 wb = WorkbookFactory.create(fisExcel);
	}
	
	/**
	 * This method is used to fetch the data from the excel sheet
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 * 
	 */
	public static String getDataFromExcel( String sheetName, int rowNumber, int cellNumber) {
		
		String data=wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		
		return data;
	}
	
	/**
	 * This method is used to set the value into Excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param value
	 */
public static void setDataIntoExcel(String sheetName, int rowNumber, int cellNumber, String value) {
	wb.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(value);
	
}

/**
 * This method is used to save the data 
 * @param fileSavePath
 * @throws IOException
 */
public static void saveExcelData(String fileSavePath)  {
	FileOutputStream fos = null;
	try {
		fos = new FileOutputStream(fileSavePath);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		wb.write(fos);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

/**
 * This method is used to close the Excel Connection
 */
public static void closeExcel() {
	try {
		wb.close();
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("while closing excel some exception accured.. please check the ExcelUtility");
	}
}

}
