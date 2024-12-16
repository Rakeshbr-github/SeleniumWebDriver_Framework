package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectModel.AccountRegistrationPage;
import pageObjectModel.HomePage;
import testbase.BaseClass;

public class TC001_Account_Registration_TestCase extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link.. ");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link.. ");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
			
		String password=randomAlphaNumeric();
			
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message..");

		String confmsg = regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("Test passed");
		}
		else
		{
			logger.error("Test failed..");
			logger.debug("Test failed..");	
			Assert.assertTrue(false);
		}
		} 
		catch (Exception e)
		{

			Assert.fail();
		} 
		finally 
		{
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}
	
	}
}
