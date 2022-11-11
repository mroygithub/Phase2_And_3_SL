package gridImplementation;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestChrome extends gridBaseCode{

    public WebDriver driver = null;

    @Test
    public void testOne() {

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle()+" by TestOne");

    }

    @BeforeMethod
    public void setup() throws MalformedURLException {

        driver = initializeBrowser("chrome");

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }
}
