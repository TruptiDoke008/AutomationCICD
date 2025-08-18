package Selenium.resources;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportsNG {
	
	static ExtentReports extent;//Declare globally so both the method access it.
	WebDriver driver;
	 
	public static ExtentReports getReportObject()
	{
		String path= System.getProperty("user.dir") + "\\Reports\\index.html";
		// reports folder automatically get's created in the project path: \\reports\\index.html
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automations Results"); //set the result name.
		reporter.config().setDocumentTitle("Test Results");//Heading of report
		
		//ExtentReports is main class, drive all reports
		extent = new ExtentReports();
		extent.attachReporter(reporter);//main class have knowledge of above class.
		extent.setSystemInfo("Tester", "Trupti Doke"); //tester info	
		
		return extent;
	}
	
	

}
