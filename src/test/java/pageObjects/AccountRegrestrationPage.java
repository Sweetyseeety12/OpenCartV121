package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegrestrationPage extends BasePage{

	//constructor
	
	public AccountRegrestrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	//locators

@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelePhone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
@FindBy(xpath="//input[@name='agree']") WebElement txtPrivacyPolicy;
@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

	
//actions

public void setFirstName(String fname) {
	txtFirstName.sendKeys(fname);
}
public void setLastName(String lname) {
	txtLastName.sendKeys(lname);
}
public void setEmail(String email) {
	txtEmail.sendKeys(email);
}
public void setTelePhone(String tel) {
	txtTelePhone.sendKeys(tel);
}
public void setPassword(String pwd) {
	txtPassword.sendKeys(pwd);
}
public void setConfirmationPassword(String pwd) {
	txtConfirmPassword.sendKeys(pwd);
}
public void setPrivacyPolicy() {
	txtPrivacyPolicy.click();;
}
public void ClickContinue() {
	//sol1
	btnContinue.click();
	
	//sol2
	//btnContinue.submit();

	//sol3
	//Actions act=new Actions(driver);
	//act.moveToElement(btnContinue).click().perform();

	//sol 4
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", btnContinue);

	//sol 5
	//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

}	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	
	
	
}



	
	

