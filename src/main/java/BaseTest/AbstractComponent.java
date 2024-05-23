package BaseTest;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent{
	
	public WebDriver driver; 
	public AbstractComponent(WebDriver driver){
		this.driver =driver;

	}
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	public void VisibilityOfElement(WebElement toogle) {
		wait.until(ExpectedConditions.visibilityOf((toogle)));
	}
	
//	s-> element.isDisplayed()

}
