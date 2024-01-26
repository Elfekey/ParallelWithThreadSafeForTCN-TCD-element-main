package Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class retryFailedTCs implements IRetryAnalyzer{

	private int retryCount = 0;
    private static final int maxRetryCount =0;
	 
// to call it we use the below line above the test
    //	@Test(description = "This isTC5",retryAnalyzer =retryFailedTCs.class)
	public boolean retry(ITestResult result) {
		
		   if (retryCount < maxRetryCount) {
			      retryCount++;
			      return true;
			    }
			    return false;
			  }

	}

