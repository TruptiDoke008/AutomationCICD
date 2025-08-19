package Selenium.SeleniumAlltestngandPom;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.SeleniumAlltestngandPom.pageobjects.Country;
import Selenium.SeleniumAlltestngandPom.pageobjects.Landingpage;
import Selenium.SeleniumAlltestngandPom.pageobjects.OrderPg;
import Selenium.SeleniumAlltestngandPom.pageobjects.ProdToCart;
import Selenium.SeleniumAlltestngandPom.pageobjects.Product_Catlog;
import Selenium.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder  extends BaseTest{
	    
	   String productname = "ZARA COAT 3"; //storing the string name into the string cz not write down the product name everywhere.

		@Test(dataProvider="getData",groups="Purches")
//		Check BaseTest.class
		public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
		Product_Catlog Producs = landpg.LoginApp(input.get("email"),input.get("Pass"));
//		Send values to the Action Method
		
	    List<WebElement> products = Producs.getProductList(); //Product List
	    Producs.getProduct(input.get("productname")); //sending product name
	    Producs.addProdToCart(input.get("productname"));

	    ProdToCart cart = new ProdToCart(driver);
	    cart.ClickBtn();
	    cart.prodTO(input.get("productname"));
	    cart.CheckouT();
		
	    Country checkBox = new Country(driver);
	    checkBox.check();
	    checkBox.SendValues();

		//Scroll the page.
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		//wait until the place order is not visible bcz in above drop down we select the value "India" hence Place order 
		//takes time to get visible.
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();//Place the order
		
		String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		//Test is caps in the website and small letters in the code hence need to apply ignore case.
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
	}
		
		@Test(dependsOnMethods={"SubmitOrder"})
		//Depends on above method, When above method get executed then after that this method will be get executed.
		public void OrderHistory() {
		    Product_Catlog Producs = landpg.LoginApp("truptidoke008@gmail.com","Vikroli@123");
		    OrderPg order = Producs.goToOrderPg();
		    Assert.assertTrue(order.VerifyOrderdisplay(productname));
		}
		
		
		@DataProvider
		public Object[][] getData() throws IOException
		{		
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Selenium//data//PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
//			OR
			
//			HashMap<String,String> map = new HashMap<String,String>();
//			//HashMap: use if you want to send large number of data.
//			//<String,String> used bcz we are sending object, string etc.
//			map.put("email", "truptidoke008@gmail.com");
//			map.put("Pass", "Vikroli@123");
//			map.put("productname", "ZARA COAT 3");
//			
//			HashMap<String,String> map1 = new HashMap<String,String>();
//			map1.put("email", "satyamchaturvedi665@gmail.com");
//			map1.put("Pass", "Boss@123");
//			map1.put("productname", "ADIDAS ORIGINAL");
			
			
			
//			return new Object[][] {{map},{map1}};
		
		}
		
//		OR
//		
//		@DataProvider
//		public Object[][] getData1()
//		{
//			return new Object[][] {{"truptidoke008@gmail.com","Vikroli@123","ZARA COAT 3"},{"satyamchaturvedi665@gmail.com","Boss@123","ADIDAS ORIGINAL"}};
//		}
		
	
}
