package com.cc.qa.util;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import com.cc.qa.base.FunzoneAdmin;
import com.relevantcodes.extentreports.LogStatus;

public class AlertUtil extends FunzoneAdmin {

	public boolean acceptAlert() throws InterruptedException {
		boolean flag = false;
		try {
			// Check the presence of alert
			Alert alert = driver.switchTo().alert();
			// Alert present; set the flag
			flag = true;
			// Accept the alert
			alert.accept();
			System.out.println("Alert accepted");
		} catch (NoAlertPresentException e) {
			// Alert not found
			System.out.println("Alert not found");
			// e.printStackTrace();
		}
		return flag;
	}

	public void checkLoader() throws InterruptedException {
		try {
			extentTest.log(LogStatus.INFO, "Checking loader...");
			System.out.println("Checking loader...");
			Thread.sleep(1000);
			long time = TestUtil.IMPLICIT_WAIT;
			for (int i = 0; i <= time; i++) {
				List<WebElement> list = driver.findElements(By.id("loading_wait"));
				int sizex = list.size();
				if (sizex > 0) {
					if (list.get(0).isDisplayed())
						Thread.sleep(500);
					else {
						break;
					}
				} else {
					break;
				}
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Loader Not Found");
		}
	}

	
}