package stepDef;

import action.Action;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.starHealtestNG;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URL;
import java.time.Duration;


public class StarHealth_testNG_PrintLinks extends Action {

    private WebDriver driver;

    //helps to generate the logs in the test report.

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;



    // Using testNG  @BeforeClass --> Launching The Browser ...
    @BeforeClass
    public synchronized void setUp(){


        // initialize the HtmlReporter

        // Create an object of Extent Reports
        extent = new ExtentReports();

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report_TestNG_PrintHomePageLinks.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "StarHealth Application - Home Print All Links");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Test Team");
        spark.config().setDocumentTitle("Report Print All Links Application QA ");
        // Name of the report
        spark.config().setReportName("StarHealth Application Using Selenium testNG ");
        // Dark Theme
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Validate StarHealth Application Using Selenium testNG");



        // System Property for Chrome Driver

        try {
            //DesiredCapabilities dc = new DesiredCapabilities();
            //dc.setBrowserName("chrome");
            //System.setProperty("webdriver.chrome.driver", "/Users/mithunroy/Downloads/BrowserDrivers/chromedriver");
            // Instantiate a ChromeDriver class.
            WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
            driver = new ChromeDriver();
            //driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
            //driver = new ChromeDriver();
            //Maximize the browser
            driver.manage().window().maximize();
            driver.get("https://www.starhealth.in/");
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            Thread.sleep(20000);
            logger.createNode("User can Successfully launch the Chrome Browser");

        }
        catch(Exception e){logger.fail("Instantiate of the Chrome Browser Failed");}
    }


    @Test
    public synchronized void starhealth002_PrintAllLink(){


        // Get the Count of All Links ...

        int totalLinkCount = driver.findElements(By.xpath("//a")).size();

        for(int i=1 ; i<=totalLinkCount ; i++){

            logger.pass(driver.findElement(By.xpath("(//a)["+i+"]")).getText().toString());
        }
    }



    // Using testNG  @AfterClass  --> Closing The Browser ...
    @AfterClass
    public synchronized void tearDown(){

        driver.quit();
        logger.pass("User Successfully closed the driver session");
        extent.flush();


    }

}
