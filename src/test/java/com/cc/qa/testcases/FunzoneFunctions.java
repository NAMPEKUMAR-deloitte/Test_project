package com.cc.qa.testcases;
import com.cc.qa.util.TestUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.cc.qa.base.FunzoneAdmin;
import com.cc.qa.pages.LoginPage;
import com.cc.qa.pages.FunzoneMethods;


@Listeners(com.cc.qa.util.Listener.class)
public class FunzoneFunctions extends FunzoneAdmin{
	FunzoneMethods fzone = new FunzoneMethods();
	LoginPage loginPage = new LoginPage();
	
	@BeforeClass(alwaysRun = true)
	public void navigateTo() throws Exception {
		loginPage.navigateToFunZone();
	}
	
	@Test(priority=1)
	public void FunZoneOperations() throws Exception{
		loginPage.login();
        fzone.RegisterOrg();
		fzone.RegisterEmployee();
		fzone.ManageUsers();
		fzone.delete();
		fzone.PageCount();
	}
}
