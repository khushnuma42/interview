package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class userPage {
WebDriver driver;
Actions ac;
public userPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="menu_admin_viewAdminModule")
WebElement clikadmin;
@FindBy(id="menu_admin_UserManagement")
WebElement clikuserman;
@FindBy(id="menu_admin_viewSystemUsers")
WebElement clikusers;
@FindBy(id="btnAdd")
WebElement clikaddd;
@FindBy(name="systemUser[employeeName][empName]")
WebElement ename;
@FindBy(name="systemUser[userName]")
WebElement username;
@FindBy(name="systemUser[password]")
WebElement password;
@FindBy(name="systemUser[confirmPassword]")
WebElement cpassword;
@FindBy(name="btnSave")
WebElement clicksave;
public void addUser(String ename,String username,String password,String cpassword)
throws Throwable{
ac=new Actions(driver);
ac.moveToElement(clikadmin).perform();
Thread.sleep(3000);
ac.moveToElement(clikuserman).perform();
Thread.sleep(3000);
ac.moveToElement(clikusers).click().perform();
Thread.sleep(3000);
ac.moveToElement(clikaddd).click().perform();
Thread.sleep(3000);
this.ename.sendKeys(ename);
this.username.sendKeys(username);
this.password.sendKeys(password);
this.cpassword.sendKeys(cpassword);
this.clicksave.click();
}

}










