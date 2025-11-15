package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		//properties file reading from resources
	FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		//logger
		 logger=LogManager.getLogger(this.getClass());
		
		//selenium grid for remote 
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			
			DesiredCapabilities cap =new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}else if (os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
		
		    }else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching os");
				return;
			}
			
			//browser
			 switch (br.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			case "firefox":cap.setBrowserName("Firefox");break;
			default:System.out.println("No matching browser ");return;
				
			}
		
			 driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		
		//browse is local 
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
			
			switch (br.toLowerCase()) {
			case "chrome":driver=new ChromeDriver();break;
			case "edge":driver=new EdgeDriver();break;
			case "firefox":driver=new FirefoxDriver();break;
			default:System.out.println("No matching browser ");return;
				
		}

		}	
		
		//parallel testing or serial execution
	/*	switch(br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default: System.out.println("Invalid browser");return;
		}
	*/
	
	
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.get("https://tutorialsninja.com/demo/");
		
		driver.get(p.getProperty("appURL"));
		
		driver.manage().window().maximize();
		
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void teardown() {
		driver.quit();
	}
	public String randomeString() {
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlpheNumberic() {
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatedNumber);
	}
	public String captureScreen(String tname) throws IOException  {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;

	}
	


}
