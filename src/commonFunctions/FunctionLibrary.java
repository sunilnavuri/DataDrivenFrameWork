package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
  //method for login
	public static boolean verifyLogin(String username,String password)
	{
		driver.get(config.getProperty("Url"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).submit();
		String expected = "dashboard";
	    String actual =driver.getCurrentUrl();
	    if(actual.contains(expected))
	    {
	    	Reporter.log("Login Success::"+expected+"        "+actual,true);
	    	return true;
	    }
	    else
	    {
	    	//capture error message
	    	String errormessage = driver.findElement(By.xpath(config.getProperty("Objerrormessage"))).getText();
	    	Reporter.log(errormessage+"       "+expected+"       "+actual);
	    	return false;
	    }
	}
}
