package pageObject;

import org.openqa.selenium.By;

public class starHealtestNG {


    public By homepageLogo(){ return By.xpath("//img[@class='jss61']");}
    public By plan_link(){ return By.xpath("//div[@class='homeHeaderMenuGA']");}
    public By forMyFamilyLink(){ return By.xpath("(//div[@class='allPlansGA'])[1]");}
    public By nameFiled(){ return By.xpath("//input[@id='name']");}
    public By mobileFiled(){ return By.xpath("//input[@id='mobile']");}
    public By email_Filed(){ return By.xpath("//input[@id='email']");}
    public By authorized_Checkbox(){ return By.xpath("//input[@id='dndConsent']");}
    public By next_button(){ return By.xpath("//span[text()='Next']");}
    public By back_button(){ return By.xpath("//span[text()='Back']");}
    public By Twitter_link(){ return By.xpath("//a[contains(@href,'twitter')]");}
    public By footerSocialMediaOptions(String option){ return By.xpath("//a[contains(@href,'"+option+"')]");}







}
