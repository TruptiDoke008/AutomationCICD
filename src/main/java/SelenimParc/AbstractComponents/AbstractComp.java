package SelenimParc.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.SeleniumAlltestngandPom.pageobjects.OrderPg;
import Selenium.SeleniumAlltestngandPom.pageobjects.ProdToCart;

public class AbstractComp {
	//This class contains all the common headers. 
	
	static WebDriver driver; //local driver variable
	
	public AbstractComp(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();	
	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Btn;
	
	@FindBy(css="[routerlink*='myorders']")
	static
	WebElement OrderHeader;

	public void WaitforElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//wait until list does not get loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitforWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		//wait until list does not get loaded
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void ClickBtn()
	{
		//click on cart button
		Btn.click();
	}
	
	public static OrderPg goToOrderPg()
	{
		//click on orders button
		OrderHeader.click();
		OrderPg order = new OrderPg(driver);
		return order;
	}
	
	
	public void WaitforElementToDiappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
