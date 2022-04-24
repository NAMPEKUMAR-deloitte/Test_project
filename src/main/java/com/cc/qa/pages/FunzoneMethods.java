package com.cc.qa.pages;
import org.openqa.selenium.By;
import com.cc.qa.base.FunzoneAdmin;
import com.cc.qa.util.AlertUtil;

import com.cc.qa.util.ElementUtil;
import com.cc.qa.util.TestUtil;

import java.io.IOException;

public class FunzoneMethods extends FunzoneAdmin {
	ElementUtil eUtil = new ElementUtil();

	AlertUtil alertUtil = new AlertUtil();

	// Side menu Links
	//Register organiser links
	public static By regOrganiser = By.xpath("//div[@class='sideitem']//div[contains(text(),'Register Organizer')]");

	public static By regOrganiserUname = By.xpath("//div[@class='form-group']//input[@name='username']");

	public static By regOrganiserPasswrd = By.xpath("//div[@class='form-group']//input[@name='password1']");

	public static By regOrganiserCnfrmPasswrd = By.xpath("//div[@class='form-group']//input[@name='password2']");

	public static By regOrganiserSubmt = By.xpath("//button[contains(text(),'Submit')]");

	//
	public static By regEmploye = By.xpath("//div[@class='sideitem']//div[contains(text(),'Register Employee')]");

	public static By regEmployeCheckBox = By.xpath("//input[contains(@id,'id_interests_0')]");

	//Manage Users
	public static By manageUserz = By.xpath("//div[@class='sideitem lighttext']//div[contains(text(),'Manage Users')]");

	public static By registerUser = By.xpath("//div[@class='card-header pagehead']//a[1]");

	public static By enterUser = By.xpath("//div[@class='form-group']//input[@id='inputFirst']");

	public static By enterPassword = By.xpath("//div[@class='form-group']//input[@id='inputLast']");

	public static By enterName = By.xpath("//div[@class='form-group']//input[@name='username']");

	public static By enterEmail = By.xpath("//div[@class='form-group']//input[@name='email']");

	public static By enterMailPass = By.xpath("//div[@class='form-group']//input[@name='password']");

	public static By registerAdmin = By.xpath("//div[@class='form-group']//button[@type='submit']");

	public static By deleteUser = By.xpath("//a[contains(@href,'12')]//button[contains(text(),'Delete')]");

	public static By deleteUserNeverMind = By.xpath("//div[@class='btnbox']//a");

	public static By deleteUserYesSure = By.xpath("//div[@class='btnbox']//button[@type='submit']");

	public static By pageselct = By.xpath("//select[@name='dataTable_length']//option[2]");

	//Actions
	public void RegisterOrg() throws IOException {
		ElementUtil.doClick(regOrganiser, "Click on Register Organiser");
		ElementUtil.clearAnddoSendKeys(regOrganiserUname, "MetaOrganisation", "Enter UserName");
		ElementUtil.clearAnddoSendKeys(regOrganiserPasswrd, "123456789", "Enter Password");
		ElementUtil.clearAnddoSendKeys(regOrganiserCnfrmPasswrd, "123456789", "Confirm Password");
		ElementUtil.doClick(regOrganiserSubmt, "Click on Submit");
		TestUtil.takeScreenshotAtEndOfTest();


	}

	public void RegisterEmployee() throws IOException {
		ElementUtil.doClick(regEmploye, "Click on Register Employee");
		ElementUtil.clearAnddoSendKeys(regOrganiserUname, "Daniel", "Enter UserName");
		ElementUtil.clearAnddoSendKeys(regOrganiserPasswrd, "123456789", "Enter Password");
		ElementUtil.clearAnddoSendKeys(regOrganiserCnfrmPasswrd, "123456789", "Confirm Password");
		ElementUtil.doClick(regEmployeCheckBox, "Select Checkbox");
		ElementUtil.doClick(regOrganiserSubmt, "Click on Submit");
		TestUtil.takeScreenshotAtEndOfTest();

	}

	public void ManageUsers() throws IOException {
		ElementUtil.doClick(manageUserz, "Click on manage Users");
		ElementUtil.doClick(registerUser, "Click on Register");
		ElementUtil.doSendKeys(enterUser, "Maddy", "Enter User");
		ElementUtil.doSendKeys(enterPassword, "lienne", "Eneter Last Name");
		ElementUtil.doSendKeys(enterName, "Madli", "Eneter User Name");
		ElementUtil.doSendKeys(enterEmail, "Madli@gmail.com", "Eneter User Name");
		ElementUtil.doSendKeys(enterMailPass, "123456", "Eneter User Password");
		ElementUtil.doClick(registerAdmin, "Click on register");
		TestUtil.takeScreenshotAtEndOfTest();

	}

	public void delete() throws IOException {
		ElementUtil.doClick(deleteUser, "Click on delete");
		ElementUtil.doClick(deleteUserNeverMind, "Click on Never Mind");
		TestUtil.takeScreenshotAtEndOfTest();

	}

	public void deleteYes() throws IOException {
		ElementUtil.doClick(deleteUser, "Click on delete");
		ElementUtil.doClick(deleteUserYesSure, "Click on Yes");
		TestUtil.takeScreenshotAtEndOfTest();

	}

	public void PageCount() throws IOException {
		ElementUtil.doClick(manageUserz, "Click on manage Users");
		ElementUtil.doClick(pageselct,"Click on page 25");
		TestUtil.takeScreenshotAtEndOfTest();
	}
}