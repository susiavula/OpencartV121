package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "Master"})
	public void verify_Login()
	
	{
		try
		{
		logger.info("**Starting TC002_LoginTest ** ");
		
		logger.info(" Click My Account  ");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Click on Login  ");
		hp.clickLogin();
		
		logger.info("set values in Login  ");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		logger.info(" My Account Page ");
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExits();
		Assert.assertTrue(targetPage);
		
		
		
		}
		
		catch(Exception e)
		{
			Assert.fail();
			
		}
		logger.info(" Test Case TC002_LoginTest is Finished.. ");
	}	
		
		
	}
	

	
	
	


