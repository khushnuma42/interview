package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ApplicationLayer.EmpPage;
import ApplicationLayer.LoginPage;
import ApplicationLayer.LogoutPage;
import Utilities.ReadExcelUtil;
public class PomDriverScript {
WebDriver driver;
ExtentReports report;
ExtentTest test;
String inputpath="E:/Selenium_Mrng/FrameWorks/TestInput/Employee.xlsx";
String outputpath="E:/Selenium_Mrng/FrameWorks/TestOutput/emp.xlsx";
@BeforeTest
public void setup()
{
report=new ExtentReports("./Reports/Datadrivenpom.html");
System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
driver=new ChromeDriver();
driver.get("http://orangehrm.qedgetech.com");
driver.manage().window().maximize();
LoginPage login=PageFactory.initElements(driver, LoginPage.class);
login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void empcreation()throws Throwable
{
EmpPage emp=PageFactory.initElements(driver, EmpPage.class);	
//access excel methods
ReadExcelUtil xl=new ReadExcelUtil(inputpath);
int rc=xl.rowCount("Emp");
int cc=xl.colCount("Emp");
Reporter.log("no of rows are::"+rc+"  "+"no of columns are::"+cc,true);
for(int i=1;i<=rc;i++)
{
test=report.startTest("Verify Emp");
String firstname=xl.getCelldata("Emp", i, 0);
String lastname=xl.getCelldata("Emp", i, 1);
String Empid=xl.getCelldata("Emp", i, 2);
emp.addEmp(firstname, lastname, Empid);
if(driver.getCurrentUrl().contains("empNumber"))
{
Reporter.log("Emp Created",true);
test.log(LogStatus.PASS, "Emp Created");
xl.setCellData("Emp", i, 3, "Pass", outputpath);
xl.greenColour("Emp", i, 3, outputpath);
}
else
{
Reporter.log("Emp Failed",true);
test.log(LogStatus.FAIL, "Emp Not Created");
xl.setCellData("Emp", i, 3, "Fail", outputpath);
xl.redColour("Emp", i, 3, outputpath);
}
report.flush();
report.endTest(test);
}
}
@AfterTest
public void teardown()throws Throwable
{
LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
logout.Logoutapp();
driver.close();
}
}











