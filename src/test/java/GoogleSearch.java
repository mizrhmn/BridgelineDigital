

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by MRAHMAN on 8/14/2017.
 */
public class GoogleSearch {

    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest test;
    public static String reportPath = null;

    @BeforeMethod
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        reportPath = "C:\\Users\\Mizan\\IdeaProjects\\BridgelineDigital\\src\\main\\java\\ExtentReport\\resutl.html";
        reports = new ExtentReports(reportPath, true);

    }//End of openBrowser


    @Test
    public static void googleSearch(){

        // Line below will start test in Extent Report
        test = reports.startTest("Google Search","Dimond Jig");

        //Line below will apply implicit wait for 10 second in the script
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        try{
            //Line below will open google home page and will send pass/fail report to the extent report
            driver.navigate().to("https://www.google.com/");
            test.log(LogStatus.PASS,"URL Test Pass");

        }catch (Exception e){
            test.log(LogStatus.FAIL, "URL not found, Test Failed" + e.getMessage());
        }

        try{
            //Line below will search for diamond jig through google search
            test.log(LogStatus.INFO, "Search for diamond jig");
            WebElement element = driver.findElement(By.name("q"));
            element.sendKeys("dimond jig");
            element.submit();
            test.log(LogStatus.PASS, "Search test passed");
         }catch (Exception e){
            test.log(LogStatus.FAIL, "Search for diamon jig failed");
        }

        try{
            test.log(LogStatus.INFO, "Clickong on 'Shopping");
            driver.findElement(By.linkText("Shopping")).click();
            test.log(LogStatus.PASS, "Clicked on Shopping successfully");

        }catch (Exception e){
            test.log(LogStatus.FAIL, "Shopping element not found");

        }
        try{
            test.log(LogStatus.INFO, "Clicking on third result");
            driver.findElement(By.xpath(".//*[@id='rso']/div/div/div[3]/div[1]/div[2]/h3/a")).click();
            test.log(LogStatus.PASS, "Clicked on third link successfully");
        }catch (Exception e){
            test.log(LogStatus.FAIL, "Unable to click on third link");

        }

    }//End of googleSearch

    @AfterMethod
    public static void closeBrowser() {
        //driver.quit();

        reports.endTest(test);

        reports.flush();

        driver.get("C:\\Users\\Mizan\\IdeaProjects\\BridgelineDigital\\src\\main\\java\\ExtentReport");
    }


} //End of GoogleSearch
