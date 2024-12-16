package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectModel.HomePage;
import pageObjectModel.LoginPage;
import pageObjectModel.MyAccountPage;
import testbase.BaseClass;

public class TC002_Login_Test_Case extends BaseClass {
	
	@Test(groups={"sanity","Master"})
	public void verify_login()
	{
		logger.info("****Starting TC002_Login_Test_Case****");
		try
		{
		
//		Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
//		LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.email(p.getProperty("email"));
		lp.pwd(p.getProperty("password"));
		lp.login();	
		
//		My account
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.accstatus();
//		Assert.assertEquals(targetpage, true, "Login Failed");
		 Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		 logger.info("****Finished TC002_Login_Test_Case****");
	}

}
