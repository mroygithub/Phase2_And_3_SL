package pageObject;

import org.openqa.selenium.By;

public class starHealCuCumber {


    public By close_WelcomeToStarHealthPopup(){ return By.xpath("//b[text()='Close']");}
    public By Buy_Now_button(){ return By.xpath("(//span[text()='Buy Now'])[1]");}
    public By nameText(){ return By.xpath("//input[@id='name']");}
    public By phone_text(){ return By.xpath("//input[@id='contact_no']");}
    public By Pin_text(){ return By.xpath("//input[@id='pinCode']");}
    public By insuranceOption_dropDown(){ return By.xpath("//div[@id='grouped-select']");}
    public By insuranceOption_dropDown_option(String option){ return By.xpath("//li[contains(.,'"+option+"')]");}
    public By planForMyFamilyLabel(){ return By.xpath("//li[contains(.,'My Family')]");}
    public By logo(){ return By.xpath("//img[@alt='Star Health']");}
}
//li[contains(.,'My Family')]