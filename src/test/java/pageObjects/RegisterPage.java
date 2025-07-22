package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
	 WebDriver driver;
		
		public RegisterPage (WebDriver driver)
		{
			super(driver);
		}
		
		@FindBy(xpath="//input[@placeholder=\"First Name\"]")
		WebElement txtfirstname;
		
		@FindBy(xpath="//input[@placeholder=\"Last Name\"]")
		WebElement txtlastname;
		
		@FindBy(xpath="//input[@name=\"email\"]")
		WebElement txtemail;
		
		@FindBy(xpath="//input[@name='telephone']")
		WebElement txttelephone;
		
		@FindBy(xpath="//input[@placeholder=\"Password\"]")
		WebElement txtpassword;
		
		@FindBy(xpath="//input[@placeholder=\"Password Confirm\"]")
		WebElement txtconfirmpassword;
		
		@FindBy(xpath="//input[@name=\"agree\"]")
		WebElement chkPolicy;
		
		@FindBy(xpath="//input[@value=\"Continue\"]")
		WebElement btnContinue;
		
		@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement msgConfirmation;
		
		
		public void Setfirst_Name(String firstname)
		{
			txtfirstname.sendKeys(firstname);
			
		}
		
		public void Setlast_Name(String lastname)
		{
			txtlastname.sendKeys(lastname);
			
		}
		
		public void Setemail(String email)
		{
			txtemail.sendKeys(email);
			
		}
		
		public void Setelephone(String tel)
		{
			txttelephone.sendKeys(tel);
			
		}
		

		public void Setpassword(String pwd)
		{
			txtpassword.sendKeys(pwd);
			
		}
		

		public void confirmpassword(String pwd)
		{
			txtconfirmpassword.sendKeys(pwd);
			
		}
		
		public void setprivacypolicy()
		{
			chkPolicy.click();
		}
		
		
		public void clickcontinue()
		{
			btnContinue.click();
			
			//so12
			//btnContinue.submit();

			//so13
			//Actions act=new Actions(driver);
			//act.moveToElement(btnContinue).click().perform();

			//so14
			//JavascriptExecutor js=(JavascriptExecutor)driver;
			//js.executeScript("arguments[0].click();", btnContinue);

			//Sol 5
			//btnContinue.sendKeys(Keys.RETURN);

			//So16
			//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		}
		
		public String checkConfirmationmsg()
		{
			try {
				return (msgConfirmation.getText());
				}
			catch (Exception e) {
				return (e.getMessage());
				}
		}
		

}
