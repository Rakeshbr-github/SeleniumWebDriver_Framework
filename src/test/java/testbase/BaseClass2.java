package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass2 {

	public static WebDriver driver; //made as static to support extentreport capturescrenshot base class object
	public Logger logger; //log4j2
	public Properties p;
	
	
	@BeforeClass(groups= {"sanity","regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		 
		logger=LogManager.getLogger(this.getClass());//Log4j
         switch(browser.toLowerCase())
         {
         case "chrome": driver=new ChromeDriver();break;
         case "edge": driver=new EdgeDriver();break;
         default : System.out.println("Invalid browser name...."); return;
         }
         driver.manage().deleteAllCookies();
 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
// 		driver.get("https://tutorialsninja.com/demo/");
 		driver.get(p.getProperty("appURL")); //reading from properties file
		driver.manage().window().maximize();

	}
	
	@AfterClass(groups= {"sanity","regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
