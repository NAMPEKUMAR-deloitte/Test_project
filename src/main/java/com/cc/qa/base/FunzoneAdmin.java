package com.cc.qa.base;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.cc.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FunzoneAdmin {

	public static WebDriver driver = null;
	public static Properties prop;
	private static String dateFormat = "dd_MM_yyyy_HH_mm";
	protected static String imgeHtmlPath;
	protected static ExtentTest extentTest;
	protected static String extentDate = new SimpleDateFormat(dateFormat).format(new Date());
	public static String fileDownloadPath = System.getProperty("user.dir") + "\\downloads";
	public static ExtentReports extentReport;
	public static String extentReportPath;
	public static String suiteName;
	public static String browserName;

	@BeforeSuite(alwaysRun = true)
	public static WebDriver initialization(ITestContext context) throws Exception {
		String date = extentDate.substring(0, 10);
		// String suiteName = context.getCurrentXmlTest().getSuite().getName();
		suiteName = context.getName();
		extentReportPath = System.getProperty("user.dir") + "\\LogsAndReports\\Reports_" + date + "\\" + suiteName + "_"
				+ extentDate;
		extentReport = new ExtentReports(extentReportPath + ".html", true);
		extentTest = extentReport.startTest("Environment initialization",
				"Driver initialization and launch web browser");
		browserName = System.getProperty("browser");
		if (browserName == null)
			browserName = prop.getProperty("browser");
		extentTest.log(LogStatus.INFO, "Browser select : " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			Map<String, Object> prefsMap = new HashMap<String, Object>();
			prefsMap.put("profile.default_content_settings.popups", 0);
			prefsMap.put("download.default_directory", fileDownloadPath);
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.setExperimentalOption("prefs", prefsMap);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("start-maximized");
			options.addArguments("--window-size=1920,1080");
			WebDriverManager.chromedriver().clearResolutionCache().setup();
			driver = new ChromeDriver(options);
			extentTest.log(LogStatus.PASS, "Chrome open sucessfully");
			System.out.println("Chrome Opened Successfully");
		} 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		extentReport.endTest(extentTest);
		extentReport.flush();
		return driver;
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		driver.quit();
		System.out.println("Suite executed...");
	}
}
