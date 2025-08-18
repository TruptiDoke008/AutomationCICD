package Selenium.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.SeleniumAlltestngandPom.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	
	public Landingpage landpg;
	
	public WebDriver initializeDriver() throws IOException
	{
		//Properties Class : This class is used to set any properties on global level.
		//if you want to run single test in multiple browsers then how you will run?.
		//Use Properties class for that. Create file with extension with ".properties" and call it here.
		Properties prop = new Properties();
		
//		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +  		"//src//main//java//Selenium//resources//GlobalDate.properties"); 
		FileInputStream fis = new FileInputStream("src/main/java/Selenium/resources/GlobalDate.properties");
		//System.getProperty("user.dir"): used to short the path location dynamically.
		//to take the file as input, give file path
		
		prop.load(fis); //load the file as input
		
//		String browserName = System.getProperty("broswer")!=null ? System.getProperty("broswer") : prop.getProperty("browser");
		//Use java ternary operator bcz from cmd we are giving the command if browser Chrome is not available use firefox or any other browser.
//		in cmd pass the command: mvn -Dbrowser=Edge
				
//		OR
		
		String browserName = System.getProperty("browser", prop.getProperty("browser"));
		
//		String browserName = prop.getProperty("browser"); //get property from file.
		
		
		if(browserName.contains("chrome")) 
		{
			
			ChromeOptions options = new ChromeOptions(); //for headless mode.
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
//			headless mode means, chrome will not invoke in front for execution but in the backend execution is going on.
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));//optional //help to run in full screen
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			//Run code in Edge.
			System.setProperty("webdriver.edge.driver", "C:\\Program Files\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		String JsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//FileUtils.readFileToString(new File("")): Read Json file and convert into the string.
		//JsonContent: store the data
		
		//String convert to HashMap.
		//Jackson Databid: this is a dependency which helps to convert string content to HashMap.
		//Add this dependencies to Pom file, Go to maven repository > search for Jackson Databid > add to pom file.
		
		ObjectMapper mapper = new ObjectMapper(); //call this class.
		
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){	
			
		});
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);
	    File file = new File(System.getProperty("user.dir") + "//Reports" + testCaseName + ".png");
	    FileUtils.copyFile(src, file);
	    return System.getProperty("user.dir") + "//Reports" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
// (alwaysRun=true) means if there is particular group is being called/run via testng xml then it will help to call this 
//	method to and does not restrict to from any other method.
	public Landingpage Launchapp() throws IOException
	{
		driver = initializeDriver();
	
		landpg = new Landingpage(driver);
//		passing the driver object into the Landingpage constructor.
		landpg.goTo();
//		Land on URL
		return landpg;
	}

	@AfterMethod(alwaysRun=true)
// (alwaysRun=true) means if there is particular group is being called/run via testng xml then it will help to call this 
//		method to and does not restrict to from any other method.	
	public void Close()
	{
		 if (driver != null) {
		        driver.close();
	}
	}
}

