package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class EmpPage {
WebDriver driver;
Actions ac;
public EmpPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="menu_pim_viewPimModule")
WebElement clickpim;
@FindBy(id="btnAdd")
WebElement clickadd;
@FindBy(name="firstName")
WebElement fname;
@FindBy(name="lastName")
WebElement lname;
@FindBy(name="employeeId")
WebElement eid;
@FindBy(id="btnSave")
WebElement clicksave;
public void addEmp(String fname,String lname,String eid)
throws Throwable{
ac=new Actions(driver);
ac.moveToElement(clickpim).click().perform();
Thread.sleep(3000);
ac.moveToElement(clickadd).click().perform();
Thread.sleep(3000);
this.fname.sendKeys(fname);
this.lname.sendKeys(lname);
this.eid.clear();
this.eid.sendKeys(eid);
ac.moveToElement(clicksave).click().perform();
Thread.sleep(5000);
}
}










