package com.cc.qa.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import com.cc.qa.base.FunzoneAdmin;

public class TestUtil extends FunzoneAdmin {

	public static long PAGE_LOAD_TIMEOUT;
	public static long IMPLICIT_WAIT;

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	AlertUtil alertUtil = new AlertUtil();

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public void takeScreenshot(String SSname) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			String screenShotPath = System.getProperty("user.dir") + "\\LogsAndReports\\Reports_"
					+ extentDate.substring(0, 10) + "\\Screenshots\\";
			FileUtils.copyFile(scrFile, new File(screenShotPath + SSname + ".png"));
			imgeHtmlPath = extentTest.addScreenCapture(screenShotPath + SSname + ".png")
					.replace(screenShotPath, "Screenshots\\").replace("file:///", "")
					.replace("<img", "<img width=\"150\" height=\"70\"");

		} catch (IOException e) {
			throw new java.lang.RuntimeException(
					"RUNTIME_ERROR : : Exception occur during taking ScreenShot: " + SSname);
		}
		System.out.println("Screenshot has been generated for " + SSname);
	}


	public void verifyPDFData(String Url, String pdfContent) throws Exception {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.navigate().to(Url);
		URL url = new URL(driver.getCurrentUrl());
		InputStream is = url.openStream();
		BufferedInputStream fileToParse = new BufferedInputStream(is);
		PDDocument document = null;
		String output = null;
		try {
			document = PDDocument.load(fileToParse);
			output = new PDFTextStripper().getText(document);
			// System.out.println(output);
		} finally {
			if (document != null) {
				document.close();
			}
			fileToParse.close();
			is.close();
		}
		Assert.assertTrue(output.contains(pdfContent));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public void writeToExcel(String filePath, String fileName, String sheetName, String[] dataToWrite)
			throws IOException {
		try {
			File file = new File(filePath + "\\" + fileName);
			FileInputStream inputStream = new FileInputStream(file);
			Workbook workbook = null;
			String fileExtensionName = fileName.substring(fileName.indexOf("."));
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			}
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			Row row = sheet.getRow(0);
			Row newRow = sheet.createRow(rowCount + 1);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = newRow.createCell(j);
				cell.setCellValue(dataToWrite[j]);
			}
			inputStream.close();

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteRow(String filePath, String fileName, String sheetName, int rowNo) throws IOException {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		String excelPath = filePath + "\\" + fileName;
		try {
			FileInputStream file = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				return false;
			}
			int lastRowNum = sheet.getLastRowNum();
			if (rowNo >= 0 && rowNo < lastRowNum) {
				sheet.shiftRows(rowNo + 1, lastRowNum, -1);
			}
			if (rowNo == lastRowNum) {
				XSSFRow removingRow = sheet.getRow(rowNo);
				if (removingRow != null) {
					sheet.removeRow(removingRow);
				}
			}
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(excelPath));
			workbook.write(outFile);
			outFile.close();

		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
		}
		return true;
	}

	

	public String[] getDataFromCSV(String path) throws IOException {
		String row;
		String[] data = null;
		File file = new File(path);
		if (file.isFile()) {
			BufferedReader csvReader = new BufferedReader(new FileReader(path));
			while ((row = csvReader.readLine()) != null) {
				data = row.split(",");
				System.out.println(data);
				break;
			}
			csvReader.close();
		}

		return data;
	}
}
