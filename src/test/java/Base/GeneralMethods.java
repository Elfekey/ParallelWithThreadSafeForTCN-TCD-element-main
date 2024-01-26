package Base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.screenShots;

public class GeneralMethods extends BaseTest{

	screenShots screenShots = new Utilities.screenShots();


//	private String TcNameGM;
//	private String TcDescriptionGM;
//


	//Start###############
	//trying new approach to make it more dynamic and automated to get tcname and description once
//	public void setTCNameGM(String TCN) {
//		TcNameGM =TCN;
//	}
//	public String getTCNameGM() {
//		return TcNameGM;
//	}
////	public void setTCDescriptionGM(String TCD) {
//		TcDescriptionGM =TCD;
//	}
//	public String getTCDescriptionGM() {
//		return TcDescriptionGM;
//	}

	//trying to call it once only in the beginning of tc to get the driver and the tcname and tcdescription only one 
//	private synchronized void TakeSC() {
//		screenShots.takeFullScreenshot(getTCNameGM(),getTCDescriptionGM(),getDriver());//,getDriver() //
//	}
	//End ##################
	
	
	//we must provide tcname and description in the testcase cause it's unique 
//	public  void NavigateTo(String url) {//,String tcN,String TcDesc
//		getDriver().navigate().to(url);
//		TakeSC();
//	}
		public synchronized void NavigateTo(String url,String tcN,String TcDesc) {//,String tcN,String TcDesc
	
			getDriver().navigate().to(url);
//			screenShots.takeFullScreenshot(tcN,TcDesc,getDriver());
		}


	public  void sendText(WebElement e,String text,String tcN,String TcDesc) {
		e.clear();
		e.sendKeys(text);
//		screenShots.takeFullScreenshot(tcN,TcDesc,getDriver());

	}





}
