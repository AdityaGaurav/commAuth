package com.imaginea.pageObjects.PageLocators;

import org.openqa.selenium.By;

import javax.print.DocFlavor;

/**
 * Created by adityag on 2/19/17.
 */
public class MobileSearchPageLocator {

    public static final By SAMSUNG_LINK = By.xpath(".//*[@id='container']//section[@class='_2XJuDa _2Zm4Qh']/div[@class='_1cEG7-']//div[@title='SAMSUNG']");
    public static final By AVAILABILITY_LINK = By.xpath(".//*[@id='container']//section[@class='_2XJuDa _2Zm4Qh']//div[contains(text(),'Availability')]");
    public static final By EXCLUDE_OUT_OF_STOCK_LINK = By.xpath(".//*[@id='container']//section[@class='_2XJuDa _2Zm4Qh']/div[@class='_1cEG7-']//div[@title='Exclude Out of Stock']");

    public static final By FIRST_ELEMENT_OF_SEARCH = By.xpath(".//*[@id='container']//div[@class='col zZCdz4'][1]/a");
    public static final By MOBILE_LINK_FIRST = By.xpath(".//*[@id='container']//div[@class='col zZCdz4'][1]/a/div[2]/div[1]/div[1]");
    public static final By MOBILE_LINK_SECOND = By.xpath(".//*[@id='container']//div[@class='col zZCdz4'][2]/a/div[2]/div[1]/div[1]");

    public static final By BUY_NOW = By.xpath(".//*[@id='container']/div/div[2]/div/div/div[1]/div/div[1]/div/div/div[2]/ul/li[2]/form/button");
    public static final By ADD_TOCart_BUTTON = By.xpath(".//*[@id='container']//button[@class='_2AkmmA _3Plo8Q _19RW-r']");
    public static final By CART_BUTTON = By.xpath(".//*[@id='container']/div/header/div[1]/div[2]/div/div/a");
    public static final By DELIVER_HERE = By.xpath(".//*[@id='ng-app']/div/div[2]/ul/li[2]/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/div[1]/div[2]/a/p[4]");
    public static final By CONTINUE_BUTTON = By.xpath(".//*[@id='order_summary_panel']/div[2]/div[3]/form/div[2]/div/div[1]/a");
}
