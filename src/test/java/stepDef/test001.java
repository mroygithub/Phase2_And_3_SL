package stepDef;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.*;
import pom.googlehome_page;
import action.Action;

public class test001 {

    public WebDriver driver = null;

    public googlehome_page home;
    public Action action;

/*

    @Before
    public void launchApp(){

        home = new googlehome_page();
        action = new Action();

        System.setProperty("webdriver.chrome.driver","/Users/mithunroy/Downloads/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");

    }*/






    @Then("User validate google logo")
    public void user_validate_google_logo() {

        System.out.println("#########  MITHUN");
        //action.pageElementIsDisplayed(driver , home.google_logo());
    }




    @Then("User close google application")
    public void user_close_google_application() {

        System.out.println("#########  MITHUN@@@@@@@@@@@@@@@@");

    }

    @Given("User launch Google application with {string}")
    public void user_launch_google_application_with(String url) {

        System.out.println(url);
        System.setProperty("webdriver.chrome.driver","/Users/mithunroy/Downloads/BrowserDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.get(url);
        driver.quit();

    }

    @Then("User validate google logo and its name is {string}")
    public void user_validate_google_logo_and_its_name_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("one plus one is two")
    public void one_plus_one_is_two() {

        System.out.println("one plus one is 2");
    }


    @Given("Two plus Two is Four")
    public void two_plus_two_is_four() {
        System.out.println("Two plus two is 4");
    }

/*
    @After
    public void closeApp(){

        driver.quit();
    }

    */








}
