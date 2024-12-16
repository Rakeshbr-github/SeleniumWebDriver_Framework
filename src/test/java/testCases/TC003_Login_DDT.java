package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectModel.HomePage;
import pageObjectModel.LoginPage;
import pageObjectModel.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC003_Login_DDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")
	public void Login_DDT(String email,String pwd,String expectedres) 
	{
	logger.info("*****Started TC003_Login_DDT********* ");
	try {
//	Homepage
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clicklogin();
	
//	LoginPage
	LoginPage lp=new LoginPage(driver);
	lp.email(email);
	lp.pwd(pwd);
	lp.login();	
	
//	My account
	MyAccountPage myacc=new MyAccountPage(driver);
	boolean targetpage=myacc.accstatus();
//	Assert.assertEquals(targetpage, true, "Login Failed");
//	 Assert.assertTrue(targetpage);	
	 
	 /*Data is valid  - login success - test pass  - logout
	                  -- login failed - test fail

	 Data is invalid - login success - test fail  - logout
	                 -- login failed - test pass
	 */
	  if(expectedres.equalsIgnoreCase("Valid"))
	  {
		  if(targetpage==true)
		  {
			  myacc.logout();
			  Assert.assertTrue(true);

		  }
		  else
		  {
			  Assert.assertTrue(false);
			  
		  }
	  }
	  
	  if(expectedres.equalsIgnoreCase("invalid"))
	  {
		  if(targetpage==true)
		  {
			  myacc.logout();
			  Assert.assertTrue(false);
		  }
		  else
		  {
			  Assert.assertTrue(true);
		  }
		  
	  }
	  
	}
	catch(Exception e)
	{
	Assert.fail();	
	}
	logger.info("*****Finished TC003_Login_DDT********* ");
	}
	

}
