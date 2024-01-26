package Base;


import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import Utilities.screenShots;

public class new_General_Methods extends BaseTest{

	screenShots screenShots = new Utilities.screenShots();

		public synchronized void NavigateTo(String url) {//,String tcN,String TcDesc
	
			getDriver().navigate().to(url);
			screenShots.takeFullScreenshot(getTestSuiteName(),getTCName(),getTCD(),getDriver());
			System.out.println("TCName And TCD USed  from Nav Are "+getTestSuiteName()+"    "+getTCName()+"    "+getTCD());
			getextentTestThreadLocal().log(Status.INFO, "Navigated to : "+url);
		}


	public  void sendText(WebElement e,String text) {
		e.clear();
		e.sendKeys(text);
		screenShots.takeFullScreenshot(getTestSuiteName(),getTCName(),getTCD(),getDriver());
		getextentTestThreadLocal().log(Status.INFO,text+" : sent to : "+e);
	}





}
