package com.restassured.commonutils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib {
    XSSFWorkbook workBook;
    XSSFSheet sheet;
	FileInputStream fileIOStream;
	private String strExcelPath;
	
	/**
	 * Constructor to retrieve excel path
	 * @param strExcelPath
	 * @throws Exception
	 */
	public ExcelLib(String strExcelPath) throws Exception {
		try {
		    this.strExcelPath = strExcelPath;
			File filePath = new File(strExcelPath);
		    fileIOStream = new FileInputStream(filePath);
		    workBook = new XSSFWorkbook(fileIOStream);
			
		} catch (Exception e) {
			System.out.println("The ExcelFile Was Not Retrieved As" + e.getMessage());
			throw e;
		}
	}


	/**
	 * Method to write data into excel
	 * @param strSheetName
	 * @param rowName
	 * @param colName
	 * @param strValueToWrite
	 * @throws Exception
	 *//*
	public synchronized void writeDataToExcel(String strSheetName, String rowName, String colName,
			String strValueToWrite) throws Exception {
	    String value = rowName;
		try {
			FileInputStream inputStream = new FileInputStream(strExcelPath);
			Workbook Wb = WorkbookFactory.create(inputStream);
			Sheet sheet = Wb.getSheet(strSheetName);
			Row row = sheet.getRow(excelGetRowNumber(strSheetName, rowName, 0));
			Cell cell = row.getCell(excelColNumber(strSheetName, colName));
			if (cell == null) {
				cell = row.createCell(excelColNumber(strSheetName, colName));
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(strValueToWrite);
			FileOutputStream outputStream = new FileOutputStream(strExcelPath);
			Wb.write(outputStream);
			outputStream.close();
			
			for (int i=0; i<100;i++) {
			    System.out.println("row name is : "+value);
			   // Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Exception Occurred While Writing Data To An Excel File" + e.toString());
throw e;
		}
	}*/


	/**
	 * Method to read data from excel
	 * @param strSheetName
	 * @param rowValue
	 * @param intColumn
	 * @return
	 */
	public String excelStringFetchDataUtility(String strSheetName, String rowValue, int intColumn) throws Throwable {
		String strLocatorvalue;
		int rowNum = -1;
		int rowCount, i;
      try {
		sheet = workBook.getSheet(strSheetName);
		rowCount = sheet.getLastRowNum();
		
		for (i = 1; i <= rowCount; i++) {
			if (rowValue.trim().equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue().trim())) {
				rowNum = i;
				break;
			}

		}
		strLocatorvalue = sheet.getRow(rowNum).getCell(intColumn).getStringCellValue().trim();
		return strLocatorvalue.trim();
		
      }catch (Throwable t) {
          throw t;
      }
	}

    /**
     * Method to read data from excel
     * @param strSheetName
     * @param rowValue
     * @param colName
     * @return
     * @throws Exception
     * @throws Error
     */
    public String excelStringFetchDataUtility(String strSheetName, String rowValue, String colName)
            throws Exception, Error {
        String strLocatorvalue;
        int rowNum = -1;
        int rowCount, i;
        int colNum = -1;
        try {
            sheet = workBook.getSheet(strSheetName);
            rowCount = sheet.getLastRowNum();
            for (i = 1; i <= rowCount; i++) {
                if (rowValue.trim().equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue().trim())) {
                    rowNum = i;
                    break;
                }

            }
            colNum = excelColNumber(strSheetName, colName);
            strLocatorvalue = sheet.getRow(rowNum).getCell(colNum).getStringCellValue().trim();
            return strLocatorvalue.trim();
        } catch (Throwable e) {
            throw (e);
        }
    }

	/**
	 * Method to get row number
	 * @param strSheetName
	 * @param strSearchValue
	 * @param colNumber
	 * @return
	 */
	public int excelGetRowNumber(String strSheetName, String strSearchValue, int colNumber) {
		int rowNum = -1;
		int rowCount, i;
		sheet = workBook.getSheet(strSheetName);
		rowCount = sheet.getLastRowNum();

		for (i = 1; i <= rowCount; i++) {
			if (strSearchValue.trim().equalsIgnoreCase(sheet.getRow(i).getCell(colNumber).getStringCellValue().trim())) {
				rowNum = i;
				break;
			}

		}

		return rowNum;
	}


	/**
	 * Method to get column number
	 * @param strSheetName
	 * @param colName
	 * @return
	 */
	public int excelColNumber(String strSheetName, String colName) {
		int colNum = -1;
		int colCount, i;

		try {
			sheet = workBook.getSheet(strSheetName);
			colCount = sheet.getRow(0).getLastCellNum();

			for (i = 1; i <= colCount; i++) {
				if (colName.trim().equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue().trim())) {
					colNum = i;
					break;
				}

			}
			return colNum;
		} catch (Exception | Error e) {
			throw (e);
		}
	}
}
