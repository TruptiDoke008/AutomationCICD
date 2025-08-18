package Selenium.SeleniumAlltestngandPom.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SelenimParc.AbstractComponents.AbstractComp;

public class Product_Catlog extends AbstractComp{
	

	WebDriver driver; // instance variable

	public Product_Catlog(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
//	List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'col-lg-4')]"));	
	@FindBy(xpath = "//div[contains(@class,'col-lg-4')]")
	List<WebElement> Products;
	
	@FindBy(css = ".ng-animating")
	WebElement Spinner;
	
	By produtsBy = By.xpath("//div[contains(@class,'col-lg-4')]");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		WaitforElementToAppear(produtsBy); //wait until the product get visible.
		return Products; //products list returning
		
	}
	
	public WebElement getProduct(String productname)
	{
		WebElement prod = Products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		//Searching text ("ZARA COAT 3"), findFirst() give very first element if there no such element
		//give null value.
		//prod store the product which are having ZARA COAT 3 name.
		return prod;
	}
	
	public ProdToCart addProdToCart(String productname) throws InterruptedException
	{
		WebElement prod = getProduct(productname);
		prod.findElement(addToCart).click(); //product added to cart
		WaitforElementToAppear(toast); //wait to appear toast notification
		WaitforElementToDiappear(Spinner); //wait until loading page disappears.
		return null;
	}
}

