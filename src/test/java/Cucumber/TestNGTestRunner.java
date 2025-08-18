package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber", glue="cucumberrr.stepDefinitions", monochrome=true,
plugin= {"html:target/cucmber.html"})
//@CucumberOptions(): use for run the tests, give the feature file path
//glue attribute use for give the step definition file
//monochrome, use to generate output in the readable form
//plugin, use if you want to store/want the output in the html file, along with this kindly give the path 
//where you want to generate the file.

//Run Cucumber files via this whole class
//AbstractTestNGCucumberTests: this is class, cucumber understand run the test in the testng

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
	
	
}
