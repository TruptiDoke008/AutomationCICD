package Selenium.SeleniumAlltestngandPom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelenimParc.AbstractComponents.AbstractComp;

public class Landingpage extends AbstractComp{

	WebDriver driver; // instance variable

	public Landingpage(WebDriver driver) 
	{
		super(driver); //Sending driver from child class to parent class, nothing but the AbstractComp class	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Initializes all WebElements annotated with @FindBy using
		// PageFactory.initElements() — this is required to make the @FindBy annotations
		// work.
	}
//	driver.findElement(By.id("userEmail")).sendKeys("truptidoke008@gmail.com");
	@FindBy(id = "userEmail")
	WebElement UserEmail;

//	driver.findElement(By.id("userPassword")).sendKeys("Vikroli@123");
	@FindBy(id = "userPassword")
	WebElement UserPass;

//	driver.findElement(By.id("login")).click();
	@FindBy(id = "login")
	WebElement LoginBtn;
	
//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']") //Customize css
	WebElement ErrorMsg;
	
	public Product_Catlog LoginApp(String email, String Pass) //Action method
	{
		UserEmail.sendKeys(email);
		UserPass.sendKeys(Pass);
		LoginBtn.click();
		
		 Product_Catlog Producs = new Product_Catlog(driver); 
		 return Producs;
	}
	
	public String Error()
	{
		WaitforWebElementToAppear(ErrorMsg); //Check AbstractComp class 
		return ErrorMsg.getText();
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	
}


