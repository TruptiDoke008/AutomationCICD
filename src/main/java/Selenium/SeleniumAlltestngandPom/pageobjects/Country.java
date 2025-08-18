package Selenium.SeleniumAlltestngandPom.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Country {
	
	WebDriver driver; // instance variable

	public Country(WebDriver driver) 
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement CheckBox;
	
//	List<WebElement> country = driver.findElements(By.cssSelector(".ta-item"));
	@FindBy(css = ".ta-item")
	List<WebElement> Contries; //store all the country values into the list

	
	public void check()
	{
		//send values to country dropdown
		CheckBox.sendKeys("India");
	}
	
	public void SendValues()
	{
		Contries.stream()
	       .filter(c -> c.getText().equalsIgnoreCase("India"))
	       .findFirst()
	       .ifPresent(WebElement::click); // clicks on it if found
	}

}
