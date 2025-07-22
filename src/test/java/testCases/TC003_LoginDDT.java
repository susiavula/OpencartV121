package testCases;
/*Data is valid - login success - test pass - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail - logout
Data is invalid -- login failed - test pass
*/

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class , groups="datadriven") //getting dataProvider from another class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("****starting TC003_LoginDDT  is started***");
		try {
			
	
	//Home Page
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	//LoginPage
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	//MyAccount
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage=macc.isMyAccountPageExits();
	Assert.assertTrue(targetPage);
	
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			macc.clickLogout();
			Assert.assertTrue(true);
			
		}
		
		else
		{
			Assert.assertTrue(false);
		}
		
	}
	
	 if(exp.equalsIgnoreCase("Invalid"))
	 {
		 if(targetPage==true)
		 {
			 macc.clickLogout();
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
	
	 logger.info("**** TC003_LoginDDT  is Finished***");
	}


}
