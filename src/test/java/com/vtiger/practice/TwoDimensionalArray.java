package com.vtiger.practice;

public class TwoDimensionalArray {
	
	public static void main(String[] args) {
		
		//String[] arr= {"Welcome", "To", "TYSS"};
		
		Object[][] arr=new Object[3][2];
		arr[0][0]="Karnataka";
		arr[0][1]=123;

		arr[1][0]="Tamilnadu";
		arr[1][1]=456;
		
		arr[2][0]="Telangana";
		arr[2][1]=789;
		
		System.out.println(arr[1][1]);
	}

}
