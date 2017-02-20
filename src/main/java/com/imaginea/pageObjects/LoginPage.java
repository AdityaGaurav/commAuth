package com.imaginea.pageObjects;

import com.imaginea.Utils.UIUtilities;
import com.imaginea.pageObjects.PageLocators.HomePageLocators;
import com.imaginea.pageObjects.PageLocators.LoginPageLocators;
import com.imaginea.setUp.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


/**
 * Created by adityag on 2/17/17.
 */
public class LoginPage extends BaseClass {

    public LoginPage(){
    }

//    public LoginPage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//    }

//    UIUtilities util = new UIUtilities();
    public boolean isLoginPageLoadedSuccessfully(){
     try{
            System.out.println("WAITING FOR 10 sec for the eement in lginpage");
            util.waitUsingExplicitWait(By.xpath(".//*[@id='container']//div[@class='aqWyJi']"),30);
            System.out.println("element loaded...returning true");
        return true;
     }
        catch(TimeoutException e){
         return false;
        }

    }

    public HomePage doLogin(){
//check for title

        util.clickOnElement(LoginPageLocators.LOGIN_LINK);
        util.enterTextToElement(LoginPageLocators.MOBILE_NUMBER, "");
        util.enterTextToElement(LoginPageLocators.PASSWORD, "");
        util.clickOnElement(LoginPageLocators.LOGIN_BUTTON);
        util.waitForPageToLoad();
        if(isLoginPageLoadedSuccessfully()){
            System.out.println("===========================");
            System.out.println("Successfully Login");
            System.out.println("===========================");
        }
        else{
            System.out.println("===========================");
            System.out.println("Login Unsuccessusful");
            System.out.println("===========================");
        }

        String logoName = util.getAttributeValue(HomePageLocators.FLIPKART_LOGO);
        System.out.println(logoName);
        Assert.assertEquals("Flipkart",logoName, "Not Matched");

        return new HomePage(util);
    }


}
