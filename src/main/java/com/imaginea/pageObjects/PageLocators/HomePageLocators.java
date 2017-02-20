package com.imaginea.pageObjects.PageLocators;

import org.openqa.selenium.By;

/**
 * Created by adityag on 2/17/17.
 */
public class HomePageLocators {

    public final static By SEARCH_BAR = By.xpath(".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[2]/form/div/div[1]/div/input");
//    public final static By SEARCH_BUTTON = By.xpath(".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[2]/form/div/div[2]/button");

    public final static By SEARCH_BUTTON = By.xpath(".//*[@id='container']//button[@class='vh79eN']");
    public static final By MOBILE = By.xpath(".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[2]/form/ul/li[1]/div/a");
    public static By FLIPKART_LOGO = By.xpath(".//*[@id='container']/div/header/div[1]/div[2]/div/div/div[1]/a/img");
    public static final By FLIPKART_LOGO_PAYMENT_PAGE = By.xpath("html/body/div[1]/div/div/div[1]/a/img");
    public static final By CART_LOGO = By.xpath(".//*[@id='container']//a[@class='_3NFO0d']");

}
