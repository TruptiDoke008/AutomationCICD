package cucumberrr.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Selenium.SeleniumAlltestngandPom.pageobjects.Landingpage;
import Selenium.SeleniumAlltestngandPom.pageobjects.ProdToCart;
import Selenium.SeleniumAlltestngandPom.pageobjects.Product_Catlog;
import Selenium.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionimplementation extends BaseTest{
	
	public Landingpage landpg;
	public Product_Catlog Producs;
	@Given("I landed on Ecomerce Page")
	public void I_landed_on_Ecomerce_Page() throws IOException
	{
		landpg = Launchapp(); //From BaseTest
	}
	
	@Given("^Logged in with username (.+) and with password (.+)$")
	//(.+): it's regular expression which we use bcz we don't know if the username and pass are stings or integers.
	//Regular expression starts with "^" cap & "$" dollar
	
	public void logged_in_with_username_and_pass(String username, String password)
	{
		Producs = landpg.LoginApp(username, password); //check class SubmitOrder
	}
	
	@When("^When I add product (.+) from Cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products = Producs.getProductList(); //Product List
	    Producs.getProduct(productName); //sending product name //check class SubmitOrder
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void check_out_Order(String productName) throws InterruptedException
	{
		//check class SubmitOrder
		Producs.addProdToCart(productName);

	    ProdToCart cart = new ProdToCart(driver);
	    cart.ClickBtn();
	    cart.prodTO(productName);
	    cart.CheckouT(); 
	}
	
	@Then("{string} message is disaplyed on ConfirmationPage.")
	public void message_displayed_Confirmition_Page(String string)
	{
String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		//Test is caps in the website and small letters in the code hence need to apply ignore case.
		Assert.assertTrue(text.equalsIgnoreCase(string));
		driver.close();
	}
	
	
	
}