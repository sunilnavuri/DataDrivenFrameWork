package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage {
	@FindBy(id="welcome")
	WebElement clickWelcome;
	@FindBy(linkText="Logout")
	WebElement clicklogout;
	public void verifyLogout()
	{
		clickWelcome.click();
		clicklogout.click();
	}

}
