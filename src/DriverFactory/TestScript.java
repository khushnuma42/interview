package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ApplicationLayer.EmpPage;
import ApplicationLayer.LoginPage;
import ApplicationLayer.LogoutPage;
import ApplicationLayer.userPage;
public class TestScript {
WebDriver driver;
ExtentReports report;
ExtentTest test;
@BeforeTest
public void generateReport()
{
report=new ExtentReports("./Reports/pom.html");
}
@BeforeMethod
public void setup()
{
System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
driver=new ChromeDriver();
driver.get("http://orangehrm.qedgetech.com");
driver.manage().window().maximize();
//accesing locators and methods
LoginPage login=PageFactory.initElements(driver, LoginPage.class);
login.verifyLogin("Admin", "Qedge123!@#");
}
@Test(priority=0)
public void usercreation()throws Throwable
{
test=report.startTest("User Creation");	
userPage user=PageFactory.initElements(driver, userPage.class);
user.addUser("Akhilesh Kumar", "Akhilesh2390", "Akhilesh@98712", "Akhilesh@98712");
if(driver.getCurrentUrl().contains("viewSystemUsers"))
{
Reporter.log("User Creation Success",true);
test.log(LogStatus.PASS, "User created");
}
else
{
Reporter.log("User Creation Fail",true);
test.log(LogStatus.FAIL, "user not created");
}
}
@Test(priority=1)
public void empcreation()throws Throwable
{
test=report.startTest("Employee Creation");	
EmpPage emp=PageFactory.initElements(driver, EmpPage.class);
emp.addEmp("Akhilesh", "siva", "98765543");
if(driver.getCurrentUrl().contains("empNumber"))
{
Reporter.log("Emp Created",true);
test.log(LogStatus.PASS, "Emp Created");
}
else
{
Reporter.log("Emp Failed",true);
test.log(LogStatus.FAIL, "Emp Not Created");
}
}
@AfterMethod
public void teardown()throws Throwable
{
LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
logout.Logoutapp();
report.flush();
report.endTest(test);
driver.close();
}
}













