package driverFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;
import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath ="F:\\Ranga Reddy\\DDT_FrameWork\\TestInput\\TestData.xlsx";
String outputpath ="F:\\Ranga Reddy\\DDT_FrameWork\\TestOutput\\DataDrivenResults.xlsx";
@Test
public void startTest() throws Throwable
{
	//access excel methods
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows
	int rc = xl.rowCount("Login");
	int cc = xl.cellCount("Login");
	Reporter.log("No of rows::"+rc+"       "+"No of cells::"+cc,true);
	for(int i=1;i<=rc;i++)
	{
		String user = xl.getCellData("Login", i, 0);
		String pass = xl.getCellData("Login", i, 1);
		//call login method from function library class
		boolean res =FunctionLibrary.verifyLogin(user, pass);
		if(res)
		{
			//if it true write into results and status cell
			xl.setCellData("Login", i, 2, "Login pass", outputpath);
			xl.setCellData("Login", i, 3, "pass", outputpath);
		}
		else
		{
			File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screens/Iteration/"+i+"   "+"LoginPage.png"));
			xl.setCellData("Login", i, 2, "Login Fail", outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
		}
	}
}
}
