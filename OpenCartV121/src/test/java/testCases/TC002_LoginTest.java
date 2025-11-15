package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;




public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Sanity","Master"})
	public void verify_Login() {
		
		logger.info("**** Starting TC002_LoginTest ******");
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccont");
		hp.ClickLogin();
		logger.info("Clicked on Login");
		
		//Login
		LoginPage lp=new LoginPage(driver);
		
		logger.info("Providing existing User Details...");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
	
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true,"Login failed");
		
		Assert.assertTrue(targetPage);
		
		}catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("**** Finished TC002_LoginTest ******");
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
