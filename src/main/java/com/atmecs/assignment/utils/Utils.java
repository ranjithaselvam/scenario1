package com.atmecs.assignment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * purpose:Read data from external file (property reader,excel reader,csv reader)
 * 
 * @author ranjitha.selvam
 */

public class Utils {
	public FileInputStream stream;
	public Properties propertyFile = new Properties();
	public Workbook book;
	public Sheet sheet;
	

	

	/**
	 * Property file Reader(read the data from property file)
	 * 
	 */
	public String propertyRead(String path, String elements) {
		String data = null;
		propertyFile = new Properties();
		FileInputStream reader = null;
		try {
			reader = new FileInputStream(path);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		try {
			propertyFile.load(reader);
			data = propertyFile.getProperty(elements);

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return data;
	}

	/**Take the value from excel file and passing the value to web site. Apache poi supports to read value from excel file.
	 * 
	 * @param path
	 * @param sheetName
	 * @return
	 */
	 

	public Object[][] getExcel(String path, String sheetName) {
		File file = new File(path);
		FileInputStream read = null;
		try {
			read = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int rowCount = 0; rowCount < sheet.getLastRowNum(); rowCount++) {
			for (int cellCount = 0; cellCount < sheet.getRow(0).getLastCellNum(); cellCount++) {
				data[rowCount][cellCount] = sheet.getRow(rowCount + 1).getCell(cellCount).toString();
			}
		}
		return data;

	
	
}
	


}
