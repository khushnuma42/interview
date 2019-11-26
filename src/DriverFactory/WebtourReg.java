package DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ReadExcelUtil;

public class WebtourReg {
WebDriver driver;
String inputpath="E:\\Selenium_Mrng\\FrameWorks\\TestInput\\WebtourRegister.xlsx";
String outputpath="E:\\Selenium_Mrng\\FrameWorks\\TestOutput\\register.xlsx";
ExtentReports report;
ExtentTest test;
@BeforeTest
public void setup()
{
report=new ExtentReports("./Reports/register.html");
System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
driver=new ChromeDriver();
}
@Test
public void verifyregister()throws Throwable
{
//to access excel methods create object
ReadExcelUtil xl=new ReadExcelUtil(inputpath);	
//count no of rows
int rc=xl.rowCount("Register");
int cc=xl.colCount("Register");
Reporter.log("n of rows::"+rc+" "+"no of columns are::"+cc,true);
for(int i=1;i<=rc;i++)
{
driver.get("http://newtours.demoaut.com/");	
driver.manage().window().maximize();
	test=report.startTest("Verify Register");
String fname=xl.getCelldata("Register", i, 0);
String lname=xl.getCelldata("Register", i, 1);
String phone=xl.getCelldata("Register", i, 2);
String email=xl.getCelldata("Register", i, 3);
String address1=xl.getCelldata("Register", i, 4);
String address2=xl.getCelldata("Register", i, 5);
String city=xl.getCelldata("Register", i, 6);
String state=xl.getCelldata("Register", i, 7);
String pcode=xl.getCelldata("Register", i, 8);
String country=xl.getCelldata("Register", i, 9);
String username=xl.getCelldata("Register", i, 10);
String password=xl.getCelldata("Register", i, 11);
String cpassword=xl.getCelldata("Register", i, 12);
driver.findElement(By.partialLinkText("REG")).click();
Thread.sleep(3000);
driver.findElement(By.name("firstName")).sendKeys(fname);
driver.findElement(By.name("lastName")).sendKeys(lname);
driver.findElement(By.name("phone")).sendKeys(phone);
driver.findElement(By.name("userName")).sendKeys(email);
driver.findElement(By.name("address1")).sendKeys(address1);
driver.findElement(By.name("address2")).sendKeys(address2);
driver.findElement(By.name("city")).sendKeys(city);
driver.findElement(By.name("state")).sendKeys(state);
driver.findElement(By.name("postalCode")).sendKeys(pcode);
new Select(driver.findElement(By.name("country"))).selectByVisibleText(country);
driver.findElement(By.name("email")).sendKeys(username);
driver.findElement(By.name("password")).sendKeys(password);
driver.findElement(By.name("confirmPassword")).sendKeys(cpassword);
driver.findElement(By.name("register")).click();
if(password.equals(cpassword))
{
String message=driver.findElement(By.xpath("//font[contains(text(),'Thank you for registering.')]")).getText();	
Reporter.log(message+"Register success",true);	
//write message into result column
xl.setCellData("Register", i, 13, message, outputpath);
//write as pass into status column
xl.setCellData("Register", i, 14, "Pass", outputpath);
xl.greenColour("Register", i, 14, outputpath);
test.log(LogStatus.PASS, message);
}
else 
{
Reporter.log("Register fail",true);
xl.setCellData("Register", i, 13, "Register Fail", outputpath);
xl.setCellData("Register", i, 14, "Fail", outputpath);
xl.redColour("Register", i, 14, outputpath);
test.log(LogStatus.FAIL, "Register fail");
}
report.flush();
report.endTest(test);
}
xl.closefile();
}
@AfterTest
public void teardown()
{
driver.quit();	
}
}














