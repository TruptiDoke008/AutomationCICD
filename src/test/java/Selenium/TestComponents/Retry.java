package Selenium.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
/*
The "IRetryAnalyzer" interface in TestNG is used to automatically retry failed test cases a certain number of times before marking them as failed.

	It’s especially useful when dealing with flaky tests — tests that fail intermittently due to network issues, timing problems, or environmental instability, rather than actual defects in the code.
*/
	    private int retryCount = 0;
	    private static final int maxRetryCount = 2; // retry twice

	    @Override
	    public boolean retry(ITestResult result) {
	        if (retryCount < maxRetryCount) {
	            retryCount++;
	            return true;
	        }
	        return false;
	    }
	}