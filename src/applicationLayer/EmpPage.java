package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class EmpPage {
	WebDriver driver;
	//constructor to override webdriver methods
	public EmpPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//define Repository
	@FindBy(xpath ="//b[normalize-space()='PIM']")
	WebElement clickPim;
	@FindBy(id ="btnAdd")
	WebElement clickAddBtn;
	@FindBy(name="firstName")
	WebElement EnterFname;
	@FindBy(name="middleName")
	WebElement EnterMname;
	@FindBy(name="lastName")
	WebElement EnterLname;
	@FindBy(name="employeeId")
	WebElement BeforeEid;
	@FindBy(id="btnSave")
	WebElement ClickSaveBtn;
	@FindBy(name ="personal[txtEmployeeId]")
	WebElement AfterEid;
	public boolean verifyAddEmp(String EnterFname,String EnterMName,String EnterLname)
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.clickPim).click().perform();
		ac.moveToElement(this.clickAddBtn).click().perform();
		this.EnterFname.sendKeys(EnterFname);
		this.EnterMname.sendKeys(EnterMName);
		this.EnterLname.sendKeys(EnterLname);
		String expectedEid =this.BeforeEid.getAttribute("value");
		ac.moveToElement(this.ClickSaveBtn).click().perform();
		String ActualEid=this.AfterEid.getAttribute("value");
		if(expectedEid.equals(ActualEid))
		{
			Reporter.log("Emp Creation Success::"+expectedEid+"    "+ActualEid,true);
			return true;
		}
		else
		{
			Reporter.log("Emp Creation Fail::"+expectedEid+"    "+ActualEid,true);
			return false;
		}

	}

}
