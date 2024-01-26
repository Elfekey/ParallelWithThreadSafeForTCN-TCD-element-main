package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends BasePage {

	public GooglePage(WebDriver driver) {
		super(driver);
	}

	//Elements 
	@FindBy (xpath ="//*[@name='q']")
	public WebElement  googleSearchElement;


}
