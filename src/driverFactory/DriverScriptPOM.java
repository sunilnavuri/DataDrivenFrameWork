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
import utilities.ExcelFileUtil;

public class DriverScriptPOM {
WebDriver driver;
String inputpath ="F:\\Ranga Reddy\\DDT_FrameWork\\TestInput\\Employee.xlsx";
String outputpath ="F:\\Ranga Reddy\\DDT_FrameWork\\TestOutput\\EmpResults.xlsx";
@BeforeTest
public void setUp()
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get("");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void startTest() throws Throwable
{
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc= xl.rowCount("EmpData");
	for(int i=1;i<=rc;i++)
	{
		String para1 = xl.getCellData("EmpData", i, 0);
		String para2 = xl.getCellData("EmpData", i, 1);
		String para3 = xl.getCellData("EmpData", i, 2);
		EmpPage emp = PageFactory.initElements(driver, EmpPage.class);
		boolean res = emp.verifyAddEmp(para1, para2, para3);
		if(res)
		{
			xl.setCellData("EmpData", i, 3, "Pass", outputpath);
		}
		else
		{
			xl.setCellData("EmpData", i, 3, "Fail", outputpath);
		}
	}
	
}
@AfterTest
public void tearDown()
{
	AdminLogoutPage logout =PageFactory.initElements(driver, AdminLogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}
























