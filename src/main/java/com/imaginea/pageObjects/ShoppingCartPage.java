package com.imaginea.pageObjects;

import com.imaginea.pageObjects.PageLocators.ShoppingCartPageLocators;
import com.imaginea.setUp.BaseClass;

/**
 * Created by adityag on 2/19/17.
 */
public class ShoppingCartPage extends BaseClass{

    public void waitForShoppingCartPageToLoad(){
        util.waitUsingExplicitWait(ShoppingCartPageLocators.PLACE_ORDER,10);
    }

    public void clickOnPlaceOrder(){
        waitForShoppingCartPageToLoad();
        util.clickOnElement(ShoppingCartPageLocators.PLACE_ORDER);
        System.out.println("clicked on placed order....");
    }
}
