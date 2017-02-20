package com.imaginea.pageObjects;

import com.imaginea.pageObjects.PageLocators.MobileSearchPageLocator;
import com.imaginea.setUp.BaseClass;
import org.openqa.selenium.By;

/**
 * Created by adityag on 2/19/17.
 */
public class ProductDescriptionPage extends BaseClass{

    public void waitForProductDescriptionPageToLoad(){
        util.waitUsingExplicitWait(MobileSearchPageLocator.ADD_TOCart_BUTTON,10);
    }

    public void addToCart(){
        waitForProductDescriptionPageToLoad();
        util.clickOnElement(MobileSearchPageLocator.ADD_TOCart_BUTTON);
        System.out.println("=======================================");
        System.out.println("Product Added to cart");
        System.out.println("=======================================");
        if(!util.isElementPresent(By.xpath(".//*[@id='container']//div[@class='JAUzCh' and contains(text(),'Product added to cart successfully')]"),5)){
            System.out.println("\"Unale to add item to cart\" error message is displayed. Re-attempting to add item to cart...");
            util.clickOnElement(MobileSearchPageLocator.ADD_TOCart_BUTTON);
        }



    }

}
