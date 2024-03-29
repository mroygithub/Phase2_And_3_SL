package stepDef;

import action.Action;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.starHealtestNG;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.testng.annotations.Parameters;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;



public class StarHealth_testNG_Home extends Action {

    private WebDriver driver;
    private starHealtestNG pageobj;

    //helps to generate the logs in the test report.

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;
    private String phone_no ,parent;



    // Using testNG  @BeforeClass --> Launching The Browser ...
    @BeforeClass
    public synchronized void setUp(){


        // initialize the HtmlReporter

        // Create an object of Extent Reports
        extent = new ExtentReports();

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report_HomePagePlan.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "StarHealth Application - Home Plan");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Test Team");
        spark.config().setDocumentTitle("StarHealth_testNG Application QA ");
        // Name of the report
        spark.config().setReportName("StarHealth Application Using Selenium testNG ");
        // Dark Theme
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Validate StarHealth Application Using Selenium testNG");


        // Creating an object for Page Object Model

        pageobj = new starHealtestNG();


        // System Property for Chrome Driver

        try {
            System.out.println("##### Starting Chrome Browser ############");
            //DesiredCapabilities dc = new DesiredCapabilities();
            //dc.setBrowserName("chrome");
            //System.setProperty("webdriver.chrome.driver", "/Users/mithunroy/Downloads/BrowserDrivers/chromedriver");
            // Instantiate a ChromeDriver class.
            //WebDriverManager.chromedriver().setup();
           // ChromeOptions opt = new ChromeOptions();
            WebDriverManager.chromedriver().driverVersion("102.0.5005.61").setup();
          //  opt.addArguments("--headless");
          //  opt.addArguments("--no-sandbox");
            //WebDriverManager.chromedriver().driverVersion("107.0.5304.110").setup();
            driver = new ChromeDriver();
            //driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
            //Maximize the browser
            driver.manage().window().maximize();
            driver.get("https://www.starhealth.in/");
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            logger.createNode("User can Successfully launch the Chrome Browser");
            Thread.sleep(20000);
        }
        catch(Exception e){logger.fail("Instantiate of the Chrome Browser Failed");}


    }

    @Test
    @Parameters({"altValue" , "name" , "mobile" , "email" , "url_text"})
    public synchronized void starhealth001_HomePageValidation(String altValue, String name , String mobile , String
            email , String url_text ){


        // Validate alt value of star health logo is ‘Star Health’ using testNG assertion

        String appAltValue = driver.findElement(pageobj.homepageLogo()).getAttribute("alt");
        Assert.assertEquals(appAltValue  , altValue);
        logger.pass("Logo Alt Value is as expected as ==>"+appAltValue);

        // Hover cursor on header menu option Plans

        WebElement plan_link = driver.findElement(pageobj.plan_link());

        //Creating object of an Actions class
        Actions action = new Actions(driver);

        //Performing the mouse hover action on the target element.
        action.moveToElement(plan_link).perform();

        // Click on forMyFamilyLink

        driver.findElement(pageobj.forMyFamilyLink()).click();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        logger.pass("User clicked on forMyFamilyLink");


        WebElement close_pop_up_link = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageobj.nameFiled()));
        driver.findElement(pageobj.nameFiled()).sendKeys(name);
        driver.findElement(pageobj.mobileFiled()).sendKeys(mobile);
        driver.findElement(pageobj.email_Filed()).sendKeys(email);
        Assert.assertTrue(driver.findElement(pageobj.authorized_Checkbox()).isSelected());

        // Click on Next button

        driver.findElement(pageobj.next_button()).click();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        logger.pass("User clicked on next Button");



        // Click on Back button


        WebElement backButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(pageobj.back_button()));

        backButton.click();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        logger.pass("User clicked on back Button");


        // Read Excel value for assertions ..

        String name_from_XL = read_data_from_XL("Name");
        String mobile_from_XL = read_data_from_XL("Mobile");
        String email_from_XL = read_data_from_XL("Email");


        Assert.assertEquals(name_from_XL,driver.findElement(pageobj.nameFiled()).getAttribute("value"));
        Assert.assertEquals(mobile_from_XL,driver.findElement(pageobj.mobileFiled()).getAttribute("value"));
        Assert.assertEquals(email_from_XL,driver.findElement(pageobj.email_Filed()).getAttribute("value"));

        logger.pass("Name field has expected value as : "+ driver.findElement(pageobj.nameFiled()).getAttribute("value"));
        logger.pass("Mobile field has expected value as : "+ driver.findElement(pageobj.mobileFiled()).getAttribute("value"));
        logger.pass("Email field has expected value as : "+ driver.findElement(pageobj.email_Filed()).getAttribute("value"));



        // Validate Social media options at starhealth Footer sections ...


        String SocialmediaoptionsFromXL = read_data_from_XL("Social_Media_Options");
        if(SocialmediaoptionsFromXL.contains(","))
        {
            try {
                String[] countOfOptionBySplit = SocialmediaoptionsFromXL.split(",");
                for (int i = 0; i < countOfOptionBySplit.length; i++) {
                    Assert.assertTrue(driver.findElement(pageobj.footerSocialMediaOptions(countOfOptionBySplit[i])).isDisplayed());
                    logger.pass("The Social Medial Option =>" + countOfOptionBySplit[i] + " " + "is available");
                }
            }
            catch(Exception e){logger.pass("The Social Medial Option is not available");}
        }



        // Scroll Down to footer section to click on Twitter


        WebElement element = driver.findElement(pageobj.Twitter_link());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        try{Thread.sleep(10000);}catch(Exception e){}

        // Switching to Parent Window ...

        // It will return the parent window name as a String

        parent=driver.getWindowHandle();

        Set<String>s=driver.getWindowHandles();

        // Now iterate using Iterator
        Iterator<String> I1= s.iterator();

        while(I1.hasNext()) {

            String child_window = I1.next();

            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                Assert.assertTrue(driver.getCurrentUrl().toString().contains(url_text));
                logger.pass("starhealthins text is present in Twitter URL");
            }
        }

        // Closing Child Tab

        driver.close();
    }


    // Using testNG  @AfterClass  --> Closing The Browser ...
    @AfterClass
    public synchronized void tearDown(){


        driver.quit();
        logger.pass("User Successfully closed the driver session");
        extent.flush();


    }

}
