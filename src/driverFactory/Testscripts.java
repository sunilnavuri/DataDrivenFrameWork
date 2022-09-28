package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.AdminLoginPage;
import applicationLayer.AdminLogoutPage;
import applicationLayer.EmpPage;

public class Testscripts {
	WebDriver driver;
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		AdminLoginPage login =PageFactory.initElements(driver, AdminLoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
		
	}
	@Test
	public void empCreation()
	{
		EmpPage emp =PageFactory.initElements(driver, EmpPage.class);
		boolean res= emp.verifyAddEmp("Akhilesh", "Testing", "Selenium");
		System.out.println(res);
		
	}
	@AfterTest
	public void tearDown()
	{
		AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
		logout.verifyLogout();
		driver.close();
	}

}
