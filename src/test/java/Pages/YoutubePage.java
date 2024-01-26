package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YoutubePage  extends BasePage{

	public YoutubePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	//Elements 
	@FindBy (xpath ="//*[@name='q']")
	public WebElement  searchbarElement;

}
