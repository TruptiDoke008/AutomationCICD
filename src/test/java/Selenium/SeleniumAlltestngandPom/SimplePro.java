package Selenium.SeleniumAlltestngandPom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Selenium.SeleniumAlltestngandPom.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SimplePro {

	public static void main(String[] args) {
		
		String productname = "ZARA COAT 3"; //storing the string name into the string cz not write down the product name everywhere.
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		driver.findElement(By.id("userEmail")).sendKeys("truptidoke008@gmail.com"); //UserID
		driver.findElement(By.id("userPassword")).sendKeys("Vikroli@123"); //Pass
		driver.findElement(By.id("login")).click();
		
//		passing the driver object into the Landingpage constructor.
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait until list does not get loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'col-lg-4')]")));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'col-lg-4')]"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		//Searching text ("ZARA COAT 3"), findFirst() give very first element if there no such element
		//give null value.
		
		//prod store the product which are having ZARA COAT 3 name.
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //product added to cart
		
		//wait until the toast notification is added.
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		
		//wait until loading page disappears.
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		
		
		List<WebElement> cartprods = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartprods.stream().anyMatch(cartprod-> cartprod.getText().equalsIgnoreCase(productname));
		//anyMatch used for if any match
		Assert.assertTrue(match); //Check if the product is in cart or not.

		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click(); //Click on checkout.
		
		//send values to country dropdown
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		
		//store all the country values into the list
		List<WebElement> country = driver.findElements(By.cssSelector(".ta-item"));
		
		country.stream()
	       .filter(c -> c.getText().equalsIgnoreCase("India"))
	       .findFirst()
	       .ifPresent(WebElement::click); // clicks on it if found

		//Scroll the page.
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		//wait until the place order is not visible bcz in above drop down we select the value "India" hence Place order 
		//takes time to get visible.
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='Place Order ']")));
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();//Place the order
		
		String text = driver.findElement(By.xpath(".hero-primary")).getText();
		
		//Test is caps in the website and small letters in the code hence need to apply ignore case.
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
		
		driver.quit();

	}

}
