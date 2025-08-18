package Selenium.SeleniumAlltestngandPom.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProdToCart extends Product_Catlog{

	WebDriver driver; // instance variable

	public ProdToCart(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> cartprods = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	static
	List<WebElement> cartprods;
	

	
//  driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkout;
	
	
	
	public static Boolean prodTO(String productname)
	{	
		Boolean match = cartprods.stream().anyMatch(cartprod-> cartprod.getText().equalsIgnoreCase(productname));
		//anyMatch used for if any match
//		Assert.assertTrue(match); //Check if the product is in cart or not.
		return match;
	}
	
	public void CheckouT()
	{
		checkout.click();//Click on Checkout Button
	}
}
