package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegrestrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegestrationTest  extends BaseClass{
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() {
		logger.info("*****  Starting TC001_AccountRegestrationTest   ******");
		
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
	
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		
		AccountRegrestrationPage regpage=new AccountRegrestrationPage(driver);
		
		logger.info("Providing customer details");
		regpage.setFirstName(randomeString().toUpperCase());//it will generate random string and convert to uppercase
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");//randomly generated the email
		regpage.setTelePhone(randomeNumber());
		
		//String password =randomAlphaNumeric();
		
		String password=randomeAlpheNumberic();
		
		regpage.setPassword(password);
		regpage.setConfirmationPassword(password);
		regpage.setPrivacyPolicy();
		regpage.ClickContinue();
		
		logger.info("Validating expected message");
		String confirm=regpage.getConfirmationMsg();
		if(confirm.equals("Your Account Has Been Created!")) {
			
			Assert.assertTrue(true);
		}else {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirm, "Your Account Has Been Created!!");
		
		}catch(Exception e) {
		
			Assert.fail();
		}
		
		logger.info("*****  Finished TC001_AccountRegestrationTest   ******");
		
	}
	
	
	
	
	
	

}
