package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData {
	
	public static void main(String[] args) throws Throwable {
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook wb = WorkbookFactory.create(fisExcel);
		Sheet sh = wb.getSheet("loginData");
		
		Object[][] arr=new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		for(int i=1; i<sh.getLastRowNum(); i++)
			{
			for(int j=0; j<sh.getRow(i).getLastCellNum(); j++)
			{
				arr[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
			}
		System.out.println(arr[5][1]);
		
	}

}
