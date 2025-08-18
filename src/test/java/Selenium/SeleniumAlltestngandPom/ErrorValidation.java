package Selenium.SeleniumAlltestngandPom;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;


import Selenium.SeleniumAlltestngandPom.pageobjects.ProdToCart;
import Selenium.SeleniumAlltestngandPom.pageobjects.Product_Catlog;
import Selenium.TestComponents.*;
import Selenium.TestComponents.Retry;

public class ErrorValidation extends BaseTest{

		@Test(groups= {"ErrorsHandling"}, retryAnalyzer = Retry.class)
//		Check BaseTest.class
		public void LoginPgValidation() throws IOException, InterruptedException
		{
			
		String productname = "ZARA COAT 3"; //storing the string name into the string cz not write down the product name everywhere.
		
		landpg.LoginApp("truptidoke008@gmail.com","Vikroli@@@123");//Wrong credentials
//		Send values to the Action Method
		
		Assert.assertEquals("Incorrect email or password.", landpg.Error());
		
		}
		
		@Test()
//		Check BaseTest.class
		public void ProductValidation() throws IOException, InterruptedException
		{
			
		String productname = "ZARA COAT 3"; //storing the string name into the string cz not write down the product name everywhere.
		Product_Catlog Producs = landpg.LoginApp("satyamchaturvedi665@gmail.com","Boss@123"); //Another user.
//		Send values to the Action Method
	    List<WebElement> products = Producs.getProductList(); //Product List
	    Producs.getProduct(productname); //sending product name
	    Producs.addProdToCart(productname);

//		To check random product does not exist. Cz provided product name "ZARA COAT 3".
	    Boolean match = ProdToCart.prodTO("ZARA COAT 3");
	    Assert.assertFalse(match);
	}

}
