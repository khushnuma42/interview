package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
this.driver=driver;
}
//define repository
@FindBy(name="txtUsername")
WebElement username;
@FindBy(xpath="//input[@id='txtPassword']")
WebElement password;
@FindBy(id="btnLogin")
WebElement login;
public void verifyLogin(String username,String password)
{
this.username.sendKeys(username);
this.password.sendKeys(password);
login.click();
}
}













