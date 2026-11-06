package basepkgswag;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import swagpagepkg.Swagloginpage;

public class Swagbase {
	public static WebDriver driver;
	public Swagloginpage swagobj;
	
	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeTest
	public void setup()
	{
		reporter = new ExtentSparkReporter("./Reports/myreport1.html");//Create a report
		reporter.config().setDocumentTitle("Automationreport");
		reporter.config().setReportName("functional test");
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Hostname", "Localhost");
		extent.setSystemInfo("OS", "windows10");
		extent.setSystemInfo("tester Name", "abc");
		extent.setSystemInfo("Browser Name", "chrome");
		
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void mthd(Method method)
	{
		swagobj = new Swagloginpage(driver);
	}
	
	@AfterMethod
	public void aftmth(ITestResult result) throws Exception {

	    if (result.getStatus() == ITestResult.FAILURE) {

	        test.log(Status.FAIL, "Test case failed is " + result.getName());
	        test.log(Status.FAIL, "Test case failed is " + result.getThrowable());

	    }
	    else if (result.getStatus() == ITestResult.SUCCESS) {

	        test.pass("Test Passed : " + result.getName());

	    }
	    else if (result.getStatus() == ITestResult.SKIP) {

	        test.skip("Test Skipped : " + result.getName());

	    }
	}

	@AfterTest
	public void endReport() {

	    extent.flush();
	    driver.quit();

	}
}
	



  
