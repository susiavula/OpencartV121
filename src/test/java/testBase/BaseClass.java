package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	  @BeforeClass(groups= {"Sanity", "Master", "Regression"})
	  @Parameters({"os","browser"})
		public void setup(String os, String br) throws IOException
		{
		//Loading config.properties file
		  FileReader file=new FileReader("./src//test//resources//config.properties");
		  prop=new Properties();
		  prop.load(file);
		  
		  logger=LogManager.getLogger(this.getClass());
		 
		  if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		  {
			  DesiredCapabilities capabilities =new DesiredCapabilities();
			  //os
			  if(os.equalsIgnoreCase("windows"))
			  {
				  capabilities.setPlatform(Platform.WIN11);
			  }
			  else if(os.equalsIgnoreCase("mac"))
			  {
				  capabilities.setPlatform(Platform.MAC);
			  }
			  else if (os.equalsIgnoreCase("linux")) {
				    capabilities.setPlatform(Platform.LINUX);
			  }
			  else
			  {
				  System.out.println("No matching Os found");
				  return;
			  }
		  
		  
		  //browser
		  switch(br.toLowerCase())
		  {
		  case "chrome": capabilities.setBrowserName("chrome"); break;
		  case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
		  case "firefox": capabilities.setBrowserName("firefox"); break;
		  default : System.out.println("Enter correct browser.."); return;
		  }
		  
		  //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		  //driver = new RemoteWebDriver(new URL("http://192.168.0.100:4444/wd/hub"), capabilities);
		  //driver = new RemoteWebDriver(new URL("http://192.168.0.100:4444/wd/hub"),capabilities);
		  driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), capabilities);


		  }
		  
		  
		//Local system
		  
		  if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		  {
		  
		  switch(br.toLowerCase())
		  {
		  case "chrome": driver=new ChromeDriver(); break;
		  case "edge": driver=new EdgeDriver(); break;
		  case "firefox": driver=new FirefoxDriver(); break;
		  default : System.out.println("Enter correct browser.."); return;
		  }
		  }
		  
		  
		
			//driver=new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("appURL"));
			driver.manage().window().maximize();
		}
		@AfterClass(groups= {"Sanity", "Master", "datadriven"})
		public void tearDown()
		{
			driver.quit();
		}
		
		//Generate Strings randomly
		public String randomString()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
			
		}
		
		//Generate numbers randomly
		
		public String randomNumber()
		{
			String generatednumber=RandomStringUtils.randomNumeric(10);
			return generatednumber;
			
		}
		
		// Generate alpha numeric
		public String randomAlphaNumeric()
		{
			String generatedstring=RandomStringUtils.randomAlphabetic(3);
			String generatednumber=RandomStringUtils.randomNumeric(3);
			return (generatedstring+"@"+generatednumber);
			
		}
		
		public String captureScreen(String tname) throws IOException {

		    if (driver == null) {
		        System.out.println("Driver is null, cannot capture screenshot.");
		        return null;
		    }

		    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		    String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname + "_" + timeStamp + ".png";
		    File targetFile = new File(targetFilePath);

		    sourceFile.renameTo(targetFile);

		    return targetFilePath;
		}


}
