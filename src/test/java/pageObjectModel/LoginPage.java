package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement myemail;
	@FindBy(xpath="//input[@id='input-password']") WebElement mypassword;
	@FindBy(xpath="//input[@value='Login']") WebElement loggclck;

	public void email(String email)
	{
		myemail.sendKeys(email);
	}

	public void pwd(String password)
	{
		mypassword.sendKeys(password);
	}

	public void login()
	{
		loggclck.click();
	}

}
