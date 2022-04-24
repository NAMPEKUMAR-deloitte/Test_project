package com.cc.qa.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import com.cc.qa.base.FunzoneAdmin;
import com.cc.qa.util.AlertUtil;
import com.cc.qa.util.ElementUtil;
import com.cc.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends FunzoneAdmin {

	AlertUtil alertUtil = new AlertUtil();

	public LoginPage() {
		TestUtil.IMPLICIT_WAIT = 10;
		TestUtil.PAGE_LOAD_TIMEOUT = 10;
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/cc/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Page Factory 
	public static By username = By.xpath("//input[contains(@name,'username')]");

	public static By password = By.xpath("//input[contains(@name,'password')]");

	public static By loginBtn = By
			.xpath("//button[contains(@id,'signin')]");
	
	public static By logoutDrpdwn = By.xpath("//div[@class='dropdown ml-auto show']//a[@id='dropdown']");

	public static By logoutBtn = By.xpath("//a[@class='dropdown-item dropitem'][2]");
	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void navigateToFunZone() throws InterruptedException {
		driver.manage().deleteAllCookies();
			ElementUtil.goToUrl(prop.getProperty("url"), "FunZone is Opened");
		extentTest.log(LogStatus.PASS, "Url loaded successfully");
	}

	public void login() throws Exception {
		ElementUtil.waitForElementPresent(username);
			ElementUtil.doSendKeys(username, prop.getProperty("user"), " Username Field");
			ElementUtil.doSendKeys(password, prop.getProperty("password"), "Password Field");
		ElementUtil.doClick(loginBtn, "Login Button");
		Thread.sleep(3000);
	}
	
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		ElementUtil.doClick(logoutDrpdwn, "Click on Profile");
		ElementUtil.doClick(logoutBtn, "Click on Logout Buton");
	
}
}