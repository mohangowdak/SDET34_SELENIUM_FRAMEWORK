package com.vtiger.practice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {
public static void main(String[] args) {
	//get the date in required format
	Date date=new Date();
SimpleDateFormat f=new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss");
String date1 = f.format(date);
System.out.println(date1);
}
}
