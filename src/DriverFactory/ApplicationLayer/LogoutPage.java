package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
WebDriver driver;
Actions ac;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="welcome")
WebElement clikwelcome;
@FindBy(linkText="Logout")
WebElement cliklogout;
public void Logoutapp()throws Throwable
{
ac=new Actions(driver);
ac.moveToElement(clikwelcome).click().perform();
Thread.sleep(4000);
ac.moveToElement(cliklogout).click().perform();
Thread.sleep(4000);
}
}
