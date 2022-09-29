package stepDef;

import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import  java.time.Duration;
import org.openqa.selenium.support.ui.*;
import pageObject.starHealCuCumber;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.*;
import java.util.*;


public class StarHealth_Cucumber {

    private WebDriver driver;
    private starHealCuCumber pageobj;

    //helps to generate the logs in the test report.

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;
    private String phone_no ,parent;



    // Using Cucumber Hooks --> Launching The Browser ...
    @Before
    public void setUp(){


        // initialize the HtmlReporter

        // Create an object of Extent Reports
        extent = new ExtentReports();

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report_Cucumber_Home.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "StarHealth_Cucumber Application");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("User Name", "Test Team");
        spark.config().setDocumentTitle("StarHealth_Cucumber Application QA ");
        // Name of the report
        spark.config().setReportName("StarHealth_Cucumber Application Using Selenium Cucumber ");
        // Dark Theme
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Validate StarHealth_Cucumber Application Using Selenium Cucumber");


        // Creating an object for Page Object Model

        pageobj = new starHealCuCumber();


        // System Property for Chrome Driver

        try {

            System.setProperty("webdriver.chrome.driver", "/Users/mithunroy/Downloads/BrowserDrivers/chromedriver");

            // Instantiate a ChromeDriver class.

            driver = new ChromeDriver();


            //Maximize the browser
            driver.manage().window().maximize();

            logger.createNode("User can Successfully Instantiate the Chrome Browser");
        }
        catch(Exception e){logger.fail("Instantiate of the Chrome Browser Failed");}


    }


    @Given("User launch StarHealth application with {string}")
    public void user_launch_star_health_application_with(String testURL) {

        try {

            // Launch Website
            driver.navigate().to(testURL);
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            logger.createNode("starHeal application launched successfully");
        }
        catch(Exception e){logger.fail("starHeal application launched loading FAIL");}

    }

    @When("User wait for Welcome to Star Health pop up")
    public void user_wait_for_welcome_to_star_health_pop_up() {
        try {

            WebElement close_pop_up_link = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(pageobj.close_WelcomeToStarHealthPopup()));
            logger.pass("Welcome To StarHealth pop up Close link is clickable");
        }
        catch(Exception e){logger.fail("Welcome To StarHealth pop up Close link is not clickable");}
    }

    @When("User close the pop up")
    public void user_close_the_pop_up() {

        driver.findElement(pageobj.close_WelcomeToStarHealthPopup()).click();
        logger.pass("Welcome To StarHealth pop up Close link is clicked successfully");

    }

    @Then("User validate Star Health home page title using Junit assertion")
    public void user_validate_star_health_home_page_title_using_junit_assertion() {


        Assert.assertEquals(driver.getTitle() , "Star Health Insurance: Medical, Accident and Travel insurance policies");
        logger.pass("Page Title is as expected as ==> "+ driver.getTitle());


    }

    @Then("User clicks on Buy Now Button")
    public void user_clicks_on_buy_now_button() {


        driver.findElement(pageobj.Buy_Now_button()).click();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        logger.pass("User clicked on Buy_Now_button");

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
            }
        }

    }

    @Then("User Type Name as {string}")
    public void user_type_name_as(String name) {

        WebElement nameFiled = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageobj.nameText()));
        driver.findElement(pageobj.nameText()).sendKeys(name);
        logger.pass("Name details is filled with Value:"+name);

    }

    @Then("User Type Phone as {string}")
    public void user_type_phone_as(String ph_no) {

        phone_no = ph_no;

        driver.findElement(pageobj.phone_text()).sendKeys(ph_no);
        logger.pass("ph_no details is filled with Value:"+ph_no);

    }

    @Then("User Type PIN as {string}")
    public void user_type_pin_as(String pin) {

        driver.findElement(pageobj.Pin_text()).sendKeys(pin);
        logger.pass("pin details is filled with Value:"+pin);

    }

    @When("User click on I need health insurance for drop down")
    public void user_click_on_i_need_health_insurance_for_drop_down() {

        WebElement insuranceOption_dropDown = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageobj.insuranceOption_dropDown()));
        driver.findElement(pageobj.insuranceOption_dropDown()).click();

    }

    @Then("User select option as {string}")
    public void user_select_option_as(String option) {

        WebElement close_pop_up_link = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageobj.insuranceOption_dropDown_option(option)));
        driver.findElement(pageobj.insuranceOption_dropDown_option(option)).click();
        try{Thread.sleep(10000);}catch(Exception e){}

    }

    @Then("User see Plan for My Family page")
    public void user_see_plan_for_my_family_page() {

       try{
        driver.findElement(pageobj.planForMyFamilyLabel()).isDisplayed();
        logger.pass("User Successfully navigated to plan For My Family details page");}
       catch(Exception e){logger.fail("User failed navigated to plan For My Family details page");}

    }

    @Then("User validate mobile number same as previous page given number using Junit assertion")
    public void user_validate_mobile_number_same_as_previous_page_given_number_using_junit_assertion() {

        WebElement close_pop_up_link = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageobj.phone_text()));
        String planForMyFamily_phoneNo = driver.findElement(pageobj.phone_text()).getAttribute("value");
        Assert.assertEquals(planForMyFamily_phoneNo  , phone_no);
        logger.pass("Phone number is as expected as ==>"+planForMyFamily_phoneNo);

    }

    @When("User clicks on Star Health logo")
    public void user_clicks_on_star_health_logo() {

        driver.findElement(pageobj.logo()).click();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        logger.pass("User clicked on logo");

    }

    @Then("Application should redirect to home page")
    public void application_should_redirect_to_home_page() {

        Assert.assertEquals(driver.getTitle() , "Star Health Insurance: Medical, Accident and Travel insurance policies");

    }

    @Then("User close the child TAB")
    public void user_close_the_child_tab() {

        driver.close();
        logger.pass("User Successfully closed the Child Window");

    }

    @Then("User navigate back to Parent TAB")
    public void user_navigate_back_to_parent_tab() {

        //switch to the parent window
        driver.switchTo().window(parent);
        logger.pass("User Successfully back to the parent Window");

    }



    // Using Cucumber Hooks --> Closing The Browser ...
    @After
    public void tearDown(){


        driver.quit();
        logger.pass("User Successfully closed the driver session");
        extent.flush();


    }

}
