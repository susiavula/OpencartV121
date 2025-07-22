package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass { 
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		try
		{
		logger.info("*** Starting  TC001_AccountRegistrationTest ***");
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Click on MyAccount Link");
		hp. clickRegister();
		
		logger.info("Click on RegisterLink");
		
		RegisterPage regpage=new RegisterPage(driver);
		logger.info("Providing customer details");
		regpage.Setfirst_Name(randomString().toUpperCase());
		regpage.Setlast_Name(randomString().toUpperCase());
		regpage.Setemail(randomString()+"@gmail.com");
		regpage.Setelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.Setpassword(password);
		regpage.confirmpassword(password);
		regpage.setprivacypolicy();
		regpage.clickcontinue();
		logger.info("Validating expected message.");
		String confmsg=regpage.checkConfirmationmsg();
		if(confmsg.equals ("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		
		
		else
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		}
		
		catch(Exception e)
		{
		
			Assert.fail();
			}
		logger.info("Finished TC001_AccountRegistrationTest ");
		
	}
}
	


