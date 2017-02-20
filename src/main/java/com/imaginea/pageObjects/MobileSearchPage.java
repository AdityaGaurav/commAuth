package com.imaginea.pageObjects;

import com.imaginea.Utils.UIUtilities;
import com.imaginea.pageObjects.PageLocators.DeliveryPageLocators;
import com.imaginea.pageObjects.PageLocators.HomePageLocators;
import com.imaginea.pageObjects.PageLocators.MobileSearchPageLocator;
import com.imaginea.setUp.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by adityag on 2/19/17.
 */
public class MobileSearchPage extends BaseClass {

    public MobileSearchPage() {

    }

    public MobileSearchPage(UIUtilities util) {
        this.util = util;
    }

    public void waitForSearchResults(){
        util.waitUsingExplicitWait(MobileSearchPageLocator.FIRST_ELEMENT_OF_SEARCH,30);
    }

    public void refineMobileSearch() {
        waitForSearchResults();
//        util.scrollDownTillElement(MobileSearchPageLocator.SAMSUNG_LINK);
        util.clickOnElement(MobileSearchPageLocator.SAMSUNG_LINK);
        waitForSearchResults();
//        util.waitUsingImplicitWait();
//        util.clickOnElement(MobileSearchPageLocator.SAMSUNG_LINK);
//        util.waitUsingImplicitWait();
//        util.scrollDownTillElement(MobileSearchPageLocator.AVAILABILITY_LINK);
        util.clickOnElement(MobileSearchPageLocator.AVAILABILITY_LINK);
        waitForSearchResults();
//        util.waitUsingImplicitWait();
//        util.clickOnElement(MobileSearchPageLocator.AVAILABILITY_LINK);
//        util.waitUsingImplicitWait();
        util.clickOnElement(MobileSearchPageLocator.EXCLUDE_OUT_OF_STOCK_LINK);
        waitForSearchResults();
//        util.waitUsingImplicitWait();

    }


    public void addProductTOCart() {
        String currentURL = util.getCurrentPageURL();
        util.clickOnElement(MobileSearchPageLocator.MOBILE_LINK_FIRST);
        ProductDescriptionPage productDescriptionPage = new ProductDescriptionPage();
        productDescriptionPage.addToCart();

//        util.clickOnElement(MobileSearchPageLocator.ADD_TOCart_BUTTON);
//        util.waitUsingImplicitWait();
        util.navigateToURL(currentURL);

        waitForSearchResults();
        util.clickOnElement(MobileSearchPageLocator.MOBILE_LINK_SECOND);
        productDescriptionPage.addToCart();

        util.clickOnElement(HomePageLocators.CART_LOGO);

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.clickOnPlaceOrder();




        util.waitUsingExplicitWait(DeliveryPageLocators.DELIVERY_HERE_BUTTON,5);
        util.clickOnElement(DeliveryPageLocators.DELIVERY_HERE_BUTTON);
        util.waitUsingExplicitWait(DeliveryPageLocators.CONTINUE_BUTTON,10);
        util.clickOnElement(DeliveryPageLocators.CONTINUE_BUTTON);
        try {
            Thread.sleep(60000);
        }catch(Exception e){

        }
        util.clickOnElement(HomePageLocators.FLIPKART_LOGO_PAYMENT_PAGE);
        System.out.println("=======================================");
        System.out.println("Product successfully added to the cart");
        System.out.println("=======================================");

    }
}
