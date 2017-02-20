package com.imaginea.pageObjects;

import com.imaginea.Utils.UIUtilities;
import com.imaginea.pageObjects.PageLocators.HomePageLocators;
import com.imaginea.setUp.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class HomePage extends BaseClass {

    public HomePage() {

    }

    public void waitForHomePageToLoad(){
        util.waitForPageToLoad();
        util.waitUsingExplicitWait(By.xpath(".//*[@id='container']//div[@class='aqWyJi']"),30);
        System.out.println("HomePage loaded successfully");
    }

    public HomePage(UIUtilities util) {
        this.util = util;
    }

    public MobileSearchPage searchForMobile() throws InterruptedException {
        waitForHomePageToLoad();
        System.out.println("========================");
        System.out.println("Enter Text In Search BAR");
        System.out.println("========================");
//        Thread.sleep(30000);
        util.enterTextToElement(HomePageLocators.SEARCH_BAR, "mobile");
//        Thread.sleep(30000);
//        util.clickOnElement(HomePageLocators.MOBILE);
        util.clickOnElement(HomePageLocators.SEARCH_BUTTON);
        System.out.println("========================");
        System.out.println("Clibk on Search Button");
        System.out.println("========================");
        return new MobileSearchPage(util);
    }

}
