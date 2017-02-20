package com.imaginea.tests;

import org.testng.annotations.Test;

/**
 * Created by adityag on 2/19/17.
 */
public class AddToCartTest extends BasePageTest {

    @Test
    public void addProductToCart() throws InterruptedException{
        loginPage.doLogin();
        homePage.searchForMobile();
        mobileSerachPage.refineMobileSearch();
        mobileSerachPage.addProductTOCart();
    }

}
