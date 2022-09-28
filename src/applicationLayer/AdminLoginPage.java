package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
//define Repository for page events
	@FindBy(name="txtUsername")
	WebElement ObjUser;
	@FindBy(xpath ="//input[@type='password']")
	WebElement ObjPass;
	@FindBy(id ="btnLogin")
	WebElement Objloginbtn;
	//write method
	public void verifyLogin(String username,String password)
	{
		ObjUser.sendKeys(username);
		ObjPass.sendKeys(password);
		Objloginbtn.submit();
	}

}
